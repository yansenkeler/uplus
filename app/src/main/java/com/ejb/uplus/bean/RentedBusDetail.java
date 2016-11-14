package com.ejb.uplus.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by John on 11/8/2016.
 */

public class RentedBusDetail
{
    LinkedHashMap<String, String> params = new LinkedHashMap<>();
    ArrayList<String> slideImages = new ArrayList<>();

    public RentedBusDetail(LinkedHashMap<String, String> params, ArrayList<String> slideImages)
    {
        this.params = params;
        this.slideImages = slideImages;
    }

    public LinkedHashMap<String, String> getParams()
    {

        return params;
    }

    public void setParams(LinkedHashMap<String, String> params)
    {
        this.params = params;
    }

    public ArrayList<String> getSlideImages()
    {
        return slideImages;
    }

    public void setSlideImages(ArrayList<String> slideImages)
    {
        this.slideImages = slideImages;
    }
}
