package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.bean.RentedBus;
import com.ejb.uplus.contract.RentedBusListContract;
import com.ejb.uplus.model.RentedBusListModel;

import java.util.ArrayList;

/**
 * Created by John on 10/24/2016.
 */

public class RentedBusListPresenter extends BasePresenter<RentedBusListContract.IView> implements RentedBusListContract.IPresenter
{

    @Override
    public void getListData()
    {
        ArrayList<RentedBus> rentedBuses = new RentedBusListModel().getRentedBuses();
        getIView().refreshListView(rentedBuses);
    }

    @Override
    public void getRefreshData()
    {
        ArrayList<RentedBus> rentedBuses = new RentedBusListModel().getRefreshRentedBuses();
        getIView().refreshListView(rentedBuses);
        getIView().stopRefresh();
    }
}
