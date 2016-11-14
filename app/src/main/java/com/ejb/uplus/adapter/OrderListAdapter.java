package com.ejb.uplus.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ejb.uplus.R;
import com.ejb.uplus.base.BaseAdapter;
import com.ejb.uplus.bean.Order;
import com.ejb.uplus.util.DateUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/26/2016.
 */

public class OrderListAdapter extends BaseAdapter<Order>
{

    public OrderListAdapter(Context ctx, List<Order> list)
    {
        super(ctx, list);
    }

    @Override
    public int getContentView()
    {
        return R.layout.item_list_order;
    }

    @Override
    public void onInitView(View view, int position)
    {
        RelativeLayout root = (RelativeLayout)view.findViewById(R.id.root);
        RoundedImageView snapshot = (RoundedImageView) view.findViewById(R.id.snapshot);
        TextView orderCode = (TextView)view.findViewById(R.id.order_code);
        TextView stateText = (TextView)view.findViewById(R.id.state_text);
        ImageView stateIcon = (ImageView)view.findViewById(R.id.state_icon);
        TextView updateTime = (TextView)view.findViewById(R.id.update_time);
        Order order = getItem(position);

        if(position%2==0){
            root.setBackground(context.getResources().getDrawable(R.drawable.btn_grey2));
        }else {
            root.setBackground(context.getResources().getDrawable(R.drawable.btn_grey1));
        }

        orderCode.setText(order.getOrderCode());
        switch (order.getState()){
            case Order.STATE_1:
                stateText.setText("待支付");
                stateIcon.setImageResource(R.drawable.image_order_state1);
                break;
            case Order.STATE_2:
                stateText.setText("已支付");
                stateIcon.setImageResource(R.drawable.image_order_state2);
                break;
            case Order.STATE_3:
                stateText.setText("已完成");
                stateIcon.setImageResource(R.drawable.image_order_state3);
                break;
            default:
                break;
        }
        Date date = order.getUpdateTime();
        updateTime.setText(DateUtil.getFormatTime(date, "yyyy-MM-dd hh:mm:ss"));
        Glide.with(context).load(order.getSnapshot()).asBitmap().placeholder(R.mipmap.icon_def_auto).into(snapshot);
    }

}
