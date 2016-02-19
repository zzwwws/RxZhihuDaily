package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.github.zzwwws.rxzhihudaily.model.entities.Feed;
import com.github.zzwwws.rxzhihudaily.model.entities.Story;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/18.
 */
public class TopicDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0x01;
    public static final int TYPE_LIST_ITEM = 0x02;

    private Context context;
    private TopicDetail topicDetail;
    private List<Story> stories;
    private String latestId;

    public TopicDetailAdapter(Context context, TopicDetail topicDetail) {
        this.context = context;
        init(topicDetail);
    }

    private void init(TopicDetail topicDetail) {
        if (stories != null) stories.clear();
        this.topicDetail = topicDetail;
        if (this.topicDetail != null) {
            this.stories = this.topicDetail.getStories();
        }
    }

    public void loadingNewTopicDetail(TopicDetail topicDetail){
        init(topicDetail);
        notifyDataSetChanged();
    }

    public void loadingOldTopicDetail(TopicDetail oldTopicDetail) {
        List<Story> tmpStory = oldTopicDetail.getStories();
        if (tmpStory != null && tmpStory.size() > 0) {

            this.stories.addAll(tmpStory);
            this.notifyDataSetChanged();
        }
    }

    public String getLatestId(){
        return this.latestId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {

        } else if (viewType == TYPE_LIST_ITEM) {

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stories.size() + 1;
    }


    public static class TopViewHolder extends RecyclerView.ViewHolder {

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class TopicListViewHolder extends RecyclerView.ViewHolder {

        public TopicListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
