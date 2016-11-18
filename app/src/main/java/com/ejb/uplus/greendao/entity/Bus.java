package com.ejb.uplus.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by John on 11/18/2016.
 */

@Entity
public class Bus
{
    @Id
    private Long userId;
    private String name;
    @Generated(hash = 2036736178)
    public Bus(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    @Generated(hash = 2029933689)
    public Bus() {
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
