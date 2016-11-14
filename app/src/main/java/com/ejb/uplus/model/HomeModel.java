package com.ejb.uplus.model;

import com.cl.core.MVPFrame.BaseModel;

import java.util.ArrayList;

/**
 * Created by John on 10/21/2016.
 */

public class HomeModel extends BaseModel{

    public ArrayList<String> getSlideData()
    {
        ArrayList<String> slideData = new ArrayList<>();
        slideData.add("http://www.sinocars.com/uploadfile/2014/1014/20141014030529778.jpg");
        slideData.add("http://p2.qhimg.com/t0117eb9de5845f9ab7.jpg");
        slideData.add("http://www.ytxww.com/upload/image/201203/20120309100442708165.jpg");
        return slideData;
    }
}
