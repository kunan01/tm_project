package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Slides;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlidesDao {

    //获取轮播图
    List<Slides> getSlidesList();
}
