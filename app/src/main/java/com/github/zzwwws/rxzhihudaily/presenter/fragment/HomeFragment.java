package com.github.zzwwws.rxzhihudaily.presenter.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.Feed;
import com.github.zzwwws.rxzhihudaily.presenter.activity.DetailActivity;
import com.github.zzwwws.rxzhihudaily.presenter.activity.MainActivity;
import com.github.zzwwws.rxzhihudaily.presenter.adapter.StoriesAdapter;
import com.github.zzwwws.rxzhihudaily.presenter.infr.RecyclerOnItemClickListener;
import com.github.zzwwws.rxzhihudaily.presenter.infr.StoryScrollListener;
import com.github.zzwwws.rxzhihudaily.presenter.impl.FetchFeedImpl;
import com.github.zzwwws.rxzhihudaily.presenter.infr.HomeRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class HomeFragment extends BaseFragment implements HomeRecyclerView, RecyclerOnItemClickListener {

    protected View rootView;
    @Bind(R.id.story_recycler)
    RecyclerView storyRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    private StoriesAdapter storiesAdapter;
    private LinearLayoutManager storyLayoutManager;
    private FetchFeedImpl fetchImpl;

    public HomeFragment() {
        setFragmentId(R.id.home_container);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment_layout, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData() {
        storiesAdapter = new StoriesAdapter(getActivity(), new Feed());
        storyRecyclerView.setHasFixedSize(true);
        storyRecyclerView.setAdapter(storiesAdapter);
        storyLayoutManager = new LinearLayoutManager(getActivity());
        storyRecyclerView.setLayoutManager(storyLayoutManager);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchImpl.loadingNew();
            }
        });
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.ColorPrimary));

        storyRecyclerView.addOnScrollListener(new StoryScrollListener(storyRecyclerView, storyLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                fetchImpl.loadingPast(currentPage);
            }

            @Override
            public void onScrollToNextDay(String date) {
                ((MainActivity) getActivity()).setToolbarTitle(date);
            }
        });
        storiesAdapter.setOnItemClickListener(this);
        fetchImpl = new FetchFeedImpl();
        fetchImpl.attachView(this);

        testRxJava();
    }

    private void testRxJava() {
        fetchImpl.loadingNew();
    }

    @Override
    public void loadingNew(Feed feed) {
        storiesAdapter.loadingNewStories(feed);
    }

    @Override
    public void loadingPast(Feed feed, String date) {

        storiesAdapter.loadingOldStories(feed, date);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(int layoutId) {

    }

    @Override
    public void onLoadingNewComplete() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadingPastComplete() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClickListener(View v, int pos) {

    }

    @Override
    public void onItemClickListener(View v, String id) {
        if(!TextUtils.isEmpty(id)){
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        }
    }
}
