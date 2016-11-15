package com.ejb.uplus.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ejb.uplus.R;
import com.ejb.uplus.base.BaseAdapter;
import com.ejb.uplus.bean.SelledBus;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by John on 10/24/2016.
 */

public class SelledBusListAdapter extends BaseAdapter<SelledBus>
{

    public SelledBusListAdapter(Context ctx, List<SelledBus> list)
    {
        super(ctx, list);
    }

    @Override
    public int getContentView()
    {
        return R.layout.item_list_selled_bus;
    }

    @Override
    public void onInitView(View view, int position)
    {
        RoundedImageView snapShot = (RoundedImageView) view.findViewById(R.id.snapshot);
        TextView name = (TextView)view.findViewById(R.id.name);
        TextView price = (TextView)view.findViewById(R.id.price);
        SelledBus selledBus = getItem(position);

        name.setText(selledBus.getName());
        price.setText(selledBus.getPrice());
        Glide.with(context).load(selledBus.getSnapshot()).asBitmap().placeholder(R.mipmap.icon_def_auto).into(snapShot);
    }
}
