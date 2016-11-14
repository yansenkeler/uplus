package com.ejb.uplus.bean;

/**
 * Created by John on 11/11/2016.
 */

public class SimpleReturnEntity
{
    private int ret;
    private Object data;
    private String msg;

    public SimpleReturnEntity()
    {
    }

    public SimpleReturnEntity(int ret, Object data, String msg)
    {
        this.ret = ret;
        this.data = data;
        this.msg = msg;
    }

    public int getRet()
    {
        return ret;
    }

    public void setRet(int ret)
    {
        this.ret = ret;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
}
