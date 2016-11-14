package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;
import com.ejb.uplus.bean.Message;

import java.util.ArrayList;

/**
 * Created by John on 11/1/2016.
 */

public class MessageListContract
{
    public interface IView extends BaseView
    {
        void initViews();
        void initConfigs();
        void setListeners();
        void initPage();
        void refreshList(ArrayList<Message> messages);
    }

    public interface IPresenter
    {
        void getMessages();
    }
}
