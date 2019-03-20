package com.tangmo.emall.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerService implements Serializable {

    private String serviceId;
    private String serviceText;

}
