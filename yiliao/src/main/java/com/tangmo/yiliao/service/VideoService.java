package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-8-28
 * @description
 */
public interface VideoService {

    //获取首页视频轮播
    Result getShufflingVideo();

    //获取首页展示视频
    Result homePageDisplay();

    //获取所有科室下排名前四视频
    Result getDepartmentAllVideoTop4();

    //根据科室id获取科室下所有视频(分页)
    Result getDepartmentByIdVideoAll(String dtId,Integer start,Integer end);

    //快搜视频
    Result getDepartmentBySearch(SelectUser selectUser);

    //通过视频分类获取视频列表(分页)
    Result getVideoList(Byte category,Integer start,Integer end);

    //获取视频详情
    Result getVideoDDById(String dvId,String userId);

    //获取视频评论
    Result getVideoComm(String dvId,Integer type,Integer start,Integer end);

    Result getVideoCommH(String cId,Integer type,Integer start,Integer end);

}
