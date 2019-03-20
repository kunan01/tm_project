package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.*;
import com.tangmo.yiliao.entity.*;
import com.tangmo.yiliao.service.ArticleService;
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
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Resource
    private UserDao userDao;

    @Resource
    private CommentsDao commentsDao;

    @Resource
    private DoctorDao doctorDao;

    @Resource
    private VideoDao videoDao;

    @Override
    public Result getPartOfArticle(Integer start,Integer end) {
        List<SystemTheArticle> systemTheArticles = articleDao.getPartOfArticle((start-1)*end,end);
        for (int i = 0; i < systemTheArticles.size();i++){
            systemTheArticles.get(i).setSaContent(systemTheArticles.get(i).getSaContent().replaceAll("\n","").replaceAll("\\<style>.*?</style>","").replaceAll("\\<.*?>",""));
        }
        return ResultUtil.success(systemTheArticles);
    }

    @Override
    public Result getArticleById(String userId, Integer start, Integer end) {
        List<SystemTheArticle> systemTheArticles = articleDao.getArticleById(userId,(start-1)*end,end);
        for (int i = 0; i < systemTheArticles.size();i++){
            systemTheArticles.get(i).setSaContent(systemTheArticles.get(i).getSaContent().replaceAll("\n","").replaceAll("\\<style>.*?</style>","").replaceAll("\\<.*?>",""));
        }
        return ResultUtil.success(systemTheArticles);
    }

    @Override
    public Result homeSearch(SelectUser selectUser) {
        Map<String , Object> map = new HashMap<>();
//      List<DoctorDetails> doctorDetails = doctorDao.getDepartmentDoctorAllByName(selectUser.getCondName(),selectUser.getCondName().substring(0,1));
        //map.put("doctorList",doctorDetails);
        List<SystemTheArticle> systemTheArticles = articleDao.getPartOfArticleByUserId(selectUser.getCondName());
        List<DoctorVideo> doctorVideos = videoDao.selectShufflingVideoByName(selectUser.getCondName());
        map.put("articleList",systemTheArticles);
        map.put("videoList",doctorVideos);
        return ResultUtil.success(map);
    }

    @Override
    @Transactional
    public Result getArticleById(String aId,String userId) {
        if(aId == null || userId == null){
            return ResultUtil.paramError();
        }
        //增加用户足迹以及游览次数
        articleDao.updVisitNumber(aId);
        if(!userId.equals("666")){
            if(userDao.getUserFootprintBytype(userId,Byte.parseByte("1"),aId) == 0){
                Footprint footprint = new Footprint();
                footprint.setFpId(EncryptUtil.get32Uuid());
                footprint.setUserId(userId);
                footprint.setFpType(Byte.parseByte("1"));
                footprint.setTypeId(aId);
                userDao.addUserFootprint(footprint);
            }
        }
        //返回值
        Map<String , Object> map = new HashMap<>();
        SystemTheArticle systemTheArticle = articleDao.getDepartmentArticlById(aId);
        systemTheArticle.setSaContent(systemTheArticle.getSaContent().replaceAll("\n","").replaceAll("\\<style>.*?</style>",""));
        map.put("article",systemTheArticle);
        List<Comments> commentsList = commentsDao.getCommentsTop3Q(aId,0);
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
        map.put("recommended",articleDao.getDepartmentArticleTop2T(systemTheArticle.getDtId(),userId));
        return ResultUtil.success(map);
    }

    @Override
    @Transactional
    public void updaucodeByDay() {
        articleDao.updaucodeByDay();
    }

    @Override
    public void updaucodeByMonth() {
        articleDao.updaucodeByMonth();
    }

    @Override
    public Result getMesCode(SelectUser selectUser) {
        if(selectUser == null){
            return ResultUtil.paramError();
        }
        Map<String,Object> map = new HashMap<>();
        List<String> phones = new ArrayList<>();
        if(selectUser.getType() == 3){
            phones = articleDao.getMesCodeBan(selectUser.getCondName(),(selectUser.getStart()-1)*selectUser.getEnd() ,selectUser.getEnd());
        }else{
            phones = articleDao.getMesCode(selectUser.getCondName(),selectUser.getType(),(selectUser.getStart()-1)*selectUser.getEnd() ,selectUser.getEnd());
        }

        if(phones != null){
            map.put("count",phones.size());
            List<Consulting> consultings = new ArrayList<>();
            for (int i = 0; i<phones.size(); i++){
                Consulting consulting = new Consulting();
                consulting.setPhone(phones.get(i));
                consulting.setCodeCount(articleDao.getMesCodecount(phones.get(i),selectUser.getType()));
                consultings.add(consulting);
            }
            map.put("codelist",consultings);
        }
        return ResultUtil.success(map);
    }

    @Override
    @Transactional
    public Result banMesCod(Integer type, String phone) {
        userDao.updatecode(type,phone);
        return ResultUtil.success();
    }
}
