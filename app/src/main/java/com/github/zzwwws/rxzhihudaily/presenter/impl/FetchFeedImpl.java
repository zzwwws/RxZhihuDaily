package com.github.zzwwws.rxzhihudaily.presenter.impl;


import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.LoadingNewCase;

import rx.Subscriber;

/**
 * Created by zzwwws on 2016/2/17.
 */
public class FetchFeedImpl<T extends RecyclerLoadingView> implements StoriesPresenter<T> {

    private RecyclerLoadingView recyclerLoadingView;
    private LoadingNewCase loadingNewCase;

    public FetchFeedImpl(){};

    @Override
    public void attachView(T view) {
        this.recyclerLoadingView = view;
        loadingNewCase = new LoadingNewCase();
    }

    @Override
    public void detachView() {
        loadingNewCase.unSubscribe();
    }

    @Override
    public void loadingNew() {
        loadingNewCase.subscribe(loadingNewSub, "");
    }

    @Override
    public void loadingPast() {

    }

    @Override
    public void showLoading() {
        recyclerLoadingView.showLoading();
    }

    @Override
    public void showError(int layoutId) {
        recyclerLoadingView.showError(layoutId);
    }

    Subscriber<LatestFeed> loadingNewSub = new Subscriber<LatestFeed>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(LatestFeed latestFeed) {
            FetchFeedImpl.this.recyclerLoadingView.loadingNew(latestFeed);
        }
    };

    Subscriber<LatestFeed> loadingPastSub = new Subscriber<LatestFeed>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(LatestFeed latestFeed) {

        }
    };
}
