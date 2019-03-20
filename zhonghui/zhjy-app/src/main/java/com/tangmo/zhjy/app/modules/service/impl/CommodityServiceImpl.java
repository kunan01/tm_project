package com.tangmo.zhjy.app.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.Commodity;
import com.tangmo.zhjy.app.modules.bean.GoodsComment;
import com.tangmo.zhjy.app.modules.dao.AppUserBeanMapper;
import com.tangmo.zhjy.app.modules.dao.CommodityDao;
import com.tangmo.zhjy.app.modules.dao.ShopVerifyDao;
import com.tangmo.zhjy.app.modules.dto.CommodityDto;
import com.tangmo.zhjy.app.modules.service.CommodityService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author boge
 * @date 18/4/4
 * @description
 */
@Service("commodityService")
public class CommodityServiceImpl implements CommodityService{
    @Resource
    private CommodityDao commodityDao;

    @Resource
    private AppUserBeanMapper appUserBeanMapper;

    @Resource
    private ShopVerifyDao shopVerifyDao;

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
        commodityDao.insertSelective(commodity);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result addCdImg(Integer userId, MultipartFile file) {
        return null;
    }

    @Override
    public Result changeCommodity(Commodity commodity) {

        Commodity commodity1 = commodityDao.selectCommodityDetail(commodity.getCdId());
        if(commodity1 != null){
            commodity.setCdType(commodity1.getCdType());
            commodityDao.updateById(commodity);
        }
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 获取2手商品列表
     *
     * @param start  开始索引
     * @param end    结束索引
     * @return
     */
    @Override
    public Result selectListTwo(Integer start, Integer end) {
        PageHelper.startPage(start,end);
        List<Commodity> list = commodityDao.selectListTwo();

        return new Result(ResultCode.SUCCESS,new PageInfo(list));
    }

    @Override
    public Result searchCdList(Integer start, Integer end) {

        PageHelper.startPage(start,end);

        List<Commodity> list=commodityDao.selectList();

        return new Result(ResultCode.SUCCESS,new PageInfo(list));
    }

    @Override
    public Result searchUserCdList(Integer userId, Integer start, Integer end) {
        PageHelper.startPage(start, end);
        List<Commodity> commodityList = commodityDao.selectListByUserId(userId);

        return new Result(ResultCode.SUCCESS,new PageInfo(commodityList));
    }

    @Override
    public Result delCommodity(Integer cdId) {
        commodityDao.deleteById(cdId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result getComments(Integer goodsId,Integer start, Integer end) {
        return new Result(ResultCode.SUCCESS,commodityDao.selectCommentByGoodsId(goodsId,(start-1)*end,end));
    }

    @Override
    public Result addComment(GoodsComment goodsComment) {
        commodityDao.insertComment(goodsComment);
        return new Result(ResultCode.SUCCESS);
    }



/*
* 个人名称已改为商铺名称
* 实体类加了一个shop_name属性（Commodity）
* */
    @Override
    public Result getCommodityDetail(Integer cdId) {
        Commodity commodity=commodityDao.selectCommodityDetail(cdId);

        if(commodity.getCdType().toString().equals("1")){
            String name = shopVerifyDao.selectByUserId(commodity.getUserId()).getShopName();
            commodity.setShop_name(name);
        }
        commodity.setCdName(commodityDao.getCommentClassTypeById(commodity.getCdClass()).getcName());

//
//        if("1".equals(commodity.getCondition())){
//            commodity.setDegree("一成新");
//        }else if("2".equals(commodity.getCondition())){
//            commodity.setDegree("二成新");
//        }else if("3".equals(commodity.getCondition())){
//            commodity.setDegree("三成新");
//        }else if("4".equals(commodity.getCondition())){
//            commodity.setDegree("四成新");
//        }else if("5".equals(commodity.getCondition())){
//            commodity.setDegree("五成新");
//        }else if("6".equals(commodity.getCondition())){
//            commodity.setDegree("六成新");
//        }else if("7".equals(commodity.getCondition())){
//            commodity.setDegree("七成新");
//        }else if("8".equals(commodity.getCondition())){
//            commodity.setDegree("八成新");
//        }else{
//            commodity.setDegree("九成新");
//        }
        return new Result(ResultCode.SUCCESS,commodity);
    }






    @Override
    public Result selectByCondition(CommodityDto commodityDto) {

        if("".equals(commodityDto.getCity()) || "".equals(commodityDto.getDistrict())){
            commodityDto.setCity(null);
            commodityDto.setDistrict(null);
        }

        if(commodityDto.getStart()!=null && commodityDto.getEnd()!=null){
            PageHelper.startPage(commodityDto.getStart(),commodityDto.getEnd());
        }

        List<Commodity> commodityList= commodityDao.selectCdByCondition(commodityDto);
        for (int i = 0;i<commodityList.size();i++){
            commodityList.get(i).setCdName(commodityDao.getCommentClassTypeById(commodityList.get(i).getCdClass()).getcName());
            Integer sellCount = commodityDao.getCommentSellCount(commodityList.get(i).getCdId());
            if(sellCount == null){
                sellCount = 0;
            }
            commodityList.get(i).setSellCount(sellCount);
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(commodityList));

    }

    @Override
    public Result addCdStar(Integer cdId, Integer userId) {
        commodityDao.updateCdStar(cdId);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result changeCdState(Integer cdId, Byte state) {
        commodityDao.updateCdState(cdId, state);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result searchSellRecord(Integer userId, Byte state, Integer start, Integer end) {
        PageHelper.startPage(start, end);
        return new Result(ResultCode.SUCCESS,new PageInfo(commodityDao.selectCdManageRecord(userId, state)));
    }

    @Override
    public Result getCommentClassType() {
        return new Result(ResultCode.SUCCESS,commodityDao.getCommentClassType(1));
    }

    @Override
    public Result getCommentClassTwoType() {
        return new Result(ResultCode.SUCCESS,commodityDao.getCommentClassType(2));
    }

    @Override
    public Result getCommentListByCId(Integer type,Integer cId, Integer pageNo, Integer pageSize,String district) {
        if(type == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        PageHelper.startPage(pageNo, pageSize);
        List<Commodity> commodityList = commodityDao.getCommentListByCId(type,cId,district);
        for (int i = 0;i<commodityList.size();i++){
            commodityList.get(i).setCdName(commodityDao.getCommentClassTypeById(commodityList.get(i).getCdClass()).getcName());
            Integer sellCount = commodityDao.getCommentSellCount(commodityList.get(i).getCdId());
            if(sellCount == null){
                sellCount = 0;
            }
            commodityList.get(i).setSellCount(sellCount);
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(commodityList));
    }

    @Override
    public Result getCommentRecommended(Integer cId) {
        if(cId == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        int a = 0;
        List<Commodity> commodityList = commodityDao.getCommentRECByShop(cId,1);
        List<Commodity> commodityLists = new ArrayList<>();
        if(commodityList.size() > 4){
            a = commodityList.size() - 4;
            Random ra =new Random();
            a = ra.nextInt(a);
            for (int i = 0;i < 4;i++){
                commodityLists.add(commodityList.get(a+1));
            }
            return new Result(ResultCode.SUCCESS,commodityLists);
        }else{
            return new Result(ResultCode.SUCCESS,commodityList);
        }
    }

    @Override
    public Result getCommentRecommendedByTwo(Integer cId) {
        if(cId == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        int a = 0;
        List<Commodity> commodityList = commodityDao.getCommentRECByShop(cId,2);
        List<Commodity> commodityLists = new ArrayList<>();
        if(commodityList.size() > 4){
            a = commodityList.size() - 4;
            Random ra =new Random();
            a = ra.nextInt(a);
            for (int i = 0;i < 4;i++){
                commodityLists.add(commodityList.get(a+1));
            }
            return new Result(ResultCode.SUCCESS,commodityLists);
        }else{
            return new Result(ResultCode.SUCCESS,commodityList);
        }
    }

    @Override
    public Result getDisByCity(String city,Integer type) {
        if(city == null){
            return new Result(ResultCode.PAPAMETE_ERROR);
        }
        return new Result(ResultCode.SUCCESS,commodityDao.getDisByCity(city,type));
    }
}
