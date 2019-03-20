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
 * @Description : TODO(角色关联菜单中间实体类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月14日 下午7:23:23
 */
public class SysRolePermission {
	
    private Integer id;
    //角色编号
    private Integer sysRoleId;
    //菜单编号
    private Integer sysPermissionId;
    
    private List<Integer> opermissions;
  

	public List<Integer> getOpermissions() {
		return opermissions;
	}

	public void setOpermissions(List<Integer> opermissions) {
		this.opermissions = opermissions;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(Integer sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public Integer getSysPermissionId() {
        return sysPermissionId;
    }

    public void setSysPermissionId(Integer sysPermissionId) {
        this.sysPermissionId = sysPermissionId;
    }

	@Override
	public String toString() {
		return "SysRolePermission [id=" + id + ", sysRoleId=" + sysRoleId + ", sysPermissionId=" + sysPermissionId
				+ ", opermissions=" + opermissions + "]";
	}

    
}