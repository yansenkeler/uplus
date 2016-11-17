package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.bean.SelledBus;
import com.ejb.uplus.contract.SelledBusListContract;
import com.ejb.uplus.model.SelledBusListModel;

import java.util.ArrayList;

/**
 * Created by John on 10/24/2016.
 */

public class SelledBusListPresenter extends BasePresenter<SelledBusListContract.IView> implements SelledBusListContract.IPresenter
{

    @Override
    public void getListData()
    {
        ArrayList<SelledBus> selledBuses = new SelledBusListModel().getListData();
        getIView().refreshListView(0, selledBuses);
        getIView().stopRefresh();
    }

    @Override
    public void getRefreshData()
    {
        ArrayList<SelledBus> selledBuses = new SelledBusListModel().getRefreshData();
        getIView().refreshListView(0, selledBuses);
        getIView().stopRefresh();
    }

    @Override
    public void getLoadData()
    {
        ArrayList<SelledBus> selledBuses = new SelledBusListModel().getLoadData();
        getIView().refreshListView(selledBuses);
        getIView().stopLoad();
    }
}
