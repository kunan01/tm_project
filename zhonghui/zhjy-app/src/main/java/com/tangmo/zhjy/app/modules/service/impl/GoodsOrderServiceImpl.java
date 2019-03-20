package com.tangmo.zhjy.app.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.OrderConst;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.Commodity;
import com.tangmo.zhjy.app.modules.bean.GoodsOrder;
import com.tangmo.zhjy.app.modules.bean.GoodsOrderSimple;
import com.tangmo.zhjy.app.modules.bean.ShopCart;
import com.tangmo.zhjy.app.modules.dao.CommodityDao;
import com.tangmo.zhjy.app.modules.dao.GoodsOrderDao;
import com.tangmo.zhjy.app.modules.service.GoodsOrderService;
import com.tangmo.zhjy.app.modules.vo.ExpressVO;
import com.tangmo.zhjy.app.utils.OrderRelated;
import com.tangmo.zhjy.app.utils.SearchExpress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author boge
 * @date 18/4/4
 * @description
 */
@Service("goodsOrderService")
public class GoodsOrderServiceImpl implements GoodsOrderService {
    @Resource
    private CommodityDao commodityDao;
    @Resource
    private GoodsOrderDao goodsOrderDao;

    @Override
    public Result addOrder(GoodsOrderSimple goodsOrderSimple) {
        String orderNo = OrderRelated.getOrderNo(goodsOrderSimple.getUserId());

        goodsOrderSimple.setOrderNumber(orderNo);
        goodsOrderSimple.setOrderState(OrderConst.ORDER_NEW);
        Commodity commodity = commodityDao.selectCommodityDetail(goodsOrderSimple.getCdId());
        if(commodity == null){
            return new Result(ResultCode.INTERNAL_ERROR);
        }
        if(commodity.getPriceNow() == null){
            return new Result(ResultCode.INTERNAL_ERROR);
        }
        goodsOrderSimple.setPayFee(goodsOrderSimple.getGoCount() * commodity.getPriceNow());
        goodsOrderDao.insertGo(goodsOrderSimple);
        if(goodsOrderSimple.getScId() != null){
            goodsOrderDao.delCartById(goodsOrderSimple.getScId());
        }
        return new Result(ResultCode.SUCCESS,goodsOrderSimple.getGoId());
    }

    @Override
    public Result searchOrderByState(Byte state, Integer userId, Integer start, Integer end) {
        PageHelper.startPage(start,end);
        return new Result(ResultCode.SUCCESS,new PageInfo(goodsOrderDao.selectOrderByState(state,userId)));
    }

    @Override
    public Result searchAllOrder(Integer userId, Integer start, Integer end) {
        PageHelper.startPage(start,end);
        return new Result(ResultCode.SUCCESS,new PageInfo(goodsOrderDao.selectAllOrder(userId)));
    }

    @Override
    public Result searchOrderDetail(Integer goId) {
        return new Result(ResultCode.SUCCESS,goodsOrderDao.selectById(goId));
    }

    @Override
    public Result payOrder(Integer goId) {
        return null;
    }

    @Override
    public Result delOrder(Integer goId) {
        return new Result(ResultCode.SUCCESS,goodsOrderDao.deleteById(goId));
    }

    @Override
    public Result changeOrderState(Integer goId, Byte state) {
        return new Result(ResultCode.SUCCESS,goodsOrderDao.updateOrderState(goId, state));
    }

    @Override
    public Result searchExpress(Integer goId) {
        GoodsOrder goodsOrder = goodsOrderDao.selectById(goId);
        if(goodsOrder == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        if (goodsOrder.getOrderState() < 2){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        if(goodsOrder.getExpressNo() == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        ExpressVO expressVO = SearchExpress.Get(goodsOrder.getExpressNo());
        if(expressVO.getMsg() != null){
            return new Result(ResultCode.FAIL,expressVO.getMsg());
        }
        expressVO.setMsg("");
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result addCart(ShopCart shopCart) {
        if(shopCart == null || shopCart.getcId() == null || shopCart.getScCount() == null
            || shopCart.getUserId() == null || shopCart.getCdSize() == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        ShopCart shopCart1 = goodsOrderDao.getShopCarByCdIdAndUserId(shopCart.getcId(),shopCart.getUserId());
        if(shopCart1 != null){
            int count = 0;
            int kucun = goodsOrderDao.getCommCountByCartId(shopCart1.getScId());
            if((shopCart1.getScCount()+shopCart.getScCount()) > kucun){
                count = kucun;
            }else{
                count = shopCart1.getScCount()+shopCart.getScCount();
            }
            goodsOrderDao.updCartCountById(shopCart1.getScId(),count);
            return new Result(ResultCode.SUCCESS);
        }
        goodsOrderDao.addCart(shopCart);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result delCartById(Integer scId) {
        if(scId == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        goodsOrderDao.delCartById(scId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result updCartCountById(Integer scId, Integer count) {
        if(scId == null || count == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        if(count <= 0){
            return new Result(10098,"购买数量不能小于0","");
        }
        if(goodsOrderDao.getCommCountByCartId(scId) < count){
            return new Result(10099,"库存不足","");
        }
        goodsOrderDao.updCartCountById(scId,count);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result getCartList(Integer userId) {
        if(userId == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        List<ShopCart> shopCarts = goodsOrderDao.getCartList(userId);
        if(shopCarts != null){
            for (int i = 0;i<shopCarts.size();i++){
                shopCarts.get(i).setCommodity(commodityDao.selectCommodityDetail(shopCarts.get(i).getcId()));
            }
        }
        return new Result(ResultCode.SUCCESS,shopCarts);
    }
}
