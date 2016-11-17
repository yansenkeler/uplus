package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 10/25/2016.
 */

public class LoginContract {

    public interface IView extends BaseView
    {
        void initViews();
        void initConfigers();
        void setListeners();
        void initPage();
        void clickableLoginBtn();
        void unclickableLoginBtn();
        String getMobileInputValue();
        String getPasswordInputValue();
    }

    public interface IPresenter{
        void login();
        void validateLoginBtn(String mobile, String password);
        boolean validateMobile(String mobile);
        boolean validatePassword(String password);
    }
}
