package com.github.zzwwws.rxzhihudaily.presenter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.presenter.adapter.TopicDetailAdapter;
import com.github.zzwwws.rxzhihudaily.presenter.infr.TopicScrollListener;
import com.github.zzwwws.rxzhihudaily.presenter.infr.TopicRecyclerView;
import com.github.zzwwws.rxzhihudaily.presenter.impl.TopicsDetailImpl;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class TopicFragment extends BaseFragment implements TopicRecyclerView {

    protected View rootView;
    @Bind(R.id.story_recycler)
    RecyclerView topicRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayoutManager topicLayoutManager;
    private TopicDetailAdapter topicDetailAdapter;
    private TopicsDetailImpl topicsImpl;
    private String topicId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.topic_fragment_layout, container, false);
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    public TopicFragment() {
        setFragmentId(R.layout.topic_fragment_layout);
    }

    public void setTopicId(String topicId){
        this.topicId = topicId;
    }

    public String getTopicId(){
        return this.topicId;
    }
    private void initData(){
        topicDetailAdapter = new TopicDetailAdapter(getActivity(), new TopicDetail());
        topicRecyclerView.setHasFixedSize(true);
        topicRecyclerView.setAdapter(topicDetailAdapter);
        topicLayoutManager = new LinearLayoutManager(getActivity());
        topicRecyclerView.setLayoutManager(topicLayoutManager);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                topicsImpl.loadingNew(topicId);
            }
        });
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.ColorPrimary));

        topicRecyclerView.addOnScrollListener(new TopicScrollListener(topicRecyclerView, topicLayoutManager) {
            @Override
            public void onLoadMore(String latestId) {
                topicsImpl.loadingPast(topicId, latestId);
            }
        });

        topicsImpl = new TopicsDetailImpl();
        topicsImpl.attachView(this);

        topicsImpl.loadingNew(topicId);
    }

    @Override
    public void loadingNew(TopicDetail detail) {
        topicDetailAdapter.loadingNewTopicDetail(detail);
    }

    @Override
    public void loadingPast(TopicDetail detail) {
        topicDetailAdapter.loadingOldTopicDetail(detail);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(int layoutId) {

    }

    @Override
    public void onLoadingNewComplete() {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoadingPastComplete() {

    }

    @Override
    public void favorite(boolean add) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
