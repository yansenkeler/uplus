package com.ejb.uplus.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

/**
 * Created by John on 10/24/2016.
 */

public class NormalImagePageAdapter extends LoopPagerAdapter {
    private String[] resArr;
    private Context context;

    public NormalImagePageAdapter(RollPagerView viewPager, String[] res, Context context) {
        super(viewPager);
        this.resArr = res;
        this.context = context;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        Glide.with(context).load(resArr[position]).asBitmap().into(view);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }

    @Override
    public int getRealCount() {
        return resArr.length;
    }
}
