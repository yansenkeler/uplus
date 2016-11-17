package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.bean.Order;
import com.ejb.uplus.contract.OrderListContract;
import com.ejb.uplus.model.OrderListModel;

import java.util.ArrayList;

/**
 * Created by John on 10/26/2016.
 */

public class OrderListPresenter extends BasePresenter<OrderListContract.IView> implements OrderListContract.IPresenter
{

    @Override
    public void getOrderList() {
        ArrayList<Order> orders = new OrderListModel().getOrderListData();
        getIView().refreshList(orders);
        getIView().stopRefresh();
    }

    @Override
    public void getLoadMoreData()
    {
        ArrayList<Order> orders = new OrderListModel().getLoadMoreData();
        getIView().refreshList(orders);
        getIView().stopLoad();
    }

    @Override
    public void getRefreshData()
    {
        ArrayList<Order> orders = new OrderListModel().getRefreshData();
        getIView().refreshList(0, orders);
        getIView().stopRefresh();
    }
}
