package com.ejb.uplus.component.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by John on 11/1/2016.
 */

public class CustomRefreshLayout extends ViewGroup
{
    public CustomRefreshLayout(Context context)
    {
        this(context, null);
    }

    public CustomRefreshLayout(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public CustomRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3)
    {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }
}
