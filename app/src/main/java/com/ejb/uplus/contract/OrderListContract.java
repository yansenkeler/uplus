package com.ejb.uplus.contract;

import android.app.Activity;
import android.os.Bundle;

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
        void stopLoad();
    }

    public interface IPresenter{
        void getOrderList();
        void getLoadMoreData();
    }
}
