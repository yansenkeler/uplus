package com.ejb.uplus.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

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

    public static Bitmap getBitmap(Context context, int vectorDrawableId) {
        Bitmap bitmap=null;
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        }else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }
}
