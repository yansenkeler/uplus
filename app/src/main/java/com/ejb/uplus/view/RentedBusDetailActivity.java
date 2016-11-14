package com.ejb.uplus.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.cl.core.MVPFrame.BaseView;
import com.cl.util.DensityUtils;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.component.pickers.TwoTimePicker;
import com.ejb.uplus.contract.RentedBusDetailContract;
import com.ejb.uplus.presenter.RentedBusDetailPresenter;
import com.ejb.uplus.util.ActivityUtil;
import com.ejb.uplus.util.DateUtil;
import com.ejb.uplus.util.ResUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by John on 10/25/2016.
 */

public class RentedBusDetailActivity extends MultiStateActivity<RentedBusDetailPresenter> implements RentedBusDetailContract.IView, View.OnClickListener, DialogInterface.OnClickListener, BaseSliderView.OnSliderClickListener
{
    private static final long SLIDER_DURATION = 3000;
    private SliderLayout sliderLayout;
    private TableLayout tableLayout;
    private TextView tel;
    private TextView subscribeBtn;
    private AlertDialog timeDailog;
    private View timeDialogLayout;
    private TwoTimePicker twoTimePicker;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        inflater = LayoutInflater.from(this);
        initViews();
        initConfigers();
        setListener();
        initPage();
    }

    @Override
    public void initViews()
    {
        sliderLayout = (SliderLayout) findViewById(R.id.slide_layout);
        tableLayout = (TableLayout)findViewById(R.id.table);
        tel = (TextView)findViewById(R.id.tel);
        subscribeBtn = (TextView)findViewById(R.id.subscribe_btn);
        timeDialogLayout = inflater.inflate(R.layout.layout_choose_start_end_date, null, false);
        twoTimePicker = (TwoTimePicker)timeDialogLayout.findViewById(R.id.two_time_picker);
    }

    @Override
    public void initPage() {
        mPresenter.getBusDetail();
        setTopBarTitle("待租车辆");
    }

    @Override
    public void initConfigers() {
        timeDailog = new AlertDialog.Builder(this, R.style.style_bottom_dialog).setView(timeDialogLayout).setPositiveButton("确定", this).setNegativeButton("取消", this).create();
        Window window = timeDailog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.y = ResUtil.getScreenHeight(this)-lp.height;
        lp.width = ResUtil.getScreenWidth(this);
    }

    @Override
    public void setListener() {
        tel.setOnClickListener(this);
        subscribeBtn.setOnClickListener(this);
    }

    @Override
    public void setSlideImages(ArrayList<String> slideImages)
    {
        for (int i=0; i<slideImages.size(); i++)
        {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description("").image(slideImages.get(i))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putInt("index", i);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.startAutoCycle(SLIDER_DURATION, SLIDER_DURATION, true);
    }

    @Override
    protected void onStop()
    {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void setTable(LinkedHashMap<String, String> params)
    {
        tableLayout.removeAllViews();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int index = 0;
        for (Map.Entry<String, String> entry: entries)
        {
            index++;
            TableRow tableRow = createTableItem(index, entry.getKey(), entry.getValue());
            tableLayout.addView(tableRow);
        }
    }

    private TableRow createTableItem(int index, String key, String value)
    {
        TableRow tableRow = new TableRow(this);
        TableLayout.LayoutParams tableLayout = new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(this, 40));
        tableRow.setBackgroundColor(getResources().getColor(R.color.color_table_border));
        tableRow.setLayoutParams(tableLayout);

        TextView textKey = new TextView(this);
        TextView textValue = new TextView(this);
        TableRow.LayoutParams keyParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        TableRow.LayoutParams valueParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textKey.setBackgroundColor(getResources().getColor(R.color.color_table_key));
        textValue.setBackgroundColor(getResources().getColor(R.color.color_table_value));
        textKey.setText(key);
        textValue.setText(value);
        textKey.setPadding((int)DensityUtils.dp2px(this, 8),
                (int)DensityUtils.dp2px(this, 8),
                (int)DensityUtils.dp2px(this, 8),
                (int)DensityUtils.dp2px(this, 8));
        textValue.setPadding((int)DensityUtils.dp2px(this, 8),
                (int)DensityUtils.dp2px(this, 8),
                (int)DensityUtils.dp2px(this, 8),
                (int)DensityUtils.dp2px(this, 8));
        textKey.setTextSize(12);
        textValue.setTextSize(12);
        if (index==0)
        {
            keyParams.setMargins((int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border));
            valueParams.setMargins(0,
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border));
        }else
        {
            keyParams.setMargins((int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    0,
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border));
            valueParams.setMargins(0,
                    0,
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border),
                    (int)getResources().getDimensionPixelSize(R.dimen.dimen_table_border));
        }
        textKey.setLayoutParams(keyParams);
        textValue.setLayoutParams(valueParams);
        tableRow.addView(textKey);
        tableRow.addView(textValue);
        return tableRow;
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_rented_bus_detail;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tel:
                ActivityUtil.jumpToDialNumber(this, tel.getText().toString());
                break;
            case R.id.subscribe_btn:
                twoTimePicker.setDate1(new Date());
                twoTimePicker.setDate2(new Date());
                timeDailog.show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onLeftClick() {
        onBackPressed();
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        switch (which)
        {
            case DialogInterface.BUTTON_POSITIVE:
                Toast.makeText(mContext, DateUtil.getFormatTime(twoTimePicker.getDate1(), "yyyy-MM-dd")+"====="+DateUtil.getFormatTime(twoTimePicker.getDate2(), "yyyy-MM-dd"), Toast.LENGTH_SHORT).show();
                timeDailog.dismiss();
                ActivityUtil.goActivity(this, ConfirmOrderActivity.class, new Bundle());
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                timeDailog.dismiss();
                Log.d("RentedBusDetailActivity", "negative btn is clicked");
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                Log.d("RentedBusDetailActivity", "neutral btn is clicked");
                break;
            default:
                break;
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider)
    {
        sliderLayout.stopAutoCycle();
        Toast.makeText(this, "image "+slider.getBundle().getInt("index")+" is clicked", Toast.LENGTH_SHORT).show();
        sliderLayout.startAutoCycle(SLIDER_DURATION, SLIDER_DURATION, true);
    }
}
