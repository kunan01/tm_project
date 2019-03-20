package com.tangmo.zhygzhglxt.service;

import com.tangmo.zhygzhglxt.utility.Result;

/**
 * Created by chengge on 2018/11/12.
 */
public interface TbBusService {

    Result likeBusByName(Integer pageSize, Integer pageNo);

    Result selBusByTime(String busNumber);

    Result getBusDetails(String routeCode);

    Result getBusRoutelDetails(String routeCode);

    Result getBusGPS(String busId);

    Result getInfoByBusIdSiteLoLa(String busId, String routeCode, String siteLa, String siteLo);

    Result getBusInfoByBusIdSiteLoLa(String busId, String routeCode, String siteLa, String siteLo);

    Result getSiteByLoLa(String routeCode, String NowLa, String NowLo);

    Result busLogin();

    Result gpsLogin();

    Result getInfoByImei(String imei, String startTime, String endTime, Integer pageNumber, Integer totalNumber);

    Result getNextInfoByImei(String imei);

    Result overInfoByImei(String imei);

    Result getLastInfoByImei(String imei);
}
