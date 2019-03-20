package com.tangmo.emall.service.impl;

import com.tangmo.emall.dao.AddressDao;
import com.tangmo.emall.entity.ParamType;
import com.tangmo.emall.entity.ShopDelivery;
import com.tangmo.emall.entity.UserAddress;
import com.tangmo.emall.service.AddressService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressDao addressDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    @Transactional
    public Result addUserAddress(UserAddress userAddress) {
        try {
            //非空判断
            if(userAddress == null || userAddress.getAddressLine1() == null || userAddress.getAddressLine2() == null ||
                    userAddress.getUserId() == null || userAddress.getUserName() == null || userAddress.getCity() == null ||
                    userAddress.getProvince() == null || userAddress.getZipCode() == null || userAddress.getCountry() == null ||
                    userAddress.getUserPhone() == null || userAddress.getIsReceivePackage() == null){
                return ResultUtil.paramError();
            }

            List<UserAddress> userAddresses = addressDao.getAddressListByUserId(userAddress.getUserId());

            if(userAddresses != null && userAddresses.size() >= 10){
                return ResultUtil.addressError();
            }

            //添加地址
            addressDao.insertAddress(userAddress);

            String rediskey = "UserAddressList"+userAddress.getUserId();

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            log.error("地址模块：'添加用户地址' 接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getUserAddressListByUserId(Integer userId) {
        try {

            //非空判断
            if(userId == null){
                return ResultUtil.paramError();
            }

            String rediskey = "UserAddressList"+userId;

            if(!jedisKeys.exists(rediskey)){
                List<UserAddress> userAddresses = addressDao.getAddressListByUserId(userId);

                if(userAddresses == null || userAddresses.size() == 0){
                    return ResultUtil.addressListError();
                }

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(userAddresses));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(userAddresses);
            }else{
                List<UserAddress> userAddresses = (List<UserAddress>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));
                return ResultUtil.success(userAddresses);
            }

        }catch (Exception e){
            log.error("地址模块：'获取用户地址集合' 接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getUserAddressById(Integer addressId,Integer userId) {
        try {

            //非空判断
            if(addressId == null || userId == null){
                return ResultUtil.paramError();
            }

            String rediskey = "UserAddressList"+userId;
            if(!jedisKeys.exists(rediskey)){
                UserAddress address = addressDao.getAddressById(addressId);

                if(address == null){
                    return ResultUtil.dataNoError();
                }

                if(!address.getUserId().toString().equals(userId.toString())){
                    return ResultUtil.addressUserError();
                }

                return ResultUtil.success(address);
            }else{
                List<UserAddress> userAddresses = (List<UserAddress>)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));

                for (int i = 0;i < userAddresses.size();i++){
                    if(addressId.toString().equals(userAddresses.get(i).getAddressId().toString())){
                        return ResultUtil.success(userAddresses.get(i));
                    }
                }

                jedisKeys.del(rediskey.getBytes());

                return ResultUtil.dataNoError();
            }

        }catch (Exception e){
            log.error("地址模块：'获取地址详细地址' 接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updAddDefaultById(UserAddress userAddress) {
        try {
            //非空判断
            if(userAddress == null || userAddress.getAddressId() == null || userAddress.getUserId() == null || userAddress.getIsDefault() == null){
                return ResultUtil.paramError();
            }

            //校验用户身份
            UserAddress userAddress1 = addressDao.getAddressById(userAddress.getAddressId());

            if(userAddress1 == null){
                return ResultUtil.paramError();
            }

            if(userAddress.getUserId() != userAddress1.getUserId()){
                return ResultUtil.addressUserError();
            }

            //更改用户其他地址为非默认
            if(userAddress.getIsDefault().toString().equals("1")){
                addressDao.updAddressIsDefaultByUserId(userAddress.getUserId());
            }

            UserAddress userAddress2 = new UserAddress();
            userAddress2.setAddressId(userAddress.getAddressId());
            userAddress2.setIsDefault(userAddress.getIsDefault());

            addressDao.updAddress(userAddress2);

            String rediskey = "UserAddressList"+userAddress.getUserId();

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("设置成功");
        }catch (Exception e){
            log.error("地址模块：'设置或取消默认地址' 接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updAddById(UserAddress userAddress) {
        try {
            //非空判断
            if(userAddress == null || userAddress.getAddressId() == null || userAddress.getUserId() == null){
                return ResultUtil.paramError();
            }

            //校验用户身份
            UserAddress userAddress1 = addressDao.getAddressById(userAddress.getAddressId());

            if(userAddress1 == null){
                return ResultUtil.paramError();
            }

            if(userAddress.getUserId() != userAddress1.getUserId()){
                return ResultUtil.addressUserError();
            }

            addressDao.updAddress(userAddress);

            String rediskey = "UserAddressList"+userAddress.getUserId();

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("修改成功");
        }catch (Exception e){
            log.error("地址模块：'修改用户地址信息' 接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delAddById(UserAddress userAddress) {
        try {
            //非空判断
            if(userAddress == null || userAddress.getAddressId() == null || userAddress.getUserId() == null){
                return ResultUtil.paramError();
            }

            //校验用户身份
            UserAddress userAddress1 = addressDao.getAddressById(userAddress.getAddressId());

            if(userAddress1 == null){
                return ResultUtil.paramError();
            }

            if(userAddress.getUserId() != userAddress1.getUserId()){
                return ResultUtil.addressUserError();
            }

            addressDao.delAddressById(userAddress.getAddressId());

            String rediskey = "UserAddressList"+userAddress.getUserId();

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            log.error("地址模块：'删除用户地址信息' 接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getDeliveryAddress(Integer shopId) {
        try {

            //非空判断
            if(shopId == null){
                return ResultUtil.paramError();
            }

            String rediskey = "DeliveryAddress"+shopId;
            if(!jedisKeys.exists(rediskey)){
                ShopDelivery shopDelivery = addressDao.getDeliveryAddress(shopId);

                if(shopDelivery == null){
                    return ResultUtil.dataNoError();
                }

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(shopDelivery));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                return ResultUtil.success(shopDelivery);
            }else{

                ShopDelivery shopDelivery = (ShopDelivery)SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));

                return ResultUtil.success(shopDelivery);
            }

        }catch (Exception e){
            log.error("地址模块：'获取店铺发货地址' 接口异常 ："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
