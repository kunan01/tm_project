package com.tangmo.zhjy.app.modules.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.ShopGoods;
import com.tangmo.zhjy.app.modules.dao.CommodityDao;
import com.tangmo.zhjy.app.modules.dao.ShopGoodsDao;
import com.tangmo.zhjy.app.modules.dto.ShopServiceDto;
import com.tangmo.zhjy.app.modules.service.ShopGoodsService;
import com.tangmo.zhjy.app.modules.vo.ShopServiceVO;
import com.tangmo.zhjy.app.modules.vo.SimpleShopGoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author boge
 * @date 18/1/2
 * @description 店铺商品信息服务实现类
 */
@Service("shopGoodsService")
public class ShopGoodsServiceImpl implements ShopGoodsService {

    @Resource
    private ShopGoodsDao shopGoodsDao;

    @Resource
    private CommodityDao commodityDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addShopService(ShopGoods shopGoods) {

        if (shopGoods.getUserId() == null) {
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        if(shopGoods.getShopClassType() == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        shopGoodsDao.insertSelective(shopGoods);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result changeShopService(ShopGoods shopGoods) {
        if(shopGoods == null || shopGoods.getSgId() == null || shopGoods.getShopClassType() == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        shopGoodsDao.updateById(shopGoods);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result searchServiceByType(Integer type, String district, Integer start, Integer end,Integer isTime) {
        PageHelper.startPage(start, end);
        List<SimpleShopGoodsVO> simpleShopGoodsVOS = shopGoodsDao.selectByType(type, district,isTime);
        for(int i = 0;i<simpleShopGoodsVOS.size();i++){
            simpleShopGoodsVOS.get(i).setShopClassTypeName(commodityDao.getCommentClassTypeById(simpleShopGoodsVOS.get(i).getShopClassType()).getcName());
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(simpleShopGoodsVOS));
    }

    @Override
    public Result getServiceClassType() {
        return new Result(ResultCode.SUCCESS,commodityDao.getCommentClassType(3));
    }

    @Override
    public Result searchUserService(Integer userId, Integer start, Integer end) {
        PageHelper.startPage(start, end);
        List<SimpleShopGoodsVO> list = shopGoodsDao.selectUserShopGoods(userId);
        for (int i = 0;i<list.size();i++){
            list.get(i).setCdName(commodityDao.getCommentClassTypeById(list.get(i).getShopClassType()).getcName());
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(list));
    }

    @Override
    public Result searchServiceDetail(Integer sgId) {
        ShopServiceVO shopServiceVO = shopGoodsDao.selectById(sgId);
        shopServiceVO.setShopClassTypeName(commodityDao.getCommentClassTypeById(shopServiceVO.getShopClassType()).getcName());
        return new Result(ResultCode.SUCCESS,shopServiceVO);
    }

    @Override
    public Result searchByCondition(ShopServiceDto shopServiceDto) {
        if(shopServiceDto.getType() == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        return new Result(ResultCode.SUCCESS,shopGoodsDao.selectByCondition(shopServiceDto));
    }

    /**
     * 删除店铺服务信息
     *
     * @param sgId
     * @return
     */
    @Override
    public Result deleteById(Integer sgId) {
        shopGoodsDao.deleteById(sgId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result getRECService(Integer sgId) {
        if (sgId == null ) {
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        int a = 0;
        List<SimpleShopGoodsVO> simpleShopGoodsVOS = shopGoodsDao.getRECService(sgId);
        List<SimpleShopGoodsVO> simpleShopGoodsVOList = new ArrayList<>();
        if(simpleShopGoodsVOS.size() > 4){
            a = simpleShopGoodsVOS.size() - 4;
            Random ra =new Random();
            a = ra.nextInt(a);
            for (int i = 0;i < 4;i++){
                simpleShopGoodsVOList.add(simpleShopGoodsVOS.get(a+1));
            }
            return new Result(ResultCode.SUCCESS,simpleShopGoodsVOList);
        }else{
            return new Result(ResultCode.SUCCESS,simpleShopGoodsVOS);
        }
    }

    @Override
    public Result getDisByCity(String city) {

        if(city == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }

        return new Result(ResultCode.SUCCESS,shopGoodsDao.getDisByCity(city));
    }
}

