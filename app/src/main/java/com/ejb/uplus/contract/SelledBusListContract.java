package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.bean.SelledBus;

import java.util.ArrayList;

/**
 * Created by John on 10/24/2016.
 */

public class SelledBusListContract {
    public interface IView extends BaseView
    {
        void initViews();
        void initPage();
        void initConfigers();
        void setListeners();
        void refreshListView(int index, ArrayList<SelledBus> selledBuses);
        void refreshListView(ArrayList<SelledBus> selledBuses);
        void stopRefresh();
        void stopLoad();
    }

    public interface IPresenter{
        void getListData();
        void getRefreshData();
        void getLoadData();
    }
}
