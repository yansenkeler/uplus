package com.ejb.uplus.presenter;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.Config;
import com.ejb.uplus.contract.ChargePileContract;

/**
 * Created by John on 11/15/2016.
 */

public class ChargePilePresenter extends BasePresenter<ChargePileContract.IView> implements ChargePileContract.IPresenter, BDLocationListener
{
    private LocationClient mLocationClient = null;
    private double latitude = 0, longitude = 0;

    @Override
    public void startLocation()
    {
        if (latitude==0 || longitude==0){
            mLocationClient = new LocationClient(mContext.getApplicationContext());
            mLocationClient.registerLocationListener(this);
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            option.setCoorType(Config.MAP_COORDINATE);//可选，默认gcj02，设置返回的定位结果坐标系
//        int span=0;
//        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            option.setOpenGps(true);//可选，默认false,设置是否使用gps
            option.setLocationNotify(false);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
            option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
            mLocationClient.setLocOption(option);
            Log.d("ChargePilePresenter", "start location");
            mLocationClient.start();
        }
    }

    @Override
    public void destroy() {
        latitude = 0;
        longitude = 0;
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation)
    {
        longitude = bdLocation.getLongitude();
        latitude = bdLocation.getLatitude();
        getIView().switchToPoint(new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
        if (mLocationClient.isStarted())
        {
            mLocationClient.stop();
        }
    }
}
