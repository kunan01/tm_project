package com.tangmo.yiliao.service.impl;

import com.tangmo.yiliao.dao.IntegralDao;
import com.tangmo.yiliao.dao.MessageDao;
import com.tangmo.yiliao.entity.Integral;
import com.tangmo.yiliao.entity.Message;
import com.tangmo.yiliao.entity.RsFile;
import com.tangmo.yiliao.entity.SelectUser;
import com.tangmo.yiliao.service.IntegralService;
import com.tangmo.yiliao.utility.code.Result;
import com.tangmo.yiliao.utility.code.ResultUtil;
import com.tangmo.yiliao.utility.util.EncryptUtil;
import com.tangmo.yiliao.utility.util.PinyinTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author boge
 * @date 18/7/6
 * @description
 */
@Service("integralService")
public class IntegralServiceImpl implements IntegralService {

    @Resource
    private IntegralDao integralDao;

    @Override
    public Result getUserIntegralAllCount(SelectUser selectUser) {
        return ResultUtil.success(integralDao.getUserIntegralAllCount(selectUser));
    }

    @Override
    public Result getUserIntegralAll(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart() -1) * selectUser.getEnd());
        if(selectUser.getOriginatorId().equals("")){
            selectUser.setOriginatorId(null);
        }
        if(selectUser.getPeopleId().equals("")){
            selectUser.setPeopleId(null);
        }
        return ResultUtil.success(integralDao.getUserIntegralAll(selectUser));
    }

    @Override
    public Result getIntegralAll() {
        return ResultUtil.success(integralDao.getIntegralAll());
    }

    @Override
    @Transactional
    public Result delIntegralById(String lrId, String userId) {
        if(lrId == null || userId == null){
            return ResultUtil.paramError();
        }
        integralDao.delIntegralById(lrId,EncryptUtil.get32Uuid(),userId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updIntegralById(String lrId, String userId, String lrName, Integer bean) {
        if(lrId == null || userId == null || lrName == null || bean == null){
            return ResultUtil.paramError();
        }

        integralDao.updIntegralById(lrId, userId, lrName, bean);

        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result addIntegralById(Integral integral) {
        PinyinTool tool = new PinyinTool();
        if(integral.getBean() == null || integral.getLrName() == null || integral.getCreateUserId() == null){
            return ResultUtil.paramError();
        }
        integral.setLrId(tool.toPinYin(integral.getLrName(), "", PinyinTool.Type.UPPERCASE));
        try {
            integralDao.addIntegralById(integral);
        }
        catch (Exception e){
            return ResultUtil.error("当前规则名称生成的规则标识重复,请更换后在试！");
        }
        return ResultUtil.success();
    }

    @Override
    public Result getSystemMessage(SelectUser selectUser) {
        selectUser.setStart((selectUser.getStart() - 1)*selectUser.getEnd());
        return ResultUtil.success(integralDao.getSystemMessage(selectUser));
    }

    @Override
    public Result getSystemMessageCount(SelectUser selectUser) {
        return ResultUtil.success(integralDao.getSystemMessageCount(selectUser));
    }

    @Override
    @Transactional
    public Result addSystemMessage(Message message) {
        message.setMiId(EncryptUtil.get32Uuid());
        message.setMiCategory(Byte.parseByte("0"));
        integralDao.addMessage(message);
        integralDao.updMessageStateO();
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result delSystemMessageById(String msId) {
        if(msId == null){
            return ResultUtil.paramError();
        }
        integralDao.delSystemMessageById(msId);
        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result updSystemMessage(Message message) {
        if(message == null){
            return ResultUtil.paramError();
        }
        integralDao.updSystemMessage(message);
        return ResultUtil.success();
    }

    @Override
    public Result getCode() {
        return ResultUtil.success(integralDao.getcode());
    }

    @Override
    @Transactional
    public Result updCode(RsFile rsFile) {
        if(rsFile == null){
            return ResultUtil.paramError();
        }
        integralDao.updCode(rsFile);
        return ResultUtil.success();
    }
}
