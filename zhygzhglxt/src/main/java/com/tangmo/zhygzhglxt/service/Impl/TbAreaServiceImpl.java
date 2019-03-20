package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbAreaMapper;
import com.tangmo.zhygzhglxt.entity.TbArea;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbAreaService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 省市区Service
 */
@Service
public class TbAreaServiceImpl implements TbAreaService {

    @Autowired
    private TbAreaMapper tbAreaMapper;

    /**
     * 根据主键查询省市区
     *
     * @param areaId 主键
     * @return
     */
    @Override
    public Result jtquery(String areaId) {

        List<TbArea> jtquery = tbAreaMapper.jtquery(areaId);
        return new Result(ResultCode.SUCCESS, jtquery);
    }

    /**
     * 添加省市区
     *
     * @param tbArea
     * @return
     */
    @Override
    public Result jtAdd(TbArea tbArea) {
        tbArea.setAreaId(EncryptUtil.get32Uuid());

        int a = tbAreaMapper.jtAdd(tbArea);
        if (a > 0) {
            return new Result(ResultCode.SUCCESS);
        }

        return new Result(ResultCode.FAIL);
    }

    /**
     * 查询省市区
     *
     * @param name     省 市 区
     * @param pageNo   当前页
     * @param pageSize 每页几条数据
     * @return
     */
    @Override
    public Result jtQueryList(String name, Integer pageNo, Integer pageSize) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }

        List<TbArea> jtQueryList = tbAreaMapper.jtQueryList(name);
        return new Result(ResultCode.SUCCESS, new PageInfo(jtQueryList));
    }
}
