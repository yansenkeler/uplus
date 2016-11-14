package com.ejb.uplus.util;

import com.ejb.uplus.bean.PostUrlBean;
import com.ejb.uplus.bean.UrlBean;

import java.util.HashMap;

/**
 * Created by John on 11/9/2016.
 */

public class UrlUtil
{
    //发送验证码
    public static final String TAG_SEND_ICODE = "key_send_icode";
    private static UrlBean urlSendIcode = new UrlBean("http://192.168.1.192:3000/api/m/user/sendVcode", new String[]{"mobile"});

    private static HashMap<String, UrlBean> urlMap = new HashMap<>();

    static
    {
        urlMap.put(TAG_SEND_ICODE, urlSendIcode);
    }

    public static PostUrlBean getPostMap(String key, Object[] paramValues)
    {
        UrlBean urlBean = urlMap.get(key);
        HashMap<String, Object> postedParams = new HashMap<>();
        String[] paramKeys = urlBean.getParams();
        for (int i=0; i<paramKeys.length; i++)
        {
            postedParams.put(paramKeys[i], paramValues[i]);
        }
        return new PostUrlBean(urlBean.getUrl(), postedParams);
    }


}
