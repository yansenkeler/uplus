package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

/**
 * Created by John on 11/15/2016.
 */

public class ChargePileContract
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
