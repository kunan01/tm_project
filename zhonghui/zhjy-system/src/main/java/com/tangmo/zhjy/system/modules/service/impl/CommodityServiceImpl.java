package com.tangmo.zhjy.system.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.enums.ResultCode;
import com.tangmo.zhjy.system.modules.bean.Commodity;
import com.tangmo.zhjy.system.modules.bean.Log;
import com.tangmo.zhjy.system.modules.bean.ShopVerify;
import com.tangmo.zhjy.system.modules.dao.AppUserBeanMapper;
import com.tangmo.zhjy.system.modules.dao.CommodityDao;
import com.tangmo.zhjy.system.modules.dao.LogMapper;
import com.tangmo.zhjy.system.modules.dao.SysShopMapper;
import com.tangmo.zhjy.system.modules.service.CommodityService;
import com.tangmo.zhjy.system.modules.vo.CommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chengge
 * @date 18/4/4
 * @description
 */
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {
    @Resource
    private CommodityDao commodityDao;


    @Resource
    private AppUserBeanMapper appUserBeanMapper;

    @Resource
    private SysShopMapper sysShopMapper;


    @Override
    public Result addCommodity(Commodity commodity) {
        if (commodity.getPriceNow() == null || commodity.getCdType() == null) {
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        if (commodity.getUserId() == null) {
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        if(commodity.getImgId() == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        commodity.setState(1);
        commodityDao.insertSelective(commodity);
        return new Result(ResultCode.SUCCESS);
    }


    @Override
    public Result changeCommodity(Integer state,Integer cdId) {


        //logMapper.insertSelective()
        commodityDao.updateById(state,cdId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result isQuality(Integer isQuality,Integer cdId) {
        commodityDao.isQuality(isQuality,cdId);
        Map map=new HashMap();
        if(isQuality==1){
            map.put("isQuality",1);
            return new Result(ResultCode.SUCCESS,map);
        }else{
            map.put("isQuality",0);
            return new Result(ResultCode.SUCCESS,map);
        }

    }

    @Override
    public Result searchCdList(Integer cdType,Integer state, Integer pageSize, Integer pageNo,String name) {

        if(name.equals("nameSystem")){
            name = null;
        }

        if(state==3){
            state=null;
        }
        if(cdType==3){
            cdType=null;
        }
        if(pageSize!=null && pageNo!=null){
            PageHelper.startPage(pageNo, pageSize);
        }

        List<CommodityVO> commodityvos =commodityDao.selectList(cdType,state,name);

        for(CommodityVO commodityVO:commodityvos){
            commodityVO.setUsername(appUserBeanMapper.selectByPrimaryKey(commodityVO.getUserId()).getNikeName());
            if(cdType != null && cdType != 2){
                ShopVerify shopVerify=sysShopMapper.selectByUserId(commodityVO.getUserId());
                String names= shopVerify.getShopName();
                String shopLog=shopVerify.getLogoImg();
                commodityVO.setShop_name(names);
                commodityVO.setShopLog(shopLog);
            }
            if(commodityVO.getPhone()==null){
                commodityVO.setPhone(appUserBeanMapper.selectByPrimaryKey(commodityVO.getUserId()).getPhone());
            }
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(commodityvos) );
    }

    @Override
    public Result delCommodity(Integer cdId) {
        commodityDao.deleteById(cdId);
        return new Result(ResultCode.SUCCESS);
    }

    /*
* 个人名称已改为商铺名称
* 实体类加了一个shop_name属性（Commodity）
* */
    @Override
    public Result getCommodityDetail(Integer cdId) {
        Commodity commodity=commodityDao.selectCommodityDetail(cdId);
        String name= sysShopMapper.selectByUserId(commodity.getUserId()).getShopName();
        commodity.setShop_name(name);
        if(commodity.getPhone()==null){
            commodity.setPhone(appUserBeanMapper.selectByPrimaryKey(commodity.getUserId()).getPhone());
        }
        if("1".equals(commodity.getCondition())){
            commodity.setDegree("一成新");
        }else if("2".equals(commodity.getCondition())){
            commodity.setDegree("二成新");
        }else if("3".equals(commodity.getCondition())){
            commodity.setDegree("三成新");
        }else if("4".equals(commodity.getCondition())){
            commodity.setDegree("四成新");
        }else if("5".equals(commodity.getCondition())){
            commodity.setDegree("五成新");
        }else if("6".equals(commodity.getCondition())){
            commodity.setDegree("六成新");
        }else if("7".equals(commodity.getCondition())){
            commodity.setDegree("七成新");
        }else if("8".equals(commodity.getCondition())){
            commodity.setDegree("八成新");
        }else{
            commodity.setDegree("九成新");
        }
        return new Result(ResultCode.SUCCESS,commodity);
    }

}

