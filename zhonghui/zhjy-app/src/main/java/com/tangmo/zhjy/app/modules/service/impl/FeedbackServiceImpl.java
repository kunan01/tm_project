package com.tangmo.zhjy.app.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.Feedback;
import com.tangmo.zhjy.app.modules.dao.FeedbackMapper;
import com.tangmo.zhjy.app.modules.service.FeedbackService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chengge on 2018/6/8.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackMapper feedbackMapper;

    Logger logger = Logger.getLogger(FeedbackServiceImpl.class);

    @Override
    public Result addFeedBack(Feedback feedback) {

        try{
            feedbackMapper.addFeedBack(feedback);
            return new Result(ResultCode.SUCCESS);
        }catch(Exception e){
            logger.info("添加反馈信息异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @Override
    public Result deleteByPrimaryKey(Integer fbid) {

        try{
            feedbackMapper.deleteByPrimaryKey(fbid);
            return new Result(ResultCode.SUCCESS);
        }catch(Exception e){
            logger.info("删除反馈信息异常："+e);
            return new Result(ResultCode.WEAK_NET_WORK);
        }
    }

    @Override
    public Result serchListFeedback(Integer pageNo,Integer pageSize) {
        if(pageSize!=null && pageNo!=null){
            PageHelper.startPage(pageNo, pageSize);
        }
        return new Result(ResultCode.SUCCESS,new PageInfo(feedbackMapper.serchListFeedback()));
    }
}
