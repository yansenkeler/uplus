package com.ejb.uplus.bean;

import java.util.Date;

/**
 * Created by John on 10/26/2016.
 */

public class Order {
    public static final int STATE_1 = 1;
    public static final int STATE_2 = 2;
    public static final int STATE_3 = 3;
    private String orderCode;
    private String snapshot;
    private Date updateTime;
    private int state;

    public Order() {}

    public Order(String orderCode, String snapshot, Date updateTime, int state) {

        this.orderCode = orderCode;
        this.snapshot = snapshot;
        this.updateTime = updateTime;
        this.state = state;
    }

    public String getOrderCode() {

        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
