package com.github.zzwwws.rxzhihudaily.presenter.impl;

/**
 * Created by zzwwws on 2016/2/23.
 */
public interface CommentPresenter<T> extends Presenter<T> {

    void loadLongComments(String id);

    void loadShortComments(String id);

}
