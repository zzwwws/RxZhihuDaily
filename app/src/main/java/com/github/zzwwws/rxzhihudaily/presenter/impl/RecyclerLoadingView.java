package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;

/**
 * Created by zzwwws on 2016/2/17.
 */
public interface RecyclerLoadingView {

    void loadingNew(LatestFeed latestFeed);

    void loadingPast();

    void showLoading();

    void showError(int layoutId);
}
