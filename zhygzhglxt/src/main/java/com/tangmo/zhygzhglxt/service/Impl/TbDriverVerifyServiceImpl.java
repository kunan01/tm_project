package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.*;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbDriverVerifyService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengge on 2018/10/23.
 */
@Service
public class TbDriverVerifyServiceImpl implements TbDriverVerifyService {

    @Autowired
    TbDriverVerifyMapper tbDriverVerifyMapper;

    @Autowired
    TbSysUserMapper tbSysUserMapper;

    @Autowired
    TbDriverRatingMapper tbDriverRatingMapper;

    @Autowired
    TbParmMapper tbParmMapper;//车辆参数类型

    @Autowired
    TbServiceTypeMapper tbServiceTypeMapper;//服务类型

    @Autowired
    TbOrderRouteMapper tbOrderRouteMapper;//订单路线

    @Autowired
    TbDriverOrderMapper tbDriverOrderMapper;//司机订单

    @Autowired
    TbPassengerOrderMapper tbPassengerOrderMapper;//乘客订单

    @Override
    public Result addDriverVerify(TbDriverVerify tbDriverVerify) {

        if (tbDriverVerify == null) {
            return new Result(ResultCode.FAIL, "信息不能为空！");
        }
        if (tbDriverVerify.getDriverUserid() == null) {
            return new Result(ResultCode.FAIL, "用户唯一标识code不能为空！");
        }
        //代表是乘客端
        if (tbDriverVerify.getIdcardFace() == null || //身份证正面
                tbDriverVerify.getUserCard() == null || //身份证号
                tbDriverVerify.getDriverName() == null || //车主姓名
                tbDriverVerify.getCarType() == null ||   //车辆类型
                tbDriverVerify.getCarNumber() == null ||   //车牌号
                tbDriverVerify.getCarType() == null ||   //车牌号
                tbDriverVerify.getCompany() == null ||   //运营公司
                tbDriverVerify.getDriverIcence() == null || //驾驶证
                tbDriverVerify.getLicensePlate() == null ||//车牌照
                tbDriverVerify.getIdcardBack() == null  //身份证反面
        ) {

            return new Result(ResultCode.FAIL, "参数信息不全！");
        }
        if (tbDriverVerify.getDriverVerifyCode() == null || "".equals(tbDriverVerify.getDriverVerifyCode())) {
            tbDriverVerify.setDriverVerifyId(EncryptUtil.get32Uuid());
            tbDriverVerify.setDriverVerifyCode(EncryptUtil.get32Uuid());
            tbDriverVerifyMapper.insertSelective(tbDriverVerify);
        } else {
            tbDriverVerify.setState("0");
            tbDriverVerifyMapper.updateByDriverVerifyCode(tbDriverVerify);
        }
//       TbDriverVerify driverVerify = tbDriverVerifyMapper.selectByUserCode(tbDriverVerify.getDriverUserid());
//
//       if(driverVerify !=null){
//            return new Result(ResultCode.DRIVER_ERROR,"已存在审核信息，请勿多次提交！");
//        }
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selDriverVerify(String name, String state, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        List<TbDriverVerify> tbDriverVerify = tbDriverVerifyMapper.selDriverVerify(name, state);

        if (tbDriverVerify.size() > 0) {
            for (TbDriverVerify tbDriverVerify1 : tbDriverVerify) {
                TbSysUser tbSysUser = tbSysUserMapper.selectByCode(tbDriverVerify1.getDriverUserid());
                tbDriverVerify1.setTbSysUser(tbSysUser);
            }
        }
        return new Result(ResultCode.SUCCESS, new PageInfo(tbDriverVerify));
    }

    @Override
    public Result verifyDriver(String state, String code) {

        if ("".equals(code) || code == null) {
            return new Result(ResultCode.FAIL, "唯一标识code不能为空！");
        }

        if ("".equals(state) || state == null) {
            return new Result(ResultCode.FAIL, "审核状态不能为空！");
        }

        int a = tbDriverVerifyMapper.verifyDriver(state, code);

        if (a > 0) {    //说明通过或者驳回了
            if ("1".equals(state)) {  //代表通过，改变车主用户为车主身份并且生成一份好评表
                TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByCode(code);
                String userCode = tbDriverVerify.getDriverUserid();
                tbSysUserMapper.updateStatusByCode("2", userCode);

                //添加好评度
                TbDriverRating tbDriverRating = new TbDriverRating();

                tbDriverRating.setDriverRatingCode(EncryptUtil.get32Uuid());
                tbDriverRating.setDriverRatingId(EncryptUtil.get32Uuid());
                tbDriverRating.setUserCode(userCode);
                tbDriverRatingMapper.insertSelective(tbDriverRating);
            }
        }
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delDriverVerifyByCode(String code) {

        tbDriverVerifyMapper.delDriverVerifyByCode(code);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selDriverVerifyByUserCode(String userCode) {

        if (userCode == null || "".equals(userCode)) {
            return new Result(ResultCode.FAIL, "用户的唯一标识不能为空！");
        }

        TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(userCode);

        if (tbDriverVerify == null) {
            Map map = new HashMap();
            map.put("state", "4");
            return new Result(ResultCode.SUCCESS, map);
        }
        return new Result(ResultCode.SUCCESS, tbDriverVerify);
    }

    /**
     * 根据用户的唯一标识code查找车主详细信息
     *
     * @param userCode
     * @return
     */
    @Override
    public Result jtQueryById(String userCode) {

        //根据code查车主车辆信息
        TbDriverVerify tbDriverVerify = tbDriverVerifyMapper.selectByUserCode(userCode);
        if (tbDriverVerify == null) {
            return new Result(ResultCode.FAIL, "未查到车主认证信息！");
        }
        //根据车主车辆司机用户id查找用户详情
        TbSysUser tbSysUser = tbSysUserMapper.selectByCode(tbDriverVerify.getDriverUserid());

        //根据车主车辆类型查询参数类型
        tbDriverVerify.setCarTypeName(tbParmMapper.selectByPrimaryKey(tbDriverVerify.getCarType()).getParmName());

        //根据车主车辆类型查询
        //jtQueryById.setServiceTypeIdName(tbServiceTypeMapper.jtQueryById(jtQueryById.getServiceTypeId()).getServiceTypeName());

        Map map = new HashMap();
        map.put("name", tbDriverVerify.getDriverName()); //姓名
        map.put("sex", tbSysUser.getSex());//性别
        map.put("balance", tbSysUser.getBalance());//余额
        map.put("imgUrl", tbSysUser.getImgUrl());//头像
        map.put("phone", tbDriverVerify.getPhone());//电话
        map.put("card", tbDriverVerify.getUserCard());//身份证
        map.put("carTypeName", tbDriverVerify.getCarTypeName());//车辆类型
        //map.put("carTypeName",tbDriverVerify.getCarTypeName());//服务类型
//        map.put("carLength",tbDriverVerify.getcar());//车厢长度
//        map.put("carTypeName",tbDriverVerify.getCarTypeName());//载重量
        map.put("state", tbDriverVerify.getState());//车辆认证


        return new Result(ResultCode.SUCCESS, map);

    }


    @Override
    public Result updateCarLaLo(String userCode, String carLa, String carLo, String driverOrderCode) {

        if (userCode == null || "".equals(userCode)) {
            return new Result(ResultCode.FAIL, "用户的唯一标识不能为空！");
        }
        if (carLa == null || "".equals(carLa)) {
            return new Result(ResultCode.FAIL, "当前司机的纬度不能为空！");
        }
        if (carLo == null || "".equals(carLo)) {
            return new Result(ResultCode.FAIL, "当前司机的经度不能为空！");
        }

        int a = tbDriverVerifyMapper.updateCarLaLo(userCode, carLa, carLo);

        if (a > 0) {
            //将订单经纬度存储起来
            TbDriverOrder tbDriverOrder = tbDriverOrderMapper.selectByCode(driverOrderCode);
            if (tbDriverOrder != null) {
                TbPassengerOrder tbPassengerOrder = tbPassengerOrderMapper.selOrderDetailByOrderCode(tbDriverOrder.getPassengerOrderCode());
                if (tbPassengerOrder != null) {
                    if ("1018".equals(tbPassengerOrder.getTbParmId())) {
                        TbOrderRoute tbOrderRoute = new TbOrderRoute();
                        tbOrderRoute.setOrderRouteId(EncryptUtil.get32Uuid());
                        tbOrderRoute.setDriverOrderCode(driverOrderCode);
                        tbOrderRoute.setLa(carLa);
                        tbOrderRoute.setLo(carLo);
                        TbOrderRoute tbOrderRoute1 = tbOrderRouteMapper.selectByDriverOrderCodeOne(driverOrderCode);
                        if (tbOrderRoute1 == null) {
                            tbOrderRoute.setTm(1478031031);
                        } else {
                            int s = (int) (new Date().getTime() / 1000);
                            // 上一次记录发送保存时间
                            int preTime = (int) (tbOrderRoute1.getCreateTime().getTime() / 1000);
                            tbOrderRoute.setTm(s - preTime);
                        }
                        tbOrderRouteMapper.insertSelective(tbOrderRoute);
                    }
                }
            }
            return new Result(ResultCode.SUCCESS, "实时更新司机经度纬度成功！");
        } else {
            return new Result(ResultCode.FAIL, "实时更新司机经度纬度失败！");
        }
    }

    @Override
    public Result selCarLaLoByUserCode(String driverUserCode) {

        if (driverUserCode == null || "".equals(driverUserCode)) {
            return new Result(ResultCode.FAIL, "司机用户的唯一标识不能为空！");
        }

        Map map = tbDriverVerifyMapper.selCarLaLoByUserCode(driverUserCode);

        return new Result(ResultCode.SUCCESS, map);
    }


}
