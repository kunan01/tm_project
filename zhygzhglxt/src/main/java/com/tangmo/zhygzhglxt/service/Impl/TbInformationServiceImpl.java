package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbInformationMapper;
import com.tangmo.zhygzhglxt.dao.TbOneClassifyMapper;
import com.tangmo.zhygzhglxt.dao.TbSysUserMapper;
import com.tangmo.zhygzhglxt.dao.TbTwoClassifyMapper;
import com.tangmo.zhygzhglxt.entity.TbInformation;
import com.tangmo.zhygzhglxt.entity.TbOneClassify;
import com.tangmo.zhygzhglxt.entity.TbSysUser;
import com.tangmo.zhygzhglxt.entity.TbTwoClassify;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbInformationService;
import com.tangmo.zhygzhglxt.utility.EncryptUtil;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengge on 2018/11/1.
 */
@Service
public class TbInformationServiceImpl implements TbInformationService {

    @Autowired
    TbInformationMapper tbInformationMapper;

    @Autowired
    TbOneClassifyMapper tbOneClassifyMapper;

    @Autowired
    TbTwoClassifyMapper tbTwoClassifyMapper;

    @Autowired
    TbSysUserMapper tbSysUserMapper;

    @Override
    public Result findInformationByCode(String code) {

        if ("".equals(code) || code == null) {
            return new Result(ResultCode.FAIL, "文章的唯一标识不能为空！");
        }

        TbInformation tbInformation = tbInformationMapper.selectByCode(code);

        if (tbInformation != null) {
            //根据二级栏目查询一级栏目
            TbTwoClassify tbTwoClassify = tbTwoClassifyMapper.selectByTwoClassifCode(tbInformation.getTwoClassifyCode());
            tbInformation.setTwoType(tbTwoClassify.getName());
            if (tbTwoClassify != null) {
                TbOneClassify tbOneClassify = tbOneClassifyMapper.selectByOneClassifCode(tbTwoClassify.getOneClassifyCode());
                if (tbOneClassify != null) {
                    tbInformation.setOneClassifyCode(tbOneClassify.getOneClassifyCode());
                    tbInformation.setOneType(tbOneClassify.getName());
                }
            }
        }

        return new Result(ResultCode.SUCCESS, tbInformation);
    }

    @Override
    public Result deleteByInformationCode(String code) {

        if ("".equals(code) || code == null) {
            return new Result(ResultCode.FAIL, "文章的唯一标识不能为空！");
        }

        tbInformationMapper.deleteByInformationCode(code);

        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result appFindPage(String name, String twoClassifyCode, Integer pageNo, Integer pageSize) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        List<TbInformation> tbInformations = tbInformationMapper.appFindPage(name, twoClassifyCode);
        for (TbInformation tbInformation : tbInformations) {
            TbSysUser tbSysUser = tbSysUserMapper.selectByCode(tbInformation.getUserCode());
            tbInformation.setUserName(tbSysUser.getUserName());
        }
        return new Result(ResultCode.SUCCESS, new PageInfo(tbInformations));
    }

    @Override
    public Result findClassifyCode(String classifCode, Integer pageNo, Integer pageSize, String name) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        List<TbInformation> tbInformations = tbInformationMapper.findClassifyCode(name, classifCode);
        for (TbInformation tbInformation : tbInformations) {
            TbSysUser tbSysUser = tbSysUserMapper.selectByCode(tbInformation.getUserCode());
            tbInformation.setUserName(tbSysUser.getNickName());
        }
        return new Result(ResultCode.SUCCESS, new PageInfo(tbInformations));
    }

    @Override
    public Result addInformation(TbInformation tbInformation) {

        tbInformation.setInformationId(EncryptUtil.get32Uuid());
        tbInformation.setInformationCode(EncryptUtil.get32Uuid());
        tbInformationMapper.insertSelective(tbInformation);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result modifyInfomation(TbInformation tbInformation) {

        tbInformationMapper.updateByCode(tbInformation);
        return new Result(ResultCode.SUCCESS);
    }


    @Override
    public Result getTwoClassifyCode(String classifCode) {

        if (classifCode == null || "".equals(classifCode)) {
            return new Result(ResultCode.FAIL, "一级栏目的唯一标识code不能为空");
        }
        List<TbTwoClassify> tbInformations = tbTwoClassifyMapper.getTwoClassifyCode(classifCode);

        return new Result(ResultCode.SUCCESS, tbInformations);
    }

    @Override
    public Result getOneClassifyCode() {

        List<TbOneClassify> tbOneClassifies = tbOneClassifyMapper.selAllClassify();

        return new Result(ResultCode.SUCCESS, tbOneClassifies);
    }
}
