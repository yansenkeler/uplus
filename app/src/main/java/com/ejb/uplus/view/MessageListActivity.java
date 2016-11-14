package com.ejb.uplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cl.core.MVPFrame.BaseView;
import com.cl.core.activity.BaseActivity;
import com.ejb.uplus.R;
import com.ejb.uplus.adapter.MessageListAdapter;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.bean.Message;
import com.ejb.uplus.component.toolbar.TopBar;
import com.ejb.uplus.contract.MessageListContract;
import com.ejb.uplus.presenter.MessageListPresenter;
import com.ejb.uplus.util.ActivityUtil;
import com.ejb.uplus.util.DateUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by John on 11/1/2016.
 */

public class MessageListActivity extends MultiStateActivity<MessageListPresenter> implements MessageListContract.IView, AdapterView.OnItemClickListener
{
    private ListView listView;
    private MessageListAdapter messageListAdapter;
    private ArrayList<Message> messages = new ArrayList<>();

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
        listView = (ListView)findViewById(R.id.list_view);
    }

    @Override
    public void initConfigs()
    {
        messageListAdapter = new MessageListAdapter(mContext, messages);
        listView.setAdapter(messageListAdapter);
    }

    @Override
    public void setListeners()
    {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void initPage()
    {
        mPresenter.getMessages();
        setTopBarTitle("消息中心");
    }

    @Override
    public void refreshList(ArrayList<Message> data)
    {
        messages.addAll(data);
        messageListAdapter.notifyDataSetChanged();
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_message_list;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Message message = messages.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("message_title", message.getTitle());
        bundle.putString("message_time", DateUtil.getFormatTime(message.getDate(), "yyyy-MM-dd hh:mm:ss"));
        bundle.putString("message_content", message.getContent());
        ActivityUtil.goActivity(this, MessageDetailActivity.class, bundle);
        messages.get(position).setRead(true);
        messageListAdapter.notifyDataSetChanged();
    }
}
