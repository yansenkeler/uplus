package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;
import com.zookey.universalpreferences.UniversalPreferences;

/**
 * Created by John on 10/25/2016.
 */

public class LoginModel extends BaseModel
{
    public void setLogin(boolean login)
    {
        UniversalPreferences.getInstance().put("is_login", login);
    }

    public void putLoginToken(String token)
    {
        UniversalPreferences.getInstance().put("login_token", token);
    }
}
