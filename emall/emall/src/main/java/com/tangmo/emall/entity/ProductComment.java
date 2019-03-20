package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductComment implements Serializable {

    //评论表主键
    private Integer commentId;

    //评论内容
    private String content;

    //评论图片
    private String imgId;

    //商品id
    private Integer productId;

    //订单号
    private String orderId;

    //用户id
    private Integer userId;

    //评分（星星数量）
    private Byte commentStar;

    //审核状态 0未审核 1已审核
    private Byte auditStatus;

    //审核时间
    private String auditTime;

    //点赞数量
    private Integer upvoteNum;

    //创建时间
    private String createdTime;

    //修改时间
    private String updatedTime;

    //图片集合
    private String[] imgList;

    //名
    private String firstName;

    //姓
    private String lastName;

    //支付时间
    private String payTime;

    //商品规格
    private String productSpecs;

    //用户是否已经点赞 true:点过 false：未点
    private boolean thumbUp;

    public void setThumbUp(Integer thumbUp) {
        if(thumbUp != null){
            if(thumbUp == 1){
                this.thumbUp = true;
            }else{
                this.thumbUp = false;
            }
        }
    }

}
