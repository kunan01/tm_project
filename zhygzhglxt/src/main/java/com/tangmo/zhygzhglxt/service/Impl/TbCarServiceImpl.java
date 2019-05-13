package com.tangmo.zhygzhglxt.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbBusMapper;
import com.tangmo.zhygzhglxt.dao.TbCarMapper;
import com.tangmo.zhygzhglxt.entity.Info;
import com.tangmo.zhygzhglxt.entity.TbCar;
import com.tangmo.zhygzhglxt.entity.dto.InfoDto;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbCarService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import com.tangmo.zhygzhglxt.utility.foreign.ForeignUtil;
import com.tangmo.zhygzhglxt.utility.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.*;

/**
 * Created by chengge on 2019/3/4.
 */
@Slf4j
@Service
public class TbCarServiceImpl implements TbCarService {

    @Autowired
    TbCarMapper tbCarMapper;  //同乡车辆
    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    public Result addCar(TbCar tbCar) {

        if (tbCar == null) {
            return new Result(ResultCode.FAIL, "信息不能为空！");
        }

        if (tbCar.getCarNumber() == null || "".equals(tbCar.getCarNumber())) {
            return new Result(ResultCode.FAIL, "车牌号不能为空！");
        }

        if (tbCar.getDeviceNumber() == null || "".equals(tbCar.getDeviceNumber())) {
            return new Result(ResultCode.FAIL, "设备号不能为空！");
        }
        tbCar.setId(EncryptUtil.get32Uuid());
        tbCarMapper.insertSelective(tbCar);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delCarById(String id) {

        if (id == null || "".equals(id)) {
            return new Result(ResultCode.FAIL, "主键不能为空！");
        }

        tbCarMapper.deleteByPrimaryKey(id);

        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result selAllTbCar(String name, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        List<TbCar> carList = tbCarMapper.selAllTbCar(name);

        return new Result(ResultCode.SUCCESS, new PageInfo(carList));
    }

    @Override
    public Result selCarByDeviceNumber(String deviceNumber) {

        if (deviceNumber == null || "".equals(deviceNumber)) {
            return new Result(ResultCode.FAIL, "设备号不能为空！");
        }

        TbCar carList = tbCarMapper.selCarByDeviceNumber(deviceNumber);

        return new Result(ResultCode.SUCCESS, carList);
    }

    @Override
    public Result selCarByCarNumber(String carNumber) {

        if (carNumber == null || "".equals(carNumber)) {
            return new Result(ResultCode.FAIL, "车牌号不能为空！");
        }

        TbCar carList = tbCarMapper.selCarByCarNumber(carNumber);

        return new Result(ResultCode.SUCCESS, carList);
    }

    @Override
    public Result getAllCarLocation() {
        List<TbCar> carList = tbCarMapper.selAllTbCar("%%");
        Double[][] strArr = new Double[carList.size()][];
        int i = 0;
        for (TbCar tbCar : carList) {
            String imei = tbCar.getDeviceNumber();
            JSONObject jsonObject = getLastInfoByImei(imei);
            if ( jsonObject == null ) {
                new Result(ResultCode.FAIL, "login不能为空！");
            }
            Object gpsinfo = jsonObject.get("gpsinfo");
            log.info("第三方获取到的gps数据:{}",gpsinfo);
            if (gpsinfo != null) {
                JSONObject gpsinfoJson = (JSONObject) JSONObject.toJSON(gpsinfo);
                Object baseInfo = gpsinfoJson.get("baseInfo");
                JSONObject baseInfoJson = (JSONObject) JSONObject.toJSON(baseInfo);
                Double relng = Double.parseDouble(baseInfoJson.get("relng").toString()) / 1000000;//经度
                Double relat = Double.parseDouble(baseInfoJson.get("relat").toString()) / 1000000;// 维度
                Double course = Double.parseDouble(baseInfoJson.get("course").toString());
                Double[] strs = {relng, relat, course};
                strArr[i] = strs;
                i++;
            }
        }
        return new Result(ResultCode.SUCCESS, strArr);
    }

    public JSONObject getLastInfoByImei(String imei) {
        String loginKey = "";
        if (!jedisKeys.exists("login")) {
            return null;
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("login");
        }
        InfoDto infoDto = new InfoDto();
        List<Info> infoList = new ArrayList<>();
        Info info = new Info();
        Map map = new HashMap<>();
        info.setId(4356);                    //消息id 获取最后一次上传的信息
        info.setSeqno(2);                    //唯一标识

        //查询开始
        map.put("imeis", imei);
        map.put("infotype", 4097);
        map.put("sn", "2");
        map.put("sessionid", loginKey);
        JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
        //查询结束
        info.setContent(json1.toJSONString());
        infoList.add(info);
        infoDto.setCommbases(infoList);
        JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
        JSONObject jsonObject = null;
        try {

            jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
            log.info("结束调用第三方接口: {}", new Date().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
