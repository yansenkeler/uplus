package com.ejb.uplus.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 11/3/2016.
 */

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter
{
    private List<T> list;

    protected Context context;

    public BaseAdapter(Context ctx)
    {
        init(ctx, new ArrayList<>());
    }

    public BaseAdapter(Context ctx, List<T> list)
    {
        init(ctx, list);
    }

    private void init(Context ctx, List<T> list)
    {
        this.list = list;
        this.context = ctx;
    }

    public List<T> getList()
    {
        return list;
    }

    public void setList(List<T> list)
    {
        this.list = list;
    }

    public void clear()
    {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list)
    {
        if (list!=null)
        {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount()
    {
        return list==null?0:list.size();
    }

    @Override
    public T getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int  position, View convertView, ViewGroup parent)
    {
        if (convertView==null)
        {
            convertView = inflate(getContentView());
        }
        onInitView(convertView, position);
        return convertView;
    }

    private View inflate(int layoutResId)
    {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layoutResId, null);
        return view;
    }

    public abstract int getContentView();

    public abstract void onInitView(View view, int position);

    @SuppressWarnings("unchecked")
    protected <E extends View> E get(View view, int id)
    {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder==null)
        {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView==null)
        {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (E) childView;
    }
}
