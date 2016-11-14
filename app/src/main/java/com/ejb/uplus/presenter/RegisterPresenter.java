package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.cl.core.retrofit.ApiCallback;
import com.cl.core.retrofit.AppClient;
import com.ejb.uplus.bean.SimpleReturnEntity;
import com.ejb.uplus.contract.RegisterContract;
import com.ejb.uplus.api.ApiStore;
import com.ejb.uplus.view.RegisterActivity;

/**
 * Created by John on 10/25/2016.
 */

public class RegisterPresenter extends BasePresenter<RegisterContract.IView> implements RegisterContract.IPresenter
{

    @Override
    public void register()
    {
        getIView().showLoadingDialog();
        String mobile = getIView().getMobileInputValue();
        String vcode = getIView().getIcodeInputValue();
        String password = getIView().getPasswordInputValue();
        ApiStore apiStore = AppClient.retrofit(ApiStore.BASE_URL).create(ApiStore.class);
        addSubscription(apiStore.register(vcode, mobile, password), new ApiCallback<SimpleReturnEntity>(){

            @Override
            public void onSuccess(SimpleReturnEntity simpleReturnEntity)
            {
                getIView().hideLoadingDailog();
                if (simpleReturnEntity.getRet()==200)
                {
                    ((RegisterActivity)getIView()).showToast("注册成功");
                }else
                {
                    ((RegisterActivity)getIView()).showToast(simpleReturnEntity.getMsg());
                }

            }

            @Override
            public void onFailure(String s)
            {
                getIView().hideLoadingDailog();
                ((RegisterActivity)getIView()).showToast(s);
            }

            @Override
            public void onFinish()
            {
                getIView().hideLoadingDailog();
            }
        });
    }

    @Override
    public void sendIcode()
    {
        getIView().showLoadingDialog();
        String mobile = getIView().getMobileInputValue();

        ApiStore apiStore = AppClient.retrofit(ApiStore.BASE_URL).create(ApiStore.class);
        addSubscription(apiStore.sendIcode(mobile), new ApiCallback<SimpleReturnEntity>(){

            @Override
            public void onSuccess(SimpleReturnEntity simpleReturnEntity)
            {
                getIView().hideLoadingDailog();
                if (simpleReturnEntity.getRet()==200)
                {
                    getIView().setIcodeInputValue((String) simpleReturnEntity.getData());
                    ((RegisterActivity)getIView()).showToast("发送验证码成功，2分钟后才可以重新发送");
                }else
                {
                    ((RegisterActivity)getIView()).showToast(simpleReturnEntity.getMsg());
                }
            }

            @Override
            public void onFailure(String s)
            {
                getIView().hideLoadingDailog();
                ((RegisterActivity)getIView()).showToast(s);
            }

            @Override
            public void onFinish()
            {
                getIView().hideLoadingDailog();
            }
        });
    }



    @Override
    public void valideteSendVcodeBtnClickable(String mobile, boolean inSendVcodeTime)
    {
        if (!inSendVcodeTime)
        {
            if (validateMobile(mobile))
            {
                getIView().clickableIcodeBtn();
            }else
            {
                getIView().unclickableIcodeBtn();
            }
        }
    }

    @Override
    public void validateRegisterBtnClickable(String mobile, String vcode, String password)
    {
        if (validateMobile(mobile) && validateVcode(vcode) && validatePassword(password))
        {
            getIView().enableRegisterBtn();
        }else
        {
            getIView().disableRegisterBtn();
        }
    }

    @Override
    public boolean validateMobile(String mobile)
    {
        return mobile.matches("^[1][358][0-9]{9}$");
    }

    @Override
    public boolean validateVcode(String vcode)
    {
        return vcode.matches("^[0-9]{4}$");
    }

    @Override
    public boolean validatePassword(String password)
    {
        return password.length()>0;
    }
}
