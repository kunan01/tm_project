package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbDriverOrder;
import com.tangmo.zhygzhglxt.entity.dto.OrderDto;
import com.tangmo.zhygzhglxt.utility.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 车主service
 */

public interface TbDriverOrderService {

    Result addDriverOrder(TbDriverOrder tbDriverOrder);

    Result updateOrderPayWay(OrderDto orderDto);

    Result selStateByDriverOrderCode(String driverOrderCode);

    /**
     * 改变车主订单状态
     *
     * @param state
     * @param code
     * @return
     */
    Result updateDriverOrderState(String state, String code);


    /**
     * 车主查询乘客所有单子（预约单 实时单）
     *
     * @param endAbout   0预约单 1实时单
     * @param orderState 0未接单 1已接单 2订单完成 3订单已取消
     * @return
     * @Param tbParmId  网约车1015，货车1016,班车1017
     * @Param userCode  用户的唯一标识code
     */
    Result jtQuery(String endAbout, String orderState, String tbParmId, String userCode);


    /**
     * 车主查询单子详情
     *
     * @param driverOrderCode 司机订单唯一标识
     * @return
     */
    Result jtQueryOrderCode(String driverOrderCode);

    /**
     * 根据司机订单的唯一标识driverCde查询同乡客运的订单路线路
     *
     * @param driverOrderCode 司机订单唯一标识
     * @return
     */
    Result selectByDriverOrderCode(String driverOrderCode);


    /**
     * 车主查询自己所有单子状态
     *
     * @param driverId         车主id
     * @param driverOrderState 订单状态（0 未完成  1完成  2 取消）
     * @return
     */
    Result jtQyeryById(String driverId, String driverOrderState);

}
