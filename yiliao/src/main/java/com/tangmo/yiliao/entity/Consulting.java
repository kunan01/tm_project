package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class Consulting {

    private String phone;
    private String codeTime;
    private Integer codeCount;

    private User user;

}
