package com.ejb.uplus.view;

import android.os.Bundle;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.InstrumentContract;
import com.ejb.uplus.presenter.InstrumentPresenter;

/**
 * Created by John on 11/1/2016.
 */

public class InstrumentActivity extends MultiStateActivity<InstrumentPresenter> implements InstrumentContract.IView
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
        setTopBarTitle("免责声明");
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_instrument;
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

    @Override
    protected void onStop() {
        super.onStop();
    }

}
