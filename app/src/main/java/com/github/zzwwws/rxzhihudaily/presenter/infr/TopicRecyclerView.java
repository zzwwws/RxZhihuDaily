package com.github.zzwwws.rxzhihudaily.presenter.infr;

import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;

/**
 * Created by zzwwws on 2016/2/19.
 */
public interface TopicRecyclerView {

    void loadingNew(TopicDetail detail);

    void loadingPast(TopicDetail detail);

    void showLoading();

    void showError(int layoutId);

    void onLoadingNewComplete();

    void onLoadingPastComplete();

    void favorite(boolean add);
}
