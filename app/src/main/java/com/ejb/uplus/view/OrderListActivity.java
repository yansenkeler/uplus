package com.ejb.uplus.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.R;
import com.ejb.uplus.adapter.OrderListAdapter;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.bean.Order;
import com.ejb.uplus.component.RalmListView.LoadMoreListView;
import com.ejb.uplus.component.RalmListView.OnLoadMoreListener;
import com.ejb.uplus.contract.OrderListContract;
import com.ejb.uplus.presenter.OrderListPresenter;
import com.ejb.uplus.util.ActivityUtil;

import java.util.ArrayList;

/**
 * Created by John on 10/26/2016.
 */

public class OrderListActivity extends MultiStateActivity<OrderListPresenter> implements OrderListContract.IView, AdapterView.OnItemClickListener, OnLoadMoreListener, View.OnClickListener
{
    private LoadMoreListView listView;
    private OrderListAdapter listAdapter;
    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
        initConfigs();
        setListeners();
        initPage();
    }

    private void initData() {

    }

    @Override
    public void initViews() {
        listView = (LoadMoreListView)findViewById(R.id.list_view);
    }

    @Override
    public void initConfigs() {
        listAdapter = new OrderListAdapter(mContext, orders);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void setListeners() {
        listView.setOnItemClickListener(this);
        listView.setOnLoadMoreListener(this);
    }

    @Override
    public void initPage() {
        mPresenter.getOrderList();
        setTopBarTitle(getResources().getString(R.string.my_orders));
    }

    @Override
    public void refreshList(ArrayList<Order> orders)
    {
        this.orders.addAll(orders);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void stopLoad()
    {
        listView.stopLoadMore();
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_order_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityUtil.goActivity(this, OrderDetailActivity.class, new Bundle());
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> mPresenter.getLoadMoreData(), 2000);
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
