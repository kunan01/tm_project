package com.tangmo.zhjy.app.modules.dao;


import com.tangmo.zhjy.app.modules.bean.BuyRecord;
import com.tangmo.zhjy.app.modules.bean.SellRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author boge
 * @date 18/1/11
 * @description 商品记录dao接口
 */
@Mapper
public interface GoodsRecordDao {

    /**
     * 增加购买记录
     *
     * @param buyRecord
     * @return
     */
    int insertBuyRecord(BuyRecord buyRecord);

    /**
     * 增加卖出记录
     *
     * @param sellRecord
     * @return
     */
    int insertSellRecord(SellRecord sellRecord);

    /**
     * 删除多条购买记录
     *
     * @param brIds 购买记录主键数组
     * @return
     */
    int deleteMultiBuyRecord(Integer[] brIds);

    /**
     * 删除多条卖出记录
     *
     * @param srIds 卖出记录主键数组
     * @return
     */
    int deleteMultiSellRecord(Integer[] srIds);

    /**
     * 购买记录列表
     *
     * @param userId
     * @param start
     * @param end
     * @return
     */
    List<BuyRecord> selectUserBuyRecord(@Param("userId") Integer userId, @Param("start") Integer start,
                                        @Param("end") Integer end);

    /**
     * 卖出记录列表
     *
     * @param userId
     * @param start
     * @param end
     * @return
     */
    List<SellRecord> selectUserSellRecord(@Param("userId") Integer userId, @Param("start") Integer start,
                                          @Param("end") Integer end);
}
