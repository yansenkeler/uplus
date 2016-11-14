package com.ejb.uplus.contract;

import android.app.Activity;
import android.os.Bundle;

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
    }

    public interface IPresenter{
        void login();
    }
}
