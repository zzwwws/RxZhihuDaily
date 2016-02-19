package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.Feed;

/**
 * Created by zzwwws on 2016/2/17.
 */
public interface RecyclerLoadingView {

    void loadingNew(Feed feed);

    void loadingPast(Feed feed, String date);

    void showLoading();

    void showError(int layoutId);

    void onLoadingNewComplete();

    void onLoadingPastComplete();
}
