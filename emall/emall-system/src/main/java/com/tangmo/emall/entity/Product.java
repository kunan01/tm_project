package com.tangmo.emall.entity;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.tangmo.emall.entity.dto.ProductDto;
import com.tangmo.emall.entity.dto.ProductUpdDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Data
public class Product implements Serializable {

    public Product(){}

    public Product(ProductUpdDto productUpdDto){
        this.productId = productUpdDto.getProductId();
        this.productName = StringUtil.isEmpty(productUpdDto.getProductName())?null:productUpdDto.getProductName();
        this.baseProp = StringUtil.isEmpty(productUpdDto.getBaseProp())?null:productUpdDto.getBaseProp();
        this.productImage = StringUtil.isEmpty(productUpdDto.getProductImage())?null:productUpdDto.getProductImage();
        this.categoryId = productUpdDto.getCategoryId()==null?null:productUpdDto.getCategoryId();
        this.descript = StringUtil.isEmpty(productUpdDto.getDescript())?null:productUpdDto.getDescript();
        this.price = productUpdDto.getPrice()==null?null:productUpdDto.getPrice();
        this.publishStatus = productUpdDto.getPublishStatus()==null?null:productUpdDto.getPublishStatus();
    }

    //商品表主键
    private Integer productId;

    //店铺id
    private Integer shopId;

    //商品名
    private String productName;

    //品牌id
    private Integer brandId;

    //商品分类id   三级id
    private Integer categoryId;

    //商品价格
    private Double price;

    //上下架状态 0未上架 1已上架
    private Byte publishStatus;

    //审核状态 0正在审核 1审核通过 2审核未通过
    private Byte auditStatus;

    //上架日期
    private String publishDate;

    //商品描述
    private String descript;

    //商品标题
    private String title;

    //商品子标题
    private String subtitle;

    //是否精品 0不是 1是
    private Byte isGood;

    //是否新品 0不是 1是
    private Byte isNew;

    //是否热销 0不是 1是
    private Byte isPopular;

    //seo描述
    private String seoText;

    //基本属性 json串
    private String baseProp;

    //基本属性 json串
    private JSONObject basePropJson;

    //销售属性 json串
    private String sellProp;

    //商品图片
    private String productImage;

    //销售数量
    private Integer sellCount;

    //查看数量
    private Integer viewCount;

    //创建时间
    private String createdTime;

    //修改时间
    private String updatedTime;

    //是否删除 0否 1是
    private Byte dataFlag;

    //商品规格集合
    private List<ProductSpec> specList;

    //商品标签集合
    private List<ProductParam> paramList;

    //商品规格
    private Map<String,List<String>> map;

    //优惠类型 0满减 1打折 2免邮 3买送 4降价
    private Byte preferentialType;

    //优惠信息 （满100减30）
    private String preferentialStr;

    //折扣比例
    private Integer disProportion;

    //是否收藏 0未收藏 1已收藏
    private Byte isCollection;

    //总库存
    private Integer totalStock;

    //商品分类信息
    private CateGory cateGory;

    //趋势id
    private Integer ttId;

    //活动id
    private Integer trId;

    private String shopUserId;


}
