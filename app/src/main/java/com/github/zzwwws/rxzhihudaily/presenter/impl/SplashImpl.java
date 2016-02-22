package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.StartImage;
import com.github.zzwwws.rxzhihudaily.presenter.infr.SplashImageView;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.SplashCase;

import rx.Subscriber;

/**
 * Created by zzw on 16/2/22.
 */
public class SplashImpl implements SplashPresenter<SplashImageView> {
    Subscriber<StartImage> startImageSubscriber;
    SplashImageView splashImageView;
    SplashCase splashCase;

    @Override
    public void getSplashImage(String density) {
        startImageSubscriber = new Subscriber<StartImage>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(StartImage startImage) {
                splashImageView.showStartImage(startImage);
            }
        };
        splashCase.subscribe(startImageSubscriber, density);
    }

    @Override
    public void attachView(SplashImageView view) {
        splashImageView = view;
        splashCase = new SplashCase();
    }

    @Override
    public void detachView() {
        splashCase.unSubscribe();
    }
}
