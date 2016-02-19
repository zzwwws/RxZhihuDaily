package com.github.zzwwws.rxzhihudaily.presenter.infr;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.zzwwws.rxzhihudaily.presenter.adapter.StoriesAdapter;

import java.util.List;

/**
 * Created by zzwwws on 2016/2/19.
 */
public abstract class StoryScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0;
    private boolean loading = true;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 0;

    private LinearLayoutManager mLinearLayoutManager;

    private StoriesAdapter adapter;

    private List<Integer> storyHeadPos;

    public StoryScrollListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
        this.adapter = (StoriesAdapter)recyclerView.getAdapter();
        storyHeadPos = this.adapter.getStoryHeadPos();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if(firstVisibleItem == 0){
            onScrollToNextDay("首页");
        }else if(storyHeadPos != null && storyHeadPos.contains(firstVisibleItem)){
            String date = firstVisibleItem == 1?"今日热闻":adapter.getTitleByHeadPos(firstVisibleItem);
            onScrollToNextDay(date);
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
            onLoadMore(currentPage);
            loading = true;
            currentPage++;
        }
    }
    public abstract void onLoadMore(int currentPage);

    public abstract void onScrollToNextDay(String date);
}
