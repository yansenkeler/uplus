package com.ejb.uplus.view;

import android.os.Bundle;
import android.view.View;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.PayContract;
import com.ejb.uplus.presenter.PayPresenter;
import com.ejb.uplus.util.ActivityUtil;

import ayar.oktay.advancedtextview.AdvancedTextView;

/**
 * Created by John on 11/1/2016.
 */

public class PayActivity extends MultiStateActivity<PayPresenter> implements PayContract.IView, View.OnClickListener {
    private AdvancedTextView mPayBtn;


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
        mPayBtn = (AdvancedTextView) findViewById(R.id.pay_btn);
    }

    @Override
    public void initConfigs()
    {

    }

    @Override
    public void setListeners()
    {
        mPayBtn.setOnClickListener(this);
    }

    @Override
    public void initPage()
    {
        setTopBarTitle("支付订单");
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_pay;
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pay_btn:
                ActivityUtil.goActivity(this, UserProfileActivity.class, new Bundle());
                break;
            default:
                break;
        }
    }
}
