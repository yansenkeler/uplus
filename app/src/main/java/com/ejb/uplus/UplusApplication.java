package com.ejb.uplus;

import com.baidu.mapapi.SDKInitializer;
import com.cl.core.application.BaseApplication;
import com.ejb.uplus.greendao.DaoManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.zookey.universalpreferences.UniversalPreferences;

/**
 * Created by John on 10/21/2016.
 */

public class UplusApplication extends BaseApplication
{
    @Override
    public void onCreate()
    {
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
        UniversalPreferences.initialize(this);
        CrashReport.initCrashReport(getApplicationContext(), "4d0de61f7f", false);
        DaoManager.getInstance().initDao(getApplicationContext());
    }

}
