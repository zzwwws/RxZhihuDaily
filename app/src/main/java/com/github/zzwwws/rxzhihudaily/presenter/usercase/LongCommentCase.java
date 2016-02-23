package com.github.zzwwws.rxzhihudaily.presenter.usercase;

import com.github.zzwwws.rxzhihudaily.model.entities.CommentEntities;
import com.github.zzwwws.rxzhihudaily.model.service.ServiceRest;


import rx.Observable;

/**
 * Created by zzwwws on 2016/2/23.
 */
public class LongCommentCase extends UserCase<CommentEntities, String> {
    @Override
    protected Observable<CommentEntities> interactor(String id) {
        return ServiceRest.getInstance().getLongComments(id);
    }
}
