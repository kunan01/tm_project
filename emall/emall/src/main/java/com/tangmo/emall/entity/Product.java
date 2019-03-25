package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


@Data
public class Product implements Serializable {

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

    //商品规格
    private Map<String,List<String>> map;

    //折扣比例
    private Integer disProportion;

    //是否收藏 0未收藏 1已收藏
    private Byte isCollection;

    //是否处方眼镜 0不是 1是
    private Integer isPrescript;

    private String shopUserId;

}
