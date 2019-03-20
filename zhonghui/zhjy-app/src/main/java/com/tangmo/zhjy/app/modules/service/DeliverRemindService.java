package com.tangmo.zhjy.app.modules.service;


import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.DeliverRemind;
import org.springframework.stereotype.Service;

/**
 * @author boge
 * @date 18/3/10
 * @description
 */
public interface DeliverRemindService {

    /**
     * 增加发货提醒
     *
     * @param deliverRemind
     * @return
     */
    Result addRemind(DeliverRemind deliverRemind);
}
