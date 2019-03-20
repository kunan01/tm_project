package com.tangmo.zhjy.app.modules.vo;

import lombok.Data;

import java.sql.Date;

/**
 * @author boge
 * @date 18/3/17
 * @description
 */
@Data
public class ShopServiceVO {
    private static final long serialVersionUID = 1L;
    private Integer sgId;
    private Integer userId;
    private String username;
    private String avatarId;
    private String city;
    private String shopName;
    private String shopContent;
    private String imgId;
    private String[] imgList;
    private Byte serviceType;
    private Double price;
    private String discountNote;
    private String mobile;
    private String address;
    private String locationLng;
    private String locationLat;
    private Date createtime;
    private Integer shopClassType;
    private String shopClassTypeName;
    private String province;
    private String district;


    public void setImgId(String imgId) {
        if(imgId == null){
            this.imgList = new String[]{};
        }else {
            String[] list = imgId.split(",");
            this.imgId = list[0];
            this.imgList = list;
        }
    }
}
