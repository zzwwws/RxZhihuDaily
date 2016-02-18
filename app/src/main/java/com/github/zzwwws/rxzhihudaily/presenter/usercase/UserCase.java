package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import android.support.annotation.CheckResult;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;


public abstract class UserCase<T, R> {

    private Subscription subscription = Subscriptions.empty();

    @SuppressWarnings("unchecked")
    public void subscribe(Observer<T> UseCaseSubscriber, R params) {

        UserCase.this.subscription = this.interactor(params)//
                .onBackpressureBuffer()//
                .take(1)//
                .filter(new Func1<T, Boolean>() {
                    @Override
                    public Boolean call(T t) {
                        return !subscription.isUnsubscribed();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(UseCaseSubscriber);
    }

    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @CheckResult
    protected abstract Observable<T> interactor(R params);
}
