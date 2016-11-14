package com.ejb.uplus.contract;

import com.cl.core.MVPFrame.BaseView;

import java.util.ArrayList;

/**
 * Created by John on 10/21/2016.
 */

public class HomeContract {

    public interface IView extends BaseView{
        //初始化布局文件的控件
        void initViews();
        void setListeners();
        //控件加载完成后初始化控件的初始状态
        void initPage();
        //初始化适配器，管理器之类
        void initConfigers();
        void setSlideData(ArrayList<String> data);
    }

    public interface IPresenter{
        void getSlideData();
        void onExitApp();
    }
}
