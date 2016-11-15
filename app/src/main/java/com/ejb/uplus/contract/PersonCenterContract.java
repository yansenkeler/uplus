package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 11/8/2016.
 */

public class PersonCenterContract
{
    public interface IView extends BaseView
    {
        void initView();
        void initConfigs();
        void setListeners();
        void initPage();
        void setAvatarVisibility(boolean isShown);
        void setLoginBtnVisibility(boolean isShown);
    }

    public interface IPresenter
    {
        void initLoginView();
        boolean isLogin();
        void setLogin(boolean login);
    }
}
