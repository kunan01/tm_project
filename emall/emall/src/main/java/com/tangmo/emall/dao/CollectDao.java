package com.tangmo.emall.dao;

import com.tangmo.emall.entity.Collect;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectDao {

    //通过用户id和商品id获取收藏信息
    Collect getCollectByUserIdAndPId(Collect collect);

    //添加收藏
    int addCollect(Collect collect);

    //通过收藏id获取收藏信息
    Collect getCollectById(Integer collectId);

    //通过收藏id删除收藏
    int delCollect(Integer collectId);

    //清空收藏
    int clearCollect(Integer userId);

    //通过用户id获取收藏集合
    List<Collect> getCollectListByUserId(Integer userId);
}
