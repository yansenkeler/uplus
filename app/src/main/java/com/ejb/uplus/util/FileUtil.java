package com.ejb.uplus.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by John on 10/27/2016.
 */

public class FileUtil
{
    private static final String TAG = "uplus_image";

    //生成拍照生成照片的存放位置
    public static Uri getCaptureSavedUri()
    {
        File savedPath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), TAG);
        if (!savedPath.exists())
        {
            if (!savedPath.mkdirs())
            {
                Log.d(TAG,
                        "failed to create directory, check if you have the WRITE_EXTERNAL_STORAGE permission");
                return null;
            }
        }
        String timestamp = new SimpleDateFormat("yyyyMMddhhss", Locale.getDefault()).format(new Date());
        File imageFile = new File(savedPath.getPath()+File.separator+"IMG_"+timestamp+".jpg");
        return Uri.fromFile(imageFile);
    }

    //根据Uri生成String
    public static String getStringFromUri(Context context, Uri uri)
    {
        if (uri!=null)
        {
            String[] paths = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, paths, null, null, null);
            if (cursor!=null)
            {
                cursor.moveToFirst();
                int index = cursor.getColumnIndex(paths[0]);
                String uriString = cursor.getString(index);
                cursor.close();
                return uriString;
            }
            return null;
        }
        else
        {
            return null;
        }
    }

    public static File getDiskCacheDir(Context ctx, String uniqueName)
    {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable())
        {
            cachePath = ctx.getExternalCacheDir().getPath();
        }else
        {
            cachePath = ctx.getCacheDir().getPath();
        }
        return new File(cachePath+File.separator+uniqueName);
    }

    public static File createCacheFile(Context ctx, String type)
    {
        File cacheFile = getDiskCacheDir(ctx, type);
        if (!cacheFile.exists())
        {
            cacheFile.mkdirs();
        }
        return cacheFile;
    }
}
