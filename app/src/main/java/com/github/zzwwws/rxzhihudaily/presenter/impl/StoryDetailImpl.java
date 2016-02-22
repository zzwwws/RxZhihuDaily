package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryExtraInfo;
import com.github.zzwwws.rxzhihudaily.presenter.infr.StoryDetailView;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.StoryDetailCase;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.StoryExtraCase;

import rx.Subscriber;

/**
 * Created by zzwwws on 2016/2/22.
 */
public class StoryDetailImpl implements StoryDetailPresenter<StoryDetailView> {

    Subscriber<StoryDetail> storyDetailSubscriber;
    Subscriber<StoryExtraInfo> storyExtraInfoSubscriber;
    StoryDetailView storyDetailView;
    StoryDetailCase storyDetailCase;
    StoryExtraCase storyExtraCase;

    @Override
    public void getExtraInfo(String id) {
        storyExtraInfoSubscriber = new Subscriber<StoryExtraInfo>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StoryExtraInfo storyExtraInfo) {
                storyDetailView.showStoryExtraInfo(storyExtraInfo);
            }
        };
        storyExtraCase.subscribe(storyExtraInfoSubscriber, id);
    }

    @Override
    public void loadStoryDetail(String id) {
        storyDetailSubscriber = new Subscriber<StoryDetail>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StoryDetail storyDetail) {
                storyDetailView.showStoryDetail(storyDetail);
            }
        };
        storyDetailCase.subscribe(storyDetailSubscriber, id);
    }

    @Override
    public void showLike() {

    }

    @Override
    public void attachView(StoryDetailView view) {
        this.storyDetailView = view;
        storyDetailCase = new StoryDetailCase();
        storyExtraCase = new StoryExtraCase();
    }

    @Override
    public void detachView() {
        storyDetailCase.unSubscribe();
        storyExtraCase.unSubscribe();
    }
}
