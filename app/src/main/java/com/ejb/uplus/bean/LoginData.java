package com.ejb.uplus.bean;

/**
 * Created by John on 11/14/2016.
 */

public class LoginData
{
    private String token;
    private String userName;

    public LoginData(String token, String userName)
    {
        this.token = token;
        this.userName = userName;
    }

    public LoginData()
    {
    }

    public String getToken()
    {

        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }
}
