package com.github.zzwwws.rxzhihudaily.presenter.impl;

import android.view.View;

/**
 * Created by zzwwws on 2016/2/17.
 */
public interface Presenter <T> {

    void attachView(T view);

    void detachView();
}
