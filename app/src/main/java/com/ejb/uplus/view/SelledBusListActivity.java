package com.ejb.uplus.view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.adapter.SelledBusListAdapter;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.bean.SelledBus;
import com.ejb.uplus.component.RalmListView.OnRefreshAndLoadMoreListener;
import com.ejb.uplus.component.RalmListView.RefreshAndLoadMoreListView;
import com.ejb.uplus.contract.SelledBusListContract;
import com.ejb.uplus.presenter.SelledBusListPresenter;
import com.ejb.uplus.util.ActivityUtil;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.NiceSpinnerAdapter;
import org.angmarch.views.NiceSpinnerBaseAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by John on 10/24/2016.
 */

public class SelledBusListActivity extends MultiStateActivity<SelledBusListPresenter> implements SelledBusListContract.IView, AdapterView.OnItemClickListener, OnRefreshAndLoadMoreListener, View.OnClickListener
{
    private RefreshAndLoadMoreListView refreshAndLoadMoreListView;
    private SelledBusListAdapter selledBusListAdapter;
    private ArrayList<SelledBus> selledBuses = new ArrayList<>();
    private MaterialSpinner mMaterialSpinner1, mMaterialSpinner2;
    private ArrayList<String> mSpinnerData1 = new ArrayList<>(Arrays.asList("选择品牌", "品牌一", "品牌二", "品牌三"));
    private ArrayList<String> mSpinnerData2 = new ArrayList<>(Arrays.asList("选择车型", "大型车", "中型车", "小型车"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        return R.layout.activity_selled_bus_list;
    }

    @Override
    public void initViews() {
        refreshAndLoadMoreListView = (RefreshAndLoadMoreListView)findViewById(R.id.list_view);
        mMaterialSpinner1 = (MaterialSpinner) findViewById(R.id.spinner1);
        mMaterialSpinner2 = (MaterialSpinner) findViewById(R.id.spinner2);
    }

    @Override
    public void initPage() {
        mPresenter.getListData();
        setTopBarTitle("待售车辆");
    }

    @Override
    public void initConfigers() {
        selledBusListAdapter = new SelledBusListAdapter(mContext, selledBuses);
        refreshAndLoadMoreListView.setAdapter(selledBusListAdapter);
        mMaterialSpinner1.setItems(mSpinnerData1);
        mMaterialSpinner2.setItems(mSpinnerData2);
    }

    @Override
    public void setListeners() {
        refreshAndLoadMoreListView.setOnRefreshAndLoadMoreListener(this);
        refreshAndLoadMoreListView.setOnItemClickListener(this);
    }

    @Override
    public void refreshListView(int index, ArrayList<SelledBus> selledBuses)
    {
        this.selledBuses.addAll(index, selledBuses);
        selledBusListAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshListView(ArrayList<SelledBus> selledBuses)
    {
        this.selledBuses.addAll(selledBuses);
        selledBusListAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefresh()
    {
        refreshAndLoadMoreListView.stopRefresh();
    }

    @Override
    public void stopLoad()
    {
        refreshAndLoadMoreListView.stopLoadMore();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityUtil.goActivity(this, SelledBusDetailActivity.class, new Bundle());
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> mPresenter.getRefreshData(), 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> mPresenter.getLoadData(), 2000);
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
