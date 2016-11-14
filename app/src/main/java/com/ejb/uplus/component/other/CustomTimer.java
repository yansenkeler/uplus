package com.ejb.uplus.component.other;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by John on 11/8/2016.
 */

public class CustomTimer
{
    private Timer timer;
    private TimerTask timerTask;
    private long duration;
    private int count;
    private OnIntervalListener listener;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            super.handleMessage(msg);
            int what = msg.what;
            if (what>0)
            {
                listener.onTime(what);
            }else if(what==0)
            {
                listener.onFinish();
            }
            else
            {
                release();
            }
        }
    };

    public void setListener(OnIntervalListener listener)
    {
        this.listener = listener;
    }

    public CustomTimer(int count, long duration, OnIntervalListener listener)
    {
        this.count = count;
        this.duration = duration;
        this.listener = listener;
        if (timer==null)
        {
            timer = new Timer();
        }
        if (timerTask==null)
        {
            timerTask = new CustomTimerTask(count);
        }
        timer.schedule(timerTask, 0, duration);
    }

    private class CustomTimerTask extends TimerTask
    {
        private int c;

        CustomTimerTask(int c)
        {
            this.c = c;
        }

        @Override
        public void run()
        {
            handler.sendEmptyMessage(c);
            c--;
        }
    }

    public interface OnIntervalListener
    {
        void onTime(int count);
        void onFinish();
    }

    public void release()
    {
        timer.cancel();
        timerTask.cancel();
        timer = null;
        timerTask = null;
    }

    public boolean isInTimer()
    {
        return timer != null || timerTask != null;
    }
}
