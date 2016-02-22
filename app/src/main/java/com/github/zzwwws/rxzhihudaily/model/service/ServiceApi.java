package com.github.zzwwws.rxzhihudaily.model.service;

import com.github.zzwwws.rxzhihudaily.model.entities.Comment;
import com.github.zzwwws.rxzhihudaily.model.entities.Feed;
import com.github.zzwwws.rxzhihudaily.model.entities.StartImage;
import com.github.zzwwws.rxzhihudaily.model.entities.Story;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryExtraInfo;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.Topics;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zzwwws on 2016/2/15.
 */
public interface ServiceApi {

    @GET("4/start-image/{density}")
    Observable<StartImage> getStartImage(@Path("density") String density);

    @GET("4/themes")
    Observable<Topics> getTopics();

    @GET("4/theme/{id}")
    Observable<TopicDetail> getTopicDetail(@Path("id") String id);

    @GET("4/theme/{topicId}/before/{storyId}")
    Observable<TopicDetail> getPastTopic(@Path("topicId") String topicId, @Path("storyId") String storyId);

    @GET("4/stories/latest")
    Observable<Feed> fetchLatestStory();

    @GET("4/news/before/{date}")
    Observable<Feed> fetchPastStory(@Path("date") String date);

    @GET("4/news/{id}")
    Observable<StoryDetail> getStoryDetail(@Path("id") String id);

    @GET("4/story-extra/{id}")
    Observable<StoryExtraInfo> getStoryExtraInfo(@Path("id") String id);

    @GET("4/story/{id}/long-comments")
    Observable<Comment> getStoryLongComments(@Path("id") String id);

    @GET("4/story/{id}/short-comments")
    Observable<Comment> getStoryShortComments(@Path("id") String id);

}
