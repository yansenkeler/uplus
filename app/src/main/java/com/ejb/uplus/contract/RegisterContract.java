package com.ejb.uplus.contract;

import android.app.Activity;
import android.os.Bundle;

import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 10/25/2016.
 */

public class RegisterContract {

    public interface IView extends BaseView{
        void initViews();
        void initConfigers();
        void setListeners();
        void initPage();
        void clickableIcodeBtn();
        void unclickableIcodeBtn();
        String getMobileInputValue();
        void setMobileInputValue(String value);
        String getIcodeInputValue();
        void setIcodeInputValue(String value);
        String getPasswordInputValue();
        void showLoadingDialog();
        void hideLoadingDailog();
        void enableRegisterBtn();
        void disableRegisterBtn();
    }

    public interface IPresenter{
        void register();
        void sendIcode();
        void valideteSendVcodeBtnClickable(String mobile, boolean inSendVcodeTime);
        void validateRegisterBtnClickable(String mobile, String vcode, String password);
        boolean validateMobile(String mobile);
        boolean validateVcode(String vcode);
        boolean validatePassword(String password);
    }
}
