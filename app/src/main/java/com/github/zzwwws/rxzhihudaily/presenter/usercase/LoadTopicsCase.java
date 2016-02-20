package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.Topics;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by zzwwws on 2016/2/18.
 */
public class LoadTopicsCase extends UserCase<Topics,String> {

    @Override
    protected Observable<Topics> interactor(String params) {
        return ServiceRest.getInstance().fetchTopicList();
    }
}
