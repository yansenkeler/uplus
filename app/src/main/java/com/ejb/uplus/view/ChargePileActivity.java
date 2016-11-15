package com.ejb.uplus.view;

import android.os.Bundle;

import com.baidu.mapapi.map.MapView;
import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.ChargePileContract;
import com.ejb.uplus.presenter.ChargePilePresenter;

/**
 * Created by John on 11/15/2016.
 */

public class ChargePileActivity extends MultiStateActivity<ChargePilePresenter> implements ChargePileContract.IView
{
    private MapView mMapView = null;

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
    protected void onDestroy()
    {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mMapView.onPause();
    }
}
