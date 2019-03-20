package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.ManyTimesDao;
import com.tangmo.yiliao.dao.MessageDao;
import com.tangmo.yiliao.dao.UserDao;
import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.service.MessageService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.file.ImgUtil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageDao messageDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ManyTimesDao manyTimesDao;

    @Override
    public Result getMessageTitle(String roleId,String userId) {
        Map<String,Object> map = new HashMap<>();
        map.put("system",messageDao.getMessageTitle(0,userId));
        if(roleId.equals("MEMBER")){
            map.put("reply",messageDao.getMessageTitle(1,userId));
        }else{
            map.put("exceptional",messageDao.getMessageTitle(2,userId));
            map.put("remind",messageDao.getMessageTitle(3,userId));
        }
        map.put("advisory",messageDao.getMessageTitle(4,userId));
        return ResultUtil.success(map);
    }

    @Override
    public Result getMessageState(String roleId,String userId) {
        Map<String,Object> map = new HashMap<>();
        Read read = messageDao.getMessageState(userId);
        map.put("systemMessage",read.getSystemMessageState());
        if(roleId.equals("MEMBER")){
            map.put("replyMessage",read.getReplyMessageState());
        }else{
            map.put("exceptionalMessage",read.getExceptionalState());
            map.put("remindMessage",read.getRemindMessageState());
        }
        map.put("advisoryMessages",read.getAdvisoryMessagesState());
        return ResultUtil.success(map);
    }

    @Override
    @Transactional
    public Result getMessageAllByType(Byte type, String userId,Integer start,Integer end) {
        //修改读取状态
        messageDao.updMessageState(type,1,userId);
        //返回值
        return ResultUtil.success(messageDao.getMessageAll(type,userId,(start-1)*end,end));
    }

    @Override
    @Transactional
    public Result IntegralExceptional(Message message) {
        Integer Count = userDao.getUserIntegral(message.getUserId());
        if(Count < message.getCount()){
            return ResultUtil.error("您的积分余额不足");
        }
        IntegralSubsidiary integralSubsidiary = new IntegralSubsidiary();
        Message addmessage1 = new Message();
        String userName = messageDao.getUserName(message.getUserId());

        addmessage1.setMiId(EncryptUtil.get32Uuid());
        addmessage1.setMiCategory(Byte.parseByte("2"));
        if(message.getMiCategory().toString().equals("0")){//打赏医生
            //增加明细记录
            integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
            integralSubsidiary.setUserId(message.getUserId());
            integralSubsidiary.setLtId("EXCEPTIONAL_PROBLEM");
            integralSubsidiary.setSyBean(message.getCount());
            integralSubsidiary.setCreateUserId(message.getUserId());
            manyTimesDao.addIntegralSubsidiary(integralSubsidiary);
            integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
            integralSubsidiary.setUserId(message.getMiId());
            integralSubsidiary.setLtId("GET_POINTS_FOR_QUESTIONS");
            integralSubsidiary.setSyBean(message.getCount());
            integralSubsidiary.setCreateUserId(message.getMiId());
            manyTimesDao.addIntegralSubsidiary(integralSubsidiary);

            //增加医生积分余额
            messageDao.updDoctorCount(message.getCount(),message.getMiId());
            userDao.updUserIntegralById(message.getMiId(),message.getCount());

            //修改读取状态
            messageDao.updMessageState(Byte.parseByte("2"),0,message.getMiId());

            //添加消息
            addmessage1.setTitle("恭喜您，您的积分增加"+message.getCount()+"分");
            addmessage1.setMiContent(userName + "打赏了你在《" + message.getTitle() + "》的问答");
            addmessage1.setUserId(message.getMiId());

        }else if(message.getMiCategory().toString().equals("1")){//打赏视频

            //获取医生id
            String doctorId = messageDao.getDoctorId(message.getMiId());

            //增加明细记录
            integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
            integralSubsidiary.setUserId(message.getUserId());
            integralSubsidiary.setLtId("PLAY_TO_WATCH_VIDEO");
            integralSubsidiary.setSyBean(message.getCount());
            integralSubsidiary.setCreateUserId(message.getUserId());
            manyTimesDao.addIntegralSubsidiary(integralSubsidiary);
            integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
            integralSubsidiary.setUserId(doctorId);
            integralSubsidiary.setLtId("VIDEO_IS_THE_INTEGRAL");
            integralSubsidiary.setSyBean(message.getCount());
            integralSubsidiary.setCreateUserId(doctorId);
            manyTimesDao.addIntegralSubsidiary(integralSubsidiary);

            //增加视频积分余额
            messageDao.updDoctorVideoCount(message.getCount(),message.getMiId());

            //增加医生积分余额
            userDao.updUserIntegralById(doctorId,message.getCount());

            //修改读取状态
            messageDao.updMessageState(Byte.parseByte("2"),0,doctorId);

            //添加消息
            addmessage1.setTitle("恭喜您，您的视频积分增加"+message.getCount()+"分");
            addmessage1.setMiContent(userName + "打赏了你的视频: " + message.getTitle());
            addmessage1.setUserId(doctorId);
        }else{//打赏问答
        }

        //减少用户余额
        messageDao.updUserCountJ(message.getCount(),message.getUserId());
        //添加消息
        messageDao.addMessage(addmessage1);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result IntegralExceptionalDoctor(Message message) {

        Integer Count = userDao.getUserIntegral(message.getUserId());
        if(Count < message.getCount()){
            return ResultUtil.error("您的积分余额不足");
        }
        IntegralSubsidiary integralSubsidiary = new IntegralSubsidiary();
        Message addmessage1 = new Message();
        String userName = messageDao.getUserName(message.getUserId());

        addmessage1.setMiId(EncryptUtil.get32Uuid());

        //增加明细记录
        integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
        integralSubsidiary.setUserId(message.getUserId());
        integralSubsidiary.setLtId("WATCH_THE_DOCTOR");
        integralSubsidiary.setSyBean(message.getCount());
        integralSubsidiary.setCreateUserId(message.getUserId());
        manyTimesDao.addIntegralSubsidiary(integralSubsidiary);
        integralSubsidiary.setSyId(EncryptUtil.get32Uuid());
        integralSubsidiary.setUserId(message.getMiId());
        integralSubsidiary.setLtId("DOCTORS_GET_POINTS");
        integralSubsidiary.setSyBean(message.getCount());
        integralSubsidiary.setCreateUserId(message.getMiId());
        manyTimesDao.addIntegralSubsidiary(integralSubsidiary);

        //增加医生积分余额
        messageDao.updDoctorCount(message.getCount(),message.getMiId());
        userDao.updUserIntegralById(message.getMiId(),message.getCount());
        //修改读取状态
        messageDao.updMessageState(Byte.parseByte("2"),0,message.getMiId());

        //添加消息
        addmessage1.setTitle("恭喜您，您的积分增加"+message.getCount()+"分");
        addmessage1.setMiCategory(Byte.parseByte("2"));
        addmessage1.setMiContent(userName + "在您发布的视频中打赏了你");
        addmessage1.setUserId(message.getMiId());

        //减少用户余额
        messageDao.updUserCountJ(message.getCount(),message.getUserId());
        //添加消息
        messageDao.addMessage(addmessage1);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result EnterTheDialogue(SelectUser selectUser) {
        if(selectUser.getType().toString().equals("0")){
            //用户进入
            //添加默认消息
            Dialogue dialogue = new Dialogue();
            dialogue.setDlId(EncryptUtil.get32Uuid());
            dialogue.setCreateUserId(selectUser.getPeopleId());
            dialogue.setOriginatorId(selectUser.getOriginatorId());
            dialogue.setPeopleId(selectUser.getPeopleId());
            dialogue.setdType(Byte.parseByte("0"));
            dialogue.setdContent("您好，请问有什么可以帮您!");
            messageDao.addDic(dialogue);
            messageDao.updMessageState(Byte.parseByte("4"),1,selectUser.getOriginatorId());
        }else{
            //医生进入
            messageDao.updMessageState(Byte.parseByte("4"),1,selectUser.getPeopleId());
        }
        return ResultUtil.success();
    }

    @Override
    public Result getUserDia(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart()-1)*selectUser.getEnd());
        List<Dialogue> dialogueList = messageDao.getDic(selectUser);
        System.out.println(dialogueList);
        for (int i = 0;i<dialogueList.size();i++){
            if(dialogueList.get(i).getdType().toString().equals("1")){
                try {
                    dialogueList.get(i).setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+dialogueList.get(i).getdContent()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ResultUtil.success(dialogueList);
    }

    @Override
    @Transactional
    public Result addUserDia(Dialogue dialogue) {
        if(dialogue == null){
            return ResultUtil.paramError();
        }
        dialogue.setDlId(EncryptUtil.get32Uuid());
        messageDao.addDic(dialogue);

        Message addmessage1 = new Message();
        addmessage1.setMiId(EncryptUtil.get32Uuid());
        addmessage1.setMiCategory(Byte.parseByte("4"));

        if(dialogue.getCreateUserId().equals(dialogue.getOriginatorId())){
            String userName = messageDao.getUserName(dialogue.getOriginatorId());
            //向医生推送
            messageDao.updMessageState(Byte.parseByte("4"),0,dialogue.getPeopleId());
            addmessage1.setTitle(userName+"发来消息");
            addmessage1.setUserId(dialogue.getPeopleId());

        }else{
            String name = messageDao.getName(dialogue.getPeopleId());
            //向用户推送
            messageDao.updMessageState(Byte.parseByte("4"),0,dialogue.getOriginatorId());
            addmessage1.setTitle(name + "医生发来消息");
            addmessage1.setUserId(dialogue.getOriginatorId());
        }
        addmessage1.setMiContent(".");
        messageDao.addMessage(addmessage1);
        return ResultUtil.success();
    }

    @Override
    public Result getConsultingAll(SelectUser selectUser) {
        //医生或者用户的id
        List<String> stringList = messageDao.getUserConsultingAll(selectUser);
        System.out.println(stringList);
        List<Dialogue> dialogueList = new ArrayList<>();
        if(stringList != null){
            if(stringList.size() > (selectUser.getStart() - 1) * selectUser.getEnd()){
                for (int i = (selectUser.getStart()-1) * selectUser.getEnd(); i < selectUser.getEnd();i++){
                    if(stringList.size()-1 < i){
                        break;
                    }
                    Dialogue dialogue = null;
                    if(selectUser.getType().toString().equals("0")){
                        //医生的
                        dialogue = messageDao.getUserConsAll(selectUser.getOriginatorId(),stringList.get(i));
                    }else{
                        //用户的
                        dialogue = messageDao.getUserByIdConsAll(stringList.get(i),selectUser.getOriginatorId());
                    }
                    if(dialogue != null){
                        if(dialogue.getdType().toString().equals("1")){
                            try {
                                dialogue.setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+dialogue.getdContent()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        dialogueList.add(dialogue);
                    }
                }
            }
        }
        return ResultUtil.success(dialogueList);
    }
}
