/**     
* @Title: SystemUser.java   
* @Package com.tangmo.zhjy.dto   
* @Description: TODO(用一句话描述该文件做什么)   
* @author xiaoming    
* @date 2018年1月7日 下午9:59:50   
* @version V1.0     
*/   
package com.tangmo.zhjy.system.modules.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

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
 * @Description : TODO(页面传过来的数据封装类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月7日 下午9:59:50
 */
public class SysUserDto {
	
	@ApiModelProperty(dataType="Integer",notes="管理员编号")
	private Integer id;

	@ApiModelProperty(dataType="String",notes="账号")
    private String username;

    @ApiModelProperty(dataType="String",notes="密码",example="min:6 max:18")
    private String password;

    @Max(value=1,message="freeze参数传递有误")
    @Min(value=0,message="freeze参数传递有误")
    @ApiModelProperty(dataType="Integer",notes="是否冻结管理员",example="0：正常 1：冻结")
    private Integer freeze=0;

    @Max(value=1,message="isShow参数传递有误")
    @Min(value=0,message="isShow参数传递有误")
    @ApiModelProperty(dataType="Integer",notes="是否显示数据",example="0:正常 1：隐藏",hidden=true)
    private Integer isShow=0;
    
    @ApiModelProperty(dataType="Integer[]",notes="分配角色",example="[Integer,Integer,Integer]")
	private List<Integer> roles;

    @ApiModelProperty(dataType="String",notes="姓名")
    private String name;

    @ApiModelProperty(dataType="String",notes="工号")
    private String jobNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public Integer getIsShow() {
		return isShow;
	}
    
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getFreeze() {
        return freeze;
    }
  
    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }


    @Override
    public String toString() {
        return "SysUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", freeze=" + freeze +
                ", isShow=" + isShow +
                ", roles=" + roles +
                '}';
    }
}
