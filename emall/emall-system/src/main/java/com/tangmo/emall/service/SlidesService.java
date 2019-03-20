package com.tangmo.emall.service;


import com.tangmo.emall.entity.Slides;
import com.tangmo.emall.utils.Result;

public interface SlidesService {

    //添加轮播图
    Result addSlides(Slides slides);

    //修改轮播图
    Result updSlides(Slides slides);

    //删除轮播图
    Result delSlides(Slides slides);

    //批量删除轮播图
    Result batchDeleteSlides(Slides slides);

    //获取轮播图
    Result getSlidesList(Integer pageNo,Integer pageSize);
}
