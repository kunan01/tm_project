package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

public class TbBusNumber {

    @ApiModelProperty(value = "公交车主键id")
    private String busId;

    @ApiModelProperty(value = "公交车关联的车辆号")
    private String busNumber;

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
}
