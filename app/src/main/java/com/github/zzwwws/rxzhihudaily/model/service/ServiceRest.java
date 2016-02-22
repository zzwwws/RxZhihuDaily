package com.github.zzwwws.rxzhihudaily.model.service;

import android.text.TextUtils;

import com.github.zzwwws.rxzhihudaily.common.AppConfig;
import com.github.zzwwws.rxzhihudaily.common.BaseApplication;
import com.github.zzwwws.rxzhihudaily.common.util.LogUtil;
import com.github.zzwwws.rxzhihudaily.common.util.NetworkUtil;
import com.github.zzwwws.rxzhihudaily.model.entities.Comment;
import com.github.zzwwws.rxzhihudaily.model.entities.Feed;
import com.github.zzwwws.rxzhihudaily.model.entities.StartImage;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryExtraInfo;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.Topics;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;

/**
 * Created by zzwwws on 2016/2/15.
 */
public class ServiceRest {

    private static final String TAG = "ServiceRest";

    public ServiceRest() {
    }

    public static ServiceRest getInstance() {
        return SingletonHolder.instance;
    }

    private ServiceApi rtcService(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(createRtcClient())
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ServiceApi.class);
    }

    private ServiceApi cachedService(){
        Retrofit retrofit = new Retrofit.Builder()
                .client(createCachedClient())
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(ServiceApi.class);
    }

    public Observable<StartImage> getStartImage(String density){
        return cachedService().getStartImage(density);
    }

    public Observable<StoryDetail> getStoryDetail(String id){

        return cachedService().getStoryDetail(id);
    }

    public Observable<StoryExtraInfo> getStoryExtraInfo(String id){
        return rtcService().getStoryExtraInfo(id);
    }

    public Observable<Comment> getShortComments(String id){
        return rtcService().getStoryShortComments(id);
    }

    public Observable<Comment> getLongComments(String id){
        return rtcService().getStoryLongComments(id);
    }

    public Observable<Topics> fetchTopicList(){return cachedService().getTopics();}

    public Observable<Feed> fetchLatestStory() {
        return rtcService().fetchLatestStory();
    }

    public Observable<Feed> fetchOldStory(String date){
        return cachedService().fetchPastStory(date);
    }

    public Observable<TopicDetail> fetchTopicDetail(String topicId){
        return cachedService().getTopicDetail(topicId);
    }

    public Observable<TopicDetail> fetchOldTopicDetail(String topicId, String latestId){
        return cachedService().getPastTopic(topicId, latestId);
    }

    private static class SingletonHolder {
        private static ServiceRest instance = new ServiceRest();
    }

    private OkHttpClient createRtcClient() {
        File httpCacheDirectory = new File(BaseApplication.get().getCacheDir(), AppConfig.CACHE_DIR_NAME);

        Cache cache = new Cache(httpCacheDirectory, AppConfig.CACHE_MAX_SIZE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor2())
                .cache(cache)
                .build();
        return okHttpClient;
    }

    private OkHttpClient createCachedClient() {
        File httpCacheDirectory = new File(BaseApplication.get().getCacheDir(), AppConfig.CACHE_DIR_NAME);

        Cache cache = new Cache(httpCacheDirectory, AppConfig.CACHE_MAX_SIZE);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor1())
                .cache(cache)
                .build();
        return okHttpClient;
    }

    /**
     * 设置max-age为60s之后，这60s之内无论是否有网,都读缓存。
     */
    public static class Interceptor1 implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtil.i(TAG, "request=" + request);
            Response response = chain.proceed(request);
            LogUtil.i(TAG, "response=" + response);

            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age="+AppConfig.CACHE_TIME_DEFAULT;
            }
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }
    }

    public static class Interceptor2 implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtil.isNetAvailable(BaseApplication.get())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtil.i(TAG, "no network");
            }
            
            Response response = chain.proceed(request);

            if (NetworkUtil.isNetAvailable(BaseApplication.get())) {
                int maxAge = AppConfig.CACHE_TIME_NETWORK_AVAILABLE; // 有网络时 设置缓存超时时间0个小时
                LogUtil.i(TAG, "has network maxAge="+maxAge);
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                LogUtil.i(TAG, "network error");
                int maxStale = AppConfig.CACHE_TIME_NETWORK_UNAVAILABLE; // 无网络时，设置超时为4周
                LogUtil.i(TAG, "has maxStale="+maxStale);
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
                LogUtil.i(TAG, "response build maxStale="+maxStale);
            }
            return response;
        }
    }
}
