package com.ejb.uplus.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import com.zookey.universalpreferences.UniversalPreferences;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by John on 10/26/2016.
 */

public class UserProfileActivity extends MultiStateActivity<UserProfilePresenter> implements UserProfileContract.IView, View.OnClickListener, CityPicker.OnCitySelectListener
{
    public static final int REQUEST_CAMERA = 0;
    public static final int REQUEST_ALBUM = 1;
    private static final int REQUEST_PERMISSION_CAMERA = 1;
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
        avatar.setOnClickListener(this);
        sexBtn.setOnClickListener(this);
        cityBtn.setOnClickListener(this);
        modifyPasswordBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
        selectCamera.setOnClickListener(this);
        selectAlbum.setOnClickListener(this);
        selectExit.setOnClickListener(this);
        selectMale.setOnClickListener(this);
        selectFemale.setOnClickListener(this);
        cityPicker.setListener(this);
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
    public void openAlbum()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_ALBUM);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults)
    {
        if (requestCode == REQUEST_PERMISSION_CAMERA)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                openCamera();
            } else
            {
                showToast("调用相机权限拒绝");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case UserProfileActivity.REQUEST_CAMERA:
                if (resultCode== Activity.RESULT_OK)
                {
                    if (cameraUri!=null)
                    {
                        avatar.setImageURI(cameraUri);
                    }else
                    {
                        showToast("拍照失败，未获取拍照图像");
                    }
                }
                else
                {
                    showToast("打开相机失败");
                }
                break;
            case UserProfileActivity.REQUEST_ALBUM:
                if (resultCode==Activity.RESULT_OK)
                {
                    if (data!=null)
                    {
                        Uri uri = data.getData();
                        avatar.setImageURI(uri);
                    }
                    else
                    {
                        showToast("相册图片返回失败");
                    }
                }
                else
                {
                    showToast("打开系统相册失败");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void logout()
    {
        finish();
        Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        cityPicker.destroy();
        mPresenter.onDestroy();
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.avatar:
                pictureSelectTypeDialog.show();
                break;
            case R.id.select_camera:
                cameraUri = FileUtil.getCaptureSavedUri();
                pictureSelectTypeDialog.dismiss();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
                }else
                {
                    openCamera();
                }
                break;
            case R.id.select_album:
                pictureSelectTypeDialog.dismiss();
                openAlbum();
                break;
            case R.id.select_exit:
                pictureSelectTypeDialog.dismiss();
                break;
            case R.id.sex_btn:
                mPresenter.dealSexSelectBtnClick();
                break;
            case R.id.select_male_btn:
                sexText.setText("男");
                sexSelectDialog.dismiss();
                break;
            case R.id.select_female_btn:
                sexText.setText("女");
                sexSelectDialog.dismiss();
                break;
            case R.id.city_btn:
                mPresenter.dealCitySelectBtnClick();
                break;
            case R.id.modify_password_btn:
                break;
            case R.id.logout_btn:
                UniversalPreferences.getInstance().put("is_login", false);
                UniversalPreferences.getInstance().put("login_token", "");
                logout();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSelect(String t1, String t2, String t3, int i1, int i2, int i3)
    {
        mPresenter.dealCitySelected(t1, t2, t3, i1, i2, i3);
    }
}
