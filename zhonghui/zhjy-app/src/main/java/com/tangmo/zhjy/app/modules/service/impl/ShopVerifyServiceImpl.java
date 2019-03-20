package com.tangmo.zhjy.app.modules.service.impl;

import com.tangmo.zhjy.app.Result;
import com.tangmo.zhjy.app.enums.ResultCode;
import com.tangmo.zhjy.app.modules.bean.ShopVerify;
import com.tangmo.zhjy.app.modules.dao.ShopVerifyDao;
import com.tangmo.zhjy.app.modules.service.ShopVerifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author boge
 * @date 18/4/7
 * @description
 */
@Service("shopVerifyService")
public class ShopVerifyServiceImpl implements ShopVerifyService {
    @Resource
    private ShopVerifyDao shopVerifyDao;
    private static final Byte NOT_VERIFY = 0;
    @Override
    public Result addShopVerify(ShopVerify shopVerify) {
        if(shopVerify.getUserId() == null || shopVerify.getIdNumber() == null || shopVerify.getShopAddress() == null){
            return new Result(ResultCode.FAIL,"信息不完整");
        }

        if(shopVerify.getShopIntro() == null || shopVerify.getShopName() == null){
            return new Result(ResultCode.FAIL,"信息不完整");
        }

//        //先判断是否有审核信息
        List<ShopVerify> result = shopVerifyDao.selectListByUserId(shopVerify.getUserId());
        if(result!=null){
            for (int i = 0;i < result.size();i++){
                if(result.get(i).getShopState().toString().equals("0")){
                    return new Result(ResultCode.FAIL,"已存在审核信息");
                }else if(result.get(i).getShopState().toString().equals("1")){
                    return new Result(ResultCode.FAIL,"审核已通过");
                }
            }
        }
        shopVerify.setShopState(NOT_VERIFY);
        shopVerifyDao.insertShopVerify(shopVerify);
        return new Result(ResultCode.SUCCESS);

    }

    @Override
    public Result searchSvInfo(Integer userId) {
        List<ShopVerify> shopVerify = shopVerifyDao.selectListByUserId(userId);
        if(shopVerify!=null){
            for (int i = 0;i<shopVerify.size();i++){
                if(shopVerify.get(i).getShopState()==0){
                    return new Result(ResultCode.SHOP_STATE0,shopVerify);
                }else if(shopVerify.get(i).getShopState()==1){
                    return new Result(ResultCode.SHOP_STATE1,shopVerify);
                }
            }
            return new Result(ResultCode.SHOP_STATE2,shopVerify);
        }
        return new Result(ResultCode.SHOP_STATE3,null);
    }

    @Override
    public Result searchAllSvInfo(Integer start, Integer end) {
        return new Result(ResultCode.SUCCESS,shopVerifyDao.selectAll(start, end));
    }

    @Override
    public Result changeSvInfo(ShopVerify shopVerify) {
        shopVerifyDao.updateSvInfo(shopVerify);
        shopVerifyDao.updateSvState(shopVerify.getSvId(),(byte)0);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    public Result updateSvState(Integer svId, Byte state) {
        shopVerifyDao.updateSvState(svId, state);
        return new Result(ResultCode.SUCCESS);
    }
}
