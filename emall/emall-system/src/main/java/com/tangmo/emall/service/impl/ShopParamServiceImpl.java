package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.ShopParamDao;
import com.tangmo.emall.entity.ParamType;
import com.tangmo.emall.entity.ParamValue;
import com.tangmo.emall.entity.RsFile;
import com.tangmo.emall.service.ShopParamService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("shopParamService")
public class ShopParamServiceImpl implements ShopParamService {

    @Resource
    private ShopParamDao shopParamDao;

    @Resource
    private FileDao fileDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;


    @Override
    @Transactional
    public Result addShopParamType(ParamType paramType) {
        try {

            if(paramType == null || paramType.getShopId() == null || paramType.getTypeName() == null){
                return ResultUtil.paramError();
            }

            //校验是否存在重复值
            ParamType paramType1 = shopParamDao.getParamTypeByName(paramType);

            if(paramType1 != null){
                return ResultUtil.dataError();
            }

            shopParamDao.addShopParamType(paramType);

            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("添加完成");
        }catch (Exception e){
            System.out.println("增加店铺参数类型接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addShopParamValue(ParamValue paramValue) {
        try {

            if(paramValue == null || paramValue.getParamValue() == null || paramValue.getTypeId() == null){
                return ResultUtil.paramError();
            }

            //校验类型是否存在
            ParamType paramType = shopParamDao.getParamTypeById(paramValue.getTypeId());
            if (paramType == null) {
                return ResultUtil.dataNoByError();
            }

            //校验是否存在重复值
            ParamValue paramValue1 = shopParamDao.getParamValueByName(paramValue);

            if(paramValue1 != null){
                return ResultUtil.dataError();
            }

            //判断是否上传图片
            if(paramValue.getParamImage() != null){

                RsFile rsFile =fileDao.getFileById(paramValue.getParamImage());
                if(rsFile == null){
                    return ResultUtil.imgError();
                }

                fileDao.updFile(paramValue.getParamImage());
            }

            shopParamDao.addShopParamValue(paramValue);

            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("添加完成");
        }catch (Exception e){
            System.out.println("增加店铺参数值接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updShopParamType(ParamType paramType) {
        try {

            if(paramType == null || paramType.getTypeId() == null || paramType.getTypeName() == null){
                return ResultUtil.paramError();
            }

            //校验类型是否存在
            ParamType paramType1 = shopParamDao.getParamTypeById(paramType.getTypeId());

            if (paramType1 == null) {
                return ResultUtil.dataNoByError();
            }

            paramType.setState(null);
            shopParamDao.updShopParamType(paramType);

            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("修改完成");
        }catch (Exception e){
            System.out.println("修改店铺参数类型接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updShopParamValue(ParamValue paramValue) {
        try {

            if(paramValue == null || paramValue.getValueId() == null || paramValue.getParamValue() == null || paramValue.getTypeId() == null){
                return ResultUtil.paramError();
            }

            //校验类型是否存在
            ParamType paramType = shopParamDao.getParamTypeById(paramValue.getTypeId());
            if (paramType == null) {
                return ResultUtil.dataNoByError();
            }

            //校验当前数据是否存在
            ParamValue paramValue1 = shopParamDao.getParamValueById(paramValue.getValueId());

            if(paramValue1 == null){
                return ResultUtil.dataNoByError();
            }
            System.out.println(paramValue1);
            System.out.println(paramValue);
            //判断是否上传图片
            if(paramValue.getParamImage() != null){
                if(paramValue1.getParamImage() != null){
                    if(!paramValue.getParamImage().equals(paramValue1.getParamImage())){
                        RsFile rsFile =fileDao.getFileById(paramValue.getParamImage());

                        if(rsFile == null){
                            return ResultUtil.imgError();
                        }

                        //删除原有图片
                        fileDao.delFile(paramValue1.getParamImage());

                        //修改图片为已用状态
                        fileDao.updFile(paramValue.getParamImage());
                    }
                }
            }

            shopParamDao.updShopParamValue(paramValue);

            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("修改完成");
        }catch (Exception e){
            System.out.println("修改店铺参数值接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delShopParamValue(Integer valueId) {
        try {

            if(valueId == null){
                return ResultUtil.paramError();
            }

            //校验当前数据是否存在
            ParamValue paramValue1 = shopParamDao.getParamValueById(valueId);

            if(paramValue1 == null){
                return ResultUtil.dataNoError();
            }

            shopParamDao.delShopParamValue(valueId);

            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("删除完成");
        }catch (Exception e){
            System.out.println("删除店铺参数值接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelShopParamValue(ParamValue paramValue) {
        try {

            if(paramValue == null || paramValue.getValueIdList() == null){
                return ResultUtil.paramError();
            }
            if(paramValue.getValueIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (int i = 0;i<paramValue.getValueIdList().length;i++){
                //校验当前数据是否存在
                ParamValue paramValue1 = shopParamDao.getParamValueById(paramValue.getValueIdList()[i]);

                if(paramValue1 != null){
                    shopParamDao.delShopParamValue(paramValue.getValueIdList()[i]);
                }

            }

            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("删除完成");
        }catch (Exception e){
            System.out.println("删除店铺参数值接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delShopParamType(Integer typeId) {
        try {

            if(typeId == null){
                return ResultUtil.paramError();
            }

            //校验当前数据是否存在
            ParamType paramType = shopParamDao.getParamTypeById(typeId);

            if(paramType == null){
                return ResultUtil.dataNoError();
            }

            shopParamDao.delShopParamType(typeId);
            shopParamDao.delShopParamValueByTypeId(typeId);

            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("删除完成");
        }catch (Exception e){
            System.out.println("删除店铺参数类型接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelShopParamType(ParamType paramType) {
        try {

            if(paramType == null || paramType.getTypeIdList() == null){
                return ResultUtil.paramError();
            }

            if(paramType.getTypeIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (int i = 0;i<paramType.getTypeIdList().length;i++){
                //校验当前数据是否存在
                ParamType paramType1 = shopParamDao.getParamTypeById(paramType.getTypeIdList()[i]);

                if(paramType1 != null){
                    shopParamDao.delShopParamType(paramType.getTypeIdList()[i]);
                    shopParamDao.delShopParamValueByTypeId(paramType.getTypeIdList()[i]);
                }
            }


            String rediskey = "ParamList";

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("删除完成");
        }catch (Exception e){
            System.out.println("删除店铺参数类型接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result getShopParamList(Integer shopId,Integer state,Integer pageNo,Integer pageSize) {
        try {

            if(shopId == null || state == null){
                return ResultUtil.paramError();
            }

            if(state == 2){
                if(pageNo == null || pageSize == null){
                    return ResultUtil.paramError();
                }

                PageHelper.startPage(pageNo,pageSize);

                PageInfo<ParamType> page = new PageInfo<>(shopParamDao.getParamTypeListByShopId(shopId));

                return ResultUtil.success(page);
            }else{
                List<ParamType> paramTypes = shopParamDao.getParamTypeListByShopId(shopId);

                PageInfo<ParamType> page = new PageInfo<>(paramTypes);

                return ResultUtil.success(page);
            }


        }catch (Exception e){
            System.out.println("查询店铺参数接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result getShopParamValueList(Integer typeId, Integer pageNo, Integer pageSize) {
        try {

            if(typeId == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            PageHelper.startPage(pageNo,pageSize);

            List<ParamValue> values = shopParamDao.getParamValueListByTypeId(typeId);

            PageInfo<ParamValue> page = new PageInfo<>(values);

            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("查询店铺参数接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result queryShopParamList() {
        try {
            List<ParamType> paramTypeList = shopParamDao.getParamTypeListByShopId(1);
            if(paramTypeList != null){
                for (ParamType paramType: paramTypeList) {
                    paramType.setParamValues(shopParamDao.getParamValueListByTypeId(paramType.getTypeId()));
                }
            }
            return ResultUtil.success(paramTypeList);
        }catch (Exception e){
            System.out.println("查询店铺参数接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
