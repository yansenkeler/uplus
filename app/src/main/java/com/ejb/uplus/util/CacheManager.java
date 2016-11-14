package com.ejb.uplus.util;

import android.content.Context;

import com.bumptech.glide.disklrucache.DiskLruCache;

import java.io.IOException;

/**
 * Created by John on 11/9/2016.
 */

public class CacheManager
{
    private static CacheManager cacheManager = null;
    private DiskLruCache cache;
    private Context context;

    private CacheManager(Context ctx)
    {
        context = ctx;
        try
        {
            cache = DiskLruCache.open(
                    FileUtil.createCacheFile(ctx, "bitmap"),
                    OsUtil.getAppVersion(ctx),
                    1,
                    Runtime.getRuntime().maxMemory()/8);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static synchronized CacheManager getInstance(Context ctx)
    {
        if (cacheManager==null)
        {
            cacheManager = new CacheManager(ctx);
        }
        return cacheManager;
    }
}
