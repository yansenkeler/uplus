package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.bean.RentedBusDetail;
import com.ejb.uplus.contract.RentedBusDetailContract;
import com.ejb.uplus.model.RentedBusDetailModel;

/**
 * Created by John on 10/25/2016.
 */

public class RentedBusDetailPresenter extends BasePresenter<RentedBusDetailContract.IView> implements RentedBusDetailContract.IPresenter
{

    @Override
    public void getBusDetail() {
        RentedBusDetail rentedBusDetail = new RentedBusDetailModel().getRentedBusDetail();
        getIView().setSlideImages(rentedBusDetail.getSlideImages());
        getIView().setTable(rentedBusDetail.getParams());
    }
}
