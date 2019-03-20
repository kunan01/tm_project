package com.tangmo.emall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangmo.emall.dao.*;
import com.tangmo.emall.entity.*;
import com.tangmo.emall.service.ShopCartService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.SerializeUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service("shopCartService")
public class ShopCartServiceImpl implements ShopCartService {

    @Resource
    private ShopCartDao shopCartDao;

    @Resource
    private ProductDao productDao;

    @Resource
    private ActivityDao activityDao;

    @Resource
    private PrescriptionDao prescriptionDao;

    @Resource
    private FileDao fileDao;

    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    @Transactional
    public Result addShopCart(ShopCart shopCart) {
        try {
            if(shopCart == null || shopCart.getUserId() == null || shopCart.getProductId() == null || shopCart.getSpecId() == null
                || shopCart.getProductNum() == null){
                return ResultUtil.paramError();
            }

            //校验商品
            Product product = productDao.getProductById(shopCart.getProductId());
            if(product == null){
                return ResultUtil.dataNoError();
            }

            if(product.getDataFlag().toString().equals("1")){
                return ResultUtil.dataNoError();
            }

            //校验规格
            ProductSpec productSpec = productDao.getProductSpecById(shopCart.getSpecId());
            if(productSpec == null){
                return ResultUtil.dataNoError();
            }

            if(productSpec.getDataFlag().toString().equals("1")){
                return ResultUtil.dataNoError();
            }

            //校验商品和规格对应关系
            if(productSpec.getProductId() != product.getProductId()){
                return ResultUtil.dataNoError();
            }

            //校验库存
            if(shopCart.getProductNum() < 1){
                shopCart.setProductNum(1);
            }
            if(shopCart.getProductNum() > productSpec.getStock()){
                shopCart.setProductNum(productSpec.getStock());
            }

            //如果有处方，校验处方信息
            if(shopCart.getPrescriptionId() != null){
                Prescription prescription = prescriptionDao.getPrescriptionById(shopCart.getPrescriptionId());
                if(prescription == null){
                    return ResultUtil.error("处方信息校验失败");
                }
            }

            if(shopCart.getPrescriptionImage() == null){
                shopCart.setPrescriptionImage(null);
            }

            //如果有处方图片
            if(shopCart.getPrescriptionImage() != null){
                //校验图片
                RsFile rsFile = fileDao.getFileById(shopCart.getPrescriptionImage());

                if(rsFile == null){
                    return ResultUtil.imgError();
                }

                //修改图片为已用状态
                fileDao.updFile(shopCart.getPrescriptionImage());
            }

            //校验购物车中是否存在当前商品
            ShopCart shopCart1 = shopCartDao.getCartByProduct(shopCart);
            if(shopCart1 != null){
                //校验库存
                if(shopCart.getProductNum()+shopCart1.getProductNum() < 1){
                    shopCart.setProductNum(1);
                }else if(shopCart.getProductNum()+shopCart1.getProductNum() > productSpec.getStock()){
                    shopCart.setProductNum(productSpec.getStock());
                }else{
                    shopCart.setProductNum(shopCart.getProductNum()+shopCart1.getProductNum());
                }


                shopCart.setCartId(shopCart1.getCartId());
                shopCartDao.updShopCart(shopCart);

                String rediskey = "ShopCartList"+shopCart.getUserId();

                if(jedisKeys.exists(rediskey)){
                    jedisKeys.del(rediskey.getBytes());
                }

                return ResultUtil.success("添加成功");
            }

            //添加购物车
            shopCartDao.addShopCart(shopCart);

            String rediskey = "ShopCartList"+shopCart.getUserId();

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            log.error("购物车模块：'加入购物车'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delShopCart(Integer userId, String cartId) {
        try {
            if(userId == null || cartId == null){
                return ResultUtil.paramError();
            }

            String[] strs = cartId.split(",");

            for (int i = 0;i< strs.length;i++){
                //校验购物车中是否存在当前商品
                ShopCart shopCart1 = shopCartDao.getCartById(Integer.parseInt(strs[i]));

                if(shopCart1 == null){
                    throw new RuntimeException();
                }

                //校验用户身份
                if(shopCart1.getUserId() != userId){
                    throw new RuntimeException();
                }
                shopCartDao.delShopCart(shopCart1.getCartId());
            }

            String rediskey = "ShopCartList"+userId;

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            log.error("购物车模块：'删除购物车'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result emptyShopCart(Integer userId) {
        try {
            if(userId == null){
                return ResultUtil.paramError();
            }

            List<ShopCart> shopCartList = shopCartDao.getCartListByUserId(userId);
            if(shopCartList == null){
                return ResultUtil.error("当前购物车信息为空");
            }

            shopCartDao.emptyShopCart(userId);

            String rediskey = "ShopCartList"+userId;

            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey.getBytes());
            }

            return ResultUtil.success("清空完成");
        }catch (Exception e){
            log.error("购物车模块：'清空购物车'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updateTheNumber(ShopCart shopCart) {
        try {
            if(shopCart == null || shopCart.getUserId() == null || shopCart.getCartId() == null || shopCart.getProductNum() == null){
                return ResultUtil.paramError();
            }

            //校验购物车中是否存在当前商品
            ShopCart shopCart1 = shopCartDao.getCartById(shopCart.getCartId());
            if(shopCart1 == null){
                return ResultUtil.dataNoError();
            }

            if(shopCart.getUserId() != shopCart1.getUserId()){
                return ResultUtil.addressUserError();
            }

            if(shopCart1.getDataFailure().toString().equals("1")){
                return ResultUtil.error("当前商品已失效！不能进行修改");
            }

            ProductSpec productSpec = productDao.getProductSpecById(shopCart1.getSpecId());

            if(shopCart.getProductNum() < 1){
                shopCart.setProductNum(1);
            }
            if(shopCart.getProductNum() > productSpec.getStock()){
                shopCart.setProductNum(productSpec.getStock());
            }

            //更新购物车数量
            shopCartDao.updShopCart(shopCart);

            String rediskey = "ShopCartList"+shopCart.getUserId();

            if(jedisKeys.exists(rediskey)){
                List<ShopCart> shopCarts = (List<ShopCart>) SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));

                for (int i = 0;i < shopCarts.size();i++){
                    if(shopCarts.get(i).getCartId().toString().equals(shopCart.getCartId().toString())){
                        shopCarts.get(i).setProductNum(shopCart.getProductNum());
                        break;
                    }
                }
                jedisKeys.del(rediskey.getBytes());

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(shopCarts));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);
            }

            return ResultUtil.success("更改完成");
        }catch (Exception e){
            log.error("购物车模块：'更改购物车数量'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getShopCartList(Integer userId,Integer pageNo,Integer pageSize) {
        try {

            if(userId == null || pageNo==null || pageSize == null){
                return ResultUtil.paramError();
            }

            String rediskey = "ShopCartList"+userId;

            if(!jedisKeys.exists(rediskey)){
                List<ShopCart> shopCarts = shopCartDao.getCartListByUserId(userId);

                jedisStrings.set(rediskey.getBytes(), SerializeUtil.serialize(shopCarts));
                //设置过期时间两个小时
                jedisStrings.expire(rediskey.getBytes(),7200);

                PageHelper.startPage(pageNo,pageSize);

                PageInfo<ShopCart> page = new PageInfo<ShopCart>(shopCarts);

                List<ShopCart> shopCartList = page.getList();

                if(shopCartList != null && shopCartList.size() != 0){
                    for (int i = 0; i < shopCartList.size(); i++){

                        Product product = productDao.getProductById(shopCartList.get(i).getProductId());

                        ProductSpec productSpec = productDao.getProductSpecById(shopCartList.get(i).getSpecId());

                        shopCartList.get(i).setProduct(product);
                        shopCartList.get(i).setProductSpec(productSpec);
                    }
                    page.setList(shopCartList);
                }



                return ResultUtil.success(page);
            }else{
                List<ShopCart> shopCarts = (List<ShopCart>) SerializeUtil.deserialize(jedisStrings.get(rediskey.getBytes()));

                PageHelper.startPage(pageNo,pageSize);

                PageInfo<ShopCart> page = new PageInfo<ShopCart>(shopCarts);

                List<ShopCart> shopCartList = page.getList();

                if(shopCartList != null && shopCartList.size() != 0){
                    for (int i = 0; i < shopCartList.size(); i++){

                        Product product = productDao.getProductById(shopCartList.get(i).getProductId());

                        ProductSpec productSpec = productDao.getProductSpecById(shopCartList.get(i).getSpecId());

                        shopCartList.get(i).setProduct(product);
                        shopCartList.get(i).setProductSpec(productSpec);
                    }
                    page.setList(shopCartList);
                }
                return ResultUtil.success(page);
            }

        }catch (Exception e){
            log.error("购物车模块：'查看用户购物车'接口异常："+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
