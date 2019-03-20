package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.SupportDao;
import com.tangmo.emall.entity.CustomerService;
import com.tangmo.emall.entity.MessageUs;
import com.tangmo.emall.service.SupportService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("supportService")
public class SupportServiceImpl implements SupportService {

    @Resource
    private SupportDao supportDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public Result getUsersMessageList(Integer state, Integer pageNo, Integer pageSize) {
        try {

            if(state == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }
            PageHelper.startPage(pageNo,pageSize);

            List<MessageUs> messageUsList = supportDao.getUsersMessageList(state);

            PageInfo<MessageUs> pageInfo = new PageInfo<>(messageUsList);

            return ResultUtil.success(pageInfo);

        }catch (Exception e){
            return ResultUtil.serviceError();
        }

    }

    @Override
    @Transactional
    public Result dealWithMessage(Integer mId) {
        try {

            if(mId == null){
                return ResultUtil.paramError();
            }

            //校验数据是否存在
            MessageUs messageUs = supportDao.getUsersMessage(mId);
            if(messageUs == null){
                return ResultUtil.dataNoError();
            }
            //校验处理状态
            if(messageUs.getState().toString().equals("1")){
                return ResultUtil.error("该反馈信息已处理过！");
            }

            //处理反馈信息（更换状态）
            supportDao.updMessageUsState(mId);

            return ResultUtil.success("处理完成");
        }catch (Exception e){
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDealWithMessage(MessageUs messageUs) {
        try {

            if(messageUs == null || messageUs.getMessageIdList() == null){
                return ResultUtil.paramError();
            }

            if(messageUs.getMessageIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (Integer mId: messageUs.getMessageIdList()) {
                //校验数据是否存在
                MessageUs messageUs1 = supportDao.getUsersMessage(mId);
                if(messageUs1 != null){
                    if(!messageUs1.getState().toString().equals("1")){
                        //处理反馈信息（更换状态）
                        supportDao.updMessageUsState(mId);
                    }
                }
            }

            return ResultUtil.success("处理完成");
        }catch (Exception e){
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getCustomerService() {
        try {
            List<CustomerService> customerService = supportDao.getCustomerService();
            return ResultUtil.success(customerService);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("获取客服服务信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updateCustomerService(CustomerService customerService) {
        try {

            if(customerService == null || customerService.getServiceId() == null || customerService.getServiceText() == null){
                return ResultUtil.paramError();
            }

            if(customerService.getServiceId().equals("") || customerService.getServiceText().equals("")){
                return ResultUtil.paramError();
            }
            supportDao.updateCustomerService(customerService);

            String key = "CustomerService";
            if(jedisKeys.exists(key)){
                jedisKeys.del(key);
            }

            return ResultUtil.success();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("修改客服服务信息接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
