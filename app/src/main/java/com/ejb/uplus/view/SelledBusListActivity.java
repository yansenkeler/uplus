package com.ejb.uplus.view;

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

import java.util.ArrayList;

/**
 * Created by John on 10/24/2016.
 */

public class SelledBusListActivity extends MultiStateActivity<SelledBusListPresenter> implements SelledBusListContract.IView, AdapterView.OnItemClickListener, OnRefreshAndLoadMoreListener, View.OnClickListener
{
    private RefreshAndLoadMoreListView refreshAndLoadMoreListView;
    private SelledBusListAdapter selledBusListAdapter;
    private ArrayList<SelledBus> selledBuses = new ArrayList<>();

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
