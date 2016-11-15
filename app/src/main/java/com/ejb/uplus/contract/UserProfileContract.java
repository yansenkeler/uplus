package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 10/26/2016.
 */

public class UserProfileContract
{
    public interface IView extends BaseView
    {
        void initViews();
        void initConfigs();
        void setListeners();
        void initPage();
        void logout();

        void openCamera();
        void openAlbum();

        void openSexSelectDialog();

        void openCityPicker(int p, int c, int d);
        void closeCityPicker();
        void setCityBtn(String text);
        boolean isCityPickerShown();

        String getSexText();
        void setMaleIcon(int res);
        void setFemaleIcon(int res);
    }

    public interface IPresenter
    {
        void getUserProfile();
        void onDestroy();
        void dealSexSelectBtnClick();
        void dealCitySelectBtnClick();
        void dealCitySelected(String t1, String t2, String t3, int i1, int i2, int i3);
    }
}
