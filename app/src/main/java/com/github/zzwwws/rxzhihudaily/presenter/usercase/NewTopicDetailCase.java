package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;

import rx.Observable;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class NewTopicDetailCase extends UserCase<TopicDetail, String> {
    @Override
    protected Observable<TopicDetail> interactor(String topicId) {
        return ServiceRest.getInstance().fetchTopicDetail(topicId);
    }
}
