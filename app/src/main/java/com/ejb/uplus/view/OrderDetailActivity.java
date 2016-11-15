package com.ejb.uplus.view;

import android.os.Bundle;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.OrderDetailContract;
import com.ejb.uplus.presenter.OrderDetailPresenter;

/**
 * Created by John on 10/26/2016.
 */

public class OrderDetailActivity extends MultiStateActivity<OrderDetailPresenter> implements OrderDetailContract.IView
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigs();
        setListeners();
        initPage();
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initConfigs() {

    }

    @Override
    public void setListeners() {
    }

    @Override
    public void initPage() {
        setTopBarTitle(getResources().getString(R.string.order_detail));
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_order_detail;
    }

    @Override
    public void onLeftClick() {
        onBackPressed();
    }

    @Override
    public void onRightClick() {

    }
}
