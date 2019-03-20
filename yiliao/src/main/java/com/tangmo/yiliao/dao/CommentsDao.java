package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-9-12
 * @description
 */
@Mapper
public interface CommentsDao {

    //获取视频或文章评论中提问前三条 (type: 0文章 1视频)
    List<Comments> getCommentsTop3Q(@Param("id") String id,@Param("type") Integer type);

    //分页获取评论
    List<Comments> getCommentsByIdF(@Param("id") String id,@Param("type") Integer type,@Param("start") Integer start,@Param("end") Integer end);

    //分页获取评论
    List<Comments> getCommentsByIdFOnH(@Param("id") String id,@Param("type") Integer type,@Param("start") Integer start,@Param("end") Integer end);

    //获取评论的回复数量
    Integer getCommentsCountByCId(String CId);

    //获取评论的前3条回复
    List<Comments> getCommentsTop3AByCId(String CId);

    //添加评论
    int addComments(Comments comments);

    //通过评论id获取评论详情
    Comments getCommentsById(String cId);

    //获取评论的所有回复
    List<Comments> getCommentsAllAByCId(String cId);

    //获取视频或文章评论中所有提问 (type: 0文章 1视频)
    List<Comments> getCommentsAllQ(@Param("id") String id,@Param("type") Integer type);

    //通过用户id获取用户角色
    String getRoleByUserId(String userId);

    //通过评论id获取用户id
    String getUserIdBycId(String cId);

    //通过文章id获取文章标题
    String getActitleById(String aId);

    //通过视频id获取视频标题
    String getVideotitleById(String vId);

    //后台删除评论
    int delCommentsById(String cId);

    //通过文章id获取科室下所有医生id
    List<String> getDoctorByActitleId(String aId);

    //通过视频id获取科室下所有医生id
    List<String> getDoctorByVideoId(String vId);

}
