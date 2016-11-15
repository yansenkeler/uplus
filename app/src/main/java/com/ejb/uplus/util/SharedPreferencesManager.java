package com.ejb.uplus.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by John on 10/27/2016.
 */

public class SharedPreferencesManager
{
    private SharedPreferencesManager sharedPreferencesManager;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private SharedPreferencesManager()
    {

    }

    private SharedPreferencesManager(Context context)
    {
        this.context = context;
        if (sharedPreferencesManager==null)
        {
            sharedPreferencesManager = new SharedPreferencesManager();
        }
        if (sharedPreferences==null)
        {
            sharedPreferences = context.getSharedPreferences("uplus_shared_preferences", Context.MODE_PRIVATE);
        }
        if (editor==null)
        {
            editor = sharedPreferences.edit();
        }
    }

    public static SharedPreferencesManager getInstance(Context context)
    {
        return new SharedPreferencesManager(context);
    }

    public void putString(String key, String value)
    {
        editor.putString(key, value);
        editor.commit();
    }

    public void putInt(String key, int value)
    {
        editor.putInt(key, value);
        editor.commit();
    }

    public void putBoolean(String key, boolean value)
    {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(String key, String defValue)
    {
        return sharedPreferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue)
    {
        return sharedPreferences.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue)
    {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public void destroy()
    {

    }
}
