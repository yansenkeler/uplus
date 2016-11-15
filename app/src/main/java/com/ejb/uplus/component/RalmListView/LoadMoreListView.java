package com.ejb.uplus.component.RalmListView;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ejb.uplus.R;

/**
 * 下拉刷新，上滑加载更多
 * Created by shenghaiyang.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    //刷新的回调接口
    private OnLoadMoreListener mListener;
    //加载更多的View
    private View mLoadMoreFooter;
    //加载更多显示的文字
    private TextView mLoadMoreTxv;
    //加载更多显示的ProgressBar
    private ProgressBar mLoadMoreProgress;

    public LoadMoreListView(Context context) {
        this(context, null);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化footer布局
        mLoadMoreFooter = LayoutInflater.from(context).inflate(R.layout.footer, null);
        mLoadMoreTxv = (TextView) mLoadMoreFooter.findViewById(R.id.loadMoreTxv);
        mLoadMoreProgress = (ProgressBar) mLoadMoreFooter.findViewById(R.id.loadMoreProgress);
        //开始时不显示ProgressBar
        mLoadMoreProgress.setVisibility(GONE);
        //添加footer
        addFooterView(mLoadMoreFooter);
        //添加滑动监听
        setOnScrollListener(this);
        //设置item的分割线
//        setDividerHeight(2);
    }


    /**
     * 测量View的高
     *
     * @param child view
     */
    private void measureViewHeight(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = getChildMeasureSpec(0, 0, params.width);
        int tempHeight = params.height;
        int childHeightSpec;
        if (tempHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(tempHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //滑动到底部
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE &&
                view.getLastVisiblePosition() == getAdapter().getCount() - 1) {
            mLoadMoreProgress.setVisibility(VISIBLE);
            mLoadMoreTxv.setText("正在加载……");
            //回调的loadmore
            mListener.onLoadMore();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }

    /**
     * 停止loadMore
     */
    public void stopLoadMore() {
        mLoadMoreTxv.setText("加载更多");
        mLoadMoreProgress.setVisibility(GONE);
    }

    /**
     * 设置OnRefreshAndLoadMoreListener
     *
     * @param listener OnRefreshAndLoadMoreListener的对象
     */
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mListener = listener;
    }

}
