package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.presenter.infr.TopicRecyclerView;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.NewTopicDetailCase;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.OldTopicDetailCase;

import rx.Subscriber;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class TopicsDetailImpl implements TopicsPresenter<TopicRecyclerView> {

    Subscriber<TopicDetail> loadingNewSub;
    Subscriber<TopicDetail> loadingPastSub;
    private TopicRecyclerView topicRecyclerView;
    private NewTopicDetailCase newTopicDetailCase;
    private OldTopicDetailCase oldTopicDetailCase;

    public TopicsDetailImpl(){

    }

    @Override
    public void loadingNew(String topicId) {
        loadingNewSub = new Subscriber<TopicDetail>() {
            @Override
            public void onCompleted() {
                TopicsDetailImpl.this.topicRecyclerView.onLoadingNewComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TopicDetail detail) {
                TopicsDetailImpl.this.topicRecyclerView.loadingNew(detail);
            }
        };

        newTopicDetailCase.subscribe(loadingNewSub, topicId);
    }

    @Override
    public void loadingPast(String topic, String lastId) {
        loadingPastSub = new Subscriber<TopicDetail>() {
            @Override
            public void onCompleted() {
                TopicsDetailImpl.this.topicRecyclerView.onLoadingPastComplete();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TopicDetail detail) {
                TopicsDetailImpl.this.topicRecyclerView.loadingPast(detail);
            }
        };
        oldTopicDetailCase.subscribe(loadingPastSub, new String[]{topic, lastId});
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(int layoutId) {
        TopicsDetailImpl.this.topicRecyclerView.showError(layoutId);
    }

    @Override
    public void attachView(TopicRecyclerView view) {
        this.topicRecyclerView = view;
        newTopicDetailCase = new NewTopicDetailCase();
        oldTopicDetailCase = new OldTopicDetailCase();
    }

    @Override
    public void detachView() {
        oldTopicDetailCase.unSubscribe();
        newTopicDetailCase.unSubscribe();
    }
}
