package com.tangmo.zhygzhglxt.service.Impl;

import com.github.pagehelper.PageHelper;
import com.tangmo.zhygzhglxt.dao.TbFeedBackMapper;
import com.tangmo.zhygzhglxt.entity.TbFeedBack;
import com.tangmo.zhygzhglxt.enums.ResultCode;
import com.tangmo.zhygzhglxt.service.TbFeedBackService;
import com.tangmo.zhygzhglxt.utility.PageInfo;
import com.tangmo.zhygzhglxt.utility.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chengge on 2018/7/10.
 */
@Service
public class TbFeedBackServiceImpl implements TbFeedBackService {

    @Autowired
    protected TbFeedBackMapper tbFeedBackMapper;

    /*
     * 增加反馈信息
     * */
    @Override
    public Result addFeedBack(TbFeedBack tbFeedBack) {

        return new Result(ResultCode.SUCCESS, tbFeedBackMapper.insert(tbFeedBack));
    }

    /*
     * 删除反馈信息
     * */
    @Override
    public Result delFeedBackById(Integer fbId) {

        return new Result(ResultCode.SUCCESS, tbFeedBackMapper.deleteByPrimaryKey(fbId));
    }

    /*
     * 查询反馈信息
     *fbState：传0 查询未读 传1查询已读 不传：查询全部
     * */
    @Override
    public Result selFeedBack(String name, Integer fbState, Integer pageSize, Integer pageNo) {

        if (pageSize != null && pageNo != null) {
            PageHelper.startPage(pageNo, pageSize);
        }
        if ("3".equals(fbState) || fbState == 3) {
            fbState = null;
        }
        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = "%%";
        }
        List<TbFeedBack> TbFeedBacks = tbFeedBackMapper.selFeedBack(name, fbState);
        if (TbFeedBacks.size() > 0) {
//            for(TbFeedBack tbFeedBack:TbFeedBacks){
//                tbFeedBack.setUserName(TbSysUserMapper.selectByPrimaryKey(tbFeedBack.getUserId()).getName());
//            }
        }
        return new Result(ResultCode.SUCCESS, new PageInfo(TbFeedBacks));
    }

    /*
     * 解决反馈信息
     * */
    @Override
    public Result verifyFeedBack(Integer fbId) {

        return new Result(ResultCode.SUCCESS, tbFeedBackMapper.verifyFeedBack(fbId));
    }


}
