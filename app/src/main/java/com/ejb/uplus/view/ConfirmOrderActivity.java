package com.ejb.uplus.view;

import android.os.Bundle;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.ConfirmOrderContract;
import com.ejb.uplus.presenter.ConfirmOrderPresenter;

/**
 * Created by John on 10/31/2016.
 */

public class ConfirmOrderActivity extends MultiStateActivity<ConfirmOrderPresenter> implements ConfirmOrderContract.IView
{

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
    public void initViews()
    {
    }

    @Override
    public void initConfigs()
    {

    }

    @Override
    public void setListeners()
    {
    }

    @Override
    public void initPage()
    {
        mPresenter.getData();
        setTopBarTitle("确认订单");
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void onLeftClick()
    {
        onBackPressed();
    }

    @Override
    public void onRightClick()
    {

    }
}
