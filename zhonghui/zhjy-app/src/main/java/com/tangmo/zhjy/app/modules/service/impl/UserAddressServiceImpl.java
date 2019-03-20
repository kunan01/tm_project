package com.tangmo.zhjy.app.modules.service.impl;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.UserAddress;
import com.tangmo.zhjy.app.modules.dao.UserAddressDao;
import com.tangmo.zhjy.app.modules.service.UserAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/4/7
 * @description
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService {
    @Resource
    private UserAddressDao userAddressDao;

    //修改默认地址
    @Override
    public Result addUserAddress(UserAddress userAddress) {
        if(userAddress.getUserId() == null){
            return new Result(ResultCode.FAIL);
        }

        //如果要设置为默认地址,则先设置其他地址为默认地址
        if(userAddress.getIsDefault() != null){
            if(userAddress.getIsDefault() == 1 || userAddress.getIsDefault().toString().equals("1")){
                userAddressDao.updateUnDefaultByUserId(userAddress.getUserId());
            }
        }
        userAddressDao.insertSelective(userAddress);
        return new Result(ResultCode.SUCCESS);
    }


    @Override
    public Result changeUserAddress(UserAddress userAddress) {

        if(userAddress.getUaId() == null){
            return new Result(ResultCode.FAIL,"地址id不能为空");
        }
        //UserAddress uadd=userAddressDao.selectListById(userAddress.getUaId());
        //如果要设置为默认地址,则先设置其他地址为默认地址
        if(userAddress.getIsDefault()!= null){
            if(userAddress.getIsDefault()==1){
                userAddressDao.updateUnDefaultByUserId(userAddress.getUserId());
            }
        }
        userAddressDao.updateById(userAddress);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result searchAddressList(Integer userId) {
        return new Result(ResultCode.SUCCESS,userAddressDao.selectListByUserId(userId));
    }

    @Override
    public Result delUserAddress(Integer uaId) {
        userAddressDao.deleteById(uaId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result changeDefault(Integer uaId) {
        UserAddress userAddress = userAddressDao.selectListById(uaId);
        if(userAddress==null || userAddress.getUserId()==null){
            return new Result(ResultCode.FAIL);
        }
        userAddressDao.updateUnDefaultByUserId(userAddress.getUserId());
        userAddressDao.updateDefaultById(uaId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result searchDefault(Integer userId) {
        UserAddress userAddress=userAddressDao.selectDefaultAddress(userId);
        if(userAddress==null){
            return new Result(ResultCode.USER_ADDRESS,null);
        }
        return new Result(ResultCode.SUCCESS,userAddress);
    }

    @Override
    public Result getAddressByUaId(Integer uaId) {
        if(uaId == null){
            return new Result(ResultCode.FAIL);
        }
        return new Result(ResultCode.SUCCESS,userAddressDao.selectListById(uaId));
    }
}
