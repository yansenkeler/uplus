package com.ejb.uplus.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.component.pickers.CityPicker;
import com.ejb.uplus.contract.UserProfileContract;
import com.ejb.uplus.presenter.UserProfilePresenter;
import com.ejb.uplus.util.FileUtil;
import com.ejb.uplus.util.OsUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by John on 10/26/2016.
 */

public class UserProfileActivity extends MultiStateActivity<UserProfilePresenter> implements UserProfileContract.IView, View.OnClickListener
{
    public static final int REQUEST_CAMERA = 0;
    public static final int REQUEST_ALBUM = 1;
    private CircleImageView avatar;
    private RelativeLayout mobileBtn, sexBtn, cityBtn, modifyPasswordBtn, logoutBtn;
    private TextView mobileText, sexText, cityText;
    private LayoutInflater inflater;
    private AlertDialog pictureSelectTypeDialog, sexSelectDialog;
    private View pictureSelectTypeDialogLayout, sexSelectDialogLayout;
    private TextView selectCamera, selectAlbum, selectExit;
    private RelativeLayout selectMale, selectFemale;
    private ImageView selectMaleIcon, selectFemaleIcon;

    private CityPicker cityPicker;
    private Uri cameraUri;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigs();
        setListeners();
        initPage();
    }

    @Override
    public void initViews()
    {
        inflater = (LayoutInflater.from(mContext));

        avatar = (CircleImageView)findViewById(R.id.avatar);
        mobileBtn = (RelativeLayout)findViewById(R.id.mobile_btn);
        sexBtn = (RelativeLayout)findViewById(R.id.sex_btn);
        cityBtn = (RelativeLayout)findViewById(R.id.city_btn);
        modifyPasswordBtn = (RelativeLayout)findViewById(R.id.modify_password_btn);
        logoutBtn = (RelativeLayout)findViewById(R.id.logout_btn);
        mobileText = (TextView)findViewById(R.id.mobile_text);
        sexText = (TextView)findViewById(R.id.sex_text);
        cityText = (TextView)findViewById(R.id.city_text);
        pictureSelectTypeDialogLayout = inflater.inflate(R.layout.layout_picture_select_type_dialog, null, false);
        selectCamera = (TextView)pictureSelectTypeDialogLayout.findViewById(R.id.select_camera);
        selectAlbum = (TextView)pictureSelectTypeDialogLayout.findViewById(R.id.select_album);
        selectExit = (TextView)pictureSelectTypeDialogLayout.findViewById(R.id.select_exit);
        sexSelectDialogLayout = inflater.inflate(R.layout.layout_sex_select_dialog, null, false);
        selectMale = (RelativeLayout)sexSelectDialogLayout.findViewById(R.id.select_male_btn);
        selectFemale = (RelativeLayout)sexSelectDialogLayout.findViewById(R.id.select_female_btn);
        selectMaleIcon = (ImageView)sexSelectDialogLayout.findViewById(R.id.male_icon);
        selectFemaleIcon = (ImageView)sexSelectDialogLayout.findViewById(R.id.female_icon);
    }

    @Override
    public void initConfigs()
    {
        pictureSelectTypeDialog = new AlertDialog.Builder(this).setView(pictureSelectTypeDialogLayout).create();
        sexSelectDialog = new AlertDialog.Builder(this).setView(sexSelectDialogLayout).create();
        cityPicker = CityPicker.getInstance(this);
    }

    @Override
    public void setListeners()
    {
        avatar.setOnClickListener(mPresenter);
        mobileBtn.setOnClickListener(mPresenter);
        sexBtn.setOnClickListener(mPresenter);
        cityBtn.setOnClickListener(mPresenter);
        modifyPasswordBtn.setOnClickListener(mPresenter);
        logoutBtn.setOnClickListener(mPresenter);
        selectCamera.setOnClickListener(mPresenter);
        selectAlbum.setOnClickListener(mPresenter);
        selectExit.setOnClickListener(mPresenter);
        selectMale.setOnClickListener(mPresenter);
        selectFemale.setOnClickListener(mPresenter);
        cityPicker.setListener(mPresenter);
    }

    @Override
    public void initPage()
    {
        setTopBarTitle(getResources().getString(R.string.personal_profile));
    }

    @Override
    public void onLeftClick()
    {
        super.onLeftClick();
        onBackPressed();
    }

    @Override
    public void onRightClick()
    {
        super.onRightClick();
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_user_profile;
    }

    @Override
    public void openSexSelectDialog()
    {
        sexSelectDialog.show();
    }

    @Override
    public void closeSexSelectDialog()
    {
        sexSelectDialog.dismiss();
    }

    @Override
    public void openCityPicker(int p, int c, int d)
    {
        cityPicker.show(p, c, d);
    }

    @Override
    public void closeCityPicker()
    {
        cityPicker.dismiss();
    }

    @Override
    public void setCityBtn(String text)
    {
        cityText.setText(text);
    }

    @Override
    public boolean isCityPickerShown()
    {
        return cityPicker.isShow();
    }

    @Override
    public String getSexText()
    {
        return sexText.getText().toString();
    }

    @Override
    public void setSexText(String text)
    {
        sexText.setText(text);
    }

    @Override
    public void setMaleIcon(int res)
    {
        selectMaleIcon.setImageResource(res);
    }

    @Override
    public void setFemaleIcon(int res)
    {
        selectFemaleIcon.setImageResource(res);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if(cityPicker.isShow())
            {
                cityPicker.dismiss();
                return true;
            }else
            {
                onBackPressed();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void openCamera()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void openCamera(Uri uri)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void openAlbum()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    @Override
    public void setAvatarUri(Uri uri)
    {
        avatar.setImageURI(uri);
    }

    @Override
    public void setAvatarBitmap(Bitmap bitmap)
    {
        avatar.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResultAction(requestCode, resultCode, data);
//        switch (requestCode)
//        {
//            case REQUEST_CAMERA:
//                if (resultCode== Activity.RESULT_OK)
//                {
//                    Log.d("qianyx3", cameraUri==null?"null":"not null");
//                    if (cameraUri!=null)
//                    {
//                        setAvatarUri(cameraUri);
//                    }
//                    else
//                    {
//                        showShortToast("拍照失败，未获取拍照图像");
//                    }
//                }
//                else
//                {
//                    showShortToast("打开相机失败");
//                }
//                break;
//            default:
//                mPresenter.onActivityResultAction(requestCode, resultCode, data);
//                break;
//        }
    }

    @Override
    public void logout()
    {

    }

    @Override
    public void showShortToast(String text)
    {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openPicturePicker()
    {
        pictureSelectTypeDialog.show();
    }

    @Override
    public void closePicturePicker()
    {
        pictureSelectTypeDialog.dismiss();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("qianyx destroy", "activity destroy-----"+OsUtil.getCurProcessName(mContext)+"("+ Process.myPid()+")");
        cityPicker.destroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.select_camera:
                cameraUri = FileUtil.getCaptureSavedUri();
                pictureSelectTypeDialog.dismiss();
                openCamera();
                break;
            default:
                break;
        }
    }
}
