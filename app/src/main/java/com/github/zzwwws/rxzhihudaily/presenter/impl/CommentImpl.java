package com.github.zzwwws.rxzhihudaily.presenter.impl;

import com.github.zzwwws.rxzhihudaily.model.entities.CommentEntities;
import com.github.zzwwws.rxzhihudaily.presenter.infr.CommentView;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.LongCommentCase;
import com.github.zzwwws.rxzhihudaily.presenter.usercase.ShortCommentCase;


import rx.Subscriber;

/**
 * Created by zzwwws on 2016/2/23.
 */
public class CommentImpl implements CommentPresenter<CommentView> {
    Subscriber<CommentEntities> longCommentSub;
    Subscriber<CommentEntities> shortCommentSub;
    LongCommentCase longCommentCase;
    ShortCommentCase shortCommentCase;
    CommentView commentView;

    @Override
    public void loadLongComments(String id) {
        longCommentSub = new Subscriber<CommentEntities>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(CommentEntities comments) {
                commentView.showLongComments(comments.getComments());
            }
        };
        longCommentCase.subscribe(longCommentSub, id);
    }

    @Override
    public void loadShortComments(String id) {
        shortCommentSub = new Subscriber<CommentEntities>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CommentEntities comments) {
                commentView.loadShortComments(comments.getComments());
            }
        };
        shortCommentCase.subscribe(shortCommentSub, id);
    }

    @Override
    public void attachView(CommentView view) {
        this.commentView = view;
        longCommentCase = new LongCommentCase();
        shortCommentCase = new ShortCommentCase();
    }

    @Override
    public void detachView() {
        longCommentCase.unSubscribe();
        shortCommentCase.unSubscribe();
    }
}
