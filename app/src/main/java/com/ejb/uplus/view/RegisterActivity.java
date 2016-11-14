package com.ejb.uplus.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.component.other.CustomTimer;
import com.ejb.uplus.contract.RegisterContract;
import com.ejb.uplus.presenter.RegisterPresenter;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by John on 10/25/2016.
 */

public class RegisterActivity extends MultiStateActivity<RegisterPresenter> implements RegisterContract.IView, View.OnClickListener, CustomTimer.OnIntervalListener, TextWatcher
{
    private static final int TIME_VCODE_OUT_OF_DATE = 120;
    private EditText mMobileInput;
    private EditText mIdentifyCodeInput;
    private TextView mSendIcodeBtn;
    private EditText mPasswordInput;
    private Button mRegisterBtn;
    private CustomTimer customTimer;
    private SweetAlertDialog loadingDialog;
    private boolean inSendVcodeTime = false;

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
        mMobileInput = (EditText) findViewById(R.id.mobile_input);
        mIdentifyCodeInput = (EditText) findViewById(R.id.identify_code_input);
        mSendIcodeBtn = (TextView) findViewById(R.id.send_icode_btn);
        mPasswordInput = (EditText) findViewById(R.id.password_input);
        mRegisterBtn = (Button) findViewById(R.id.register_btn);

    }

    @Override
    public void initConfigers() {
        loadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.color_crimson));
        loadingDialog.setTitleText("Loading...");
        loadingDialog.setCancelable(false);
    }

    @Override
    public void setListeners() {
        mSendIcodeBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
        mMobileInput.addTextChangedListener(this);
        mIdentifyCodeInput.addTextChangedListener(this);
        mPasswordInput.addTextChangedListener(this);
    }

    @Override
    public void initPage() {
        setTopBarTitle(getResources().getString(R.string.register));
        unclickableIcodeBtn();
        disableRegisterBtn();
    }

    @Override
    public void onBackPressed()
    {
        if (customTimer!=null && customTimer.isInTimer())
        {
            customTimer.release();
        }
        super.onBackPressed();
    }

    @Override
    public void clickableIcodeBtn()
    {
        mSendIcodeBtn.setClickable(true);
        mSendIcodeBtn.setBackgroundResource(R.drawable.btn_deep_sky_blue);
    }

    @Override
    public void unclickableIcodeBtn()
    {
        mSendIcodeBtn.setClickable(false);
        mSendIcodeBtn.setBackgroundResource(R.drawable.btn_bg_unclickable);
    }

    @Override
    public String getMobileInputValue()
    {
        return mMobileInput.getText().toString();
    }

    @Override
    public void setMobileInputValue(String value)
    {
        mMobileInput.setText(value);
    }

    @Override
    public String getIcodeInputValue()
    {
        return mIdentifyCodeInput.getText().toString();
    }

    @Override
    public void setIcodeInputValue(String value)
    {
        mIdentifyCodeInput.setText(value);
    }

    @Override
    public String getPasswordInputValue()
    {
        return mPasswordInput.getText().toString();
    }

    @Override
    public void showLoadingDialog()
    {
        loadingDialog.show();
    }

    @Override
    public void hideLoadingDailog()
    {
        loadingDialog.hide();
    }

    @Override
    public void enableRegisterBtn()
    {
        mRegisterBtn.setEnabled(true);
        mRegisterBtn.setBackgroundResource(R.drawable.btn_bg_accent);
    }

    @Override
    public void disableRegisterBtn()
    {
        mRegisterBtn.setEnabled(false);
        mRegisterBtn.setBackgroundResource(R.drawable.btn_bg_unclickable);
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_register;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_icode_btn:
                mPresenter.sendIcode();
                customTimer = new CustomTimer(TIME_VCODE_OUT_OF_DATE, 1000, this);
                break;
            case R.id.register_btn:
                mPresenter.register();
                break;
            default:
                break;
        }
    }

    @Override
    public void onLeftClick() {
        onBackPressed();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onTime(int count)
    {
        inSendVcodeTime = true;
        mSendIcodeBtn.setText(count+"s后重新发送");
        unclickableIcodeBtn();
    }

    @Override
    public void onFinish()
    {
        inSendVcodeTime = false;
        mSendIcodeBtn.setText("发送验证码");
        clickableIcodeBtn();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        String mobile = mMobileInput.getText().toString();
        String vcode = mIdentifyCodeInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        mPresenter.validateRegisterBtnClickable(mobile, vcode, password);
        mPresenter.valideteSendVcodeBtnClickable(mobile, inSendVcodeTime);
    }

    @Override
    public void afterTextChanged(Editable s)
    {

    }
}
