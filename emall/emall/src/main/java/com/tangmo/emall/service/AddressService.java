package com.tangmo.emall.service;

import com.tangmo.emall.entity.UserAddress;
import com.tangmo.emall.utils.Result;

public interface AddressService {

    //添加用户地址
    Result addUserAddress(UserAddress userAddress);

    //获取用户地址集合
    Result getUserAddressListByUserId(Integer userId);

    //获取用户地址详情
    Result getUserAddressById(Integer addressId,Integer userId);

    //设置或取消默认地址
    Result updAddDefaultById(UserAddress userAddress);

    //修改用户地址信息
    Result updAddById(UserAddress userAddress);

    //删除用户地址信息
    Result delAddById(UserAddress userAddress);

    //获取店铺发货地址
    Result getDeliveryAddress(Integer shopId);

}
