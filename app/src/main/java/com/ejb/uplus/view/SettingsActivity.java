package com.ejb.uplus.view;

import android.os.Bundle;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.SettingsContract;
import com.ejb.uplus.presenter.SettingsPresenter;

/**
 * Created by John on 11/1/2016.
 */

public class SettingsActivity extends MultiStateActivity<SettingsPresenter> implements SettingsContract.IView
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
        setTopBarTitle("设置");
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_settings;
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
