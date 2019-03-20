package com.tangmo.emall.dao;

import com.tangmo.emall.entity.ShopDelivery;
import com.tangmo.emall.entity.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao {

    //通过用户id获取集合
    List<UserAddress> getAddressListByUserId(Integer userId);

    //添加用户地址
    int insertAddress(UserAddress userAddress);

    //通过地址id获取地址详情
    UserAddress getAddressById(Integer aId);

    //通过地址id修改地址信息
    int updAddress(UserAddress userAddress);

    //更改用户下的所有地址为非默认
    int updAddressIsDefaultByUserId(Integer userId);

    //删除地址信息
    int delAddressById(Integer aId);

    //获取店铺发货地址
    ShopDelivery getDeliveryAddress(Integer shopId);

}
