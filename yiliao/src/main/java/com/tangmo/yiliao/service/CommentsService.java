package com.tangmo.yiliao.service;

import com.tangmo.yiliao.entity.Comments;
import com.tangmo.yiliao.utility.code.Result;

/**
 * @author hanjialin
 * @date 2018-9-12
 * @description
 */
public interface CommentsService {

    //添加评论
    Result addComments(Comments comments);

    //通过评论id查询所有回复
    Result getCommentsDById(String cId);

    //通过文章或者视频id查询所有评论以及3条回复
    Result getCommentsALLWById(String id,Integer type);
}
