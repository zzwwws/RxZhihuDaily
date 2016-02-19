package com.github.zzwwws.rxzhihudaily.model.service;

import com.github.zzwwws.rxzhihudaily.model.entities.Feed;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

/**
 * Created by zzwwws on 2016/2/15.
 */
public class ServiceRest {

    private FetchService serviceApi;

    public ServiceRest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        serviceApi = retrofit.create(FetchService.class);
    }

    public static ServiceRest getInstance() {
        return SingletonHolder.instance;
    }

    public Observable<Feed> fetchLatest() {
        return serviceApi.fetchLatest();
    }

    public Observable<Feed> fetchOld(String date){
        return serviceApi.fetchPast(date);
    }

    private static class SingletonHolder {
        private static ServiceRest instance = new ServiceRest();
    }
}
