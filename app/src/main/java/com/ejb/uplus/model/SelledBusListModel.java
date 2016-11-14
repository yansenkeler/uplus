package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;
import com.ejb.uplus.bean.SelledBus;

import java.util.ArrayList;

/**
 * Created by John on 10/25/2016.
 */

public class SelledBusListModel extends BaseModel
{
    public ArrayList<SelledBus> getListData()
    {
        ArrayList<SelledBus> selledBuses = new ArrayList<>();
        selledBuses.add(new SelledBus("http://news.ddc.net.cn/UpFile/sImage/20160929024607955.jpg", "电动物流车1", "19万元"));
        selledBuses.add(new SelledBus("http://www.haoguba.com/uploads/allimg/160928/4-16092P93KR05.jpg", "电动物流车2", "20万元"));
        selledBuses.add(new SelledBus("http://www.cnautonews.com/syqc/kec/201512/W020151228373609642597.png", "电动物流车3", "21万元"));
        selledBuses.add(new SelledBus("http://www.56clte.org/uploadfile/2016/0926/20160926100151690.jpg", "电动物流车4", "22万元"));
        selledBuses.add(new SelledBus("http://www2.autoimg.cn/newsdfs/g6/M08/6D/3A/620x0_1_autohomecar__wKgH3Ff6F5yAFi82AASKKqpo-98288.jpg", "电动物流车5", "23万元"));
        return selledBuses;
    }

    public ArrayList<SelledBus> getRefreshData()
    {
        ArrayList<SelledBus> selledBuses = new ArrayList<>();
        selledBuses.add(new SelledBus("http://news.ddc.net.cn/UpFile/sImage/20160929024607955.jpg", "电动物流车1", "200元/天"));
        return selledBuses;
    }

    public ArrayList<SelledBus> getLoadData()
    {
        ArrayList<SelledBus> selledBuses = new ArrayList<>();
        selledBuses.add(new SelledBus("http://www.56clte.org/uploadfile/2016/0926/20160926100151690.jpg", "电动物流车4", "150元/天"));
        return selledBuses;
    }
}
