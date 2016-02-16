package com.github.zzwwws.rxzhihudaily.model.service;

import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by zzwwws on 2016/2/15.
 */
public interface FetchService {
    @GET("api/4/stories/latest")
    Call<LatestFeed> repoContributors();
}
