package com.tangmo.emall.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tangmo.emall.dao.CateGoryDao;
import com.tangmo.emall.dao.FileDao;
import com.tangmo.emall.dao.ProductDao;
import com.tangmo.emall.dao.ShopParamDao;
import com.tangmo.emall.entity.*;
import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.entity.dto.ProductImageDto;
import com.tangmo.emall.entity.dto.ProductSpecDto;
import com.tangmo.emall.entity.dto.ProductUpdDto;
import com.tangmo.emall.service.ProductService;
import com.tangmo.emall.utils.Result;
import com.tangmo.emall.utils.ResultUtil;
import com.tangmo.emall.utils.jedis.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDao productDao;

    @Resource
    private FileDao fileDao;

    @Resource
    private CateGoryDao cateGoryDao;

    @Resource
    private ShopParamDao shopParamDao;

    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    @Transactional
    public Result addProduct(ProductUpdDto productUpdDto) {
        try {
            //非空校验
            if(productUpdDto == null || productUpdDto.getProductName() == null || productUpdDto.getCategoryId() == null
                    || productUpdDto.getDescript() == null || productUpdDto.getBaseProp() == null || productUpdDto.getProductImage() == null){
                return ResultUtil.paramError();
            }

            //校验json数据格式
            try {
                JSONObject.parseObject(productUpdDto.getBaseProp());
            }catch (Exception e){
                return ResultUtil.jsonError();
            }

            //校验图片是否存在
            RsFile rsFile = fileDao.getFileById(productUpdDto.getProductImage());
            if(rsFile == null){
                return ResultUtil.imgError();
            }

            //把图片改为已用状态
            fileDao.updFile(productUpdDto.getProductImage());

            Product product = new Product(productUpdDto);
            //添加商品
            productDao.addProduct(product);

            if(productUpdDto.getParamIdList() != null){
                if(productUpdDto.getParamIdList().length != 0){

                    ProductParam productParam = new ProductParam();
                    productParam.setProductId(product.getProductId());
                    for (Integer paramId: productUpdDto.getParamIdList()) {
                        productParam.setValueId(paramId);
                        //添加标签
                        productDao.addProductParam(productParam);
                    }
                }
            }

            if(productUpdDto.getProductSpecDtoList() != null){
                if(productUpdDto.getProductSpecDtoList().size() != 0){

                    ProductSpec productSpec = new ProductSpec();
                    productSpec.setProductId(product.getProductId());
                    for (ProductSpecDto specDto: productUpdDto.getProductSpecDtoList()) {
                        productSpec.setProductSpecs(specDto.getProductSpecs());
                        productSpec.setStock(specDto.getStock());
                        productSpec.setPrice(specDto.getPrice());
                        //添加规格
                        productDao.addProductProp(productSpec);

                        if(specDto.getProductImageDtoList() != null){
                            if(specDto.getProductImageDtoList().size() != 0){
                                ProductImage productImage = new ProductImage();
                                productImage.setProductId(product.getProductId());
                                productImage.setSpecId(productSpec.getSpecId());
                                for (ProductImageDto productImageDto: specDto.getProductImageDtoList()) {

                                    //校验图片是否存在
                                    RsFile rsFile1 = fileDao.getFileById(productImageDto.getImageUrl());

                                    if(rsFile1 != null){
                                        //把图片改为已用状态
                                        fileDao.updFile(productImageDto.getImageUrl());
                                        productImage.setImageDesc(productImageDto.getImageDesc());
                                        productImage.setIsMaster(productImageDto.getIsMaster());
                                        productImage.setImageUrl(productImageDto.getImageUrl());
                                        productImage.setImageOrder(productImageDto.getImageOrder());
                                        productImage.setImageStatus(productImageDto.getImageStatus());
                                        //添加规格图片
                                        productDao.addProductPropImage(productImage);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("添加商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updProduct(ProductUpdDto productUpdDto) {
        //非空校验
        if(productUpdDto == null || productUpdDto.getProductId() == null){
            return ResultUtil.paramError();
        }

        Product product1 = productDao.getProductById(productUpdDto.getProductId());
        if(product1 == null){
            return ResultUtil.dataNoError();
        }

        if(product1.getPublishStatus().toString().equals("1")){
            return ResultUtil.error("请先下架商品在进行修改操作");
        }

        Product product = new Product(productUpdDto);

        if(product.getBaseProp() != null){
            //校验json数据格式
            try {
                JSONObject.parseObject(product.getBaseProp());
            }catch (Exception e){
                return ResultUtil.jsonError();
            }
        }

        if(product.getProductImage() != null){
            //校验图片是否存在
            RsFile rsFile = fileDao.getFileById(product.getProductImage());

            if(rsFile == null){
                return ResultUtil.imgError();
            }

            if(!product.getProductImage().equals(rsFile.getFId())){
                //删除原图片
                fileDao.delFile(product1.getProductImage());

                //把新图片改为已用状态
                fileDao.updFile(product.getProductImage());
            }

        }

        //修改商品
        productDao.updProduct(product);

        if(productUpdDto.getParamIdList() != null){
            if(productUpdDto.getParamIdList().length != 0){

                //清空标签
                productDao.delProductParamByPId(product.getProductId());
                ProductParam productParam = new ProductParam();
                productParam.setProductId(product.getProductId());
                for (Integer paramId: productUpdDto.getParamIdList()) {
                    productParam.setValueId(paramId);
                    //添加标签
                    productDao.addProductParam(productParam);
                }
            }
        }

        if(productUpdDto.getProductSpecDtoList() != null){
            if(productUpdDto.getProductSpecDtoList().size() != 0){

                //清空商品规格
                productDao.delProductSpecByPId(product.getProductId());

                //清空商品图片
                productDao.delProductImageByPId(product.getProductId());

                ProductSpec productSpec = new ProductSpec();
                productSpec.setProductId(product.getProductId());
                for (ProductSpecDto specDto: productUpdDto.getProductSpecDtoList()) {
                    productSpec.setProductSpecs(specDto.getProductSpecs());
                    productSpec.setStock(specDto.getStock());
                    productSpec.setPrice(specDto.getPrice());
                    //添加规格
                    productDao.addProductProp(productSpec);

                    if(specDto.getProductImageDtoList() != null){
                        if(specDto.getProductImageDtoList().size() != 0){
                            ProductImage productImage = new ProductImage();
                            productImage.setProductId(product.getProductId());
                            productImage.setSpecId(productSpec.getSpecId());
                            for (ProductImageDto productImageDto: specDto.getProductImageDtoList()) {

                                //校验图片是否存在
                                RsFile rsFile = fileDao.getFileById(productImageDto.getImageUrl());

                                if(rsFile != null){
                                    //把图片改为已用状态
                                    fileDao.updFile(productImageDto.getImageUrl());

                                    productImage.setImageDesc(productImageDto.getImageDesc());
                                    productImage.setIsMaster(productImageDto.getIsMaster());
                                    productImage.setImageUrl(productImageDto.getImageUrl());
                                    productImage.setImageOrder(productImageDto.getImageOrder());
                                    productImage.setImageStatus(productImageDto.getImageStatus());

                                    //添加规格图片
                                    productDao.addProductPropImage(productImage);
                                }
                            }
                        }
                    }
                }
            }
        }

        return ResultUtil.success();
    }

    @Override
    @Transactional
    public Result addProductProp(ProductSpec productSpec) {
        try {

            //非空校验
            if(productSpec == null || productSpec.getProductId() == null || productSpec.getProductSpecs() == null ||
                    productSpec.getStock() == null || productSpec.getPrice() == null || productSpec.getProductImages() == null){
                return ResultUtil.paramError();
            }


            //校验商品是否存在
            Product product1 = productDao.getProductById(productSpec.getProductId());

            if(product1 == null){
                return ResultUtil.dataNoError();
            }

            if(product1.getPublishStatus().toString().equals("1")){
                return ResultUtil.error("请先下架商品在进行修改操作");
            }

            //校验json数据格式
            try {
                JSONObject.parseObject(productSpec.getProductSpecs());
            }catch (Exception e){
                return ResultUtil.jsonError();
            }

            //添加商品规格
            productDao.addProductProp(productSpec);

            List<ProductImage> list = productSpec.getProductImages();

            for (int i = 0;i < list.size();i++){

                //校验图片是否存在
                RsFile rsFile = fileDao.getFileById(list.get(i).getImageUrl());

                if(rsFile == null){
                    return ResultUtil.imgError();
                }

                //把图片改为已用状态
                fileDao.updFile(list.get(i).getImageUrl());

                list.get(i).setSpecId(productSpec.getSpecId());
                list.get(i).setProductId(productSpec.getProductId());

                //添加规格图片
                productDao.addProductPropImage(list.get(i));
            }

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("添加商品规格接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result updProductProp(ProductSpec productSpec) {
        try {

            //非空校验
            if(productSpec == null || productSpec.getProductId() == null){
                return ResultUtil.paramError();
            }

            //校验规格是否存在
            ProductSpec product1 = productDao.getProductSpecById(productSpec.getSpecId());

            if(product1 == null){
                return ResultUtil.dataNoError();
            }

            Product product = productDao.getProductById(product1.getProductId());

            if(product == null){
                return ResultUtil.dataNoError();
            }

            if(product.getPublishStatus().toString().equals("1")){
                return ResultUtil.error("请先下架商品在进行修改操作");
            }

            if(productSpec.getProductSpecs() != null){
                //校验json数据格式
                try {
                    JSONObject.parseObject(productSpec.getProductSpecs());
                }catch (Exception e){
                    return ResultUtil.jsonError();
                }
            }

            List<ProductImage> list = productSpec.getProductImages();

            if(list != null){
                for (int i = 0; i<list.size(); i++){
                    if(list.get(i).getImageUrl() != null){

                        //校验图片是否存在
                        RsFile rsFile = fileDao.getFileById(list.get(i).getImageUrl());

                        if(rsFile == null){
                            return ResultUtil.imgError();
                        }

                        ProductImage productImage = productDao.getProductImageById(list.get(i).getImageId());

                        //删除原图片
                        fileDao.delFile(productImage.getImageUrl());

                        try {
                            //把新图片改为已用状态
                            fileDao.updFile(list.get(i).getImageUrl());
                        }catch (Exception e){
                            throw new RuntimeException();
                        }
                    }
                    //替换原有图片
                    productDao.updProductImage(list.get(i));
                }

            }

            //修改商品规格
            productDao.updProductProp(productSpec);

            return ResultUtil.success();
        }catch (Exception e){
            System.out.println("修改商品规格接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delProductProp(ProductSpec productSpec) {
        try {

            if(productSpec == null || productSpec.getSpecId() == null){
                return ResultUtil.paramError();
            }

            ProductSpec productSpec1 = productDao.getProductSpecById(productSpec.getSpecId());

            if(productSpec1 == null){
                return ResultUtil.dataNoError();
            }

            //删除规格
            productDao.delProductSpecById(productSpec.getSpecId());

            //删除规格图片信息
            productDao.delProductImageById(productSpec.getSpecId());

            //把购物车和未支付订单当前商品改为失效状态
            productDao.updShopCartBySId(productSpec.getSpecId());
            productDao.updOrderBySId(productSpec.getSpecId());

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除商品规格接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delProduct(ProductDto productDto) {
        try {

            if(productDto == null || productDto.getProductIdList() == null){
                return ResultUtil.paramError();
            }
            if(productDto.getProductIdList().length == 0){
                return ResultUtil.paramError();
            }

            Integer success = 0;
            String proId = "";

            for (Integer productId: productDto.getProductIdList()) {
                Product product1 = productDao.getProductById(productId);
                if(product1 != null){
                    if(!product1.getPublishStatus().toString().equals("1")){
                        //删除商品
                        productDao.delProduct(productId);

                        //删除规格
                        productDao.delProductSpecByPId(productId);

                        //删除规格图片信息
                        productDao.delProductImageByPId(productId);

                        //把购物车和未支付订单当前商品改为失效状态
                        productDao.updShopCartByPId(productId);
                        productDao.updOrderByPId(productId);
                        productDao.updCollectByPId(productId);
                        //删除商品标签
                        productDao.delProductParamByPId(productId);
                        success++;
                    }else{
                        if(proId.equals("")){
                            proId = productId.toString();
                        }else{
                            proId = proId + "," + productId.toString();
                        }
                    }
                }else{
                    if(proId.equals("")){
                        proId = productId.toString();
                    }else{
                        proId = proId + "," + productId.toString();
                    }
                }
            }
            if(proId.equals("")){
                return ResultUtil.success("删除成功");
            }else{
                return ResultUtil.error("操作完成，删除成功记录数："+success+"，未删除的商品编号为："+proId+"! 请确认商品下架后在进行删除操作");
            }
        }catch (Exception e){
            System.out.println("删除商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result shelvesProduct(Product product) {
        try {
            if(product == null || product.getProductId() == null){
                return ResultUtil.paramError();
            }

            //校验商品
            Product product1 = productDao.getProductById(product.getProductId());

            if(product1 == null){
                return ResultUtil.dataNoError();
            }

            if(product1.getPublishStatus().toString().equals("1")){
                return ResultUtil.error("当前商品已上架");
            }

            if(!product1.getAuditStatus().toString().equals("1")){
                return ResultUtil.error("该商品未审核，不能进行上架操作");
            }

            List<ProductSpec> productSpecs = productDao.getProductSpecListByPId(product.getProductId());

            if(productSpecs == null){
                return ResultUtil.error("请为该商品添加规格后重试");
            }

            //上架商品
            product.setPublishStatus(Byte.parseByte("1"));

            productDao.updProduct(product);
            return ResultUtil.success("上架成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("上架商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }

    }

    @Override
    @Transactional
    public Result theShelvesProduct(Product product) {
        try {
            if(product == null || product.getProductId() == null){
                return ResultUtil.paramError();
            }

            //校验商品
            Product product1 = productDao.getProductById(product.getProductId());

            if(product1 == null){
                return ResultUtil.dataNoError();
            }

            if(product1.getPublishStatus().toString().equals("0")){
                return ResultUtil.error("当前商品已下架");
            }

            //下架商品
            product.setPublishStatus(Byte.parseByte("0"));

            //把购物车和未支付订单以及收藏当前商品改为失效状态
            productDao.updShopCartByPId(product.getProductId());
            productDao.updOrderByPId(product.getProductId());
            productDao.updCollectByPId(product.getProductId());
            //删除商品标签
            productDao.delProductParamByPId(product.getProductId());

            product.setPublishStatus(Byte.parseByte("0"));

            productDao.updProduct(product);
            return ResultUtil.success("下架成功");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("下架商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result setProductDiscounts(ProductDto productDto) {
        try {
            if(productDto == null || productDto.getProductIdList() == null || productDto.getDiscount() == null){
                return ResultUtil.paramError();
            }
            if(productDto.getProductIdList().length == 0){
                return ResultUtil.paramError();
            }
            if(productDto.getDiscount() <= 0 || productDto.getDiscount() > 100){
                return ResultUtil.error("折扣率输入有误");
            }

            Product product = new Product();
            product.setDisProportion(productDto.getDiscount());
            for (Integer productId: productDto.getProductIdList()) {
                //修改折扣
                product.setProductId(productId);
                productDao.updProduct(product);
            }

            return ResultUtil.success("设置完成");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("设置商品折扣接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getProductList(ProductDto productDto) {
        try {

            if(productDto == null || productDto.getSorting() == null){
                return ResultUtil.paramError();
            }
            if(productDto.getPageNo() != null && productDto.getPageSize() != null){
                PageHelper.startPage(productDto.getPageNo(),productDto.getPageSize());
            }

            PageInfo<Product> page = new PageInfo<>(productDao.getProductListByDto(productDto));

            List<Product> productList = page.getList();

            if(productList != null && productList.size() != 0){
                for (int i = 0; i< productList.size(); i++){

                    productList.get(i).setBasePropJson(JSONObject.parseObject(productList.get(i).getBaseProp()));

//                    List<ProductSpec> specs = productDao.getProductSpecListByPId(productList.get(i).getProductId());
//                    //总库存
//                    int total = 0;
//
//                    if(specs != null){
//                        for (int j = 0; j<specs.size(); j++){
//                            total = total + specs.get(j).getStock();
//                            List<ProductImage> productImages = productDao.getProductImageListBySId(specs.get(j).getSpecId());
//
//                            if(productImages != null){
//                                specs.get(j).setProductImages(productImages);
//                            }
//                        }
//                        productList.get(i).setSpecList(specs);
//                    }

                    productList.get(i).setTotalStock(productDao.getProductSpecTotal(productList.get(i).getProductId()));
                }
                page.setList(productList);
            }
            return ResultUtil.success(page);
        }catch (Exception e){
            System.out.println("查询商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getTrendProductList(ProductDto productDto) {
        try {
            if(productDto.getProductName() == null || productDto.getTaId() == null){
                return ResultUtil.paramError();
            }

            List<Product> productList = productDao.getTrendProductListByDto(productDto);

            return ResultUtil.success(productList);
        }catch (Exception e) {
            System.out.println("查询商品接口异常" + e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getAdvertisingProductList(ProductDto productDto) {
        try {
            if(productDto.getProductName() == null || productDto.getRaId() == null){
                return ResultUtil.paramError();
            }

            List<Product> productList = productDao.getAdvertisingProductList(productDto);

            return ResultUtil.success(productList);
        }catch (Exception e) {
            System.out.println("查询商品接口异常" + e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    public Result getProductListById(Integer productId) {
        try {

            if(productId == null){
                return ResultUtil.paramError();
            }

            Product product = productDao.getProductById(productId);

            if(product != null){

                product.setBasePropJson(JSONObject.parseObject(product.getBaseProp()));

                //查询分类信息
                CateGory cateGory = cateGoryDao.getCateGoryById(product.getCategoryId());
                if(cateGory != null){
                    //查询父级分类信息
                    CateGory cateGory1 = cateGoryDao.getCateGoryById(cateGory.getParentId());

                    cateGory.setParentName(cateGory1.getCategoryName());
                    product.setCateGory(cateGory);
                }

                List<ProductSpec> specs = productDao.getProductSpecListByPId(product.getProductId());
                //总库存
                int total = 0;

                if(specs != null){
                    for (int j = 0; j<specs.size(); j++){

                        specs.get(j).setProductSpecsJson(JSONObject.parseObject(specs.get(j).getProductSpecs()));

                        total = total + specs.get(j).getStock();

                        List<ProductImage> productImages = productDao.getProductImageListBySId(specs.get(j).getSpecId());
                        if(productImages != null){
                            specs.get(j).setProductImages(productImages);
                        }
                    }
                    product.setSpecList(specs);
                }

                List<ProductParam> productParams = productDao.getProductParamByProductId(product.getProductId());
                if(productParams != null){
                    for (ProductParam productParam: productParams) {
                        //查询标签信息
                        ParamValue paramValue = shopParamDao.getParamValueById(productParam.getValueId());
                        if(paramValue != null){
                            ParamType paramType = shopParamDao.getParamTypeById(paramValue.getTypeId());
                            paramValue.setTypeValue(paramType.getTypeName());
                            productParam.setParamValue(paramValue);
                        }
                    }
                }
                product.setParamList(productParams);
            }
            return ResultUtil.success(product);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("查询商品接口异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result setOrCancelNewProduct(String productId, Integer isNew) {
        try {

            if(productId == null || isNew == null){
                return ResultUtil.paramError();
            }
            if(productId.equals("")){
                return ResultUtil.paramError();
            }

            String[] strs = productId.split(",");

            for (int i = 0;i < strs.length; i++){
                productDao.setOrCancelNewProduct(Integer.parseInt(strs[i]),isNew);
            }

            String rediskey = "NewGoods";
            if(jedisKeys.exists(rediskey)){
                jedisKeys.del(rediskey);
            }

            return ResultUtil.success("操作完成");
        }catch (Exception e){
            System.out.println("设置或取消新品商品异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result addProductParam(ProductParam productParam) {
        try {

            if(productParam == null || productParam.getProductId() == null || productParam.getValueId() == null){
                return ResultUtil.paramError();
            }

            //校验是否已有当前标签
            ProductParam productParam1 = productDao.getProductParamByName(productParam);
            if(productParam1 != null){
                return ResultUtil.dataError();
            }

            productDao.addProductParam(productParam);

            return ResultUtil.success("添加成功");
        }catch (Exception e){
            System.out.println("添加商品标签异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }

    @Override
    @Transactional
    public Result delProductParam(String lId) {
        try {

            if(lId == null || lId.equals("")){
                return ResultUtil.paramError();
            }

            String[] strs = lId.split(",");

            for (int i =0; i<strs.length;i++){

                //校验是否已有当前标签
                ProductParam productParam = productDao.getProductParamByById(Integer.parseInt(strs[i]));
                if(productParam != null){
                    productDao.delProductParam(Integer.parseInt(strs[i]));
                }

            }

            return ResultUtil.success("删除成功");
        }catch (Exception e){
            System.out.println("删除商品标签异常"+e.getMessage());
            return ResultUtil.serviceError();
        }
    }
}
