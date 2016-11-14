package com.ejb.uplus.presenter;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.bean.Message;
import com.ejb.uplus.contract.MessageListContract;
import com.ejb.uplus.model.MessageListModel;

import java.util.ArrayList;

/**
 * Created by John on 11/1/2016.
 */

public class MessageListPresenter extends BasePresenter<MessageListContract.IView> implements MessageListContract.IPresenter
{
    private MessageListModel model;

    @Override
    public void getMessages()
    {
        ArrayList<Message> messages = new MessageListModel().getMessageData();
        getIView().refreshList(messages);
    }
}
