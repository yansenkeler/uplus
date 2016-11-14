package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by John on 10/25/2016.
 */

public class RentedBusDetailContract {

    public interface IView extends BaseView{
        void initViews();
        void initPage();
        void initConfigers();
        void setListener();
        void setSlideImages(ArrayList<String> slideImages);
        void setTable(LinkedHashMap<String, String> params);
    }

    public interface IPresenter{
        void getBusDetail();
    }
}
