package com.ejb.uplus.component.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ejb.uplus.R;

/**
 * Created by John on 10/26/2016.
 */

public class TopBar extends RelativeLayout implements View.OnClickListener{
    private ImageView backBtn;
    private TextView title;
    private FrameLayout rightFrame;
    private String titleVal;
    private boolean hasBackBtn;
    private boolean hasRightContent;
    private int rightContentRef;
    private OnButtonClickListener listener;
    private Context mContext;

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflate(context, R.layout.layout_top_bar, this);
        backBtn = (ImageView) findViewById(R.id.back_btn);
        title = (TextView)findViewById(R.id.top_bar_title);
        rightFrame = (FrameLayout)findViewById(R.id.right_frame);

        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.TopBar);
        titleVal = typedArray.getString(R.styleable.TopBar_title);
        hasBackBtn = typedArray.getBoolean(R.styleable.TopBar_has_back_btn, true);
        hasRightContent = typedArray.getBoolean(R.styleable.TopBar_has_right_content, false);
        rightContentRef = typedArray.getResourceId(R.styleable.TopBar_right_content, R.layout.layout_right_content);
        typedArray.recycle();

        backBtn.setOnClickListener(this);
        rightFrame.setOnClickListener(this);
        title.setText(titleVal);
        backBtn.setVisibility(hasBackBtn ? VISIBLE: GONE);
        rightFrame.setVisibility(hasRightContent ? VISIBLE: GONE);
        View rc = inflate(context, rightContentRef, null);
        rightFrame.addView(rc, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setHasBackBtn(boolean hasBackBtn)
    {
        this.hasBackBtn = hasBackBtn;
        backBtn.setVisibility(hasBackBtn ? VISIBLE: GONE);
    }

    public void setHasRightContent(boolean hasRightContent)
    {
        this.hasRightContent = hasRightContent;
        rightFrame.setVisibility(hasRightContent ? VISIBLE: GONE);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context) {
        this(context, null);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.back_btn:
                listener.onLeftClick();
                break;
            case R.id.right_frame:
                listener.onRightClick();
                break;
            default:
                break;
        }
    }

    public void setRightContentRef(int res)
    {
        this.rightContentRef = res;
        rightFrame.removeAllViews();
        View rc = inflate(mContext, rightContentRef, null);
        rightFrame.addView(rc, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setTitle(String titleVal) {
        this.titleVal = titleVal;
        title.setText(titleVal);
    }

    public void setListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnButtonClickListener{
        void onLeftClick();
        void onRightClick();
    }
}
