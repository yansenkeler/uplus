package com.ejb.uplus.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.cl.core.MVPFrame.BasePresenter;
import com.cl.core.MVPFrame.BaseView;
import com.cl.core.activity.BaseActivity;
import com.ejb.uplus.R;
import com.ejb.uplus.component.other.CustomLoadingView;
import com.ejb.uplus.component.other.MultiStateView;
import com.ejb.uplus.component.toolbar.TopBar;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by John on 11/7/2016.
 */

public abstract class MultiStateActivity<P extends BasePresenter> extends BaseActivity<BasePresenter> implements MultiStateView.StateListener, TopBar.OnButtonClickListener

{
    private MultiStateView multiStateView;
    public P mPresenter;
    private CustomLoadingView logo = null;
    private TopBar topBar;
    private boolean isTopBarShown = true;
    private boolean isTopBarBackBtnShown = true;
    private boolean isTopBarRightContentShown = false;
    private SweetAlertDialog loadingDialog;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_state);
        topBar = (TopBar)findViewById(R.id.top_bar);
        multiStateView = (MultiStateView) findViewById(R.id.multiStateView);
        multiStateView.setStateListener(this);
        multiStateView.setViewForState(getContentId(), MultiStateView.VIEW_STATE_CONTENT);
        multiStateView.setViewForState(R.layout.default_unknown_view, MultiStateView.VIEW_STATE_UNKNOWN);
        multiStateView.setViewForState(R.layout.default_empty_view, MultiStateView.VIEW_STATE_EMPTY);
        multiStateView.setViewForState(R.layout.default_error_view, MultiStateView.VIEW_STATE_ERROR);
        multiStateView.setViewForState(R.layout.default_loading_view, MultiStateView.VIEW_STATE_LOADING);
        this.mPresenter = (P) super.mPresenter;
        this.mPresenter.initPresenter(this.getBaseView());
        topBar.setListener(this);
        isTopBarShown = topBarVisilable()[0];
        isTopBarBackBtnShown = topBarVisilable()[1];
        isTopBarRightContentShown = topBarVisilable()[2];
        topBar.setVisibility(isTopBarShown?View.VISIBLE:View.GONE);
        topBar.setHasBackBtn(isTopBarBackBtnShown);
        topBar.setHasRightContent(isTopBarRightContentShown);
        topBar.setRightContentRef(getRightContentRef());
        loadingDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        loadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.color_crimson));
        loadingDialog.setTitleText("Loading...");
        loadingDialog.setCancelable(false);
    }

    public void showLoadingDailog(String text)
    {
        loadingDialog.setTitleText(text);
        loadingDialog.show();
    }

    public void hideLoadingDailog()
    {
        if (loadingDialog!=null && loadingDialog.isShowing())
        {
            loadingDialog.hide();
        }
    }

    protected void setTopBarTitle(String title)
    {
        topBar.setTitle(title);
    }

    //返回topbar,topbar返回按钮,topbar右边按钮是否可见
    protected boolean[] topBarVisilable()
    {
        return new boolean[]{ true, true, false };
    }

    protected int getRightContentRef()
    {
        return R.layout.layout_right_content;
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public BaseView getBaseView()
    {
        return bindView();
    }

    protected abstract BaseView bindView();

    protected abstract int getContentId();

    public void setState(@MultiStateView.ViewState int state)
    {
        multiStateView.setViewState(state);
        if (state==MultiStateView.VIEW_STATE_LOADING)
        {
            View v = multiStateView.getView(state);
            if (v != null)
            {
                logo = (CustomLoadingView) v.findViewById(R.id.loading_icon);
                logo.startAnim();
            }
        }
    }

    public View getStateView(@MultiStateView.ViewState int state)
    {
        return multiStateView.getView(state);
    }

    @Override
    public void onStateChanged(@MultiStateView.ViewState int viewState)
    {

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (logo!=null)
        {
            logo.stopAnim();
        }
    }

    @Override
    public void onStateInflated(@MultiStateView.ViewState int viewState, @NonNull View view)
    {

    }

    @Override
    public void onLeftClick()
    {

    }

    @Override
    public void onRightClick()
    {

    }
}
