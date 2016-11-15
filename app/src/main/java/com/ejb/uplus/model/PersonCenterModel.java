package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;
import com.zookey.universalpreferences.UniversalPreferences;

/**
 * Created by John on 11/8/2016.
 */

public class PersonCenterModel extends BaseModel
{
    public boolean isLogin()
    {
        return UniversalPreferences.getInstance().get("is_login", false);
    }

    public void setLogin(boolean login)
    {
        UniversalPreferences.getInstance().put("is_login", login);
    }
}
