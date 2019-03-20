package com.tangmo.zhjy.app.modules.dao;

import com.tangmo.zhjy.app.modules.bean.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author boge
 * @date 17/12/25
 * @description
 */
@Mapper
public interface UserAddressDao {

    /**
     * 增加用户地址Selective
     *
     * @param userAddress 用户地址信息实体
     * @return
     */
    int insertSelective(UserAddress userAddress);

    /**
     * 通过用户Id查询用户信息
     *
     * @param userId 用户idupdateById
     * @return
     */
    List<UserAddress> selectListByUserId(Integer userId);

    /**
     * 通过主键修改用户地址信息
     *
     * @param userAddress 用户地址信息实体
     * @return
     */
    int updateById(UserAddress userAddress);

    /**
     * 通过主键删除用户地址信息
     *
     * @param uaId
     * @return
     */
    int deleteById(Integer uaId);

    /**
     * 通过主键查询地址信息
     *
     * @param uaId
     * @return
     */
    UserAddress selectListById(Integer uaId);

    /**
     * 通过主键更改为默认地址
     *
     * @param uaId
     * @return
     */
    int updateDefaultById(Integer uaId);

    /**
     * 设置用户的所有地址信息为非默认
     *
     * @param userId
     * @return
     */
    int updateUnDefaultByUserId(Integer userId);

    /**
     * 查询默认地址信息
     *
     * @param userId
     * @return
     */
    UserAddress selectDefaultAddress(Integer userId);
}
