package com.ejb.uplus.bean;

import java.util.HashMap;

/**
 * Created by John on 11/9/2016.
 */

public class PostUrlBean
{
    private String url;
    private HashMap<String, Object> params;

    public PostUrlBean()
    {
    }

    public PostUrlBean(String url, HashMap<String, Object> params)
    {
        this.url = url;
        this.params = params;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public HashMap<String, Object> getParams()
    {
        return params;
    }

    public void setParams(HashMap<String, Object> params)
    {
        this.params = params;
    }
}
