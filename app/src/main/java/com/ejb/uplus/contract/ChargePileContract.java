package com.ejb.uplus.contract;

import android.support.annotation.IdRes;

import com.baidu.mapapi.model.LatLng;
import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 11/15/2016.
 */

public class ChargePileContract
{

    public interface IView extends BaseView
    {
        void initViews();
        void initConfigs();
        void setListeners();
        void initPage();
        void switchToPoint(LatLng latLng);
        void addMarker(LatLng point, int iconRes);
    }

    public interface IPresenter
    {
        void startLocation();
    }
}
