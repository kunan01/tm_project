package com.tangmo.zhjy.app.modules.dao;


import com.tangmo.zhjy.app.modules.bean.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author boge
 * @date 18/1/15
 * @description 通用dao接口
 */
@Mapper
public interface CommonDao {


    /**
     * 获取行政区域
     *
     * @param city
     * @return
     */
    List<Map<String, Object>> selectDistrict(String city);

    //获取系统消息
    List<Message> getMessage();

}
