package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by zzwwws on 2016/2/18.
 */
public class LoadingNewCase extends UserCase<LatestFeed,String> {


    @Override
    protected Observable<LatestFeed> interactor(String params) {
        return ServiceRest.getInstance().fetchLatest();
    }
}
