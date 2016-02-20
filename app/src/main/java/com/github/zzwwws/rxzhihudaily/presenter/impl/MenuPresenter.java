package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.Other;

import java.util.List;

/**
 * Created by zzwwws on 2016/2/20.
 */
public interface MenuPresenter<T> extends Presenter<T> {

    void loadTopics();

    void downLoadOffLine();

    void login();
}
