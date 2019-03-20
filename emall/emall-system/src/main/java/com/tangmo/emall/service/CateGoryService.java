package com.tangmo.emall.service;

import com.tangmo.emall.entity.CateGory;
import com.tangmo.emall.entity.SortingWay;
import com.tangmo.emall.utils.Result;

public interface CateGoryService {

    //添加商品分类
    Result addCategory(CateGory cateGory);

    //修改商品分类
    Result updCategory(CateGory cateGory);

    //获取一级商品分类
    Result getCateGoryList(Integer state,Integer pageNo,Integer pageSize);

    //获取全部商品分类信息
    Result queryCateGoryList();

    //通过一级获取二级商品分类
    Result getCateGoryListByTwoLevel(Integer state,Integer parentId,Integer pageNo,Integer pageSize);

    //删除商品分类
    Result delCateGory(Integer cId);

    //批量删除商品分类
    Result batchDelCateGory(CateGory cateGorys);

    //校验当前分类是否存在子集分类
    Result getIsPType(Integer cId);

    //增加商品筛选规则
    Result addSortingWay(SortingWay sortingWay);

    //修改商品筛选规则
    Result updateSortingWay(SortingWay sortingWay);

    //删除商品筛选规则
    Result delSortingWay(Integer sId);

    //批量删除商品筛选规则
    Result batchDelSortingWay(SortingWay sortingWay);

    //获取商品筛选方式
    Result getSortingWay(Integer sortingType,Integer pageNo,Integer pageSize);
}
