package com.tangmo.zhjy.app.modules.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class AppUserDto {
    
    private Integer id;

    /**
     * 手机号码 || 账号
     */
    @ApiModelProperty(value="手机号码")
    @Length(max=11)
    private String phone;
    /**
     * 昵称
     */
    @ApiModelProperty(value="昵称")
    private String nikeName;
    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String sex;
    /**
     * 密码
     */
    @ApiModelProperty(value="登录密码")
    @Length(min=6,max=18)
    private String password;
    /**
     * 真是姓名
     */
    @ApiModelProperty(value="用户真实姓名")
    private String realName;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value="18位身份证号码")
    @Length(max=18)
    private String identityId;
    /**
     * 是否实名
     */
    @ApiModelProperty(value="是否实名：0：未实名 1：待审核 2：通过 3：驳回")
    @Max(value=3)
    @Min(value=0)
    private String isRealname;
    /**
     * 是否是商家
     */
    @ApiModelProperty(value="是否是商家:0：普通用户,1:商户")
    @Max(value=1)
    @Min(value=0)
    private String isStore;
    /**
     * 是否冻结
     */
    @ApiModelProperty(value="是否冻结用户:0：正常,1:冻结")
    @Max(value=1)
    @Min(value=0)
    private String freeze;
    /**
     * 所在省
     */
    @ApiModelProperty(value="用户所在省：添加必填")
    private String province;
    /**
     * 所在市
     */
    @ApiModelProperty(value="用户所在市：添加必填")
    private String city;
    
    /**
     * 头像
     */
    @ApiModelProperty(value="用户头像")
    private String headImage;
    
    /**
     * 身份证正面
     */
    @ApiModelProperty(value="身份证正面")
    private String front;
    
    /**
     * 身份证反面
     */
    @ApiModelProperty(value="身份证反面")
    private String verso;

    /**
     * 验证码
     */
    @ApiModelProperty(value="验证码")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFront() {
		return front;
	}

	public void setFront(String front) {
		this.front = front;
	}

	public String getVerso() {
		return verso;
	}

	public void setVerso(String verso) {
		this.verso = verso;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName == null ? null : nikeName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId == null ? null : identityId.trim();
    }

    public String getIsRealname() {
        return isRealname;
    }

    public void setIsRealname(String isRealname) {
        this.isRealname = isRealname == null ? null : isRealname.trim();
    }

    public String getIsStore() {
        return isStore;
    }

    public void setIsStore(String isStore) {
        this.isStore = isStore == null ? null : isStore.trim();
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze == null ? null : freeze.trim();
    }

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    @Override
    public String toString() {
        return "AppUserDto{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", identityId='" + identityId + '\'' +
                ", isRealname='" + isRealname + '\'' +
                ", isStore='" + isStore + '\'' +
                ", freeze='" + freeze + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", headImage='" + headImage + '\'' +
                ", front='" + front + '\'' +
                ", verso='" + verso + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}