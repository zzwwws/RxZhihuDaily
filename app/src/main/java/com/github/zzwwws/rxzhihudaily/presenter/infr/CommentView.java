package com.github.zzwwws.rxzhihudaily.presenter.infr;

import com.github.zzwwws.rxzhihudaily.model.entities.Comment;

import java.util.List;

/**
 * Created by zzw on 16/2/21.
 */
public interface CommentView {

    void showLongComments(List<Comment> longComments);

    void showShortComments(List<Comment> shortComments);

    void showLike(String uid, boolean like);

    void report(String uid);

    void replyTo(String uid);
}
