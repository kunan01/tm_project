package com.tangmo.zhjy.system.modules.service;

import com.tangmo.zhjy.system.Result;
import com.tangmo.zhjy.system.modules.bean.Feedback;

/**
 * Created by chengge on 2018/6/8.
 */
public interface FeedbackService {

    //增加反馈信息
    Result addFeedBack(Feedback feedback);

    //根据反馈id删除
    Result deleteByPrimaryKey(Integer fbid);

    //分页查询所有反馈列表
    Result serchListFeedback(Integer pageNo, Integer pageSize);

}
