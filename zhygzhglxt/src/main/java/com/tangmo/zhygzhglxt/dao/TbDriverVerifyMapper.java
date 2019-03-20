package com.tangmo.zhygzhglxt.dao;

import com.tangmo.zhygzhglxt.entity.TbDriverVerify;
import com.tangmo.zhygzhglxt.utility.Result;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Map;

public interface TbDriverVerifyMapper {

    /**
     * This method was generated by MyBatis Generator.
     */
    int deleteByPrimaryKey(String driverVerifyId);

    /**
     * This method was generated by MyBatis Generator.
     */
    int insert(TbDriverVerify record);

    /**
     * 动态添加
     */
    int insertSelective(TbDriverVerify record);

    /**
     * This method was generated by MyBatis Generator.
     */
    TbDriverVerify selectByPrimaryKey(String driverVerifyId);

    /**
     * 根据车牌号查询
     */
    TbDriverVerify selectByCarNumber(@Param("carNumber") String carNumber);

    /**
     * 根据多个车牌号查询车主司机
     */
    List<TbDriverVerify> selectByCarNumbers(List<String> busNumbers);


    /**
     * 根据用户id查询车主审核的信息
     */
    TbDriverVerify selectByUserCode(String userCode);

    /**
     * 根据唯一标识code查找审核信息
     */
    TbDriverVerify selectByCode(String code);

    /**
     * 根据当前司机用户的唯一标识查询司机实时的经度纬度
     */
    Map selCarLaLoByUserCode(@Param("driverUserCode") String driverUserCode);

    /**
     * 实时更新司机当前的经度纬度
     */
    int updateCarLaLo(@Param("userCode") String userCode, @Param("carLa") String carLa, @Param("carLo") String carLo);


    /**
     * 根据模糊参数和状态查询（分页）
     */
    List<TbDriverVerify> selDriverVerify(@Param("name") String name, @Param("state") String state);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKeySelective(TbDriverVerify record);

    /**
     * 根据车主审核的唯一标识来修改审核信息
     */
    int updateByDriverVerifyCode(TbDriverVerify record);

    /**
     * This method was generated by MyBatis Generator.
     */
    int updateByPrimaryKey(TbDriverVerify record);

    /**
     * 审核车主信息.
     */
    int verifyDriver(@Param("state") String state, @Param("code") String code);

    /**
     * T概念性删除车主的审核信息
     */
    int delDriverVerifyByCode(String code);


    /**
     * 根据唯一标识code查找车主详细信息
     */
    TbDriverVerify jtQueryById(String code);


    /**
     * 后台查询所有车主信息
     *
     * @param name  模糊参数 时间
     * @param state 0待审核 1已审核 2审核驳回
     * @return
     */
    List<TbDriverVerify> jtQueryList(@Param("name") String name, @Param("state") String state);


}