package com.tangmo.zhjy.app.modules.service;


import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.modules.bean.GoodsReturn;
import org.springframework.stereotype.Service;

/**
 * @author boge
 * @date 18/3/10
 * @description 退货原因服务层
 */
@Service("goodsReturnService")
public interface GoodsReturnService {

    /**
     * 增加退货原因
     *
     * @param goodsReturn
     * @return
     */
    Result addGoodsReturn(GoodsReturn goodsReturn);
}
