package com.ejb.uplus.bean;

/**
 * Created by John on 11/9/2016.
 */

public class UrlBean
{
    private String url;
    private String[] params;

    public UrlBean(String url, String[] params)
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

    public String[] getParams()
    {
        return params;
    }

    public void setParams(String[] params)
    {
        this.params = params;
    }
}
