package com.tangmo.zhygzhglxt.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chengge on 2019/3/4.
 */
/*
 * 封装json参数（对设备厂商2的封装格式）
 * */
public class Info {

    /**
     * 获取最后信息的消息类型ID是:4356
     */
    @ApiModelProperty(value = "获取最后信息的消息类型ID是:4356")
    private Integer id;//获取最后信息的消息类型ID是:4356

    /**
     * 流水号自行定义
     */
    @ApiModelProperty(value = "流水号自行定义")
    private Integer seqno;//流水号自行定义

    /**
     * 根据章节3的请求参考位置请求报文生成json字符串
     */
    @ApiModelProperty(value = "根据章节3的请求参考位置请求报文生成json字符串")
    private String content;//根据章节3的请求参考位置请求报文生成json字符串

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeqno() {
        return seqno;
    }

    public void setSeqno(Integer seqno) {
        this.seqno = seqno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id='" + id + '\'' +
                ", seqno='" + seqno + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
