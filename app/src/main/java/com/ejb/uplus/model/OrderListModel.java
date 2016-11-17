package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;
import com.ejb.uplus.bean.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by John on 11/7/2016.
 */

public class OrderListModel extends BaseModel
{
    public ArrayList<Order> getOrderListData()
    {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order("ADA1234", "http://science.china.com.cn/images/attachement/png/site555/20160908/5065f32cc251193b7a5432.png", new Date(System.currentTimeMillis()), Order.STATE_1));
        orders.add(new Order("THE7896", "http://img00.hc360.com/service/201609/201609191200053722.jpg", new Date(System.currentTimeMillis()), Order.STATE_2));
        orders.add(new Order("IHG8765", "http://www.ucicq.com/uploads/allimg/160919/1910451563_0.jpg", new Date(System.currentTimeMillis()), Order.STATE_3));
        orders.add(new Order("JNG1236", "http://img1.sc115.com/uploads/sc/jpg/138/9990.jpg", new Date(System.currentTimeMillis()), Order.STATE_1));
        orders.add(new Order("OKN8973", "http://ww2.sinaimg.cn/large/006erGLUgw1ewr6e8h1i9j30ur0hwti9.jpg", new Date(System.currentTimeMillis()), Order.STATE_2));
        orders.add(new Order("UHY8735", "http://i1.chexun.net/images/2015/0907/17110/news_default_E6E280BE6E00FE7420E14E0161C94E55.jpg", new Date(System.currentTimeMillis()), Order.STATE_3));
        orders.add(new Order("IOJ8972", "http://pic11.nipic.com/20101117/5953219_224031064744_2.jpg", new Date(System.currentTimeMillis()), Order.STATE_1));
        return orders;
    }

    public ArrayList<Order> getLoadMoreData()
    {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(""+new Random(System.currentTimeMillis()).nextInt(100000), "http://pic11.nipic.com/20101117/5953219_224031064744_2.jpg", new Date(System.currentTimeMillis()), Order.STATE_1));
        return orders;
    }

    public ArrayList<Order> getRefreshData()
    {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(""+new Random(System.currentTimeMillis()).nextInt(100000), "http://pic11.nipic.com/20101117/5953219_224031064744_2.jpg", new Date(System.currentTimeMillis()), Order.STATE_3));
        return orders;
    }
}
