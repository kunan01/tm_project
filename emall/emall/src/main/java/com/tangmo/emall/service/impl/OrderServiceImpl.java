package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.config.RedisLock;
import com.tangmo.emall.dao.*;
import com.tangmo.emall.entity.*;
import com.tangmo.emall.service.OrderService;
import com.tangmo.emall.utils.OrderRelated;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private AddressDao addressDao;

    @Resource
    private PrescriptionDao prescriptionDao;

    @Resource
    private FileDao fileDao;

    @Autowired
    private RedisLock redisLock;

    @Override
    @Transactional
    public Result addOrder(OrderDetail orderDetail) {
        try {
            System.out.println(orderDetail);
            if(orderDetail == null || orderDetail.getProductId() == null || orderDetail.getSpecId() == null || orderDetail.getAddressId() == null ||
                    orderDetail.getUserId() == null || orderDetail.getProductPrice() == null || orderDetail.getProductCount() == null ||
                    orderDetail.getExpressId() == null){
                return ResultUtil.paramError();
            }

            //校验商品是否存在
            Product product = productDao.getProductById(orderDetail.getProductId());
            if(product == null){
                return ResultUtil.dataNoError();
            }
            if(product.getDataFlag().toString().equals("1") || !product.getAuditStatus().toString().equals("1")
                    || product.getPublishStatus().toString().equals("0")){
                return ResultUtil.dataNoError();
            }

            //校验规格是否存在
            ProductSpec productSpec = productDao.getProductSpecById(orderDetail.getSpecId());
            if(product == null){
                return ResultUtil.dataNoError();
            }
            if(productSpec.getDataFlag().toString().equals("1")){
                return ResultUtil.dataNoError();
            }

            //校验地址是否存在
            UserAddress userAddress = addressDao.getAddressById(orderDetail.getAddressId());
            if(userAddress == null){
                return ResultUtil.dataNoError();
            }
            if(userAddress.getDataStatus().toString().equals("0")){
                return ResultUtil.dataNoError();
            }

            //校验配送方式是否存在
            ShippingInfo shippingInfo = productDao.getStoreDistributionById(orderDetail.getExpressId());
            if(shippingInfo == null){
                return ResultUtil.dataNoError();
            }

            //如果有处方，校验处方信息
            if(orderDetail.getPrescriptionId() != null){
                Prescription prescription = prescriptionDao.getPrescriptionById(orderDetail.getPrescriptionId());
                if(prescription == null){
                    return ResultUtil.error("处方信息校验失败");
                }
            }

            if(orderDetail.getPrescriptionImage() == null || orderDetail.getPrescriptionImage().equals("")){
                orderDetail.setPrescriptionImage(null);
            }

            //如果有处方图片
            if(orderDetail.getPrescriptionImage() != null){
                //校验图片
                RsFile rsFile = fileDao.getFileById(orderDetail.getPrescriptionImage());

                if(rsFile == null){
                    return ResultUtil.imgError();
                }

                //修改图片为已用状态
                fileDao.updFile(orderDetail.getPrescriptionImage());
            }

            Double apiPrice = productSpec.getPrice() * 100;
            Double jdbPrice = orderDetail.getProductPrice() * 100;

            //校验数量以及价格
            if(apiPrice < jdbPrice){
                return ResultUtil.error("商品单价有误");
            }
            if(productSpec.getStock() < orderDetail.getProductCount()){
                return ResultUtil.error("商品库存不足");
            }
            if(orderDetail.getProductCount() < 1){
                return ResultUtil.error("输入数量有误");
            }

            //处方价格
            Double prescPrice = 0.0;

            if(orderDetail.getPrescriptionPrice() != null){
                prescPrice = orderDetail.getPrescriptionPrice();
            }

            //订单金额
            Double orderMoney = productSpec.getPrice() * orderDetail.getProductCount() + shippingInfo.getPrice() + prescPrice;

            //实付金额
            Double payMoney = orderDetail.getProductPrice() * orderDetail.getProductCount() + shippingInfo.getPrice() + prescPrice;

            //优惠金额
            Double discountMoney = orderMoney - payMoney;

            //添加订单
            Order order = new Order();
            order.setOrderSn(OrderRelated.getOrderNo());
            order.setUserId(orderDetail.getUserId());
            order.setConsignee(userAddress.getUserName());
            order.setAddressLine1(userAddress.getAddressLine1());
            order.setAddressLine2(userAddress.getAddressLine2());
            order.setCity(userAddress.getCity());
            order.setProvince(userAddress.getProvince());
            order.setZipCode(userAddress.getZipCode());
            order.setCountry(userAddress.getCountry());
            order.setUserPhone(userAddress.getUserPhone());
            order.setOrderMoney(orderMoney);
            order.setDiscountMoney(discountMoney); //优惠券金额
            order.setExpressMoney(shippingInfo.getPrice());  //运费
            order.setPayMoney(payMoney);

            orderDao.addOrder(order);

            OrderDetail detail = new OrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setProductId(product.getProductId());
            detail.setSpecId(productSpec.getSpecId());
            detail.setProductName(product.getProductName());
            detail.setProductCount(orderDetail.getProductCount());
            detail.setProductPrice(orderDetail.getProductPrice());
            detail.setPrescriptionPrice(prescPrice);

            orderDao.addOrderDetail(detail);

            //暂时性减少商品库存 当交易关闭时加回来
            productDao.destocking(productSpec.getSpecId(),orderDetail.getProductCount());

            return ResultUtil.success(detail.getDetailId());
        }catch (Exception e){
            log.error("订单模块: '生成订单'接口异常 ： "+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result cancelOrder(Integer detailId) {
        try {

            if(detailId == null){
                return ResultUtil.paramError();
            }

            //查询订单明细
            OrderDetail orderDetail = orderDao.getOrderDetailById(detailId);
            if(orderDetail == null){
                return ResultUtil.error("订单不存在");
            }

            //查询订单详情
            Order order = orderDao.getOrderById(orderDetail.getOrderId());
            if(order == null){
                return ResultUtil.error("订单不存在");
            }

            if(order.getOrderStatus().toString().equals("0")) {
                //取消订单
                orderDao.cancelOrder(orderDetail.getOrderId());

                //还原商品库存
                productDao.increaseInventory(orderDetail.getSpecId(),orderDetail.getProductCount());

            }else if(order.getOrderStatus().toString().equals("-4")){

                return ResultUtil.error("该订单已失效");
            }else if(order.getOrderStatus().toString().equals("-3")){

                return ResultUtil.error("该订单已取消");
            }else{

                return ResultUtil.error("该订单正在操作，目前不能取消！");
            }

            return ResultUtil.success("成功取消订单");
        }catch (Exception e){
            log.error("订单模块: '取消订单'接口异常 ： "+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result deleteOrder(Integer detailId) {
        try {

            if(detailId == null){
                return ResultUtil.paramError();
            }

            //查询订单明细
            OrderDetail orderDetail = orderDao.getOrderDetailById(detailId);
            if(orderDetail == null){
                return ResultUtil.error("订单不存在");
            }

            //查询订单详情
            Order order = orderDao.getOrderById(orderDetail.getOrderId());
            if(order == null){
                return ResultUtil.error("订单不存在");
            }

            if(order.getOrderStatus().toString().equals("-2") || order.getOrderStatus().toString().equals("1") ||
                    order.getOrderStatus().toString().equals("2")) {

                return ResultUtil.error("该订单正在操作，目前不能删除！");
            }else if(order.getOrderStatus().toString().equals("4")) {

                return ResultUtil.error("该订单已删除！");
            }else{

                //删除订单
                orderDao.deleteOrder(orderDetail.getOrderId());
            }
            return ResultUtil.success("成功删除订单");
        }catch (Exception e){
            log.error("订单模块: '删除订单'接口异常 ： "+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result queryOrderList(Integer userId, Integer state, Integer pageNo, Integer pageSize) {
        try {

            if(userId == null || state == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            PageHelper.startPage(pageNo,pageSize);

            PageInfo<OrderDetail> pageInfo = new PageInfo<>(orderDao.getOrderListByUserId(userId,state));

            return ResultUtil.success(pageInfo);
        }catch (Exception e){
            log.error("订单模块: '查询订单列表'接口异常 ： "+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getOrderDetailById(Integer detailId) {
        try {

            if(detailId == null){
                return ResultUtil.paramError();
            }

            OrderDetail orderDetail = orderDao.getOrderDetailAllById(detailId);

            return ResultUtil.success(orderDetail);
        }catch (Exception e){
            log.error("订单模块: '查询订单详情'接口异常 ： "+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result ConfirmOrder(Integer detailId) {
        try {

            if(detailId == null){
                return ResultUtil.paramError();
            }

            //查询订单明细
            OrderDetail orderDetail = orderDao.getOrderDetailById(detailId);
            if(orderDetail == null){
                return ResultUtil.error("订单不存在");
            }

            //查询订单详情
            Order order = orderDao.getOrderById(orderDetail.getOrderId());
            if(order == null){
                return ResultUtil.error("订单不存在");
            }

            if(order.getOrderStatus().toString().equals("2")) {
                //确认收货
                orderDao.ConfirmOrder(orderDetail.getOrderId());
            }else{

                return ResultUtil.error("该订单当前状态不能进行收货");
            }
            return ResultUtil.success("收货成功");
        }catch (Exception e){
            log.error("订单模块: '确认收货'接口异常 ： "+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getPayInformation(String detailId) {
        try {

            if(detailId == null || detailId.equals("")){
                return ResultUtil.paramError();
            }

            String[] strs = detailId.split(",");

            //实付金额
            Double payMoney = null;

            String orderNo = null;

            for (int i = 0;i<strs.length;i++){
                OrderDetail orderDetail = orderDao.getOrderDetailAllById(Integer.parseInt(strs[i]));

                if(payMoney == null){
                    payMoney = orderDetail.getOrder().getPayMoney();
                }else{
                    payMoney = payMoney + orderDetail.getOrder().getPayMoney();
                }

                if(orderNo == null){
                    orderNo = orderDetail.getOrder().getOrderSn();
                }else{
                    orderNo = orderNo + "," + orderDetail.getOrder().getOrderSn();
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("money",payMoney);
            map.put("orderNo",orderNo);
            return ResultUtil.success(map);
        }catch (Exception e){
            log.error("订单模块: '获取支付页面信息'接口异常 ： "+e.getMessage());
            return ResultUtil.serviceError();
        }

    }
}
