package com.github.zzwwws.rxzhihudaily.presenter.impl;

/**
 * Created by zzwwws on 2016/2/18.
 */
public interface StoriesPresenter<T extends RecyclerLoadingView> extends Presenter<T> {

    void loadingNew();

    void loadingPast(int page);

    void showLoading();

    void showError(int layoutId);
}
