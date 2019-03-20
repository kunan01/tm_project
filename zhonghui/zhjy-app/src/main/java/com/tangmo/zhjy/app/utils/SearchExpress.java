package com.tangmo.zhjy.app.utils;



import com.tangmo.zhjy.app.modules.vo.ExpressDetailVO;
import com.tangmo.zhjy.app.modules.vo.ExpressVO;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author boge
 * @date 18/3/10
 * @description
 */
@Slf4j
public class SearchExpress {
    public static final String APP_KEY = "da8c4213b24d8dd2";// 你的appkey
    public static final String URL = "http://api.jisuapi.com/express/query";
    public static final String type = "auto"; //自动识别

    /**
     * 查询快递
     * @param number 快递单号
     */
    public static ExpressVO Get(String number) {
        ExpressVO expressVO = new ExpressVO();
        String result = null;
        String url = URL + "?appkey=" + APP_KEY + "&type=" + type + "&number=" + number;

        try {
            result = HttpUtil.sendGet(url);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getIntValue("status") != 0) {
                expressVO.setMsg(json.getString("msg"));
                return expressVO;
            } else {
                //查询成功,得到物流结果
                JSONObject resultarr = (JSONObject) json.getJSONObject("result");
                if (resultarr != null) {
                    expressVO.setDeliverystatus(resultarr.getIntValue("deliverystatus"));
                    expressVO.setCompany(resultarr.getString("type"));
                    expressVO.setNumber(number);
                    List<ExpressDetailVO> detailList = new ArrayList<>();
                    if (resultarr.get("list") != null) {
                        JSONArray list = resultarr.getJSONArray("list");
                        for (int j = 0; j < list.size(); j++) {
                            JSONObject list_obj = (JSONObject) list.get(j);
                            if (list_obj != null) {
                                ExpressDetailVO expressDetailVO = new ExpressDetailVO();
                                expressDetailVO.setStatus(list_obj.getString("status"));
                                expressDetailVO.setTime(list_obj.getString("time"));
                                detailList.add(expressDetailVO);
                            }
                        }
                    }else{
                        expressVO.setExpressDetailVOs(new ArrayList<>());
                    }
                    expressVO.setExpressDetailVOs(detailList);
                }else{
                    expressVO.setMsg("系统出错,暂无物流信息");
                }
            }
        } catch (Exception e) {
            expressVO.setMsg("系统错误,请稍后重试");
            return expressVO;
        }
        return expressVO;
    }
}
