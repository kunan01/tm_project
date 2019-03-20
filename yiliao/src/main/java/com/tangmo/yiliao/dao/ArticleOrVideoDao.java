package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.DoctorVideo;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.entity.SystemTheArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author boge
 * @date 18/9/5
 * @description
 */
@Mapper
public interface ArticleOrVideoDao {

    List<SystemTheArticle> getArticleAllByApp(@Param("start") Integer start,@Param("end") Integer end);

    //后台获取所有文章信息(分页)
    List<SystemTheArticle> getArticleAll(SelectUser selectUser);

    //删除文章信息
    int delArticleById(@Param("saId") String saId,@Param("userId") String userId);

    //修改文章信息
    int upArticleById(SystemTheArticle systemTheArticle);

    //添加文章信息
    int addArticle(SystemTheArticle systemTheArticle);

    //查询文章数量
    Integer getArticleCount(SelectUser selectUser);

    //后台条件获取所有视频信息(分页)
    List<DoctorVideo> getVideoAll(SelectUser selectUser);

    //后台条件获取所有视频数量
    Integer getVideoCountAll(SelectUser selectUser);

    //后台删除视频
    int delVideoById(@Param("dvId") String dvId,@Param("userId") String userId);

    //后台添加视频
    int addVideo(DoctorVideo doctorVideo);

    //后台修改视频
    int updVideo(DoctorVideo doctorVideo);

    //查询视频目前排名
    Integer getVideoSoId(String dvId);

    int updVideoSoIdJia(@Param("soId") Integer soId,@Param("newSoId") Integer newSoId);

    int updVideoSoIdJian(@Param("soId") Integer soId,@Param("newSoId") Integer newSoId);

    //获取视频数量
    Integer getVideoCount();

    //添加视频时排序
    int addVideoOrderBy(@Param("soId") Integer soId);

    int delVideoOrderBy(@Param("dvId") String dvId);
}
