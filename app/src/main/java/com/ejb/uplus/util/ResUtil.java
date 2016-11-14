package com.ejb.uplus.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.WindowManager;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by John on 10/27/2016.
 */

public class ResUtil
{
    public static String parseJsonFile(Context context, String fileName)
    {
        AssetManager assetManager = context.getAssets();
        try
        {
            InputStream inputStream = assetManager.open(fileName);
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String str = null;
            while ((str=bufferedReader.readLine())!=null)
            {
                stringBuilder.append(str);
            }
            String result = stringBuilder.toString();
            bufferedReader.close();
            inputStream.close();
            return result;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int getScreenWidth(Context ctx)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context ctx)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return  displayMetrics.heightPixels;
    }
}
