package com.ejb.uplus.presenter;

import android.os.Handler;
import android.widget.Toast;

import com.cl.core.MVPFrame.BasePresenter;
import com.ejb.uplus.contract.HomeContract;
import com.ejb.uplus.model.HomeModel;
import com.ejb.uplus.view.HomeActivity;

import java.util.ArrayList;

/**
 * Created by John on 10/21/2016.
 */

public class HomePresenter extends BasePresenter<HomeContract.IView> implements HomeContract.IPresenter{
    private boolean canExit = false;

    public HomePresenter() {
        super();
    }

    @Override
    public void getSlideData()
    {
        ArrayList<String> slideData = new HomeModel().getSlideData();
        getIView().setSlideData(slideData);
    }

    @Override
    public void onExitApp()
    {
        if (canExit){
            ((HomeActivity)getIView()).onBackPressed();
        }else {
            canExit = true;
            Toast.makeText(mContext, "再次点击返回退出应用", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    canExit = false;
                }
            }, 2000);
        }
    }
}
