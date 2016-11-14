package com.ejb.uplus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ejb.uplus.R;
import com.ejb.uplus.base.BaseAdapter;
import com.ejb.uplus.bean.RentedBus;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 10/25/2016.
 */

public class RentedBusListAdapter extends BaseAdapter<RentedBus>
{
    public RentedBusListAdapter(Context ctx, List<RentedBus> list)
    {
        super(ctx, list);
    }

    @Override
    public int getContentView()
    {
        return R.layout.item_list_rented_bus;
    }

    @Override
    public void onInitView(View view, int position)
    {
        RoundedImageView snapshot = (RoundedImageView) view.findViewById(R.id.snapshot);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView price = (TextView)view.findViewById(R.id.price);
        RentedBus rentedBus = getItem(position);

        Glide.with(context).load(rentedBus.getSnapshot()).asBitmap().placeholder(R.mipmap.icon_def_auto).into(snapshot);
        name.setText(rentedBus.getName());
        price.setText(rentedBus.getPrice());
    }
}
