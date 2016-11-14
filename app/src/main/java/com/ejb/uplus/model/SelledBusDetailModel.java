package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;
import com.ejb.uplus.bean.SelledBusDetail;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by John on 11/8/2016.
 */

public class SelledBusDetailModel extends BaseModel
{
    public SelledBusDetail getSelledBusDetail()
    {
        ArrayList<String> sliderImages = new ArrayList<>();
        sliderImages.add("http://www.tyncar.com/uploads/allimg/130706/1-130F6150500A4.jpg");
        sliderImages.add("http://i2.dd-img.com/upload/2016/1020/1476949904699.jpg");
        sliderImages.add("http://ww1.sinaimg.cn/bmiddle/a5b6813djw1ec0bfgjwn9j20c8085q43.jpg");
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("厂商", "广汽丰田");
        params.put("级别", "中型SUV");
        params.put("发动机", "2.0T 220马力 L4");
        params.put("变速箱", "6挡手自一体");
        params.put("长*宽*高\n(mm)", "4855*1925*1720");
        params.put("车身结构", "5门5座SUV");
        params.put("整车质保", "3年或10万公里");
        params.put("油箱容积\n(L)", "72");
        params.put("发动机型号", "8AR-FTS");
        params.put("排量(L)", "2");
        params.put("进气形式", "涡轮增压");
        params.put("汽缸数(个)", "4");
        params.put("燃料形式", "汽油");
        params.put("供油方式", "混合喷射");
        params.put("环保标准", "国IV(国V)");
        params.put("驱动方式", "前置前驱");
        params.put("助力类型", "电动助力");
        params.put("灯光类型", "卤素");
        return new SelledBusDetail(params, sliderImages);
    }

}
