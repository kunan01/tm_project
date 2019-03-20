package com.tangmo.zhjy.app.modules.bean;


import java.io.Serializable;
import java.sql.Date;

/**
 * @author boge
 * @date 18/1/9
 * @description 商品评论
 */
public class GoodsComment implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer gcId;
    private Integer goodsId;
    private Integer userId;
    private String username;
    private String avatarId;
    private Byte belongType;
    private String comment;

    /**
     * 评价指标相关
     */
    private Byte isAnonymous;
    private Byte star;
    private Date createTime;

    public void setStar(Byte star) {
        if(star == null){
            star = 0;
        }
        this.star = star;
    }

    public void setIsAnonymous(Byte isAnonymous) {
        if(isAnonymous == null){
            isAnonymous = 0;
        }
        this.isAnonymous = isAnonymous;
    }

    public Byte getStar() {
        if(star == null){
            return 0;
        }
        return star;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getGcId() {
        return gcId;
    }

    public void setGcId(Integer gcId) {
        this.gcId = gcId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public Byte getBelongType() {
        return belongType;
    }

    public void setBelongType(Byte belongType) {
        this.belongType = belongType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getIsAnonymous() {
        if(isAnonymous == null){
            return 0;
        }
        return isAnonymous;

    }
}
