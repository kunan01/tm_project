package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbRegularBusMapper;
import com.tangmo.zhygzhglxt.dao.TbShuttleBusMapper;
import com.tangmo.zhygzhglxt.entity.TbRegularBus;
import com.tangmo.zhygzhglxt.entity.TbShuttleBus;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbShuttleBusService;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 班车ServiceImpl
 */
@Service
public class TbShuttleBusServiceImpl implements TbShuttleBusService {

    @Autowired
    private TbShuttleBusMapper tbShuttleBusMapper;

    @Autowired
    private TbRegularBusMapper tbRegularBusMapper;

    /**
     * 查询所有班车
     *
     * @param name     途径站名
     * @param areaId   区域id
     * @param pageNo   当前页
     * @param pageSize 每页几条
     * @return
     */
    @Override
    public Result jtQueryShuttleBus(String name, String areaId, Integer pageNo, Integer pageSize) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        List<TbShuttleBus> jtQueryShuttleBus = tbShuttleBusMapper.jtQueryShuttleBus(name, areaId);

        if (jtQueryShuttleBus.size() > 0) {
            for (TbShuttleBus tbShuttleBus : jtQueryShuttleBus) {

                String address = tbShuttleBus.getAddress();
                tbShuttleBus.setNames(address.split(","));
                String busCode = tbShuttleBus.getShuttleBusCode();
                List<String> tbRegularBuses = tbRegularBusMapper.selectByBusRoute(busCode);
                tbShuttleBus.setBusNumbers(tbRegularBuses);
            }
        }


        return new Result(ResultCode.SUCCESS, new PageInfo(jtQueryShuttleBus));
    }

    /**
     * 添加班车
     *
     * @param tbShuttleBus
     * @return
     */
    @Override
    public Result jtAdd(TbShuttleBus tbShuttleBus) {

        int a = tbShuttleBusMapper.jtAdd(tbShuttleBus);
        if (a > 0) {
            return new Result(ResultCode.SUCCESS, a);
        }
        return new Result(ResultCode.FAIL);
    }

    /**
     * 删除指定班车车次
     *
     * @param shuttleBusCode 班车表唯一标识
     * @return
     */
    @Override
    public Result jtDelete(String shuttleBusCode) {
        int a = tbShuttleBusMapper.jtDelete(shuttleBusCode);
        if (a > 0) {
            return new Result(ResultCode.SUCCESS, a);
        }
        return new Result(ResultCode.FAIL);
    }

    /**
     * 修改指定班车车次
     *
     * @param tbShuttleBus 班车表唯一标识
     * @return
     */
    @Override
    public Result jtUpdate(TbShuttleBus tbShuttleBus) {
        int a = tbShuttleBusMapper.jtUpdate(tbShuttleBus);
        if (a > 0) {
            return new Result(ResultCode.SUCCESS, a);
        }
        return new Result(ResultCode.FAIL);
    }
}
