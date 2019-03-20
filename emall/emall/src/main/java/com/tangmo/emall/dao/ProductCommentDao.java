package com.tangmo.emall.dao;

import com.tangmo.emall.entity.ProductComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chamber
 * @date 2019/1/9.
 * @Description
 */
@Repository
public interface ProductCommentDao {

    //添加商品评论
    int addComment(ProductComment productComment);

    //通过商品id查询商品评论
    List<ProductComment> getCommentList(@Param("sortingRules") String sortingRules,@Param("productId") Integer productId,@Param("userId") Integer userId);

    //增加点赞数量
    int giveALike(Integer commentId);

    //判断用户使用已经对当前评论点赞
    Integer getUserGiveCount(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    //增加点赞记录
    int addGive(@Param("userId") Integer userId,@Param("commentId") Integer commentId);

    //用户是否可以对当前商品进行评论 返回订单号
    String canYouComment(@Param("userId") Integer userId,@Param("productId") Integer productId);

}
