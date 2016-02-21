package com.github.zzwwws.rxzhihudaily.presenter.impl;

/**
 * Created by zzwwws on 2016/2/20.
 */
public interface MenuPresenter<T> extends Presenter<T> {

    void loadTopics();

    void downLoadOffLine();

    void login();
}
