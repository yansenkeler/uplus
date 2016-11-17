package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;
import com.ejb.uplus.bean.RentedBus;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by John on 11/8/2016.
 */

public class RentedBusListModel extends BaseModel
{
    public ArrayList<RentedBus> getRentedBuses()
    {
        ArrayList<RentedBus> rentedBuses = new ArrayList<>();
        rentedBuses.add(new RentedBus("http://www.itdcw.com/uploads/image/20160617/1466153106564609.jpg", "物流车1", "150元/天"));
        rentedBuses.add(new RentedBus("http://file.china-nengyuan.com/999/product/big/201408/p939373001407366272.jpg", "物流车2", "160元/天"));
        rentedBuses.add(new RentedBus("http://i2.dd-img.com/upload/2016/0808/1470652772661.jpg", "物流车3", "170元/天"));
        rentedBuses.add(new RentedBus("http://ww1.sinaimg.cn/large/a946aac5gw1en71nwzezhj21kw11xtli.jpg", "物流车4", "180元/天"));
        rentedBuses.add(new RentedBus("http://pic.58pic.com/58pic/13/35/83/18458PICSCy_1024.jpg", "物流车5", "190元/天;"));
        return rentedBuses;
    }

    public ArrayList<RentedBus> getRefreshRentedBuses()
    {
        ArrayList<RentedBus> rentedBuses = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        int count = random.nextInt(1000);
        rentedBuses.add(new RentedBus("http://news.ddc.net.cn/UpFile/zmj2012/DSC_7822.jpg", "物流车"+count, count+"元/天"));
        return rentedBuses;
    }

    public ArrayList<RentedBus> getLoadMoreRentedBuses()
    {
        ArrayList<RentedBus> rentedBuses = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        int count = random.nextInt(1000);
        rentedBuses.add(new RentedBus("http://news.ddc.net.cn/UpFile/zmj2012/DSC_7822.jpg", "物流车"+count, count+"元/天"));
        return rentedBuses;
    }
}
