package com.tangmo.yiliao.service;


import com.tangmo.yiliao.entity.DoctorVideo;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.entity.SystemTheArticle;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-9-5
 * @description 后台文章和视频
 */
public interface ArticleOrVideoService {

    Result getArticleAllByApp(Integer start,Integer end);

    //后台获取所有文章信息(分页)
    Result getArticleAll(SelectUser selectUser);

    //删除文章信息
    Result delArticleById(String saId,String userId);

    //修改文章信息
    Result upArticleById(SystemTheArticle systemTheArticle);

    //添加文章信息
    Result addArticle(SystemTheArticle systemTheArticle);

    //查询文章数量
    Result getArticleCount(SelectUser selectUser);

    //后台条件获取所有视频信息(分页)
    Result getVideoAll(SelectUser selectUser);

    //后台获取视频详情
    Result getVideoDDById(String dvId);

    //后台条件获取所有视频数量
    Result getVideoCountAll(SelectUser selectUser);

    //后台删除视频
    Result delVideoById(String dvId,String userId);

    //添加视频
    Result addVideo(DoctorVideo doctorVideo);

    //修改视频
    Result updVideo(DoctorVideo doctorVideo);

    //后台获取视频所有评论
    Result getVideoComments(String dvId,Integer type);

    //后台删除评论
    Result delCommentsById(String cId);
}
