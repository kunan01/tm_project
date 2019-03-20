package com.tangmo.emall.service;

import com.tangmo.emall.entity.Collect;
import com.tangmo.emall.utils.Result;

public interface CollectService {

    //添加收藏
    Result AddCollection(Collect collect);

    //删除收藏
    Result delCollection(Integer userId,String collectId);

    //清空收藏
    Result EmptyCollection(Integer userId);

    //获取用户收藏列表
    Result QueryCollectionList(Integer userId,Integer pageNo,Integer pageSize);
}
