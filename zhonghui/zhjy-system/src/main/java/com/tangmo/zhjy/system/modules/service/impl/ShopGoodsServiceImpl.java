package com.tangmo.zhjy.system.modules.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.ShopGoods;
import com.tangmo.zhjy.system.modules.dao.AppUserBeanMapper;
import com.tangmo.zhjy.system.modules.dao.ShopGoodsDao;
import com.tangmo.zhjy.system.modules.service.ShopGoodsService;
import com.tangmo.zhjy.system.modules.vo.SimpleShopGoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    private AppUserBeanMapper appUserBeanMapper;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result changeShopService(ShopGoods shopGoods) {
        shopGoodsDao.updateById(shopGoods);
        return new Result(ResultCode.SUCCESS);

    }

    @Override
    public Result selectByState(Integer state,Integer pageSize, Integer pageNo,String name) {

        if(name.equals("nameSystem")){
            name = null;
        }

        if(pageSize!=null && pageNo!=null){
            PageHelper.startPage(pageNo, pageSize);
        }
        List<SimpleShopGoodsVO> SimpleShopGoodss=shopGoodsDao.selectByState(state,name);
        for(SimpleShopGoodsVO simpleShopGoodsVO:SimpleShopGoodss){
            simpleShopGoodsVO.setUserName(appUserBeanMapper.selectByPrimaryKey(simpleShopGoodsVO.getUserId()).getNikeName());
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(SimpleShopGoodss));
    }

    @Override
    public Result searchUserService(Integer userId, Integer start, Integer end) {
        return new Result(ResultCode.SUCCESS,shopGoodsDao.selectUserShopGoods(userId, start, end));
    }

    @Override
    public Result searchServiceDetail(Integer sgId) {
        return new Result(ResultCode.SUCCESS,shopGoodsDao.selectById(sgId));
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

    /**
     * 审核店铺服务信息
     *
     * @param sgId
     * @return
     */
    @Override
    public Result audit(Integer state,Integer sgId) {
        shopGoodsDao.auditById(state,sgId);
        return new Result(ResultCode.SUCCESS);
    }


}

