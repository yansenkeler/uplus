package com.ejb.uplus.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.contract.ConfirmOrderContract;
import com.ejb.uplus.presenter.ConfirmOrderPresenter;
import com.ejb.uplus.util.ActivityUtil;

/**
 * Created by John on 10/31/2016.
 */

public class ConfirmOrderActivity extends MultiStateActivity<ConfirmOrderPresenter> implements ConfirmOrderContract.IView, View.OnClickListener {
    private TextView mConfirmOrder;

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
        mConfirmOrder = (TextView)findViewById(R.id.confirm_order_btn);
    }

    @Override
    public void initConfigs()
    {

    }

    @Override
    public void setListeners()
    {
        mConfirmOrder.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.confirm_order_btn:
                ActivityUtil.goActivity(this, PayActivity.class, new Bundle());
                break;
            default:
                break;
        }
    }
}
