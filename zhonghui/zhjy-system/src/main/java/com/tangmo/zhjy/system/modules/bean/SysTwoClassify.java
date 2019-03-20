package com.tangmo.zhjy.system.modules.bean;

public class SysTwoClassify {
    private Integer id;

    private String name;

    private String icon;

    private String url;
    
    private Integer freeze;
    
    private Integer isShow;

    private Integer appClassifyid;
    
    public SysTwoClassify() {
	}
    /**
     * 
    * <p>Title: 增加构造器</p>   
    * <p>Description: </p>   
    * @param name
    * @param icon
    * @param url
    * @param appClassifyid
     */
    public SysTwoClassify(String name,String icon,String url,Integer appClassifyid,Integer freeze) {
    	this.name=name;
    	this.icon=icon;
    	this.url=url;
    	this.appClassifyid=appClassifyid;
    	this.freeze=freeze;
	}
    
    public SysTwoClassify(Integer id,String name,String icon,String url,Integer appClassifyid,Integer freeze,Integer isShow) {
    	this.name=name;
    	this.icon=icon;
    	this.url=url;
    	this.appClassifyid=appClassifyid;
    	this.id=id;
    	this.freeze=freeze;
    	this.isShow=isShow;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getAppClassifyid() {
        return appClassifyid;
    }

    public void setAppClassifyid(Integer appClassifyid) {
        this.appClassifyid = appClassifyid;
    }
}