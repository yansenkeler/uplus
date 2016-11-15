package com.ejb.uplus;

import com.cl.core.application.BaseApplication;
import com.zookey.universalpreferences.UniversalPreferences;

/**
 * Created by John on 10/21/2016.
 */

public class UplusApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        UniversalPreferences.initialize(this);
    }
}
