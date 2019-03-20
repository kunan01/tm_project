package com.tangmo.zhjy.system.modules.bean;

import java.util.List;

/**
 * 
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
 * @Description : TODO(角色实体类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月13日 下午10:09:12
 */
public class SysRoleBean {
    private Integer id;
    //角色名称
    private String name;
    //是否冻结该角色
    private Integer freeze;
    //是否显示该数据
    private Integer isShow;
    //存放当前角色拥有的所有菜单
    private List<SysPermission> sysPermissions;
    
    public SysRoleBean() {
    	
	}
    
    public SysRoleBean(Integer id,String name,Integer freeze, Integer isShow) {
    	this.id=id;
    	this.name=name;
    	this.freeze=freeze;
    	this.isShow=isShow;
   	}
    
    
    
    public List<SysPermission> getSysPermissions() {
		return sysPermissions;
	}



	public void setSysPermissions(List<SysPermission> sysPermissions) {
		this.sysPermissions = sysPermissions;
	}



	/**
     * 
    * <p>Title: 添加构造器</p>   
    * <p>Description: </p>   
    * @param name
     */
    public SysRoleBean(String name,int freeze) {
    	this.name=name;
    	this.freeze=freeze;
	}
    
    /**
     * 
    * <p>Title: 修改构造器</p>   
    * <p>Description: </p>   
    * @param id
    * @param name
    * @param freeze
     */
    public SysRoleBean(int id,String name,int freeze) {
    	this.name=name;
    	this.id=id;
    	this.freeze=freeze;
	}

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
        this.name = name == null ? null : name.trim();
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
    
    

	@Override
	public String toString() {
		return "SysRoleBean [id=" + id + ", name=" + name + ", freeze=" + freeze + ", isShow=" + isShow
				+ ", sysPermissions=" + sysPermissions + "]";
	}
    
    
}