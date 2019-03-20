package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.entity.TbFeedBack;
import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2018/7/10.
 */
public interface TbFeedBackService {

    Result addFeedBack(TbFeedBack tbFeedBack);

    Result delFeedBackById(Integer fbId);

    Result selFeedBack(String name, Integer fbState, Integer pageSize, Integer pageNo);

    Result verifyFeedBack(Integer fbId);
}
