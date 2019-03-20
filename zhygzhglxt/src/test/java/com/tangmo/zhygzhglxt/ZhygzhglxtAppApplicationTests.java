package com.tangmo.zhygzhglxt;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tangmo.zhygzhglxt.dao.TbRouteDetailMapper;
import com.tangmo.zhygzhglxt.entity.Info;
import com.tangmo.zhygzhglxt.entity.TbRouteDetail;
import com.tangmo.zhygzhglxt.entity.dto.BalanceOrderDto;
import com.tangmo.zhygzhglxt.entity.dto.InfoDto;
import com.tangmo.zhygzhglxt.service.AliwithdrawalService;
import com.tangmo.zhygzhglxt.service.Impl.TbBusServiceImpl;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.Gps;
import com.tangmo.zhygzhglxt.utility.GpsUtil;
import com.tangmo.zhygzhglxt.utility.Result;
import com.tangmo.zhygzhglxt.utility.foreign.ForeignUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhygzhglxtAppApplicationTests {

    @Autowired
    AliwithdrawalService aliwithdrawalServiceImpl;

    @Autowired
    TbRouteDetailMapper tbRouteDetailMapper;

    @Test
    public void contextLoads() throws Exception {

//		String sessionId = "19104171-058b-4c81-9f5c-2743b02d9989";
//		String imei = "817112100120399";//设备号
//
//		//=======================================
//		if("".equals(sessionId)){
//
//			InfoDto infoDto = new InfoDto();
//			List<Info> infoList = new ArrayList<>();
//			Info info = new Info();
//			Map map = new HashMap<>();
//			info.setId(1);                  //获取最后信息的消息类型ID是:4356
//			info.setSeqno(2);               //流水号自行定义
//
//			//登录开始
//			map.put("username","Vip218");            //用户名
//			map.put("password","Vip218123");         //密码
//			map.put("stamp",new Date().getTime());    //时间戳
//			map.put("srcnodetype",202);               //服务器类型
//			map.put("remotenodetype",114);           //客户端类型
//			JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
//			//登录结束
//
//			info.setContent(json1.toJSONString());
//			infoList.add(info);
//			infoDto.setCommbases(infoList);
//
//			JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
//			JSONObject jsonObject = null;
//
//			try {
//				jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//			if(jsonObject != null){
//				if(jsonObject.getString("sessionid") != null){
//					System.out.println("===login===="+jsonObject.getString("sessionid"));
//					sessionId = jsonObject.getString("sessionid");
//				}
//			}
//		}
//
//
//		//=======================================
//
//		//===========================================
//
//		//开始时间：
//
////		String startTime = "1552013160000";
////		String endTime = "1552015380000";
////		//结束时间：
////
////		//查询历史信息开始
////		InfoDto infoDto = new InfoDto();
////		List<Info> infoList = new ArrayList<>();
////		Info info = new Info();
////		Map map = new HashMap<>();
////		info.setId(4353);                    //消息id
////		info.setSeqno(2);                    //唯一标识
////
////		//查询开始
////		map.put("imei",imei);               //设备号
////		map.put("infotype",4097);          //GPS信息ID: 4097
////		map.put("sn","1");                  //序列号(唯一ID)
////		map.put("starttime",startTime);    //开始时间(从1970-1-1 0:0:0开始的毫秒数，格林威治时间)
////		map.put("endtime",endTime);        //结束时间(从1970-1-1 0:0:0开始的毫秒数，格林威治时间)
//////        map.put("pageNumber",pageNumber);  //分页，每页最多条数（默认100条）
//////        map.put("totalNumber",totalNumber); //最多取总条数（默认5000条）
////		map.put("sessionid",sessionId);      //登录返回的sessionId
////		JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
////		//查询结束
////		info.setContent(json1.toJSONString());
////		infoList.add(info);
////		infoDto.setCommbases(infoList);
////		JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
////		JSONObject jsonObject = null;
////		try {
////			jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////		//查询历史信息结束
////		JSONArray jsonArray = jsonObject.getJSONArray("gpsinfo");
////		for(int i = 0 ; i < jsonArray.size() ; i++){
////			JSONObject jsonObject1 = jsonArray.getJSONObject(i);
////			JSONObject jsonObject2 = jsonObject1.getJSONObject("baseInfo");
////			String relng1 = jsonObject2.getString("relng");
////			String relat1 = jsonObject2.getString("relat");
////
////			String speed = jsonObject2.getString("speed");//速度
////			String course = jsonObject2.getString("course");//方向
////			String gpsTime = jsonObject2.getString("gpsTime");//时间
////			Double relng = new Double(relng1)/1000000;
////			Double relat = new Double(relat1)/1000000;
////            System.out.println("===="+jsonObject2);
////
////			Gps gps = (Gps) GpsUtil.gps_to_tx(relat.toString(),relng.toString()).getData();
////			String la = gps.getWgLat(); //车辆的纬度
////			String lo = gps.getWgLon(); //车辆的经度
////			System.out.println("===经度====="+lo+"===纬度====="+la);
////			System.out.println("@@@@@@@@--"+(i+1)+"--@@@@@@@@@");
////
////			TbRouteDetail tbRouteDetail = new TbRouteDetail();
////			tbRouteDetail.setId(EncryptUtil.get32Uuid());
////			tbRouteDetail.setRouteDetailCode(EncryptUtil.get32Uuid());
////			tbRouteDetail.setRouteCode("2");
////			tbRouteDetail.setLa(la);
////			tbRouteDetail.setLo(lo);
////			tbRouteDetail.setSort((i+1));
////			tbRouteDetail.setDirect(course);
////			tbRouteDetail.setSpeed(speed);
////			tbRouteDetail.setGpsTime(gpsTime);
////		    tbRouteDetail.setTm("10");
////			if(i == 0){
////				tbRouteDetail.setTm(1478031031+"");
////			}
////			else{
////				JSONObject jsonObject3 = jsonArray.getJSONObject(i-1);
////				JSONObject jsonObject4 = jsonObject3.getJSONObject("baseInfo");
////				String gpsTime2 = jsonObject4.getString("gpsTime");//前一个时间
////				int tm = (int)((Long.parseLong(gpsTime)-Long.parseLong(gpsTime2))/1000);
////				tbRouteDetail.setTm(String.valueOf(tm));
////			}
////			tbRouteDetailMapper.insertSelective(tbRouteDetail);
////
////		}
//		//===========================================
//
//		//下一页==========================================
//
//        InfoDto infoDto = new InfoDto();
//        List<Info> infoList = new ArrayList<>();
//        Info info = new Info();
//        Map map = new HashMap<>();
//        info.setId(4354);                    //消息id:下一页报文
//        info.setSeqno(2);                    //唯一标识
//
//        //查询开始
//        map.put("imei",imei);               //设备号
//        map.put("infotype",4097);           //Gps信息
//        map.put("sn","1");                  //序列号
//        map.put("sessionid",sessionId);      //sessionId
//        JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
//        //查询结束
//        info.setContent(json1.toJSONString());
//        infoList.add(info);
//        infoDto.setCommbases(infoList);
//        JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //查询历史信息结束
//        JSONArray jsonArray = jsonObject.getJSONArray("gpsinfo");
//        for(int i = 0 ; i < jsonArray.size() ; i++){
//            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//            JSONObject jsonObject2 = jsonObject1.getJSONObject("baseInfo");
//            String relng1 = jsonObject2.getString("relng");
//            String relat1 = jsonObject2.getString("relat");
//			  String speed = jsonObject2.getString("speed");//速度
//		      String course = jsonObject2.getString("course");//方向
//		      String gpsTime = jsonObject2.getString("gpsTime");//时间
//            Double relng = new Double(relng1)/1000000;
//            Double relat = new Double(relat1)/1000000;
//            System.out.println("===经度====="+relng+"===纬度====="+relat);
//            System.out.println("@@@@@@@@--"+(i+1)+"--@@@@@@@@@");
//
//            Gps gps = (Gps) GpsUtil.gps_to_tx(relat.toString(),relng.toString()).getData();
//            String la = gps.getWgLat(); //车辆的纬度
//            String lo = gps.getWgLon(); //车辆的经度
//
//            TbRouteDetail tbRouteDetail = new TbRouteDetail();
//            tbRouteDetail.setId(EncryptUtil.get32Uuid());
//            tbRouteDetail.setRouteDetailCode(EncryptUtil.get32Uuid());
//            tbRouteDetail.setRouteCode("2");
//            tbRouteDetail.setLa(la);
//            tbRouteDetail.setLo(lo);
//            tbRouteDetail.setSort((i+201));
//			tbRouteDetail.setDirect(course);
//			tbRouteDetail.setSpeed(speed);
//			tbRouteDetail.setGpsTime(gpsTime);
//			tbRouteDetail.setTm("10");
//			if(i != 0 ){
//				JSONObject jsonObject3 = jsonArray.getJSONObject(i-1);
//				JSONObject jsonObject4 = jsonObject3.getJSONObject("baseInfo");
//				String gpsTime2 = jsonObject4.getString("gpsTime");//前一个时间
//				int tm = (int)((Long.parseLong(gpsTime)-Long.parseLong(gpsTime2))/1000);
//				tbRouteDetail.setTm(String.valueOf(tm));
//			}
//			tbRouteDetailMapper.insertSelective(tbRouteDetail);
//        }
//		//下一页==========================================
//		System.out.println("=====sessionId=======:"+sessionId+"=============");
    }

}
