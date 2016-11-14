package com.ejb.uplus.presenter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.util.Log;
import android.view.View;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.R;
import com.ejb.uplus.component.pickers.CityPicker;
import com.ejb.uplus.contract.UserProfileContract;
import com.ejb.uplus.util.FileUtil;
import com.ejb.uplus.util.OsUtil;
import com.ejb.uplus.view.UserProfileActivity;

/**
 * Created by John on 10/26/2016.
 */

public class UserProfilePresenter extends BasePresenter<UserProfileContract.IView> implements UserProfileContract.IPresenter, View.OnClickListener, CityPicker.OnCitySelectListener
{
    private int pi=0, ci=0, di=0;
    private Uri cameraUri;

    @Override
    public void getUserProfile()
    {

    }

    @Override
    public void onActivityResultAction(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case UserProfileActivity.REQUEST_CAMERA:
                if (resultCode== Activity.RESULT_OK)
                {
                    if (cameraUri!=null)
                    {
                        getIView().setAvatarUri(cameraUri);
                    }else
                    {
                        getIView().showShortToast("拍照失败，未获取拍照图像");
                    }
                }
                else
                {
                    getIView().showShortToast("打开相机失败");
                }
                break;
            case UserProfileActivity.REQUEST_ALBUM:
                if (resultCode==Activity.RESULT_OK)
                {
                    if (data!=null)
                    {
                        Uri uri = data.getData();
                        getIView().setAvatarUri(uri);
                    }
                    else
                    {
                        getIView().showShortToast("相册图片返回失败");
                    }
                }
                else
                {
                    getIView().showShortToast("打开系统相册失败");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy()
    {
        pi = 0;
        ci = 0;
        di = 0;
        cameraUri = null;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.avatar:

//                activity.openPicturePicker();
                break;
            case R.id.select_camera:
                cameraUri = FileUtil.getCaptureSavedUri();
                getIView().closePicturePicker();
                Log.d("qianyx1", cameraUri==null?"null":"not null-----"+OsUtil.getCurProcessName(mContext)+"("+ Process.myPid()+")");
                getIView().openCamera(cameraUri);
                break;
            case R.id.select_album:
                getIView().closePicturePicker();
                getIView().openAlbum();
                break;
            case R.id.select_exit:
                getIView().closePicturePicker();
                break;
            case R.id.mobile_btn:
                break;
            case R.id.sex_btn:
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
                break;
            case R.id.select_male_btn:
                getIView().setSexText("男");
                getIView().closeSexSelectDialog();
                break;
            case R.id.select_female_btn:
                getIView().setSexText("女");
                getIView().closeSexSelectDialog();
                break;
            case R.id.city_btn:
                if (getIView().isCityPickerShown())
                {
                    getIView().closeCityPicker();
                }
                getIView().openCityPicker(pi, ci, di);
                break;
            case R.id.modify_password_btn:
                break;
            case R.id.logout_btn:
                getIView().logout();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSelect(String t1, String t2, String t3, int i1, int i2, int i3)
    {
        String s = t1+"  "+t2+"  "+t3;
        getIView().setCityBtn(s);
        pi = i1;
        ci = i2;
        di = i3;
    }

}
