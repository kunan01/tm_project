package com.tangmo.yiliao.entity;

import lombok.Data;

@Data
public class Read {

    private String userId;
    private Byte exceptionalState;
    private Byte systemMessageState;
    private Byte replyMessageState;
    private Byte remindMessageState;
    private Byte advisoryMessagesState;
}
