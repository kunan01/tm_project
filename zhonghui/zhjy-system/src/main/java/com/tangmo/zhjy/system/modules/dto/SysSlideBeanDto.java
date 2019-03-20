package com.tangmo.zhjy.system.modules.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;

public class SysSlideBeanDto {
	
    private Integer id;
    @ApiModelProperty(value="标题",required=true)
    private String title;
    @ApiModelProperty(value="图片访问地址",required=true)
    private String url;
    @ApiModelProperty(value="是否显示",hidden=true)
    private Integer isShow=0;
    @ApiModelProperty(value="排序字段",notes="默认：0")
    @Min(value=0)
    private Integer rank;
    @ApiModelProperty(value="是否冻结",notes="0：正常、1：冻结")
    @Max(value=1)
    @Min(value=0)
    private Integer freeze;
    
	/**
	 * 办事、百科外键
	 */
    @ApiModelProperty(value="文章编号",notes="办事、百科文章ID（add:必填）")
	private Integer informationId;
	
	
	public Integer getInformationId() {
		return informationId;
	}

	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

	@Override
	public String toString() {
		return "AppSlideBeanDto [id=" + id + ", title=" + title + ", url=" + url + ", isShow=" + isShow + ", rank="
				+ rank + ", freeze=" + freeze + "]";
	}
    
    
}