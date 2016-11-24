package com.ejb.uplus.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.ChargePileContract;
import com.ejb.uplus.presenter.ChargePilePresenter;
import com.ejb.uplus.util.ResUtil;

/**
 * Created by John on 11/15/2016.
 */

public class ChargePileActivity extends MultiStateActivity<ChargePilePresenter> implements ChargePileContract.IView
{
    private static final int REQUEST_PERMISSION_LOCATION = 1;
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigs();
        setListeners();
        initPage();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_LOCATION)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                mPresenter.startLocation();
            } else
            {
                showToast("调用定位权限拒绝");
            }
        }
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_charge_pile;
    }

    @Override
    public void initViews()
    {
        mMapView = (MapView) findViewById(R.id.map_view);
    }

    @Override
    public void initConfigs()
    {
        mBaiduMap = mMapView.getMap();
    }

    @Override
    public void onLeftClick()
    {
        super.onLeftClick();
        onBackPressed();
    }

    @Override
    public void setListeners()
    {

    }

    @Override
    public void initPage()
    {
        setTopBarTitle("充电桩");

    }

    @Override
    public void switchToPoint(LatLng latLng)
    {
        MapStatus mapStatus = new MapStatus.Builder()
                .target(latLng)
                .zoom(com.ejb.uplus.Config.MAP_ZOOM_LEVEL)
                .build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mBaiduMap.animateMapStatus(mapStatusUpdate);
        addMarker(latLng, R.drawable.ic_place_black_24dp);
    }

    @Override
    public void addMarker(LatLng point, int iconRes)
    {
        Bitmap bitmap = ResUtil.getBitmap(mContext, iconRes);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        MarkerOptions markerOptions = new MarkerOptions()
                .draggable(false)
                .icon(bitmapDescriptor)
                .position(point)
                .draggable(false);
        mBaiduMap.addOverlay(markerOptions);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mMapView.onDestroy();
        mPresenter.destroy();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMapView.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_PERMISSION_LOCATION);
        }else
        {
            mPresenter.startLocation();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mMapView.onPause();
    }
}
