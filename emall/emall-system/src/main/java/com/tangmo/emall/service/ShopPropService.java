package com.tangmo.emall.service;

import com.tangmo.emall.entity.PropKey;
import com.tangmo.emall.entity.PropValue;
import com.tangmo.emall.utils.Result;

public interface ShopPropService {

    //添加店铺规格key
    Result addPropKey(PropKey propKey);

    //添加店铺规格Value
    Result addPropValue(PropValue propValue);

    //删除店铺规格Value
    Result delPropValue(PropValue propValue);

    //批量删除店铺规格Value
    Result batchDelPropValue(PropValue propValue);

    //删除店铺规格Key
    Result delPropKey(PropKey propKey);

    //批量删除店铺规格key
    Result batchDelPropKey(PropKey propKey);

    //校验当前key下是否存在value
    Result getIsValueByKey(Integer keyId);

    //获取key集合
    Result getListKey(Integer shopId,Integer state,Integer pageNo,Integer pageSize);

    //获取value集合
    Result getListValue(Integer keyId,Integer pageNo,Integer pageSize);

    //修改店铺规格key 的属性名称
    Result updPropKey(PropKey propKey);

    //修改店铺规格value 的属性名称
    Result updPropValue(PropValue propValue);
}
