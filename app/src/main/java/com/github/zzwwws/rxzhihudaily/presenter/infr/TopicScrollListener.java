package com.github.zzwwws.rxzhihudaily.presenter.infr;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.zzwwws.rxzhihudaily.presenter.adapter.TopicDetailAdapter;

import java.util.List;

/**
 * Created by zzwwws on 2016/2/19.
 */
public abstract class TopicScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private LinearLayoutManager mLinearLayoutManager;

    private TopicDetailAdapter adapter;

    public TopicScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.adapter = (TopicDetailAdapter)recyclerView.getAdapter();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            onLoadMore(adapter.getLatestId());
            loading = true;
        }
    }
    public abstract void onLoadMore(String latestId);

}
