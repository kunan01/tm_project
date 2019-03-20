package com.tangmo.emall.dao;

import com.tangmo.emall.entity.PropKey;
import com.tangmo.emall.entity.PropValue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopPropDao {

    //添加属性key
    int addPropKey(PropKey propKey);

    //添加属性value
    int addPropValue(PropValue propValue);

    //通过名称，店铺id，以及分类id查询key
    PropKey getPropKeyByName(PropKey propKey);

    //通过keyid 以及规格value 查询PropValue
    PropValue getPropValueByName(PropValue propValue);

    //通过keyId查询key数据
    PropKey getPropKeyById(Integer keyId);

    //通过valueId 查询PropValue
    PropValue getPropValueById(Integer valueId);

    //通过valueId 删除PropValue数据
    int delPropValue(Integer valueId);

    //通过keyId 删除PropKey数据
    int delPropKey(Integer keyId);

    //通过keyId 删除PropValue数据
    int delPropValueByKeyId(Integer keyId);

    //通过keyId 获取当前key下的所有Value
    List<PropValue> getPropValueByKeyId(Integer keyId);

    //通过店铺id和分类id获取key
    List<PropKey> getPropKeyByShopIdAndCId(Integer shopId);

    //通过三级分类id查询key的数量
    Integer getPropKeyCount(Integer categoryId);

    //修改key 的属性名称
    int updPropKey(PropKey propKey);

    //修改value 的属性名称
    int updPropValue(PropValue propValue);
}
