package com.ejb.uplus.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cl.core.MVPFrame.BaseView;
import com.cl.core.activity.BaseActivity;
import com.ejb.uplus.R;
import com.ejb.uplus.base.MultiStateActivity;
import com.ejb.uplus.bean.Message;
import com.ejb.uplus.component.toolbar.TopBar;
import com.ejb.uplus.contract.MessageDetailContract;
import com.ejb.uplus.presenter.MessageDetailPresenter;
import com.ejb.uplus.util.DateUtil;

/**
 * Created by John on 11/2/2016.
 */

public class MessageDetailActivity extends MultiStateActivity<MessageDetailPresenter> implements MessageDetailContract.IView
{
    private Message message = new Message();
    private TextView title;
    private TextView time;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initData();
        initViews();
        initConfigs();
        setListeners();
        initPage();
    }

    private void initData()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null && bundle.containsKey("message_title") && bundle.containsKey("message_time") && bundle.containsKey("message_content"))
        {
            String s = bundle.getString("message_title", "message_title");
            String t = bundle.getString("message_time", "message_time");
            String c = bundle.getString("message_content", "message_content");
            if (s!=null)
            {
                message.setTitle(s);
            }
            if (t!=null)
            {
                message.setDate(DateUtil.getOriginDate(t, "yyyy-MM-dd hh:mm:ss"));
            }
            if (c!=null)
            {
                message.setContent(c);
            }
        }
    }

    @Override
    public void initViews()
    {
        title = (TextView)findViewById(R.id.title);
        time = (TextView)findViewById(R.id.time);
        content = (TextView)findViewById(R.id.content);
    }

    @Override
    public void initConfigs()
    {

    }

    @Override
    public void setListeners()
    {
    }

    @Override
    public void initPage()
    {
        title.setText(message.getTitle());
        time.setText(DateUtil.getFormatTime(message.getDate(), "yyyy-MM-dd hh:mm:ss"));
        content.setText(message.getContent());
        setTopBarTitle("消息详情");
    }

    @Override
    protected BaseView bindView()
    {
        return this;
    }

    @Override
    protected int getContentId()
    {
        return R.layout.activity_message_detail;
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
}
