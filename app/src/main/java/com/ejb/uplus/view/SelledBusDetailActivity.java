package com.ejb.uplus.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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
import com.ejb.uplus.contract.SelledBusDetailContract;
import com.ejb.uplus.presenter.SelledBusDetailPresenter;
import com.ejb.uplus.util.ActivityUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by John on 10/25/2016.
 */

public class SelledBusDetailActivity extends MultiStateActivity<SelledBusDetailPresenter> implements SelledBusDetailContract.IView, View.OnClickListener, BaseSliderView.OnSliderClickListener
{
    private static final long SLIDER_DURATION = 3000;
    private SliderLayout sliderLayout;
    private TableLayout tableLayout;
    private TextView tel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigers();
        setListeners();
        initPage();
    }

    @Override
    public void initViews() {
        sliderLayout = (SliderLayout) findViewById(R.id.slide_layout);
        tableLayout = (TableLayout)findViewById(R.id.table);
        tel = (TextView)findViewById(R.id.tel);
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
    public void initPage() {
        mPresenter.getSelledBusDetail();
        setTopBarTitle("待售车辆");
    }

    @Override
    public void initConfigers()
    {

    }

    @Override
    public void setListeners() {
        tel.setOnClickListener(this);
    }

    @Override
    public void setSlideData(ArrayList<String> slideData)
    {
        for (int i=0; i<slideData.size(); i++)
        {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.description("").image(slideData.get(i))
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
    public void setTableData(LinkedHashMap<String, String> tableData)
    {
        tableLayout.removeAllViews();
        Set<Map.Entry<String, String>> entries = tableData.entrySet();
        int index = 0;
        for (Map.Entry<String, String> entry: entries)
        {
            index++;
            TableRow tableRow = createTableItem(index, entry.getKey(), entry.getValue());
            tableLayout.addView(tableRow);
        }
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_selled_bus_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tel:
                ActivityUtil.jumpToDialNumber(this, tel.getText().toString());
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
    public void onSliderClick(BaseSliderView slider)
    {
        sliderLayout.stopAutoCycle();
        Toast.makeText(this, "image "+slider.getBundle().getInt("index")+" is clicked", Toast.LENGTH_SHORT).show();
        sliderLayout.startAutoCycle(SLIDER_DURATION, SLIDER_DURATION, true);
    }
}
