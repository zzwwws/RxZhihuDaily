package com.github.zzwwws.rxzhihudaily.model.service;

import android.content.Context;
import android.widget.Toast;

import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public void get(final Context context) {
        Observable<LatestFeed> call = serviceApi.repoContributors();
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LatestFeed>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LatestFeed latestFeed) {
                        Toast.makeText(context, latestFeed != null ? latestFeed.getDate() : "dlsf", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static class SingletonHolder {
        private static ServiceRest instance = new ServiceRest();
    }
}
