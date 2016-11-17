package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.bean.RentedBus;

import java.util.ArrayList;

/**
 * Created by John on 10/24/2016.
 */

public class RentedBusListContract {
    public interface IView extends BaseView{
        void initViews();
        void initPage();
        void initConfigers();
        void setListener();
        void refreshListView(ArrayList<RentedBus> rentedBuses);
        void refreshListView(int index, ArrayList<RentedBus> rentedBuses);
        void stopRefresh();
        void stopLoadMore();
        void refreshInitList();
    }

    public interface IPresenter{
        void getListData();
        void getRefreshData();
        void getLoadMoreData();
    }
}
