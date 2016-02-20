package com.github.zzwwws.rxzhihudaily.presenter.impl;


import com.github.zzwwws.rxzhihudaily.common.util.TimeUtil;
import com.github.zzwwws.rxzhihudaily.model.entities.Feed;
import com.github.zzwwws.rxzhihudaily.presenter.infr.HomeRecyclerView;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.NewStoryCase;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.OldStoryCase;

import rx.Subscriber;

/**
 * Created by zzwwws on 2016/2/17.
 */
public class FetchFeedImpl implements StoriesPresenter<HomeRecyclerView> {

    Subscriber<Feed> loadingNewSub;
    Subscriber<Feed> loadingPastSub;
    private HomeRecyclerView homeRecyclerView;
    private NewStoryCase newStoryCase;
    private OldStoryCase oldStoryCase;

    public FetchFeedImpl() {
    }

    @Override
    public void attachView(HomeRecyclerView view) {
        this.homeRecyclerView = view;
        newStoryCase = new NewStoryCase();
        oldStoryCase = new OldStoryCase();
    }

    @Override
    public void detachView() {
        newStoryCase.unSubscribe();
        oldStoryCase.unSubscribe();
    }

    @Override
    public void loadingNew() {
        loadingNewSub = new Subscriber<Feed>() {
            @Override
            public void onCompleted() {
                homeRecyclerView.onLoadingNewComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Feed feed) {
                FetchFeedImpl.this.homeRecyclerView.loadingNew(feed);
            }
        };
        newStoryCase.subscribe(loadingNewSub, "");
    }

    @Override
    public void loadingPast(final int page) {
        loadingPastSub = new Subscriber<Feed>() {
            @Override
            public void onCompleted() {
                homeRecyclerView.onLoadingPastComplete();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Feed feed) {
                FetchFeedImpl.this.homeRecyclerView.loadingPast(feed, TimeUtil.getPastDateStringDisplay(page+1));
            }
        };
        oldStoryCase.subscribe(loadingPastSub, TimeUtil.getPastDatetimeString(page));
    }

    @Override
    public void showLoading() {
        homeRecyclerView.showLoading();
    }

    @Override
    public void showError(int layoutId) {
        homeRecyclerView.showError(layoutId);
    }

}
