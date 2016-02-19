package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class OldTopicDetailCase extends UserCase<TopicDetail, String[]> {
    @Override
    protected Observable<TopicDetail> interactor(String[] params) {
        return ServiceRest.getInstance().fetchOldTopicDetail(params[0], params[1]);
    }
}
