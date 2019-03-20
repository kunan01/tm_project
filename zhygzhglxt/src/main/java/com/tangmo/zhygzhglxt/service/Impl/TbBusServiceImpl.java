package com.tangmo.zhygzhglxt.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.tangmo.zhygzhglxt.dao.*;
import com.tangmo.zhygzhglxt.entity.*;
import com.tangmo.zhygzhglxt.entity.dto.InfoDto;
import com.tangmo.zhygzhglxt.entity.vo.BusVo;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbBusService;
import com.tangmo.zhygzhglxt.utility.*;
import com.tangmo.zhygzhglxt.utility.foreign.ForeignUtil;
import com.tangmo.zhygzhglxt.utility.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chengge on 2018/11/12.
 */
@Service
public class TbBusServiceImpl implements TbBusService {


    @Autowired
    TbBusMapper tbBusMapper;  //公交车

    @Autowired
    TbRouteMapper tbRouteMapper; //路线

    @Autowired
    TbRouteDetailMapper tbRouteDetailMapper; //路线明细

    @Autowired
    TbSiteMapper tbSiteMapper;   //站点

    @Autowired
    TbTravelMapper tbTravelMapper;  //行驶

    @Autowired
    TbBusNumberMapper tbBusNumberMapper;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    public Result likeBusByName(Integer pageSize, Integer pageNo) {

        List<TbBus> tbBuses = tbBusMapper.likeBusByName(pageSize, (pageNo - 1) * pageSize); //查询公交车

        List<TbBus> tbBusList = new ArrayList<>();
        if (tbBuses != null && tbBuses.size() > 0) {
            for (int j = 0; j < tbBuses.size(); j++) {
                //根据公交车的路线查询站点
                String[] routeCodes = tbBuses.get(j).getRouteCodes().split(",");  //该公交下所有的路线
                if (routeCodes != null && routeCodes.length != 0) {
                    List<TbRoute> tbRoutes1 = tbRouteMapper.selectByRouteCodeses(routeCodes);
                    if (tbRoutes1.size() > 0) {
                        for (TbRoute tbRoute : tbRoutes1) {
                            String[] str = tbRoute.getRouteName().split("-");
                            tbRoute.setRouteNameStart(str[0]);
                            tbRoute.setRouteNameEnd(str[1]);
                            TbBus tbBus = new TbBus(tbBuses.get(j).getBusId(), tbBuses.get(j).getBusCode(), tbBuses.get(j).getBusName(), tbBuses.get(j).getBusNumber(), tbBuses.get(j).getBusStartTime(), tbBuses.get(j).getBusEndTime(), tbBuses.get(j).getBusPrice(), tbBuses.get(j).getDistance(), tbBuses.get(j).getRouteCodes(), tbBuses.get(j).getCreateTime(), tbBuses.get(j).getUpdateTime(), tbRoute);
                            tbBusList.add(tbBus);
                        }
                    }
                }
//                if(routeCodes != null && routeCodes.length != 0){
//                    for(int i = 0;i< routeCodes.length;i++){
//                        TbRoute tbRoutes = tbRouteMapper.selectByRouteCodes(routeCodes[i]);
//                        if(tbRoutes != null){
//                            String[] str = tbRoutes.getRouteName().split("-");
//                            tbRoutes.setRouteNameStart(str[0]);
//                            tbRoutes.setRouteNameEnd(str[1]);
//
//                            TbBus tbBus = new TbBus(tbBuses.get(j).getBusId(),tbBuses.get(j).getBusCode(),tbBuses.get(j).getBusName(),tbBuses.get(j).getBusNumber(),tbBuses.get(j).getBusStartTime(),tbBuses.get(j).getBusEndTime(),tbBuses.get(j).getBusPrice(),tbBuses.get(j).getDistance(),tbBuses.get(j).getRouteCodes(),tbBuses.get(j).getCreateTime(),tbBuses.get(j).getUpdateTime(),tbRoutes);
//                            tbBusList.add(tbBus);
//                        }
//                    }
//                }
            }
        }
        return new Result(ResultCode.SUCCESS, new PageInfo(tbBusList));
    }

