package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryExtraInfo;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by zzwwws on 2016/2/22.
 */
public class StoryExtraCase extends UserCase<StoryExtraInfo,String> {

    @Override
    protected Observable<StoryExtraInfo> interactor(String params) {
        return ServiceRest.getInstance().getStoryExtraInfo(params);
    }
}
