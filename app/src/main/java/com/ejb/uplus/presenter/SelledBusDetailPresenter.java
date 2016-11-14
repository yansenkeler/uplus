package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.bean.SelledBusDetail;
import com.ejb.uplus.contract.SelledBusDetailContract;
import com.ejb.uplus.model.SelledBusDetailModel;

/**
 * Created by John on 10/25/2016.
 */

public class SelledBusDetailPresenter extends BasePresenter<SelledBusDetailContract.IView> implements SelledBusDetailContract.IPresenter
{

    @Override
    public void getSelledBusDetail()
    {
        SelledBusDetail selledBusDetail = new SelledBusDetailModel().getSelledBusDetail();
        getIView().setSlideData(selledBusDetail.getSliderImages());
        getIView().setTableData(selledBusDetail.getParams());
    }
}
