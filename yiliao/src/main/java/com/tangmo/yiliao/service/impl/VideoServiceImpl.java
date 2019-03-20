package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.CommentsDao;
import com.tangmo.yiliao.dao.DepartmentDao;
import com.tangmo.yiliao.dao.UserDao;
import com.tangmo.yiliao.dao.VideoDao;
import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.service.VideoService;
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
@Service("videoService")
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoDao videoDao;

    @Resource
    private DepartmentDao departmentDao;

    @Resource
    private CommentsDao commentsDao;

    @Resource
    private UserDao userDao;

    @Override
    public Result getShufflingVideo() {
        return ResultUtil.success(videoDao.getShufflingVideo());
    }

    @Override
    public Result homePageDisplay() {
        Map<Object, Object> map = new HashMap<>();
        List<Object> doctorVideos = new ArrayList<>();
        for (int i = 1;i <= 4;i++){
            List<DoctorVideo> doctorVideoList = videoDao.getKnowledgeVideo((i-1)*4,4);
            if(doctorVideoList != null){
                doctorVideos.add(doctorVideoList);
            }
        }
        //知识课堂
        map.put("knowledge",doctorVideos);
        //小视频
        map.put("shortVideo",videoDao.getShortVideo());
        return ResultUtil.success(map);
    }

    @Override
    public Result getDepartmentAllVideoTop4() {
        Map<Object, Object> tun = new HashMap<>();
        List<String> strs = new ArrayList<>();
        Map<Object, Object> res = new HashMap<>();
        List<Department> departmentList = departmentDao.getDepartmentAll();
        for (int i = 0; i < departmentList.size(); i++){
            strs.add(departmentList.get(i).getDtId()+","+departmentList.get(i).getDtName());
            res.put(departmentList.get(i).getDtId(),videoDao.getDepartmentVideoTop4ById(departmentList.get(i).getDtId()));
        }
        tun.put("key",strs);
        tun.put("values",res);
        return ResultUtil.success(tun);
    }

    @Override
    public Result getDepartmentByIdVideoAll(String dtId, Integer start, Integer end) {
        return ResultUtil.success(videoDao.getDepartmentByIdVideoAll(dtId,(start-1)*end,end));
    }

    @Override
    public Result getDepartmentBySearch(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart()-1)*selectUser.getEnd());
        return ResultUtil.success(videoDao.getDepartmentBySearch(selectUser));
    }

    @Override
    public Result getVideoList(Byte category, Integer start, Integer end) {
        return ResultUtil.success(videoDao.getVideoList(category,(start-1)*end,end));
    }

    @Override
    @Transactional
    public Result getVideoDDById(String dvId, String userId) {
        Map<Object, Object> map = new HashMap<>();

        //增加用户足迹以及游览次数
        videoDao.updVisitNumber(dvId);
        if(!userId.equals("666")){
            if(userDao.getUserFootprintBytype(userId,Byte.parseByte("0"),dvId) == 0){
                Footprint footprint = new Footprint();
                footprint.setFpId(EncryptUtil.get32Uuid());
                footprint.setUserId(userId);
                footprint.setFpType(Byte.parseByte("0"));
                footprint.setTypeId(dvId);
                userDao.addUserFootprint(footprint);
            }
        }

        DoctorVideo doctorVideo = videoDao.getVideoById(dvId);

        //视频详情
        map.put("video",doctorVideo);
        //视频评论
        List<Comments> commentsList = commentsDao.getCommentsTop3Q(dvId,1);
        if(commentsList != null){
            for (int i = 0;i < commentsList.size(); i++){
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
                                String[] strings = comments.get(j).getcContent().split("/");
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
        map.put("comments",commentsList);
        map.put("recommended",videoDao.getDepartmentVideoTop2T(userId));
        return ResultUtil.success(map);
    }

    @Override
    public Result getVideoComm(String dvId,Integer type,Integer start,Integer end) {
        List<Comments> commentsList = commentsDao.getCommentsByIdF(dvId,type,(start - 1) * end,end);
        if(commentsList != null){
            for (int i = 0;i < commentsList.size(); i++){
                if(commentsList.get(i).getcAttribute().toString().equals("1")){
                    try {
                        commentsList.get(i).setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+commentsList.get(i).getcContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ResultUtil.success(commentsList);
    }

    @Override
    public Result getVideoCommH(String cId, Integer type,Integer start, Integer end) {
        List<Comments> commentsList = commentsDao.getCommentsByIdFOnH(cId,type,(start - 1) * end,end);
        if(commentsList != null){
            for (int i = 0;i < commentsList.size(); i++){
                if(commentsList.get(i).getcAttribute().toString().equals("1")){
                    try {
                        commentsList.get(i).setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+commentsList.get(i).getcContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return ResultUtil.success(commentsList);
    }
}
