package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.cl.core.retrofit.ApiCallback;
import com.cl.core.retrofit.AppClient;
import com.ejb.uplus.api.ApiStore;
import com.ejb.uplus.bean.LoginReturnEntity;
import com.ejb.uplus.contract.LoginContract;
import com.ejb.uplus.model.LoginModel;
import com.ejb.uplus.view.LoginActivity;

/**
 * Created by John on 10/25/2016.
 */

public class LoginPresenter extends BasePresenter<LoginContract.IView> implements LoginContract
        .IPresenter
{

    @Override
    public void login()
    {
        String mobile = getIView().getMobileInputValue();
        String password = getIView().getPasswordInputValue();
        ApiStore apiStore = AppClient.retrofit(ApiStore.BASE_URL).create(ApiStore.class);
        addSubscription(apiStore.login(mobile, password), new ApiCallback<LoginReturnEntity>()
        {
            @Override
            public void onSuccess(LoginReturnEntity loginReturnEntity)
            {
                if (loginReturnEntity.getRet() == 200)
                {
                    String token = loginReturnEntity.getData().getToken();
                    new LoginModel().setLogin(true);
                    new LoginModel().putLoginToken(token);
                    ((LoginActivity) getIView()).showToast("登录成功");
                    ((LoginActivity) getIView()).finish();
                } else
                {
                    ((LoginActivity) getIView()).showToast(loginReturnEntity.getMsg());
                }
            }

            @Override
            public void onFailure(String s)
            {
                ((LoginActivity) getIView()).showToast(s);
            }

            @Override
            public void onFinish()
            {

            }
        });
    }

    @Override
    public void validateLoginBtn(String mobile, String password)
    {
        if (validateMobile(mobile) && validatePassword(password))
        {
            getIView().clickableLoginBtn();
        } else
        {
            getIView().unclickableLoginBtn();
        }
    }

    @Override
    public boolean validateMobile(String mobile)
    {
        return mobile.matches("^[1][358]\\d{9}$");
    }

    @Override
    public boolean validatePassword(String password)
    {
        return password.length() > 0;
    }
}
