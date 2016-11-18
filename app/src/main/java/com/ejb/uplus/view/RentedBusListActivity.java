package com.ejb.uplus.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.adapter.RentedBusListAdapter;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.bean.RentedBus;
import com.ejb.uplus.component.RalmListView.LoadMoreListView;
import com.ejb.uplus.component.RalmListView.OnLoadMoreListener;
import com.ejb.uplus.contract.RentedBusListContract;
import com.ejb.uplus.presenter.RentedBusListPresenter;
import com.ejb.uplus.util.ActivityUtil;
import com.ejb.uplus.util.ResUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by John on 10/24/2016.
 */

public class RentedBusListActivity extends MultiStateActivity<RentedBusListPresenter> implements RentedBusListContract.IView, AdapterView.OnItemClickListener, View.OnClickListener, OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemSelectedListener
{
    private LoadMoreListView mLoadMoreListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ArrayList<RentedBus> rentedBuses = new ArrayList<>();
    private RentedBusListAdapter rentedBusListAdapter;
    private Spinner mSpinner1, mSpinner2;
    private ArrayList<String> mSpinnerData1 = new ArrayList<>(Arrays.asList("全部品牌", "品牌一", "品牌二", "品牌三"));
    private ArrayList<String> mSpinnerData2 = new ArrayList<>(Arrays.asList("全部车型", "大型车", "中型车", "小型车"));

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
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        mLoadMoreListView = (LoadMoreListView) findViewById(R.id.list_view);
        mSpinner1 = (Spinner) findViewById(R.id.spinner1);
        mSpinner2 = (Spinner) findViewById(R.id.spinner2);
    }

    @Override
    public void initPage() {
        setTopBarTitle("待租车辆");
    }

    @Override
    public void initConfigers() {
        rentedBusListAdapter = new RentedBusListAdapter(mContext, rentedBuses);
        mLoadMoreListView.setAdapter(rentedBusListAdapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mSpinnerData1);
        mSpinner1.setAdapter(adapter1);
        mSpinner1.setDropDownWidth(ResUtil.getScreenWidth(this)/2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mSpinnerData2);
        mSpinner2.setAdapter(adapter2);
        mSpinner2.setDropDownWidth(ResUtil.getScreenWidth(this)/2);
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
    }

    @Override
    public void setListener() {
        mLoadMoreListView.setOnItemClickListener(this);
        mLoadMoreListView.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSpinner1.setOnItemSelectedListener(this);
        mSpinner2.setOnItemSelectedListener(this);
    }

    @Override
    public void refreshListView(ArrayList<RentedBus> rentedBuses)
    {
        this.rentedBuses.addAll(rentedBuses);
        rentedBusListAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshListView(int index, ArrayList<RentedBus> rentedBuses)
    {
        this.rentedBuses.addAll(index, rentedBuses);
        rentedBusListAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopRefresh()
    {
        if (mSwipeRefreshLayout.isRefreshing())
        {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void stopLoadMore()
    {
        mLoadMoreListView.stopLoadMore();
    }

    @Override
    public void refreshInitList()
    {
        rentedBuses.clear();
        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getListData();
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
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mPresenter.getRefreshData();
            }
        }, 2000);
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
    protected void onResume()
    {
        super.onResume();
        refreshInitList();
    }

    @Override
    public void onLoadMore()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mPresenter.getLoadMoreData();
            }
        }, 2000);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        switch (view.getId())
        {
            case R.id.spinner1:
                break;
            case R.id.spinner2:
                break;
            default:
                break;
        }
        refreshInitList();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}
