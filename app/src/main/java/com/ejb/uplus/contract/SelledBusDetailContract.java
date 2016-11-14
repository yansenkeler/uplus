package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by John on 10/25/2016.
 */

public class SelledBusDetailContract {
    public interface IView extends BaseView{
        void initViews();
        void initPage();
        void initConfigers();
        void setListeners();
        void setSlideData(ArrayList<String> slideData);
        void setTableData(LinkedHashMap<String, String> tableData);
    }

    public interface IPresenter{
        void getSelledBusDetail();
    }
}
