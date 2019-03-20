package com.tangmo.emall.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * @author boge
 * @date 2018-8-27
 * @description
 */

@Data
public class Result<T> implements Serializable {

    private String code;
    private String msg;
    private T data;
}
