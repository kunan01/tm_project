package com.tangmo.yiliao.service.impl;


import com.tangmo.yiliao.dao.CommentsDao;
import com.tangmo.yiliao.dao.MessageDao;
import com.tangmo.yiliao.entity.Comments;
import com.tangmo.yiliao.entity.Message;
import com.tangmo.yiliao.entity.SystemTheArticle;
import com.tangmo.yiliao.service.CommentsService;
import com.tangmo.yiliao.utility.SensitiveToFilter.SensitivewordFilter;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.file.ImgUtil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

    @Resource
    private CommentsDao commentsDao;

    @Resource
    private MessageDao messageDao;


    @Override
    @Transactional
    public Result addComments(Comments comments) {
        if(comments == null){
            return ResultUtil.paramError();
        }

        //获取视频或文章标题
        String title = "";

        //获取评论者或回复者姓名
        String userName = messageDao.getUserName(comments.getUserId());

        comments.setcId(EncryptUtil.get32Uuid());

        if(comments.getcAttribute().toString().equals("0")){
            SensitivewordFilter filter = new SensitivewordFilter();
            comments.setcContent(filter.replaceSensitiveWord(comments.getcContent(),2,"*"));
        }

        if(comments.getcStatus().toString().equals("1")){  //回复
            Comments comm = commentsDao.getCommentsById(comments.getReplyId());
            comments.setcType(comm.getcType());
            comments.setArticlesOrVideoId(comm.getArticlesOrVideoId());
            if(commentsDao.getRoleByUserId(comments.getUserId()).equals("DOCTOR")){
                if(comm.getcType().toString().equals("0")){
                    //文章标题
                    title = commentsDao.getActitleById(comm.getArticlesOrVideoId());
                }else{
                    //视频标题
                    title = commentsDao.getVideotitleById(comm.getArticlesOrVideoId());
                }

                //需要通知的用户id
                String ttUserId = commentsDao.getUserIdBycId(comments.getReplyId());

                //修改读取状态
                messageDao.updMessageState(Byte.parseByte("1"),0,ttUserId);

                //添加消息
                Message addmessage1 = new Message();
                addmessage1.setMiId(EncryptUtil.get32Uuid());
                addmessage1.setMiCategory(Byte.parseByte("1"));
                addmessage1.setTitle(title);
                addmessage1.setMiContent(userName + "医生回复了你在《" +title + "》的提问");
                addmessage1.setUserId(ttUserId);
                addmessage1.setCommId(comments.getReplyId());
                messageDao.addMessage(addmessage1);
            }
        }else{
            //提问
            if (comments.getcType().toString().equals("0")) {
                //文章标题
                title = commentsDao.getActitleById(comments.getArticlesOrVideoId());
            } else {
                //视频标题
                title = commentsDao.getVideotitleById(comments.getArticlesOrVideoId());
            }

            //需要通知的医生集合
            List<String> users = null;

            if (comments.getcType().toString().equals("0")) {
                users = commentsDao.getDoctorByActitleId(comments.getArticlesOrVideoId());
            } else {
                users = commentsDao.getDoctorByVideoId(comments.getArticlesOrVideoId());
            }

            if (users != null) {
                for (int i = 0; i < users.size(); i++) {
                    //修改读取状态
                    messageDao.updMessageState(Byte.parseByte("3"), 0, users.get(i));

                    //添加消息
                    Message addmessage1 = new Message();
                    addmessage1.setMiId(EncryptUtil.get32Uuid());
                    addmessage1.setMiCategory(Byte.parseByte("3"));
                    addmessage1.setTitle(title);
                    addmessage1.setMiContent(userName + "在《" + title + "》中发表了提问");
                    addmessage1.setUserId(users.get(i));
                    addmessage1.setCommId(comments.getcId());
                    messageDao.addMessage(addmessage1);
                }
            }
        }
        //添加消息
        if(comments.getReplyId() == null || comments.getReplyId().equals("")){
            comments.setReplyId(null);
        }
        commentsDao.addComments(comments);
        return ResultUtil.success();
    }

    @Override
    public Result getCommentsDById(String cId) {
        if(cId == null || cId.equals("")){
            return ResultUtil.paramError();
        }
        Comments comments = commentsDao.getCommentsById(cId);
        if(comments.getcAttribute().toString().equals("1")){
            try {
                comments.setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+comments.getcContent()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        comments.setReplyCount(commentsDao.getCommentsCountByCId(cId));
        List<Comments> commentsList = commentsDao.getCommentsAllAByCId(cId);
        if(commentsList != null){
            for (int i = 0;i<commentsList.size();i++){
                if(commentsList.get(i).getcAttribute().toString().equals("1")){
                    try {
                        commentsList.get(i).setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+commentsList.get(i).getcContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        comments.setCommentsList(commentsList);
        return ResultUtil.success(comments);
    }

    @Override
    public Result getCommentsALLWById(String id, Integer type) {
        List<Comments> commentsList = commentsDao.getCommentsAllQ(id,type);
        if(commentsList != null){
            for (int i = 0; i < commentsList.size(); i++){
                if(commentsList.get(i).getcAttribute().toString().equals("1")){
                    try {
                        commentsList.get(i).setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+commentsList.get(i).getcContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                commentsList.get(i).setReplyCount(commentsDao.getCommentsCountByCId(commentsList.get(i).getcId()));
                List<Comments> comments = commentsDao.getCommentsTop3AByCId(commentsList.get(i).getcId());
                if(comments != null){
                    for (int j = 0;j<comments.size();j++){
                        if(comments.get(j).getcAttribute().toString().equals("1")){
                            try {
                                comments.get(j).setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+comments.get(j).getcContent()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                commentsList.get(i).setCommentsList(comments);
            }
        }
        return ResultUtil.success(commentsList);
    }
}
