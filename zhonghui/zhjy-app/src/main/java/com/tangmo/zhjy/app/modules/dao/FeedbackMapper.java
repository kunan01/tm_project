package com.tangmo.zhjy.app.modules.dao;

import com.tangmo.zhjy.app.modules.bean.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by chengge on 2018/6/8.
 */
@Mapper
public interface FeedbackMapper {

    //增加反馈
    int addFeedBack(Feedback feedback);

    //根据反馈id删除
    int deleteByPrimaryKey(Integer fbid);

    //查询所有反馈信息
    List<Feedback> serchListFeedback();
}
