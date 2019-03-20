/**     
 * @Title: AppNavigationDto.java   
 * @Package com.tangmo.zhjy.modules.app.dto   
 * @Description: TODO(用一句话描述该文件做什么)   
 * @author xiaoming    
 * @date 2018年1月17日 下午2:38:29   
 * @version V1.0     
 */   
package com.tangmo.zhjy.system.modules.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import io.swagger.annotations.ApiModelProperty;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 *
 * @Description : TODO(这里用一句话描述这个类的作用)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月17日 下午2:38:29
 */
public class SysNavigationDto {

	private Integer id;
	@ApiModelProperty(value="菜单名称")
	private String name;
	@ApiModelProperty(value="菜单图标访问路径")
	private String icon;
	@ApiModelProperty(value="菜单访问地址",notes="必须是合法的：http://xxxx")
	private String url;
	@ApiModelProperty(value="是否显示",notes="0:显示、1：隐藏")
	@Max(value=1)
	@Min(value=0)
	private Integer freeze;
	@ApiModelProperty(value="排序",dataType="int",notes="默认：0")
	private Integer rank;
	@ApiModelProperty(value="用户点击量",hidden=true)
	private Integer pageView;
	@ApiModelProperty(value="是否删除",notes="0:正常、1：删除",hidden=true)
	 private Integer isShow;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getFreeze() {
		return freeze;
	}

	public void setFreeze(Integer freeze) {
		this.freeze = freeze;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
	
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		return "AppNavigationDto [id=" + id + ", name=" + name + ", icon=" + icon + ", url=" + url + ", freeze="
				+ freeze + ", rank=" + rank + ", pageView=" + pageView + ", isShow=" + isShow + "]";
	}
	
	


}
