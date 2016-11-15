package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 10/31/2016.
 */

public class ConfirmOrderContract
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
        void getData();
    }
}
