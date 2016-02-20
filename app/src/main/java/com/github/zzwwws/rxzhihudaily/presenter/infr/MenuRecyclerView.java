package com.github.zzwwws.rxzhihudaily.presenter.infr;

import com.github.zzwwws.rxzhihudaily.model.entities.Other;

import java.util.List;

/**
 * Created by zzwwws on 2016/2/20.
 */
public interface MenuRecyclerView {

    void bindTopics(List<Other> topics);

    void downLoadOffLine();

    void authLogin();
}
