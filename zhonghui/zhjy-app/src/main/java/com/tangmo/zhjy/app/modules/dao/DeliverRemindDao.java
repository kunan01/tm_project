package com.tangmo.zhjy.app.modules.dao;


import com.tangmo.zhjy.app.modules.bean.DeliverRemind;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author boge
 * @date 18/3/10
 * @description
 */
@Mapper
public interface DeliverRemindDao {

    /**
     * 增加发货提醒
     * @param deliverRemind
     * @return
     */
    int insertDr(DeliverRemind deliverRemind);
}
