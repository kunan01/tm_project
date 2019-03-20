package com.tangmo.zhjy.app.modules.service.impl;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.DeliverRemind;
import com.tangmo.zhjy.app.modules.dao.DeliverRemindDao;
import com.tangmo.zhjy.app.modules.service.DeliverRemindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/4/7
 * @description
 */
@Service("deliverRemindService")
public class DeliverRemindServiceImpl implements DeliverRemindService {
    @Resource
    private DeliverRemindDao deliverRemindDao;
    @Override
    public Result addRemind(DeliverRemind deliverRemind) {
        deliverRemindDao.insertDr(deliverRemind);
        return new Result(ResultCode.SUCCESS);
    }
}
