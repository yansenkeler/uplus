package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.bean.Order;

import java.util.ArrayList;

/**
 * Created by John on 10/26/2016.
 */

public class OrderListContract {

    public interface IView extends BaseView{
        void initViews();
        void initConfigs();
        void setListeners();
        void initPage();
        void refreshList(ArrayList<Order> orders);
        void refreshList(int index, ArrayList<Order> orders);
        void refreshInitList();
        void stopLoad();
        void stopRefresh();
    }

    public interface IPresenter{
        void getOrderList();
        void getLoadMoreData();
        void getRefreshData();
    }
}
