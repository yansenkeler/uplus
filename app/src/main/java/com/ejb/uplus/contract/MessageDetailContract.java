package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 11/2/2016.
 */

public class MessageDetailContract
{
    public interface IView extends BaseView
    {
        void initViews();
        void initConfigs();
        void setListeners();
        void initPage();
    }

    public interface IPresenter
    {

    }
}
