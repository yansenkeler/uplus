package com.ejb.uplus.contract;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

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
        void showShortToast(String text);

        void openPicturePicker();
        void closePicturePicker();
        void openCamera();
        void openCamera(Uri uri);
        void openAlbum();
        void setAvatarUri(Uri uri);
        void setAvatarBitmap(Bitmap bitmap);

        void openSexSelectDialog();
        void closeSexSelectDialog();

        void openCityPicker(int p, int c, int d);
        void closeCityPicker();
        void setCityBtn(String text);
        boolean isCityPickerShown();

        String getSexText();
        void setSexText(String text);
        void setMaleIcon(int res);
        void setFemaleIcon(int res);
    }

    public interface IPresenter
    {
        void getUserProfile();
        void onActivityResultAction(int requestCode, int resultCode, Intent data);
        void onDestroy();
    }
}
