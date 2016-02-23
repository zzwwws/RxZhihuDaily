package com.github.zzwwws.rxzhihudaily.presenter.infr;

import android.view.View;

import com.github.zzwwws.rxzhihudaily.model.entities.Comment;

import java.util.List;

/**
 * Created by zzw on 16/2/21.
 */
public interface CommentView {

    void showLongComments(List<Comment> longComments);

    void loadShortComments(List<Comment> shortComments);

    void onCommentClick(View view, Comment comment);
}
