package com.tangmo.zhjy.app.modules.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class AppCollectDto {
	
	@NotNull
	@ApiModelProperty(value="文章编号")
    private Integer informationid;
	@NotNull
	@ApiModelProperty(value="用户编号")
    private Integer userId;
    
    public AppCollectDto() {
    	
	}
    public Integer getInformationid() {
        return informationid;
    }

    public void setInformationid(Integer informationid) {
        this.informationid = informationid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
