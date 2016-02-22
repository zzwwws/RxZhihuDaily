package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.Story;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicDetail;
import com.github.zzwwws.rxzhihudaily.presenter.infr.RecyclerOnItemClickListener;
import com.github.zzwwws.rxzhihudaily.presenter.ui.view.HorizontalListView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/18.
 */
public class TopicDetailAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0x01;
    public static final int TYPE_LIST_HEADER = 0x02;
    public static final int TYPE_LIST_ITEM = 0x03;

    private Context context;
    private TopicDetail topicDetail;
    private List<Story> stories;
    private String latestId;
    private ChiefEditorAdapter chiefEditorAdapter;
    private RecyclerOnItemClickListener listener;

    public TopicDetailAdapter(Context context, TopicDetail topicDetail) {
        this.context = context;
        init(topicDetail);
    }

    private void init(TopicDetail topicDetail) {
        if (stories != null) stories.clear();
        this.topicDetail = topicDetail;
        if (this.topicDetail != null) {
            this.stories = this.topicDetail.getStories();
            if(stories.size() > 0){
                int len = this.stories.size();
                this.latestId = this.stories.get(len -1).getId()+"";
            }
        }
        this.notifyDataSetChanged();
    }

    public void loadingNewTopicDetail(TopicDetail topicDetail) {
        init(topicDetail);
    }

    public void loadingOldTopicDetail(TopicDetail oldTopicDetail) {
        List<Story> tmpStory = oldTopicDetail.getStories();
        if (tmpStory != null && tmpStory.size() > 0) {
            this.stories.addAll(tmpStory);
            int len = this.stories.size();
            if(stories.size() > 0){
                this.latestId = this.stories.get(len -1).getId()+"";
            }
            this.notifyDataSetChanged();
        }
    }


    public String getLatestId() {
        return this.latestId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_story_item, parent, false);
            TopViewHolder vh = new TopViewHolder(v);
            return vh;
        } else if (viewType == TYPE_LIST_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_list_header, parent, false);
            ListHeaderHolder vh = new ListHeaderHolder(v);
            return vh;
        } else if (viewType == TYPE_LIST_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
            TopicListViewHolder vh = new TopicListViewHolder(v);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            bindHeadData((TopViewHolder)holder);
        } else if (holder instanceof ListHeaderHolder) {
            bindEditorData((ListHeaderHolder)holder);
        } else if(holder instanceof TopicListViewHolder){
            bindStoryData((TopicListViewHolder)holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position == 1) {
            return TYPE_LIST_HEADER;
        } else return TYPE_LIST_ITEM;
    }


    @Override
    public int getItemCount() {
        return stories.size() + 2;
    }

    private void bindHeadData(TopViewHolder holder){
        Glide
                .with(context)
                .load(topicDetail.getImage())
                .dontAnimate()
                .placeholder(R.drawable.image_top_default)
                .error(R.drawable.image_top_default)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imgTopStory);
        holder.tvTopStory.setText(topicDetail.getDescription());
    }

    private void bindEditorData(ListHeaderHolder holder){
        if(chiefEditorAdapter == null){
            chiefEditorAdapter = new ChiefEditorAdapter(context, topicDetail.getEditors());
        }
        holder.horizontalListView.setAdapter(chiefEditorAdapter);
        chiefEditorAdapter.setData(topicDetail.getEditors());

        holder.horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = (String)view.getTag();
                // TODO: 2016/2/20 editor profile
            }
        });
    }

    private void bindStoryData(TopicListViewHolder holder, int position){
        final int tPos = position - 2;

        holder.tvStoryDesc.setText(stories.get(tPos).getTitle());
        List<String> images = stories.get(tPos).getImages();
        if(images == null || images.size() == 0){
            holder.imgStoryPic.setVisibility(View.GONE);
            holder.imgStoryMultiFlag.setVisibility(View.GONE);
        }else{
            Glide
                    .with(context)
                    .load(stories.get(tPos).getImages().get(0))
                    .dontAnimate()
                    .placeholder(R.drawable.image_small_default)
                    .error(R.drawable.image_small_default)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.imgStoryPic);
            holder.imgStoryMultiFlag.setVisibility(stories.get(tPos).getMultipic()?View.VISIBLE:View.GONE);
        }
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClickListener(v, stories.get(tPos).getId()+"");
                }
            }
        });
    }

    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener listener) {
        this.listener = listener;
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_top_story)
        ImageView imgTopStory;
        @Bind(R.id.tv_top_story)
        TextView tvTopStory;

        public TopViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class ListHeaderHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.horizontal_list_view)
        HorizontalListView horizontalListView;

        public ListHeaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class TopicListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_story_title)
        TextView tvStoryTitle;
        @Bind(R.id.img_story_pic)
        ImageView imgStoryPic;
        @Bind(R.id.tv_story_desc)
        TextView tvStoryDesc;
        @Bind(R.id.img_story_multi_flag)
        ImageView imgStoryMultiFlag;
        public TopicListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        public View getView(){
            return this.itemView;
        }
    }
}
