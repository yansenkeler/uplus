package com.ejb.uplus.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends MultiStateActivity<LoginPresenter> implements LoginContract.IView, View.OnClickListener, TextWatcher
{
    private EditText mMobileInput;
    private EditText mPasswordInput;
    private TextView mForgetPassword;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigers();
        setListeners();
        initPage();
    }

    @Override
    public void initViews()
    {
        mMobileInput = (EditText) findViewById(R.id.mobile_input);
        mPasswordInput = (EditText) findViewById(R.id.password_input);
        mForgetPassword = (TextView) findViewById(R.id.forget_password);
        mLoginBtn = (Button) findViewById(R.id.login_btn);
    }

    @Override
    public void initConfigers(){

    }

    @Override
    public void setListeners() {
        mMobileInput.addTextChangedListener(this);
        mPasswordInput.addTextChangedListener(this);
        mForgetPassword.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
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
        unclickableLoginBtn();
    }

    @Override
    public void clickableLoginBtn()
    {
        mLoginBtn.setClickable(true);
        mLoginBtn.setBackgroundResource(R.drawable.btn_bg_accent);
    }

    @Override
    public void unclickableLoginBtn()
    {
        mLoginBtn.setClickable(false);
        mLoginBtn.setBackgroundResource(R.drawable.btn_bg_unclickable);
    }

    @Override
    public String getMobileInputValue()
    {
        return mMobileInput.getText().toString();
    }

    @Override
    public String getPasswordInputValue()
    {
        return mPasswordInput.getText().toString();
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
            case R.id.forget_password:
                break;
            case R.id.login_btn:
                mPresenter.login();
                break;
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        String mobile = mMobileInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        mPresenter.validateLoginBtn(mobile, password);
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }
}
