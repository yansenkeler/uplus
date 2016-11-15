package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.R;
import com.ejb.uplus.contract.UserProfileContract;

/**
 * Created by John on 10/26/2016.
 */

public class UserProfilePresenter extends BasePresenter<UserProfileContract.IView> implements UserProfileContract.IPresenter
{
    private int pi=0, ci=0, di=0;

    @Override
    public void getUserProfile()
    {

    }

    @Override
    public void onDestroy()
    {
        pi = 0;
        ci = 0;
        di = 0;
    }

    @Override
    public void dealSexSelectBtnClick()
    {
        String sexText = getIView().getSexText();
        switch (sexText)
        {
            case "男":
                getIView().setMaleIcon(R.mipmap.icon_male_selected);
                getIView().setFemaleIcon(R.mipmap.icon_female_normal);
                break;
            case "女":
                getIView().setMaleIcon(R.mipmap.icon_male_normal);
                getIView().setFemaleIcon(R.mipmap.icon_female_selected);
                break;
            default:
                getIView().setMaleIcon(R.mipmap.icon_male_normal);
                getIView().setFemaleIcon(R.mipmap.icon_female_normal);
                break;
        }
        getIView().openSexSelectDialog();
    }

    @Override
    public void dealCitySelectBtnClick()
    {
        if (getIView().isCityPickerShown())
        {
            getIView().closeCityPicker();
        }
        getIView().openCityPicker(pi, ci, di);
    }

    @Override
    public void dealCitySelected(String t1, String t2, String t3, int i1, int i2, int i3)
    {
        String s = t1+"  "+t2+"  "+t3;
        getIView().setCityBtn(s);
        pi = i1;
        ci = i2;
        di = i3;
    }

}
