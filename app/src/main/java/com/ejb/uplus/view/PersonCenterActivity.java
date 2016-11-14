package com.ejb.uplus.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.component.toolbar.TopBar;
import com.ejb.uplus.contract.PersonCenterContract;
import com.ejb.uplus.presenter.PersonCenterPresenter;
import com.ejb.uplus.util.ActivityUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by John on 10/28/2016.
 */

public class PersonCenterActivity extends MultiStateActivity<PersonCenterPresenter> implements PersonCenterContract.IView, View.OnClickListener
{
    private FrameLayout unloginFrame;
    private TextView loginBtn;
    private RelativeLayout loginFrame;
    private CircleImageView avatar;
    private TextView name;
    private RelativeLayout allOrderBtn;
    private RelativeLayout myMessageBtn;
    private RelativeLayout settingsBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initView();
        initConfigs();
        setListeners();
        initPage();
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_person_center;
    }

    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.anim_person_close_enter, R.anim.anim_person_close_exit);
    }

    @Override
    public void initView()
    {
        unloginFrame = (FrameLayout)findViewById(R.id.unlogin_frame);
        loginBtn = (TextView)findViewById(R.id.login_btn);
        loginFrame = (RelativeLayout)findViewById(R.id.login_frame);
        avatar = (CircleImageView)findViewById(R.id.avatar);
        name = (TextView)findViewById(R.id.name);
        allOrderBtn = (RelativeLayout)findViewById(R.id.all_order_btn);
        myMessageBtn = (RelativeLayout)findViewById(R.id.my_message_btn);
        settingsBtn = (RelativeLayout)findViewById(R.id.settings_btn);
    }

    @Override
    public void initConfigs()
    {

    }

    @Override
    public void setListeners()
    {
        loginBtn.setOnClickListener(this);
        loginFrame.setOnClickListener(this);
        allOrderBtn.setOnClickListener(this);
        myMessageBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
    }

    @Override
    public void initPage()
    {
        mPresenter.initLoginView();
        setTopBarTitle("个人中心");
    }

    @Override
    public void setAvatarVisibility(boolean isShown)
    {
        if (isShown)
        {
            unloginFrame.setVisibility(View.VISIBLE);
        }else
        {
            unloginFrame.setVisibility(View.GONE);
        }
    }

    @Override
    public void setLoginBtnVisibility(boolean isShown)
    {
        if (isShown)
        {
            loginBtn.setVisibility(View.VISIBLE);
        }else
        {
            loginBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLeftClick()
    {
        onBackPressed();
    }

    @Override
    public void onRightClick()
    {

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.login_btn:
                ActivityUtil.goActivity(this, LoginActivity.class, new Bundle());
                break;
            case R.id.login_frame:
                ActivityUtil.goActivity(this, UserProfileActivity.class, new Bundle());
                break;
            case R.id.all_order_btn:
                if (true)
                {
                    ActivityUtil.goActivity(this, OrderListActivity.class, new Bundle());
                }else
                {
                    ActivityUtil.goActivity(this, LoginActivity.class, new Bundle());
                }
                break;
            case R.id.my_message_btn:
                if (true)
                {
                    ActivityUtil.goActivity(this, MessageListActivity.class, new Bundle());
                }else
                {
                    ActivityUtil.goActivity(this, LoginActivity.class, new Bundle());
                }
                break;
            case R.id.settings_btn:
                if (false)
                {
                    ActivityUtil.goActivity(this, SettingsActivity.class, new Bundle());
                }else
                {
                    ActivityUtil.goActivity(this, LoginActivity.class, new Bundle());
                }
                break;
            default:
                break;
        }
    }
}
