package com.ejb.uplus.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cl.core.MVPFrame.BaseView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.component.other.MultiStateView;
import com.ejb.uplus.component.toolbar.TopBar;
import com.ejb.uplus.contract.HomeContract;
import com.ejb.uplus.presenter.HomePresenter;
import com.ejb.uplus.util.ActivityUtil;

import java.util.ArrayList;

/**
 * Created by John on 10/21/2016.
 */

public class HomeActivity extends MultiStateActivity<HomePresenter> implements HomeContract.IView, View.OnClickListener, BaseSliderView.OnSliderClickListener
{
    private static final int SLIDER_DURATION = 4000;
    private SliderLayout mSlideLayout;
    private LinearLayout mBuyBusBtn;
    private LinearLayout mLeaseBusBtn;
    private LinearLayout mChargingPileBtn;
    private LinearLayout mInstrumentBtn;
    private LinearLayout mDemo1Btn;
    private LinearLayout mDemo2Btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigers();
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
        return R.layout.activity_home;
    }

    @Override
    public void initViews() {
        mSlideLayout = (SliderLayout) findViewById(R.id.slide_layout);
        mBuyBusBtn = (LinearLayout) findViewById(R.id.buy_bus_btn);
        mLeaseBusBtn = (LinearLayout) findViewById(R.id.lease_bus_btn);
        mChargingPileBtn = (LinearLayout) findViewById(R.id.charging_pile_btn);
        mInstrumentBtn = (LinearLayout) findViewById(R.id.instrument_btn);
        mDemo1Btn = (LinearLayout) findViewById(R.id.demo1_btn);
        mDemo2Btn = (LinearLayout) findViewById(R.id.demo2_btn);
    }

    @Override
    public void setListeners() {
        mBuyBusBtn.setOnClickListener(this);
        mLeaseBusBtn.setOnClickListener(this);
        mChargingPileBtn.setOnClickListener(this);
        mInstrumentBtn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected int getRightContentRef()
    {
        return R.layout.layout_home_right_content;
    }

    @Override
    protected boolean[] topBarVisilable()
    {
        return new boolean[]{ true, false, true};
    }

    @Override
    public void initPage() {
        mPresenter.getSlideData();
        setState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void initConfigers() {
        setTopBarTitle("UPLUS");

    }

    //改变slide图片
    @Override
    public void setSlideData(ArrayList<String> data)
    {
        for (int i=0; i<data.size(); i++)
        {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description("").image(data.get(i))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putInt("index", i);
            mSlideLayout.addSlider(textSliderView);
        }
        mSlideLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_bus_btn:
                ActivityUtil.goActivity(this, SelledBusListActivity.class, new Bundle());
                break;
            case R.id.lease_bus_btn:
                ActivityUtil.goActivity(this, RentedBusListActivity.class, new Bundle());
                break;
            case R.id.charging_pile_btn:

                break;
            case R.id.instrument_btn:
                ActivityUtil.goActivity(this, InstrumentActivity.class, new Bundle());
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
            mPresenter.onExitApp();
        }
        return false;
    }

    @Override
    protected void onStop()
    {
        mSlideLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mSlideLayout.startAutoCycle(SLIDER_DURATION, SLIDER_DURATION, true);
    }

    @Override
    public void onLeftClick()
    {

    }

    @Override
    public void onRightClick()
    {
        ActivityUtil.goActivity(this, PersonCenterActivity.class, new Bundle());
        overridePendingTransition(R.anim.anim_person_open_enter, R.anim.anim_person_open_exit);
    }

    @Override
    public void onSliderClick(BaseSliderView slider)
    {
        mSlideLayout.stopAutoCycle();
        Toast.makeText(this, "image "+slider.getBundle().getInt("index")+" is clicked", Toast.LENGTH_SHORT).show();
        mSlideLayout.startAutoCycle(SLIDER_DURATION, SLIDER_DURATION, true);
    }
}
