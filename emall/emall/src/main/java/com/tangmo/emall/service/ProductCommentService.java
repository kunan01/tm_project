package com.tangmo.emall.service;

import com.tangmo.emall.entity.ProductComment;
import com.tangmo.emall.utils.Result;

public interface ProductCommentService {

    //添加商品评论
    Result addComment(ProductComment productComment);

    //获取商品评论
    Result getCommentList(Integer sType,Integer productId,Integer pageNo,Integer pageSize,Integer userId);

    //点赞评论
    Result giveALike(Integer userId,Integer commentId);

    //获取评论排序方式
    Result getCommentSortingWay();

    //用户是否可以对当前商品进行评论
    Result canYouComment(Integer userId,Integer productId);



}
