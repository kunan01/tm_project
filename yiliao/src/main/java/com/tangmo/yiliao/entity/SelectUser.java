package com.tangmo.yiliao.entity;


import lombok.Data;

public class SelectUser {

    private Integer type;
    private String condName;
    private String condPhone;
    private Integer start;
    private Integer end;
    private String originatorId;
    private String peopleId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCondName() {
        return condName;
    }

    public void setCondName(String condName) {
        this.condName = condName;
    }

    public String getCondPhone() {
        return condPhone;
    }

    public void setCondPhone(String condPhone) {
        this.condPhone = condPhone;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(String originatorId) {
        this.originatorId = originatorId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }
}
