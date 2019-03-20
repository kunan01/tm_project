package com.tangmo.zhygzhglxt.entity.dto;

import com.tangmo.zhygzhglxt.entity.Info;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by chengge on 2019/3/4.
 */
public class InfoDto {

    /**
     * 报文
     */
    @ApiModelProperty(value = "运输证")
    private List<Info> commbases;

    public List<Info> getCommbases() {
        return commbases;
    }

    public void setCommbases(List<Info> commbases) {
        this.commbases = commbases;
    }

    @Override
    public String toString() {
        return "InfoDto{" +
                "commbases=" + commbases +
                '}';
    }
}
