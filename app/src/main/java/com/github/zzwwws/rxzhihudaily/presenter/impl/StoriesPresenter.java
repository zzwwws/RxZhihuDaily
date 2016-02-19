package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.presenter.infr.HomeRecyclerView;

/**
 * Created by zzwwws on 2016/2/18.
 */
public interface StoriesPresenter<T extends HomeRecyclerView> extends Presenter<T> {

    void loadingNew();

    void loadingPast(int page);

    void showLoading();

    void showError(int layoutId);
}
