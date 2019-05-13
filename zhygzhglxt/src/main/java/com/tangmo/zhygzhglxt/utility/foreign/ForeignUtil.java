package com.tangmo.zhygzhglxt.utility.foreign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tangmo.zhygzhglxt.dao.TbRouteDetailMapper;
import com.tangmo.zhygzhglxt.utility.HttpUtils;
import com.tangmo.zhygzhglxt.utility.sendMsg.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by chengge on 2018/11/27.
 * desc:进行一些对外接口的调用
 */
@Slf4j
public class ForeignUtil {

    public static final String URL1 = "http://43.247.69.180:11009/get.html?";

    public static final String URL2 = "http://47.104.238.142:8999/Service.asmx?op=getLoginKey";

    public static final String URL3 = "120.234.33.70:11009/post.html?";

    public static final String URL4 = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    public TbRouteDetailMapper tbRouteDetailMapper;


    /*
     * 发送get请求公交的什么（）数据
     * */
    public static JSONObject getInformation1(String info) throws Exception {

        String code = null;
        String result = null;
        JSONObject json = null;
        String url = URL1 + info;
        //String url = "{URL}/get.html?{\"commbases\":[{\"id\":1,\"seqno\":2,\"content\":\"{\\\"username\\\":\\\"zhanghao\\\",\\\"password\\\":\\\"mima\\\",\\\"stamp\\\":1528963860732,\\\"srcnodetype\\\": 202,\\\"srcnodeid\\\": 202,\\\"remotenodetype\\\": 114}\"}]}";
        try {
            Long startTime = new Date().getTime();
            log.info("开始调用第三方接口: {}", startTime);
            result = HttpUtil.sendGet(url);
            log.info("结束调用第三方接口耗时: {}", new Date().getTime()-startTime);
            System.out.println("=======开始输出返回数据========="+ result);
            if (result != null) {
                json = JSONObject.parseObject(result);
                JSONArray jsonArray = json.getJSONArray("commbases");
                JSONObject jsonObject = (JSONObject) jsonArray.get(0);
                JSONObject jsonObject1 = jsonObject.getJSONObject("content");
                System.out.println("====resultarr===" + jsonObject1.getIntValue("retcode"));

                if (jsonObject1.getIntValue("retcode") == 0) {
                    return jsonObject1;
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("调用第三方接口异常: {}",e.getMessage());
            return null;
        }
        // return new Result(ResultCode.SUCCESS,json);
    }

    /*
     * 发送http的post请求公交的什么（）数据
     * */
    public static void postInformation1() throws Exception {

        //String param = "{hotelIsquality: 1,pageNo: 1,pageSize: 10}";
        JSONObject jsobj = new JSONObject();
        jsobj.put("strLoginName", "igps");
        jsobj.put("strPassword", "123456");
        //jsobj.put("userName", "18329785735");

//        JSONObject jsobj = new JSONObject();
//        jsobj.put("hotelIsquality", "1");
//        jsobj.put("pageNo", "1");
//        jsobj.put("pageSize", "10");

        String code = null;
        String result = null;
        JSONObject json = null;

        try {
            result = HttpUtils.post2(URL2, jsobj);
            //json = JSONObject.parseObject(result);
            System.out.println("=============" + result + "========");
//                if (json.getIntValue("status") != 0) {
//                    System.out.println(json.getString("msg"));
//                } else {
//                    JSONObject resultarr = json.getJSONObject("result");
//                    String count = resultarr.getString("count");
//                    String accountid = resultarr.getString("accountid");
//                    System.out.println(count + " " + accountid);
//                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //return new Result(ResultCode.SUCCESS,json);
    }

    public static void main(String[] args) throws Exception {

    }

}
