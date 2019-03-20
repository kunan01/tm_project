package com.tangmo.zhjy.app.modules.bean;

public class AppNavigationBean {
    private Integer id;

    private String name;

    private String icon;

    private String url;

    private Integer freeze;

    private Integer isShow;

    private Integer rank;

    private Integer pageView;
    
    public AppNavigationBean() {
	}
    
    /**
     * 增加构造器
    * <p>Title: </p>   
    * <p>Description: </p>
     */
    public AppNavigationBean(String name,String icon,String url,Integer freeze,Integer rank) {
    	this.name=name;
    	this.icon=icon;
    	this.url=url;
    	this.freeze=freeze;
    	this.rank=rank;
  	}
    
    /**
     * 修改构造器
    * <p>Title: </p>   
    * <p>Description: </p>
     */
    public AppNavigationBean(Integer id,String name,String icon,String url,Integer freeze,Integer rank,Integer is_show) {
    	this.id=id;
    	this.name=name;
    	this.icon=icon;
    	this.url=url;
    	this.freeze=freeze;
    	this.rank=rank;
    	this.isShow=is_show;
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
}