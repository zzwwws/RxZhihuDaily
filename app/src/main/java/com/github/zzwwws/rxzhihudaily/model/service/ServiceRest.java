package com.github.zzwwws.rxzhihudaily.model.service;

import com.github.zzwwws.rxzhihudaily.model.entities.Feed;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

/**
 * Created by zzwwws on 2016/2/15.
 */
public class ServiceRest {

    private ServiceApi serviceApi;

    public ServiceRest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        serviceApi = retrofit.create(ServiceApi.class);
    }

    public static ServiceRest getInstance() {
        return SingletonHolder.instance;
    }

    public Observable<Feed> fetchLatestStory() {
        return serviceApi.fetchLatest();
    }

    public Observable<Feed> fetchOldStory(String date){
        return serviceApi.fetchPast(date);
    }

    public Observable<TopicDetail> fetchTopicDetail(String topicId){
        return serviceApi.getTopicDetail(topicId);
    }

    public Observable<TopicDetail> fetchOldTopicDetail(String topicId, String latestId){
        return serviceApi.getPastTopic(topicId, latestId);
    }

    private static class SingletonHolder {
        private static ServiceRest instance = new ServiceRest();
    }
}
