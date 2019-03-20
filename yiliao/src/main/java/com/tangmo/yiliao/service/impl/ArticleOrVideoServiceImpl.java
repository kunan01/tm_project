package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.ArticleOrVideoDao;
import com.tangmo.yiliao.dao.CommentsDao;
import com.tangmo.yiliao.dao.VideoDao;
import com.tangmo.yiliao.entity.Comments;
import com.tangmo.yiliao.entity.DoctorVideo;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.entity.SystemTheArticle;
import com.tangmo.yiliao.service.ArticleOrVideoService;
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
 * @author boge
 * @date 18/9/5
 * @description
 */
@Service("articleOrVideoService")
public class ArticleOrVideoServiceImpl implements ArticleOrVideoService {

    @Resource
    private ArticleOrVideoDao articleOrVideoDao;

    @Resource
    private CommentsDao commentsDao;

    @Resource
    private VideoDao videoDao;

    @Override
    public Result getArticleAllByApp(Integer start, Integer end) {
        List<SystemTheArticle> systemTheArticles = articleOrVideoDao.getArticleAllByApp((start - 1) * end, end);
        for (int i = 0;i < systemTheArticles.size();i++){
            systemTheArticles.get(i).setSaContent(systemTheArticles.get(i).getSaContent().replaceAll("\n","").replaceAll("\\<style>.*?</style>","").replaceAll("\\<.*?>",""));
        }
        return ResultUtil.success(systemTheArticles);
    }

    @Override
    public Result getArticleAll(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart() - 1) * selectUser.getEnd());
        return ResultUtil.success(articleOrVideoDao.getArticleAll(selectUser));
    }

    @Override
    public Result delArticleById(String saId, String userId) {
        if(saId == null || userId == null){
            return ResultUtil.paramError();
        }
        articleOrVideoDao.delArticleById(saId, userId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result upArticleById(SystemTheArticle systemTheArticle) {
        if(systemTheArticle.getDtId() == null || systemTheArticle.getSaId() ==  null || systemTheArticle.getSaContent() == null
                || systemTheArticle.getSaTitle() == null || systemTheArticle.getSaImgId() == null){
            return ResultUtil.paramError();
        }
        articleOrVideoDao.upArticleById(systemTheArticle);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result addArticle(SystemTheArticle systemTheArticle) {
        System.out.println(systemTheArticle);
        if(systemTheArticle.getDtId() == null || systemTheArticle.getCreateUserId() ==  null || systemTheArticle.getSaContent() == null || systemTheArticle.getSaTitle() == null){
            return ResultUtil.paramError();
        }
        SensitivewordFilter filter = new SensitivewordFilter();
//        systemTheArticle.setSaContent(filter.replaceSensitiveWord(systemTheArticle.getSaContent(),2,"*"));
        System.out.println(filter.replaceSensitiveWord(systemTheArticle.getSaContent(),2,"*"));
        systemTheArticle.setSaTitle(filter.replaceSensitiveWord(systemTheArticle.getSaTitle(),2,"*"));
        systemTheArticle.setSaId(EncryptUtil.get32Uuid());
        articleOrVideoDao.addArticle(systemTheArticle);
//        System.out.println(systemTheArticle);
        return ResultUtil.success();
    }

    @Override
    public Result getArticleCount(SelectUser selectUser) {
        return ResultUtil.success(articleOrVideoDao.getArticleCount(selectUser));
    }

    @Override
    public Result getVideoAll(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart()-1)*selectUser.getEnd());
        if(selectUser.getOriginatorId().equals("")){
            selectUser.setOriginatorId(null);
        }
        if(selectUser.getPeopleId().equals("")){
            selectUser.setPeopleId(null);
        }
        return ResultUtil.success(articleOrVideoDao.getVideoAll(selectUser));
    }

    @Override
    public Result getVideoDDById(String dvId) {
        System.out.println(dvId);
        return ResultUtil.success(videoDao.getVideoById(dvId));
    }

    @Override
    public Result getVideoCountAll(SelectUser selectUser) {
        return ResultUtil.success(articleOrVideoDao.getVideoCountAll(selectUser));
    }

    @Override
    @Transactional
    public Result delVideoById(String dvId, String userId) {
        if(dvId== null || userId == null){
            return ResultUtil.paramError();
        }
        articleOrVideoDao.delVideoOrderBy(dvId);
        articleOrVideoDao.delVideoById(dvId,userId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result addVideo(DoctorVideo doctorVideo) {
        if(doctorVideo == null){
            return ResultUtil.paramError();
        }
        Integer videoCount = articleOrVideoDao.getVideoCount();
        if(doctorVideo.getSortingId() == null){
            doctorVideo.setSortingId(videoCount+1);
        }else{
            if(videoCount >= doctorVideo.getSortingId()){
                articleOrVideoDao.addVideoOrderBy(doctorVideo.getSortingId());
            }
        }
        doctorVideo.setDvId(EncryptUtil.get32Uuid());
//        SensitivewordFilter filter = new SensitivewordFilter();
//        doctorVideo.setDvContent(filter.replaceSensitiveWord(doctorVideo.getDvContent(),2,"*"));
//        doctorVideo.setDvTitle(filter.replaceSensitiveWord(doctorVideo.getDvTitle(),2,"*"));
        try {
            articleOrVideoDao.addVideo(doctorVideo);
        }catch (Exception e){
            return ResultUtil.paramError();
        }
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updVideo(DoctorVideo doctorVideo) {
        System.out.println(doctorVideo);
        if(doctorVideo == null){
            return ResultUtil.paramError();
        }
        //目前排名
        Integer soId = articleOrVideoDao.getVideoSoId(doctorVideo.getDvId());
        if(soId > doctorVideo.getSortingId()){
            articleOrVideoDao.updVideoSoIdJia(soId,doctorVideo.getSortingId());
        }else if(soId < doctorVideo.getSortingId()){
            articleOrVideoDao.updVideoSoIdJian(soId,doctorVideo.getSortingId());
        }
        try {
            articleOrVideoDao.updVideo(doctorVideo);
        }catch (Exception e){
            return ResultUtil.paramError();
        }
        return ResultUtil.success();
    }

    @Override
    public Result getVideoComments(String dvId,Integer type) {
        List<Comments> commentsList = commentsDao.getCommentsAllQ(dvId,type);
        if(commentsList != null){
            for (int i = 0; i < commentsList.size(); i++){
                if(commentsList.get(i).getcAttribute().toString().equals("1")){
                    try {
                        commentsList.get(i).setFileLength(ImgUtil.getAmrDuration("E:/Tomcat 8.0/webapps/static"+commentsList.get(i).getcContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                List<Comments> comments = commentsDao.getCommentsAllAByCId(commentsList.get(i).getcId());
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

    @Override
    @Transactional
    public Result delCommentsById(String cId) {
        if(cId == null){
            return ResultUtil.paramError();
        }
        commentsDao.delCommentsById(cId);
        return ResultUtil.success();
    }
}
