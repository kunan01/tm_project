package com.tangmo.yiliao.dao;

import com.tangmo.yiliao.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
@Mapper
public interface VideoDao {

    //获取首页视频轮播
    List<DoctorVideo> getShufflingVideo();

    //获取首页视频轮播
    List<DoctorVideo> selectShufflingVideoByName(@Param("title")String title);

    //获取首页16个知识课堂视频
    List<DoctorVideo> getKnowledgeVideo(@Param("start")Integer start,@Param("end")Integer end);

    //获取首页6个短视频
    List<DoctorVideo> getShortVideo();

    //根据科室id获取当前科室下排名前四的视频
    List<DoctorVideo> getDepartmentVideoTop4ById(String dtId);

    //根据科室id获取科室下所有视频(分页)
    List<DoctorVideo> getDepartmentByIdVideoAll(@Param("dtId") String dtId,@Param("start") Integer start,@Param("end") Integer end);

    //快搜视频
    List<DoctorVideo> getDepartmentBySearch(SelectUser selectUser);

    //通过视频分类获取视频列表(分页)
    List<DoctorVideo> getVideoList(@Param("category") Byte category,@Param("start") Integer start,@Param("end") Integer end);

    //获取用户视频足迹(分页)
    List<DoctorVideo> getUserVideoFootprint(@Param("userId") String userId,@Param("start") Integer start,@Param("end") Integer end);

    //获取医师我的视频(分页)
    List<DoctorVideo> getUserDoctorVideoById(@Param("userId") String userId,@Param("start") Integer start,@Param("end") Integer end);

    //获取视频详情 *******
    DoctorVideo getVideoById(String dvId);

    //获取推荐视频
    List<DoctorVideo> getDepartmentVideoTop2T(String userId);

    //增加视频游览次数
    int updVisitNumber(String dvId);

}
