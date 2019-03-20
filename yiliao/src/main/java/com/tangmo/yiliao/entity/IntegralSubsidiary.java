package com.tangmo.yiliao.entity;

import lombok.Data;

/**
 * @author hanjialin
 * @date 2018-8-27
 * @description 积分明细
 */
@Data
public class IntegralSubsidiary {
    private String syId;
    private String userId;
    private String ltId;
    private Integer syBean;
    private String createTime;
    private String updateTime;
    private String createUserId;
    private String updateUserId;
    private String levelName;
    private String superiorId;

    private String orderId;

}
