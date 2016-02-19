package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.presenter.infr.TopicRecyclerView;

/**
 * Created by zzwwws on 2016/2/19.
 */
public interface TopicsPresenter<T extends TopicRecyclerView> extends Presenter<T> {

    void loadingNew(String topicId);

    void loadingPast(String topic, String lastId);

    void showLoading();

    void showError(int layoutId);
}
