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
 * @Description : TODO(关联操作权限中间实体类)
 * ---------------------------------
 * @Author : xiaoming
 * @Date : 2018年1月14日 下午7:20:05
 */
public class SysRelatierolOperation {
    private Integer id;
    //关联角色记录ID
    private Integer sysRelatierolId;
    //操作编号ID
    private Integer sysOperationId;
    //批量的操作编号ID
    private List<Integer>  sysOperationIds;
    
    public List<Integer> getSysOperationIds() {
		return sysOperationIds;
	}

	public void setSysOperationIds(List<Integer> sysOperationIds) {
		this.sysOperationIds = sysOperationIds;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysRelatierolId() {
        return sysRelatierolId;
    }

    public void setSysRelatierolId(Integer sysRelatierolId) {
        this.sysRelatierolId = sysRelatierolId;
    }

    public Integer getSysOperationId() {
        return sysOperationId;
    }

    public void setSysOperationId(Integer sysOperationId) {
        this.sysOperationId = sysOperationId;
    }

	@Override
	public String toString() {
		return "SysRelatierolOperation [id=" + id + ", sysRelatierolId=" + sysRelatierolId + ", sysOperationId="
				+ sysOperationId + ", sysOperationIds=" + sysOperationIds + "]";
	}
    
    
}