    @Override
    public Result selBusByTime(String busNumber) {

        if (busNumber == null || "".equals(busNumber)) {
            return new Result(ResultCode.FAIL, "公交号不能为空！");
        }

        //根据公交号查询行驶表中最新的数据

//        List<TbBus> tbBuses = tbBusMapper.likeBusByName(name); //根据公交车的名称模糊查询
//        List<TbBus> buses = new ArrayList();
//        if(tbBuses.size()>0){
//            for(TbBus tbBus : tbBuses){
//                //根据公交车的路线查询站点
//                String[] routeCodes = tbBus.getRouteCodes().split(",");  //该公交下所有的路线
//                List<TbRoute> tbRoutes = tbRouteMapper.selectByRouteCodes(routeCodes);
//
//                for(TbRoute tbRoute : tbRoutes){
//                    String[] siteCodes = tbRoute.getSiteCodes().split(","); //该路线下所有的站点
//                    List<TbSite> tbSites = tbSiteMapper.selectBySiteCodes(siteCodes);
//
//                    tbBus.setStartAddress((tbSiteMapper.selectBySiteCode(siteCodes[0])).getSiteName());
//                    tbBus.setEndAddress((tbSiteMapper.selectBySiteCode(siteCodes[siteCodes.length-1])).getSiteName());
////                    List<String> sites = new ArrayList();
////                    for(TbSite tbSite: tbSites){
////                        sites.add(tbSite.getSiteName());
////                    }
//                    tbRoute.setTbSiteList(tbSites);
//                    tbBus.setTbRoute(tbRoute);
//                    buses.add(tbBus);
//                }
//            }
//        }
//        return new Result(ResultCode.SUCCESS,new PageInfo(buses));
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result getBusDetails(String routeCode) {
        List<TbBus> tbBuses = tbBusMapper.likeBusByNameAll();
        int s = 0;
        TbBus tbBus = null;
        if (tbBuses != null && tbBuses.size() > 0) {
            for (int j = 0; j < tbBuses.size(); j++) {
                String[] codes = tbBuses.get(j).getRouteCodes().split(",");
                for (int a = 0; a < codes.length; a++) {
                    if (codes[a].equals(routeCode)) {
                        tbBus = tbBuses.get(j);
                        s = 1;
                        break;
                    }
                }
                if (s == 1) {
                    break;
                }
            }
        }


        TbRoute tbRoutes = tbRouteMapper.selectByRouteCodes(routeCode);
        if (tbRoutes != null) {
            String[] str = tbRoutes.getRouteName().split("-");
            tbRoutes.setRouteNameStart(str[0]);
            tbRoutes.setRouteNameEnd(str[1]);

            String[] sCodes = tbRoutes.getSiteCodes().split(",");
            List<TbSite> sites = new ArrayList<>();
            if (sCodes != null && sCodes.length > 0) {
                for (int i = 0; i < sCodes.length; i++) {
                    TbSite tbSite = tbSiteMapper.selectBySiteCodes(sCodes[i]);
                    if (tbSite != null) {
                        sites.add(tbSite);
                    }
                }
            }
            tbRoutes.setTbSiteList(sites);
            tbBus.setTbRoute(tbRoutes);
        }

        return new Result(ResultCode.SUCCESS, tbBus);
    }

    @Override
    public Result getBusRoutelDetails(String routeCode) {

        if (routeCode == null || "".equals(routeCode)) {
            return new Result(ResultCode.FAIL, "路线的唯一标识不能为空！");
        }
        TbRoute tbRoutes = tbRouteMapper.selectByRouteCodes(routeCode);
        if (tbRoutes == null) {
            return new Result(ResultCode.FAIL, "该路线不存在！");
        }
        Map map = new HashMap();
        List<TbSite> sites = new ArrayList<>();

        List<TbRouteDetail> routeDetails = tbRouteDetailMapper.getBusRoutelDetails(routeCode);

        if (tbRoutes != null) {
            String[] sCodes = tbRoutes.getSiteCodes().split(",");
            if (sCodes != null && sCodes.length > 0) {
                for (int i = 0; i < sCodes.length; i++) {
                    TbSite tbSite = tbSiteMapper.selectBySiteCodes(sCodes[i]);
                    if (tbSite != null) {
                        sites.add(tbSite);
                    }
                }
            }
        }

        map.put("routeDetail", routeDetails);
        map.put("sites", sites);
        return new Result(ResultCode.SUCCESS, map);
    }

    @Override
    public Result getBusGPS(String busId) {
        String loginKey = "";
        if (!jedisKeys.exists("loginKey")) {
            return new Result(ResultCode.FAIL, "loginKey不能为空！");
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("loginKey");
        }
        //TbBus tbBus = tbBusMapper.selectByPrimaryKey(busId);
        String busNumberId = tbBusMapper.selectBusNumberIdByBusId(busId);
        TbBusNumber tbBusNumber = tbBusNumberMapper.selectByPrimaryKey(busNumberId);
        String[] busNumbers = tbBusNumber.getBusNumber().split(",");
        //Map<String,Object> map = new HashMap<>();
        List<BusVo> list = new ArrayList();
        if (busNumbers != null && busNumbers.length > 0) {
            for (int i = 0; i < busNumbers.length; i++) {
                System.out.println(busNumbers[i]);
                list.add(GetBusGps.getBusGps(busNumbers[i], loginKey));
                //map.put(busNumbers[i],GetBusGps.getBusGps(busNumbers[i]));
            }
        }
        List<BusVo> lists = new ArrayList();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    lists.add(list.get(i));
                }
            }
        }
        return new Result(ResultCode.SUCCESS, lists);
    }

    @Override
    public Result getSiteByLoLa(String routeCode, String NowLa, String NowLo) {

        if (routeCode == null || NowLa == null || NowLo == null || "".equals(routeCode) || "".equals(NowLa) || "".equals(NowLo)) {
            return new Result(ResultCode.FAIL, "路线的唯一标识、当前的经度纬度不能为空！");
        }
        TbRoute tbRoute = tbRouteMapper.selectByRouteCodes(routeCode);

        if (tbRoute == null) {
            return new Result(ResultCode.FAIL, "该路线不存在！");
        }
        String[] siteCodes = tbRoute.getSiteCodes().split(",");
        TbSite site = tbSiteMapper.getSiteByLoLa(NowLa, NowLo, siteCodes);
        return new Result(ResultCode.SUCCESS, site);
    }

    @Override
    public Result getInfoByBusIdSiteLoLa(String busId, String routeCode, String siteLa, String siteLo) {

        String loginKey = "";
        if (!jedisKeys.exists("loginKey")) {
            return new Result(ResultCode.FAIL, "loginKey不能为空！");
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("loginKey");
        }
        //TbBus tbBus = tbBusMapper.selectByPrimaryKey(busId);
        String busNumberId = tbBusMapper.selectBusNumberIdByBusId(busId);
        TbBusNumber tbBusNumber = tbBusNumberMapper.selectByPrimaryKey(busNumberId);
        String[] busNumbers = tbBusNumber.getBusNumber().split(",");
        List<BusVo> list = new ArrayList();

        if (busNumbers != null && busNumbers.length > 0) {
            for (int i = 0; i < busNumbers.length; i++) {
                System.out.println(busNumbers[i]);
                list.add(GetBusGps.getBusGps(busNumbers[i], loginKey));
            }
        }

        List<BusVo> lists = new ArrayList();//详细车辆的经度纬度

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    if ("1".equals(list.get(i).getIsOnline())) {
                        if (Double.parseDouble(list.get(i).getLon()) > 0 && Double.parseDouble(list.get(i).getLat()) > 0) {
                            lists.add(list.get(i));
                        }
                    }
                }
            }
        }
        System.out.println("最后上线的车辆有：" + lists.size());
        List<BusVo> listBuses = new ArrayList();//没过站的详细车辆的经度纬度
        if (lists.size() > 0) {
            for (int i = 0; i < lists.size(); i++) {
                //拿到每一辆车的经度纬度，已知站点的经度纬度
                BusVo busVo = lists.get(i);
                try {
                    Gps gps = (Gps) GpsUtil.gps_to_tx(busVo.getLat(), busVo.getLon()).getData();
                    String la = gps.getWgLat(); //车辆的纬度
                    String lo = gps.getWgLon(); //车辆的经度
                    //根据车辆的经度和纬度，查询最近的路线详情的经纬度点
                    TbRouteDetail tbRouteDetail = tbRouteDetailMapper.selectByDistance(routeCode, la, lo);
                    //根据站点的经度纬度查询在路线详情中的经纬度点
                    TbRouteDetail tbRouteDetail2 = tbRouteDetailMapper.selectByDistance(routeCode, siteLa, siteLo);
                    if (tbRouteDetail == null || tbRouteDetail2 == null) {
                        return new Result(ResultCode.BUS_ERROR4, "暂无路线详情！");
                    }
                    //if(tbRouteDetail.getSort() <= tbRouteDetail2.getSort()){ //求站点之前的距离
                    List<TbRouteDetail> routeDetails = new ArrayList<>();
                    if (tbRouteDetail.getSort() <= tbRouteDetail2.getSort()) {
                        //查询这两点之间所有的经纬度点，根据序号排序（升序）
                        routeDetails = tbRouteDetailMapper.selectBySort(routeCode, tbRouteDetail.getSort(), tbRouteDetail2.getSort());
                    } else {
                        //查询这两点之间所有的经纬度点，根据序号排序（升序）
                        routeDetails = tbRouteDetailMapper.selectBySort(routeCode, tbRouteDetail2.getSort(), tbRouteDetail.getSort());
                    }
                    //查询这两点之间所有的经纬度点，根据序号排序（升序）
                    //List<TbRouteDetail> routeDetails =tbRouteDetailMapper.selectBySort(routeCode,tbRouteDetail.getSort(),tbRouteDetail2.getSort());
                    if (routeDetails.size() > 0) {
                        Double distace = 0d;
                        if (routeDetails.size() == 1) {
                            distace = 0d;
                        } else {
                            for (int j = 0; j < routeDetails.size() - 1; j++) {
                                TbRouteDetail routeDetail = routeDetails.get(j);
                                Double la1 = Double.parseDouble(routeDetail.getLa());
                                Double lo1 = Double.parseDouble(routeDetail.getLo());

                                TbRouteDetail routeDetail2 = routeDetails.get(j + 1);
                                Double la2 = Double.parseDouble(routeDetail2.getLa());
                                Double lo2 = Double.parseDouble(routeDetail2.getLo());

                                Double mi = MapUtil.GetDistance(la1, lo1, la2, lo2);
                                distace += mi;
                            }
                        }
                        lists.get(i).setDistance(String.valueOf(distace));
                        System.out.println("第" + i + "辆车的距离为：" + distace);
                        listBuses.add(lists.get(i));
                    }
//                    }else{
//                        System.out.println("车辆已经过了站点！");
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (listBuses.size() > 0) {
            //根据数组listBuses求出里面距离最短的公交车//求出大概多久到站的时间
            List<Double> distances = new ArrayList<>();
            for (BusVo busVo : listBuses) {
                distances.add(Double.parseDouble(busVo.getDistance()));
            }
            String distance = ToBigUtil.comporeTo(distances);
            BusVo tbBusVo = null;

            for (BusVo busVo : listBuses) {
                if (distance.equals(busVo.getDistance())) { //如果相等那这个就是最短的距离的车
                    //求出到站时间：路程/速度
                    Double speed = 30.00d;
                    if (Double.parseDouble(busVo.getSpeed()) > 5) {
                        speed = Double.parseDouble(busVo.getSpeed());
                    }
                    Double hours = Double.parseDouble(distance) / (speed * 1000);//每小时多少km
                    Double minutes = hours * 60;
                    int a = minutes.intValue();
                    busVo.setSiteTime(Double.parseDouble(String.valueOf(a)));
                    tbBusVo = busVo;
                }
            }
            return new Result(ResultCode.SUCCESS, tbBusVo);
        } else {
            return new Result(ResultCode.BUS_ERROR3, "暂无车辆！");
        }
    }

    @Override
    public Result getBusInfoByBusIdSiteLoLa(String busId, String routeCode, String siteLa, String siteLo) {

        String loginKey = "";
        if (!jedisKeys.exists("loginKey")) {
            return new Result(ResultCode.FAIL, "loginKey不能为空！");
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("loginKey");
        }
        String busNumberId = tbBusMapper.selectBusNumberIdByBusId(busId);
        TbBusNumber tbBusNumber = tbBusNumberMapper.selectByPrimaryKey(busNumberId);
        String[] busNumbers = tbBusNumber.getBusNumber().split(",");
        List<BusVo> list = new ArrayList();

        if (busNumbers != null && busNumbers.length > 0) {
            for (int i = 0; i < busNumbers.length; i++) {
                System.out.println(busNumbers[i]);
                list.add(GetBusGps.getBusGps(busNumbers[i], loginKey));
            }
        }

        List<BusVo> lists = new ArrayList();//详细车辆的经度纬度

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    if ("1".equals(list.get(i).getIsOnline())) {
                        if (Double.parseDouble(list.get(i).getLon()) > 0 && Double.parseDouble(list.get(i).getLat()) > 0) {
                            lists.add(list.get(i));
                        }
                    }
                }
            }
        }
        System.out.println("最后上线的车辆有：" + lists.size());
        List<BusVo> listBuses = new ArrayList();//没过站的详细车辆的经度纬度
        if (lists.size() > 0) {
            for (int i = 0; i < lists.size(); i++) {
                //拿到每一辆车的经度纬度，已知站点的经度纬度
                BusVo busVo = lists.get(i);
                try {
                    Gps gps = (Gps) GpsUtil.gps_to_tx(busVo.getLat(), busVo.getLon()).getData();
                    String la = gps.getWgLat(); //车辆的纬度
                    String lo = gps.getWgLon(); //车辆的经度
                    //根据车辆的经度和纬度，查询最近的路线详情的经纬度点
                    TbRouteDetail tbRouteDetail = tbRouteDetailMapper.selectByDistance(routeCode, la, lo);
                    //根据站点的经度纬度查询在路线详情中的经纬度点
                    TbRouteDetail tbRouteDetail2 = tbRouteDetailMapper.selectByDistance(routeCode, siteLa, siteLo);
                    if (tbRouteDetail == null || tbRouteDetail2 == null) {
                        return new Result(ResultCode.BUS_ERROR4, "暂无路线详情！");
                    }
                    //if(tbRouteDetail.getSort() <= tbRouteDetail2.getSort()){ //求站点之前的距离
                    List<TbRouteDetail> routeDetails = new ArrayList<>();
                    if (tbRouteDetail.getSort() <= tbRouteDetail2.getSort()) {
                        //查询这两点之间所有的经纬度点，根据序号排序（升序）
                        routeDetails = tbRouteDetailMapper.selectBySort(routeCode, tbRouteDetail.getSort(), tbRouteDetail2.getSort());
                    } else {
                        //查询这两点之间所有的经纬度点，根据序号排序（升序）
                        routeDetails = tbRouteDetailMapper.selectBySort(routeCode, tbRouteDetail2.getSort(), tbRouteDetail.getSort());
                    }
                    //查询这两点之间所有的经纬度点，根据序号排序（升序）
                    //List<TbRouteDetail> routeDetails =tbRouteDetailMapper.selectBySort(routeCode,tbRouteDetail.getSort(),tbRouteDetail2.getSort());
                    if (routeDetails.size() > 0) {
                        Double distace = 0d;
                        if (routeDetails.size() == 1) {
                            distace = 0d;
                        } else {
                            for (int j = 0; j < routeDetails.size() - 1; j++) {
                                TbRouteDetail routeDetail = routeDetails.get(j);
                                Double la1 = Double.parseDouble(routeDetail.getLa());
                                Double lo1 = Double.parseDouble(routeDetail.getLo());

                                TbRouteDetail routeDetail2 = routeDetails.get(j + 1);
                                Double la2 = Double.parseDouble(routeDetail2.getLa());
                                Double lo2 = Double.parseDouble(routeDetail2.getLo());

                                Double mi = MapUtil.GetDistance(la1, lo1, la2, lo2);
                                distace += mi;
                            }
                        }
                        lists.get(i).setDistance(String.valueOf(distace));
                        System.out.println("第" + i + "辆车的距离为：" + distace);
                        listBuses.add(lists.get(i));
                    }
//                    }else{
//                        System.out.println("车辆已经过了站点！");
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (listBuses.size() > 0) {
            //根据数组listBuses求出里面距离最短的公交车//求出大概多久到站的时间
            List<Double> distances = new ArrayList<>();
            for (BusVo busVo : listBuses) {
                distances.add(Double.parseDouble(busVo.getDistance()));
            }
            String distance = ToBigUtil.comporeTo(distances);
            BusVo tbBusVo = null;

            for (BusVo busVo : listBuses) {
                if (distance.equals(busVo.getDistance())) { //如果相等那这个就是最短的距离的车
                    tbBusVo = busVo;
                }
            }
            return new Result(ResultCode.SUCCESS, tbBusVo);
        } else {
            return new Result(ResultCode.BUS_ERROR3, "暂无车辆！");
        }
    }

    @Override
    public Result busLogin() {

        BusVo busVo = GetBusGps.busLogin();
        if (busVo != null) {
            if (busVo.getLoninKey() != null || "".equals(busVo.getLoninKey())) {
                System.out.println("==================结束了=" + busVo.getLoninKey() + "==============");
                jedisStrings.set("loginKey", busVo.getLoninKey());
            }
        }
        return new Result(ResultCode.SUCCESS, null);
    }

    @Override
    public Result gpsLogin() {

        InfoDto infoDto = new InfoDto();
        List<Info> infoList = new ArrayList<>();
        Info info = new Info();
        Map map = new HashMap<>();
        info.setId(1);                  //获取最后信息的消息类型ID是:4356
        info.setSeqno(2);               //流水号自行定义

        //登录开始
        map.put("username", "Vip218");           //用户名
        map.put("password", "Vip218123");        //密码
        map.put("stamp", new Date().getTime());    //时间戳
        map.put("srcnodetype", 202);              //服务器类型
        map.put("remotenodetype", 114);           //客户端类型
        JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
        //登录结束

        info.setContent(json1.toJSONString());
        infoList.add(info);
        infoDto.setCommbases(infoList);

        JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
        JSONObject jsonObject = null;

        try {
            jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (jsonObject != null) {
            if (jsonObject.getString("sessionid") != null) {
                System.out.println("===login====" + jsonObject.getString("sessionid"));
                jedisStrings.set("login", jsonObject.getString("sessionid"));
            }
        }
        return new Result(ResultCode.SUCCESS, null);
    }

    @Override
    public Result getInfoByImei(String imei, String startTime, String endTime, Integer pageNumber, Integer totalNumber) {

        String loginKey = "";
        if (!jedisKeys.exists("login")) {
            return new Result(ResultCode.FAIL, "login不能为空！");
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("login");
        }

        InfoDto infoDto = new InfoDto();
        List<Info> infoList = new ArrayList<>();
        Info info = new Info();
        Map map = new HashMap<>();
        info.setId(4353);                    //消息id
        info.setSeqno(2);                   //唯一标识

        //查询开始
        map.put("imei", imei);               //设备号
        map.put("infotype", 4097);          //GPS信息ID: 4097
        map.put("sn", "1");                  //序列号(唯一ID)
        map.put("starttime", startTime);    //开始时间(从1970-1-1 0:0:0开始的毫秒数，格林威治时间)
        map.put("endtime", endTime);        //结束时间(从1970-1-1 0:0:0开始的毫秒数，格林威治时间)
        map.put("pageNumber", pageNumber);  //分页，每页最多条数（默认100条）
        map.put("totalNumber", totalNumber); //最多取总条数（默认5000条）
        map.put("sessionid", loginKey);      //登录返回的sessionId
        JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
        //查询结束
        info.setContent(json1.toJSONString());
        infoList.add(info);
        infoDto.setCommbases(infoList);
        JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
        JSONObject jsonObject = null;
        try {
            jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, jsonObject);
    }

    @Override
    public Result getNextInfoByImei(String imei) { //获取最新的定位

        String loginKey = "";
        if (!jedisKeys.exists("login")) {

            return new Result(ResultCode.FAIL, "login不能为空！");
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("login");
        }

        InfoDto infoDto = new InfoDto();
        List<Info> infoList = new ArrayList<>();
        Info info = new Info();
        Map map = new HashMap<>();
        info.setId(4354);                    //消息id:下一页报文
        info.setSeqno(2);                    //唯一标识

        //查询开始
        map.put("imei", imei);               //设备号
        map.put("infotype", 4097);           //Gps信息
        map.put("sn", "1");                  //序列号
        map.put("sessionid", loginKey);      //sessionId
        JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
        //查询结束
        info.setContent(json1.toJSONString());
        infoList.add(info);
        infoDto.setCommbases(infoList);
        JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
        JSONObject jsonObject = null;
        try {
            jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, jsonObject);
    }

    @Override
    public Result overInfoByImei(String imei) {

        String loginKey = "";
        if (!jedisKeys.exists("login")) {
            return new Result(ResultCode.FAIL, "login不能为空！");
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("login");
        }

        InfoDto infoDto = new InfoDto();
        List<Info> infoList = new ArrayList<>();
        Info info = new Info();
        Map map = new HashMap<>();
        info.setId(4355);                    //消息id:结束历史查询
        info.setSeqno(2);                    //唯一标识

        //查询开始
        map.put("imei", imei);
        map.put("infotype", 4097);
        map.put("sn", "1");
        map.put("sessionid", loginKey);
        JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
        //查询结束
        info.setContent(json1.toJSONString());
        infoList.add(info);
        infoDto.setCommbases(infoList);
        JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
        JSONObject jsonObject = null;
        try {
            jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, jsonObject);
    }

    @Override
    public Result getLastInfoByImei(String imei) {

        String loginKey = "";
        if (!jedisKeys.exists("login")) {
            return new Result(ResultCode.FAIL, "login不能为空！");
        } else {
            //若存在，则直接从redis里面取出相应数
            loginKey = jedisStrings.get("login");
        }

        InfoDto infoDto = new InfoDto();
        List<Info> infoList = new ArrayList<>();
        Info info = new Info();
        Map map = new HashMap<>();
        info.setId(4356);                    //消息id 获取最后一次上传的信息
        info.setSeqno(2);                    //唯一标识

        //查询开始
        map.put("imeis", imei);
        map.put("infotype", 4097);
        map.put("sn", "2");
        map.put("sessionid", loginKey);
        JSONObject json1 = (JSONObject) JSONObject.toJSON(map);//将java对象转换为json对象
        //查询结束
        info.setContent(json1.toJSONString());
        infoList.add(info);
        infoDto.setCommbases(infoList);
        JSONObject json2 = (JSONObject) JSONObject.toJSON(infoDto);//将java对象转换为json对象
        JSONObject jsonObject = null;
        try {
            jsonObject = ForeignUtil.getInformation1(json2.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(ResultCode.SUCCESS, jsonObject);
    }

    public Result insert(TbRouteDetail tbRouteDetail) {

        tbRouteDetailMapper.insertSelective(tbRouteDetail);
        return new Result(ResultCode.SUCCESS);
    }

//    public static void main(String[] args) {
//        Double minutes = 0.2344456 * 60 ;
//        int a = minutes.intValue();
//        System.out.println(Double.parseDouble(String.valueOf(a)));
//    }
}
