package com.tangmo.zhjy.app.modules.service.impl;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.GoodsReturn;
import com.tangmo.zhjy.app.modules.dao.GoodsReturnDao;
import com.tangmo.zhjy.app.modules.service.GoodsReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/4/7
 * @description
 */
@Service("goodsReturnService")
public class GoodsReturnServiceImpl implements GoodsReturnService {
    @Resource
    private GoodsReturnDao goodsReturnDao;
    @Override
    public Result addGoodsReturn(GoodsReturn goodsReturn) {
        goodsReturnDao.insertGoodsReturn(goodsReturn);
        return new Result(ResultCode.SUCCESS);
    }
}
