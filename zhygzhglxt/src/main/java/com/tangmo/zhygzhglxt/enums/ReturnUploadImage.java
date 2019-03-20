package com.tangmo.zhygzhglxt.enums;

/**
 * Created by chengge on 2018/9/21.
 */
public class ReturnUploadImage {

    private String state;//上传的状态SUCCESS
    private String url;//上传的地址
    private String title;//图片的名称
    private String original;//图片的名称


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
