package com.ejb.uplus.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ejb.uplus.R;
import com.ejb.uplus.base.BaseAdapter;
import com.ejb.uplus.bean.Message;
import com.ejb.uplus.util.DateUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created by John on 11/1/2016.
 */

public class MessageListAdapter extends BaseAdapter<Message>
{
    public MessageListAdapter(Context ctx, List<Message> list)
    {
        super(ctx, list);
    }

    @Override
    public int getContentView()
    {
        return R.layout.item_list_message;
    }

    @Override
    public void onInitView(View view, int position)
    {
        TextView title = get(view, R.id.title);
        TextView time = get(view, R.id.time);
        Message message = getItem(position);

        title.setText(message.getTitle());
        time.setText(DateUtil.getFormatTime(message.getDate(), "yyyy-MM-mm hh:mm:ss"));
        if (message.isRead())
        {
            view.setBackground(context.getResources().getDrawable(R.drawable.btn_grey1));
        }else
        {
            view.setBackground(context.getResources().getDrawable(R.drawable.btn_grey2));
        }

    }

}
