package com.tangmo.zhjy.app.modules.service;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.Commodity;
import com.tangmo.zhjy.app.modules.bean.GoodsComment;
import com.tangmo.zhjy.app.modules.dto.CommodityDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author boge
 * @date 18/4/4
 * @description 商品服务类
 */

public interface CommodityService {
    /**
     * 增加商品信息
     *
     * @param commodity 商品实体
     * @return
     */
    Result addCommodity(Commodity commodity);

    /**
     * 上传商品图片
     *
     * @param userId
     * @param file
     * @return
     */
    Result addCdImg(Integer userId, MultipartFile file);

    /**
     * 修改商品信息
     *
     * @param commodity
     * @return
     */
    Result changeCommodity(Commodity commodity);




    /**
     * 获取商品列表
     *
     * @param start  开始索引
     * @param end    结束索引
     * @return
     */
    Result searchCdList(Integer start, Integer end);



    /**
     * 获取2手商品列表
     *
     * @param start  开始索引
     * @param end    结束索引
     * @return
     */
    Result selectListTwo(Integer start, Integer end);


    /**
     * 查询指定用户的商品信息
     *
     * @param userId
     * @param start
     * @param end
     * @return
     */
    Result searchUserCdList(Integer userId, Integer start, Integer end);

    /**
     * 删除指定商品信息
     *
     * @param cdId
     * @return
     */
    Result delCommodity(Integer cdId);

    /**
     * 获取商品评论列表
     *
     * @param goodsId    商品表主键
     * @param start      开始索引
     * @param end        结束索引
     * @return
     */
    Result getComments(Integer goodsId,Integer start, Integer end);

    /**
     * 增加评论
     *
     * @param goodsComment
     * @return
     */
    Result addComment(GoodsComment goodsComment);

    /**
     * 得到商品详细信息
     *
     * @param cdId
     * @return
     */
    Result getCommodityDetail(Integer cdId);

    /**
     * 根据条件筛选商品
     *
     * @param commodityDto
     * @return
     */
    Result selectByCondition(CommodityDto commodityDto);

    /**
     * 商品好评加1
     *
     * @param cdId   商品Id
     * @param userId 用户Id
     * @return
     */
    Result addCdStar(Integer cdId, Integer userId);

    /**
     * 更改商品上下架状态
     *
     * @param cdId
     * @param state
     * @return
     */
    Result changeCdState(Integer cdId, Byte state);

    /**
     * 查询上架/下架商品销售记录
     *
     * @param userId
     * @param state
     * @param start
     * @param end
     * @return
     */
    Result searchSellRecord(Integer userId, Byte state, Integer start, Integer end);


    //获取商城商品分类
    Result getCommentClassType();

    //获取二手商品分类
    Result getCommentClassTwoType();

    //通过分类id获取商品集合
    Result getCommentListByCId(Integer type,Integer cId,Integer pageNo,Integer pageSize,String district);

    Result getCommentRecommended(Integer cId);

    Result getCommentRecommendedByTwo(Integer cId);

    Result getDisByCity(String city,Integer type);
}
