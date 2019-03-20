package com.tangmo.emall.dao;

import com.tangmo.emall.entity.ParamType;
import com.tangmo.emall.entity.ParamValue;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopParamDao {

    //通过店铺id和名称查询类型信息
    ParamType getParamTypeByName(ParamType paramType);

    //增加店铺类型
    int addShopParamType(ParamType paramType);

    //通过参数值和类型id查询参数信息
    ParamValue getParamValueByName(ParamValue paramValue);

    //增加店铺参数值
    int addShopParamValue(ParamValue paramValue);

    //通过类型id查询类型信息
    ParamType getParamTypeById(Integer tId);

    //修改店铺参数类型信息
    int updShopParamType(ParamType paramType);

    //通过参数值id查询参数信息
    ParamValue getParamValueById(Integer valueId);

    //修改店铺参数值信息
    int updShopParamValue(ParamValue paramValue);

    //删除店铺参数值信息
    int delShopParamValue(Integer valueId);

    //删除店铺参数类型
    int delShopParamType(Integer typeId);

    //通过参数类型id删除参数值
    int delShopParamValueByTypeId(Integer typeId);

    //通过shopId查询参数类型
    List<ParamType> getParamTypeListByShopId(Integer shopId);

    //通过typeId查询参数值
    List<ParamValue> getParamValueListByTypeId(Integer typeId);

}
