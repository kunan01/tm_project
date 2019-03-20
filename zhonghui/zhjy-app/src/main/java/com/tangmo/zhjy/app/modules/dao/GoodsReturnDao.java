package com.tangmo.zhjy.app.modules.dao;


import com.tangmo.zhjy.app.modules.bean.GoodsReturn;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author boge
 * @date 18/3/10
 * @description 退货原因dao
 */
@Mapper
public interface GoodsReturnDao {

    /**
     * 增加退货原因
     *
     * @param goodsReturn
     * @return
     */
    int insertGoodsReturn(GoodsReturn goodsReturn);

}
