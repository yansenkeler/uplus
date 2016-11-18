package com.ejb.uplus.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ejb.uplus.greendao.gen.DaoMaster;
import com.ejb.uplus.greendao.gen.DaoSession;

/**
 * Created by John on 11/17/2016.
 */

public class DaoManager
{
    private static final String DB_NAME = "UPLUS_DB";
    private static DaoManager instance = null;
    private DaoMaster.DevOpenHelper mOpenHelper;
    private SQLiteDatabase mDatabase;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    public static DaoManager getInstance()
    {
        if (instance==null)
        {
            instance = new DaoManager();
        }
        return instance;
    }

    public void initDao(Context context)
    {
        mOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDatabase = mOpenHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDatabase);
        mDaoSession = mDaoMaster.newSession();
    }


}
