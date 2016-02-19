package com.github.zzwwws.rxzhihudaily.model.service;

import com.github.zzwwws.rxzhihudaily.model.entities.Feed;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zzwwws on 2016/2/15.
 */
public interface FetchService {
    @GET("api/4/stories/latest")
    Observable<Feed> fetchLatest();

    @GET("api/4/news/before/{date}")
    Observable<Feed> fetchPast(@Path("date") String date);

}
