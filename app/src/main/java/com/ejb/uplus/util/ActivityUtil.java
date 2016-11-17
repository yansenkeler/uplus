package com.ejb.uplus.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.widget.LinearLayout;

import com.thefinestartist.finestwebview.FinestWebView;

/**
 * Created by John on 10/26/2016.
 */

public class ActivityUtil {
    public static void goActivity(Activity act, Class<? extends Activity> cls, Bundle bundle){
        Intent intent = new Intent(act, cls);
        intent.putExtras(bundle);
        act.startActivity(intent);
    }

    public static void goActivityForResult(Activity act, Class<? extends Activity> cls, Bundle bundle, int reqCode){
        Intent intent = new Intent(act, cls);
        intent.putExtras(bundle);
        act.startActivityForResult(intent, reqCode);
    }

    public static void jumpToDialNumber(Context context, String number)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void loadWeb(Context ctx, String url)
    {
        new FinestWebView.Builder(ctx)
                .webViewUseWideViewPort(true)
//                .webViewLoadWithOverviewMode(true)
                .show(url);
    }

    public static void loadWebOnlyWithTitle(Context ctx, String url)
    {
        new FinestWebView.Builder(ctx)
                .webViewUseWideViewPort(true)
                .showUrl(false)
                .showIconClose(false)
                .showIconMenu(false)
                .showSwipeRefreshLayout(false)
                .toolbarScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
                .show(url);
    }
}
