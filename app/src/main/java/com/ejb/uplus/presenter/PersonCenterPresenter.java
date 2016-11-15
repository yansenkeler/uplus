package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.contract.PersonCenterContract;
import com.ejb.uplus.model.PersonCenterModel;
import com.ejb.uplus.view.PersonCenterActivity;

/**
 * Created by John on 11/8/2016.
 */

public class PersonCenterPresenter extends BasePresenter<PersonCenterContract.IView> implements PersonCenterContract.IPresenter
{

    @Override
    public void initLoginView()
    {
        if (!new PersonCenterModel().isLogin())
        {
            getIView().setAvatarVisibility(false);
            getIView().setLoginBtnVisibility(true);
        }else
        {
            getIView().setAvatarVisibility(true);
            getIView().setLoginBtnVisibility(false);
        }
    }

    @Override
    public boolean isLogin()
    {
        return new PersonCenterModel().isLogin();
    }

    @Override
    public void setLogin(boolean login)
    {
        new PersonCenterModel().setLogin(login);
    }
}
