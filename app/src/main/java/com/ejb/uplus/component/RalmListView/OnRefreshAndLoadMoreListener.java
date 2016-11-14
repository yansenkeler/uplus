package com.ejb.uplus.component.RalmListView;
/**
 * RefreshAndLoadMoreListView的回调接口
 * Created by qianyx.
 */
public interface OnRefreshAndLoadMoreListener {
    /**
     * 刷新
     */
    void onRefresh();

    /**
     * 加载更多
     */
    void onLoadMore();
}