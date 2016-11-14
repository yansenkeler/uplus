package com.ejb.uplus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by John on 10/26/2016.
 */

public class DateUtil {
    public static String getFormatTime(Date date, String formatString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static Date getOriginDate(String time, String formatString)
    {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString, Locale.getDefault());
        try
        {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
}
