package com.ejb.uplus.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.adapter.RentedBusListAdapter;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.bean.RentedBus;
import com.ejb.uplus.component.RalmListView.OnRefreshListener;
import com.ejb.uplus.component.RalmListView.RefreshListView;
import com.ejb.uplus.contract.RentedBusListContract;
import com.ejb.uplus.presenter.RentedBusListPresenter;
import com.ejb.uplus.util.ActivityUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by John on 10/24/2016.
 */

public class RentedBusListActivity extends MultiStateActivity<RentedBusListPresenter> implements RentedBusListContract.IView, AdapterView.OnItemClickListener, OnRefreshListener, View.OnClickListener {
    private RefreshListView listView;
    private ArrayList<RentedBus> rentedBuses = new ArrayList<>();
    private RentedBusListAdapter rentedBusListAdapter;
    private MaterialSpinner mMaterialSpinner1, mMaterialSpinner2;
    private ArrayList<String> mSpinnerData1 = new ArrayList<>(Arrays.asList("选择品牌", "品牌一", "品牌二", "品牌三"));
    private ArrayList<String> mSpinnerData2 = new ArrayList<>(Arrays.asList("选择车型", "大型车", "中型车", "小型车"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        initConfigers();
        setListener();
        initPage();
    }

    @Override
    public void initViews()
    {
        listView = (RefreshListView)findViewById(R.id.list_view);
        mMaterialSpinner1 = (MaterialSpinner) findViewById(R.id.spinner1);
        mMaterialSpinner2 = (MaterialSpinner) findViewById(R.id.spinner2);
    }

    @Override
    public void initPage() {
        mPresenter.getListData();
        setTopBarTitle("待租车辆");
    }

    @Override
    public void initConfigers() {
        rentedBusListAdapter = new RentedBusListAdapter(mContext, rentedBuses);
        listView.setAdapter(rentedBusListAdapter);
        mMaterialSpinner1.setItems(mSpinnerData1);
        mMaterialSpinner2.setItems(mSpinnerData2);
    }

    @Override
    public void setListener() {
        listView.setOnItemClickListener(this);
        listView.setOnRefreshListener(this);
    }

    @Override
    public void refreshListView(ArrayList<RentedBus> rentedBuses)
    {
        this.rentedBuses.addAll(0, rentedBuses);
        rentedBusListAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefresh()
    {
        listView.stopRefresh();
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_rented_bus_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityUtil.goActivity(this, RentedBusDetailActivity.class, new Bundle());
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> mPresenter.getRefreshData(), 2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
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
}
