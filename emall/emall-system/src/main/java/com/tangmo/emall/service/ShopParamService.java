package com.tangmo.emall.service;

import com.tangmo.emall.entity.ParamType;
import com.tangmo.emall.entity.ParamValue;
import com.tangmo.emall.utils.Result;

public interface ShopParamService {

    //增加店铺参数类型
    Result addShopParamType(ParamType paramType);

    //增加店铺参数值
    Result addShopParamValue(ParamValue paramValue);

    //修改店铺参数类型
    Result updShopParamType(ParamType paramType);

    //修改店铺参数值
    Result updShopParamValue(ParamValue paramValue);

    //删除店铺参数值
    Result delShopParamValue(Integer valueId);

    //批量删除店铺参数值
    Result batchDelShopParamValue(ParamValue paramValue);

    //删除店铺参数类型
    Result delShopParamType(Integer typeId);

    //
    Result batchDelShopParamType(ParamType paramType);

    //查询店铺参数类型
    Result getShopParamList(Integer shopId,Integer state,Integer pageNo,Integer pageSize);

    //查询店铺参数值
    Result getShopParamValueList(Integer typeId,Integer pageNo,Integer pageSize);

    //查询全部参数
    Result queryShopParamList();
}
