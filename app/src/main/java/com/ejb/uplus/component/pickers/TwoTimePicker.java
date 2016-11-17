package com.ejb.uplus.component.pickers;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.view.WheelTime;
import com.ejb.uplus.R;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 11/1/2016.
 */

public class TwoTimePicker extends LinearLayout
{
    private WheelTime wheelTime1, wheelTime2;
    private String title1="", title2="";
    private TextView tv1, tv2;

    public TwoTimePicker(Context context)
    {
        this(context, null);
    }

    public TwoTimePicker(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public TwoTimePicker(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TwoTimePicker);
        title1 = typedArray.getString(R.styleable.TwoTimePicker_title1);
        title2 = typedArray.getString(R.styleable.TwoTimePicker_title2);

        inflate(context, R.layout.layout_two_time_picker, this);
        View view1 = findViewById(R.id.timepicker1);
        View view2 = findViewById(R.id.timepicker2);
        tv1 = (TextView)findViewById(R.id.title1);
        tv2 = (TextView)findViewById(R.id.title2);

        tv1.setText(title1);
        tv2.setText(title2);

        wheelTime1 = new WheelTime(view1);
        wheelTime2 = new WheelTime(view2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime1.setPicker(year, month, day, hours, minute);
        wheelTime2.setPicker(year, month, day, hours, minute);

        typedArray.recycle();
    }

    public void setDate1(Date d1)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime1.setPicker(year, month, day, hours, minute);
    }

    public void setDate2(Date d2)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d2);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        wheelTime2.setPicker(year, month, day, hours, minute);
    }

    public Date getDate1()
    {
        try
        {
            return WheelTime.dateFormat.parse(wheelTime1.getTime());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Date getDate2()
    {
        try
        {
            return WheelTime.dateFormat.parse(wheelTime2.getTime());
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
