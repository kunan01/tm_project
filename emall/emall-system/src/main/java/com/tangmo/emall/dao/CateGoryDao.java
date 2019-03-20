package com.tangmo.emall.dao;

import com.tangmo.emall.entity.CateGory;
import com.tangmo.emall.entity.SortingWay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CateGoryDao {

    //通过分类名称以及分类级别查询信息
    CateGory getCateGoryByNameAndLevel(@Param("name")String name,@Param("level")Integer level);

    //添加分类
    int addCateGory(CateGory cateGory);

    //修改分类
    int updCategory(CateGory cateGory);

    //通过商品级别获取商品分类
    List<CateGory> getCateGoryList(Integer level);

    //通过分类id查询信息
    CateGory getCateGoryById(Integer cId);

    //通过父级id获取下级所有分类
    List<CateGory> getCateGoryListByPId(Integer cId);

    //通过分类id删除信息
    int delCateGory(Integer cId);

    //通过父级id删除下级所有分类
    int delCateGoryListByPId(Integer cId);

    //添加商品排序规则
    int addSortingWay(SortingWay sortingWay);

    //通过商品排序规则id获取排序规则
    SortingWay getSortingWayById(Integer sId);

    //修改商品排序规则
    int updateSortingWay(SortingWay sortingWay);

    //删除商品筛选规则
    int delSortingWay(Integer sId);

    //获取商品筛选方式
    List<SortingWay> getSortingWay(Integer sortingType);
}
