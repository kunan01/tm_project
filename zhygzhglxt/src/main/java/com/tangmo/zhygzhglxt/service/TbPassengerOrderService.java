package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbPassengerOrder;
import com.tangmo.zhygzhglxt.entity.dto.OrderDto;
import com.tangmo.zhygzhglxt.utility.Result;

import java.util.Map;

/**
 * 乘客订单表
 */
public interface TbPassengerOrderService {

    Result addPassengerOrder(TbPassengerOrder tbPassengerOrder);

    Result selPassOrderByUserCode(String name, String state, String userCode, String endAbout, Integer pageSize, Integer pageNo);

    Result selOrderByCondition(OrderDto orderDto);

    Result selPassOrderByUserCodeAndType(String name, String userCode, String type, Integer pageSize, Integer pageNo);

    /**
     * 用户修改订单状态
     *
     * @param orderCode  订单唯一标识
     * @param orderState 订单状态（0未接单 1已接单 2订单完成 3订单已取消）
     * @return
     */
    Result jtUpdateById(String orderCode, String orderState);

    /**
     * 根据用户的唯一标识code查找是否有当前行程的订单
     */
    Result selOrderStateByUserCode(String userCode);

    /**
     * 根据用户订单的的唯一标识code查找订单详情
     */
    Result selOrderDetailByOrderCode(String passengerOrderCode);

    /**
     * 乘客端实时查询订单是否有被接单
     */
    Result selOrderStateByOrderCode(String passengerOrderCode);

    /**
     * 乘客端取消乘客订单
     */
    Result cancelByOrderCode(String passengerOrderCode);

    /**
     * 乘客对司机订单的点评（评价）
     */
    Result ratingByOrderCode(String passengerOrderCode, String opinion, String rating);

    /**
     * 根据乘客订单的唯一标识查询订单的时间和当前时间
     */
    Result selTimeByOrderCode(String passengerOrderCode);

    /**
     * 乘客端在被接单后实时查询该订单是否完成，是否是发起线上支付
     */
    Result selPayWayByOrderCode(String passengerOrderCode);

    /**
     * 订单统计：后台根据年月查询订单 实时单完成单量 预约单完成单量 取消的实时单量 取消的预约单单量
     */
    Result selOrderCountByDate(String timeDate);

    /**
     * 订单统计：后台根据年月查询订单 这个月内每天的用户的单量的统计和完成单量
     */
    Result selOrderCountListByDate(String timeDate);

    /**
     * 订单统计：根据开始时间和结束时间查询每日的订单统计量
     */
    Result selOrderByStartDateEndDate(String startDate, String endDate, Integer pageSize, Integer pageNo);

    /**
     * 订单统计：根据开始时间和结束时间且订单类型查询每日的通乡客运的订单统计量(列表)
     */
    Result selOrderByCountry(String name, String startDate, String endDate, String orderState, String carNumber, Integer pageSize, Integer pageNo);

    /**
     * 订单统计：后台根据年月查询订单的完成量和取消量
     */
    Result selOrderByDate(String timeDate);

    /**
     * 统计用户订单
     */
    Result totalUserOrder(Integer pageSize, Integer pageNo);


    /**
     * 后台根据查询所有订单
     *
     * @param name       模糊(开始地，结束地)
     * @param orderState 订单状态（0未接单 1已接单 2订单完成 3订单已取消）
     * @param endAbout   是否是预约单 （0是预约订单  1实时订单）
     * @param pageSize   每页几条数据
     * @param pageNo     当前页
     * @return
     */
    Result jtQueryByList(String name, String orderState, String endAbout, Integer pageSize, Integer pageNo);

    /**
     * 查询指定订单详情
     *
     * @param orderCode 订单唯一标识
     * @return
     */
    Result jtQueryByListByCode(String orderCode);

    /**
     * 删除指定订单（假删）
     *
     * @param orderCode 订单唯一标识
     * @return
     */
    Result jtDelete(String orderCode);

}
