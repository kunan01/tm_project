package com.tangmo.emall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.ShopPropDao;
import com.tangmo.emall.entity.PropKey;
import com.tangmo.emall.entity.PropValue;
import com.tangmo.emall.entity.RecommendAdvertising;
import com.tangmo.emall.service.ShopPropService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("shopPropService")
public class ShopPropServiceImpl implements ShopPropService {

    @Resource
    private ShopPropDao shopPropDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    @Transactional
    public Result addPropKey(PropKey propKey) {
        try {

            if(propKey == null || propKey.getKeyName() == null || propKey.getShopId() == null){
                return ResultUtil.paramError();
            }

            //校验当前key是否存在
            PropKey propKey1 = shopPropDao.getPropKeyByName(propKey);
            if(propKey1 != null){
                return ResultUtil.dataError();
            }

            shopPropDao.addPropKey(propKey);

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("添加店铺规格key接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }

    }

    @Override
    @Transactional
    public Result addPropValue(PropValue propValue) {
        try {

            if(propValue == null || propValue.getKeyId() == null || propValue.getPropValue() == null){
                return ResultUtil.paramError();
            }

            //校验key是否存在
            PropKey propKey = shopPropDao.getPropKeyById(propValue.getKeyId());
            if(propKey == null){
                return ResultUtil.dataNoByError();
            }

            //校验当前value是否存在
            PropValue propValue1 = shopPropDao.getPropValueByName(propValue);
            if(propValue1 != null){
                return ResultUtil.dataError();
            }

            shopPropDao.addPropValue(propValue);
            return ResultUtil.success("添加成功");
        }catch (Exception e){

            System.out.println("添加店铺规格Value接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delPropValue(PropValue propValue) {
        try {

            if(propValue == null || propValue.getValueId() == null){
                return ResultUtil.paramError();
            }

            //校验当前value是否存在
            PropValue propValue1 = shopPropDao.getPropValueById(propValue.getValueId());
            if(propValue1 == null){
                return ResultUtil.dataNoError();
            }

            //删除
            shopPropDao.delPropValue(propValue.getValueId());
            return ResultUtil.success("删除成功");
        }catch (Exception e){

            System.out.println("删除店铺规格Value接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelPropValue(PropValue propValue) {
        try {

            if(propValue == null || propValue.getValueIdList() == null){
                return ResultUtil.paramError();
            }
            if(propValue.getValueIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (Integer valueId: propValue.getValueIdList()) {
                //校验当前value是否存在
                PropValue propValue1 = shopPropDao.getPropValueById(valueId);
                if(propValue1 != null){
                    //删除
                    shopPropDao.delPropValue(valueId);

                }

            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){

            System.out.println("删除店铺规格Value接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delPropKey(PropKey propKey) {
        try {

            if(propKey == null || propKey.getKeyId() == null){
                return ResultUtil.paramError();
            }

            //校验当前Key是否存在
            PropKey propKey1 = shopPropDao.getPropKeyById(propKey.getKeyId());
            if(propKey1 == null){
                return ResultUtil.dataNoError();
            }

            //删除当前Key下的Value
            shopPropDao.delPropValueByKeyId(propKey.getKeyId());

            //删除当前Key
            shopPropDao.delPropKey(propKey.getKeyId());

            return ResultUtil.success("删除成功");
        }catch (Exception e){

            System.out.println("删除店铺规格Key接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result batchDelPropKey(PropKey propKey) {
        try {

            if(propKey == null || propKey.getKeyIdList() == null){
                return ResultUtil.paramError();
            }
            if(propKey.getKeyIdList().length == 0){
                return ResultUtil.paramError();
            }

            for (Integer keyId: propKey.getKeyIdList()) {
                //校验当前Key是否存在
                PropKey propKey1 = shopPropDao.getPropKeyById(keyId);
                if(propKey1 != null){
                    //删除当前Key下的Value
                    shopPropDao.delPropValueByKeyId(keyId);

                    //删除当前Key
                    shopPropDao.delPropKey(keyId);

                }
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){

            System.out.println("删除店铺规格Key接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getIsValueByKey(Integer keyId) {
        try {

            if(keyId == null){
                return ResultUtil.paramError();
            }

            //校验当前Key是否存在
            PropKey propKey1 = shopPropDao.getPropKeyById(keyId);
            if(propKey1 == null){
                return ResultUtil.dataNoError();
            }

            List<PropValue> propValueList = shopPropDao.getPropValueByKeyId(keyId);

            if(propValueList != null && propValueList.size() != 0){
                return ResultUtil.success(true);
            }else{
                return ResultUtil.success(false);
            }
        }catch (Exception e){

            System.out.println("校验当前key下是否存在value接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getListKey(Integer shopId,Integer state,Integer pageNo,Integer pageSize) {
        try {

            if(shopId == null || state == null){
                return ResultUtil.paramError();
            }

            if(state == 2){
                if(pageNo == null || pageSize == null){
                    return ResultUtil.paramError();
                }

                PageHelper.startPage(pageNo,pageSize);

                List<PropKey> propKeys = shopPropDao.getPropKeyByShopIdAndCId(shopId);

                PageInfo<PropKey> page = new PageInfo<>(propKeys);

                return ResultUtil.success(page);
            }else{

                List<PropKey> propKeys = shopPropDao.getPropKeyByShopIdAndCId(shopId);

                PageInfo<PropKey> page = new PageInfo<>(propKeys);

                return ResultUtil.success(page);
            }
        }catch (Exception e){
            System.out.println("获取key集合接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getListValue(Integer keyId, Integer pageNo, Integer pageSize) {
        try {

            if(keyId == null || pageNo == null || pageSize == null){
                return ResultUtil.paramError();
            }

            PageHelper.startPage(pageNo,pageSize);

            List<PropValue> list = shopPropDao.getPropValueByKeyId(keyId);

            PageInfo<PropValue> page = new PageInfo<>(list);

            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("获取value集合接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result queryPropList() {
        try {

            List<PropKey> propKeys = shopPropDao.getPropKeyByShopIdAndCId(1);
            if(propKeys != null){
                for (PropKey propkey :propKeys) {
                    propkey.setValueList(shopPropDao.getPropValueByKeyId(propkey.getKeyId()));
                }
            }

            return ResultUtil.success(propKeys);
        }catch (Exception e){
            System.out.println("获取value集合接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updPropKey(PropKey propKey) {
        try {

            if(propKey == null || propKey.getKeyId() == null || propKey.getKeyName() == null){
                return ResultUtil.paramError();
            }

            //校验当前Key是否存在
            PropKey propKey1 = shopPropDao.getPropKeyById(propKey.getKeyId());

            if(propKey1 == null){
                return ResultUtil.dataNoError();
            }

            //修改
            shopPropDao.updPropKey(propKey);

            return ResultUtil.success("修改成功");
        }catch (Exception e){

            System.out.println("修改店铺规格key 的属性名称接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updPropValue(PropValue propValue) {
        try {

            if(propValue == null || propValue.getValueId() == null || propValue.getPropValue() == null){
                return ResultUtil.paramError();
            }

            //校验当前Value是否存在
            PropValue propValue1 = shopPropDao.getPropValueById(propValue.getValueId());

            if(propValue1 == null){
                return ResultUtil.dataNoError();
            }
            //修改
            shopPropDao.updPropValue(propValue);
            return ResultUtil.success("修改成功");
        }catch (Exception e){

            System.out.println("修改店铺规格value 的属性名称接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
