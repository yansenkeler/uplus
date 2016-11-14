package com.ejb.uplus.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by John on 11/8/2016.
 */

public class SelledBusDetail
{
    private LinkedHashMap<String, String> params = new LinkedHashMap<>();
    private ArrayList<String> sliderImages = new ArrayList<>();

    public SelledBusDetail(LinkedHashMap<String, String> params, ArrayList<String> sliderImages)
    {
        this.params = params;
        this.sliderImages = sliderImages;
    }

    public LinkedHashMap<String, String> getParams()
    {

        return params;
    }

    public void setParams(LinkedHashMap<String, String> params)
    {
        this.params = params;
    }

    public ArrayList<String> getSliderImages()
    {
        return sliderImages;
    }

    public void setSliderImages(ArrayList<String> sliderImages)
    {
        this.sliderImages = sliderImages;
    }
}
