package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Slides;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlidesDao {

    //获取轮播图
    List<Slides> getSlidesList();

    //添加轮播图
    int addSlides(Slides slides);

    //通过id获取轮播图信息
    Slides getSlidesById(Integer sId);

    //修改轮播图
    int updSlides(Slides slides);

    //通过id删除轮播图
    int delSlides(Integer sId);

    //修改轮播图排序
    int updSlidesSort(Integer slideSort);

    //获取轮播图数量
    Integer getSlidesCount();

    //修改时轮播图排序
    int updSlidesByJian(@Param("slideSort") Integer slideSort, @Param("newSlideSort") Integer newSlideSort);
    int updSlidesByJia(@Param("slideSort") Integer slideSort,@Param("newSlideSort") Integer newSlideSort);

    //删除时轮播图排序
    int delSlidesCount(Integer slideSort);


}
