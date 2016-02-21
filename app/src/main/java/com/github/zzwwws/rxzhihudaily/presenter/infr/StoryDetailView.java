package com.github.zzwwws.rxzhihudaily.presenter.infr;

import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryExtraInfo;

/**
 * Created by zzw on 16/2/21.
 */
public interface StoryDetailView {

    void showStoryDetail(StoryDetail detail);

    void showStoryExtraInfo(StoryExtraInfo info);

    void showLike(boolean like);
}
