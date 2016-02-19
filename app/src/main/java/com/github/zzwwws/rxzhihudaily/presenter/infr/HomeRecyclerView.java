package com.github.zzwwws.rxzhihudaily.presenter.infr;

import com.github.zzwwws.rxzhihudaily.model.entities.Feed;

/**
 * Created by zzwwws on 2016/2/17.
 */
public interface HomeRecyclerView {

    void loadingNew(Feed feed);

    void loadingPast(Feed feed, String date);

    void showLoading();

    void showError(int layoutId);

    void onLoadingNewComplete();

    void onLoadingPastComplete();
}
