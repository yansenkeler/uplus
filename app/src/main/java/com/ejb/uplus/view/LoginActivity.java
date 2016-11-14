package com.ejb.uplus.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cl.core.MVPFrame.BaseView;
import com.cl.core.activity.BaseActivity;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.component.toolbar.TopBar;
import com.ejb.uplus.contract.LoginContract;
import com.ejb.uplus.presenter.LoginPresenter;
import com.ejb.uplus.util.ActivityUtil;

/**
 * Created by John on 10/25/2016.
 */

public class LoginActivity extends MultiStateActivity<LoginPresenter> implements LoginContract.IView, View.OnClickListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigers();
        setListeners();
        initPage();
    }

    @Override
    public void initViews() {
    }

    @Override
    public void initConfigers(){

    }

    @Override
    public void setListeners() {
    }

    @Override
    protected int getRightContentRef()
    {
        return R.layout.layout_login_right_content;
    }

    @Override
    protected boolean[] topBarVisilable()
    {
        return new boolean[]{true, true, true};
    }

    @Override
    public void initPage() {
        setTopBarTitle(getResources().getString(R.string.login));
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            default:
                break;
        }
    }

    @Override
    public void onLeftClick()
    {
        onBackPressed();
    }

    @Override
    public void onRightClick()
    {
        ActivityUtil.goActivity(this, RegisterActivity.class, new Bundle());
    }
}
