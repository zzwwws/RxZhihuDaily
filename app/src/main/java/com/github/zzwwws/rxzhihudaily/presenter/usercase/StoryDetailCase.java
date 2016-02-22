package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by zzwwws on 2016/2/22.
 */
public class StoryDetailCase extends UserCase<StoryDetail,String> {

    @Override
    protected Observable<StoryDetail> interactor(String params) {
        return ServiceRest.getInstance().getStoryDetail(params);
    }
}
