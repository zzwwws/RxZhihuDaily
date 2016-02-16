package com.github.zzwwws.rxzhihudaily.model.service;

import android.content.Context;
import android.widget.Toast;

import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by zzwwws on 2016/2/15.
 */
public class ServiceRest {

    private GithubService serviceApi;

    public ServiceRest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://news-at.zhihu.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        serviceApi = retrofit.create(GithubService.class);
    }

    public static ServiceRest getInstance() {
        return SingletonHolder.instance;
    }

    public void get(final Context context) {
        Call<LatestFeed> call = serviceApi.repoContributors();

        //异步
        call.enqueue(new Callback<LatestFeed>() {
            @Override
            public void onResponse(Call<LatestFeed> call, Response<LatestFeed> response) {
                LatestFeed latestFeed = response.body();
                Toast.makeText(context, latestFeed != null ? latestFeed.getDate() : "dlsf", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<LatestFeed> call, Throwable t) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static class SingletonHolder {
        private static ServiceRest instance = new ServiceRest();
    }
}
