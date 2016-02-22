package com.github.zzwwws.rxzhihudaily.presenter.impl;

/**
 * Created by zzwwws on 2016/2/22.
 */
public interface StoryDetailPresenter<T> extends Presenter<T> {

    void getExtraInfo(String id);

    void loadStoryDetail(String id);

    void showLike();
}
