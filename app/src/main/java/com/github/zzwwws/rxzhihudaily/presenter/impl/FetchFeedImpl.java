package com.github.zzwwws.rxzhihudaily.presenter.impl;


import com.github.zzwwws.rxzhihudaily.common.util.TimeUtil;
import com.github.zzwwws.rxzhihudaily.model.entities.Feed;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.LoadingNewCase;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.LoadingOldCase;

import rx.Subscriber;

/**
 * Created by zzwwws on 2016/2/17.
 */
public class FetchFeedImpl<T extends RecyclerLoadingView> implements StoriesPresenter<T> {

    Subscriber<Feed> loadingNewSub;
    Subscriber<Feed> loadingPastSub;
    private RecyclerLoadingView recyclerLoadingView;
    private LoadingNewCase loadingNewCase;

    ;
    private LoadingOldCase loadingOldCase;
    public FetchFeedImpl() {
    }

    @Override
    public void attachView(T view) {
        this.recyclerLoadingView = view;
        loadingNewCase = new LoadingNewCase();
        loadingOldCase = new LoadingOldCase();
    }

    @Override
    public void detachView() {
        loadingNewCase.unSubscribe();
    }

    @Override
    public void loadingNew() {
        loadingNewSub = new Subscriber<Feed>() {
            @Override
            public void onCompleted() {
                recyclerLoadingView.onLoadingNewComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Feed feed) {
                FetchFeedImpl.this.recyclerLoadingView.loadingNew(feed);
            }
        };
        loadingNewCase.subscribe(loadingNewSub, "");
    }

    @Override
    public void loadingPast(final int page) {
        loadingPastSub = new Subscriber<Feed>() {
            @Override
            public void onCompleted() {
                recyclerLoadingView.onLoadingPastComplete();
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Feed feed) {
                FetchFeedImpl.this.recyclerLoadingView.loadingPast(feed, TimeUtil.getPastDateStringDisplay(page+1));
            }
        };
        loadingOldCase.subscribe(loadingPastSub, TimeUtil.getPastDatetimeString(page));
    }

    @Override
    public void showLoading() {
        recyclerLoadingView.showLoading();
    }

    @Override
    public void showError(int layoutId) {
        recyclerLoadingView.showError(layoutId);
    }

}
