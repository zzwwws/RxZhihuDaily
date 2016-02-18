package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;
import com.github.zzwwws.rxzhihudaily.model.entities.Story;
import com.github.zzwwws.rxzhihudaily.presenter.ui.widget.AutoScrollViewPager;
import com.github.zzwwws.rxzhihudaily.presenter.ui.widget.ViewPagerCompact;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/18.
 */
public class StoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0x01;
    public static final int TYPE_LIST_ITEM = 0x02;

    private Context context;
    private LatestFeed latestFeed;
    private List<Story> stories;

    private List<Integer> storyHeadPos = new ArrayList<>();

    private TopStoryAdapter topStoryAdapter;

    public StoriesAdapter(Context context, LatestFeed latestFeed) {
        this.context = context;
        init(latestFeed);
    }

    public void loadingNewStories(LatestFeed latestFeed){
        init(latestFeed);
        notifyDataSetChanged();
    }

    public void loadingOldStories(LatestFeed latestFeed) {
        List<Story> tmpStory = latestFeed.getStories();
        if (tmpStory != null && tmpStory.size() > 0) {
            storyHeadPos.add(this.stories.size() + 1);
            this.stories.addAll(tmpStory);
            this.notifyDataSetChanged();
        }
    }

    private void init(LatestFeed latestFeed){
        if(stories != null)stories.clear();
        if(storyHeadPos != null)storyHeadPos.clear();
        this.latestFeed = latestFeed;
        if (this.latestFeed != null) {
            this.stories = this.latestFeed.getStories();
            storyHeadPos.add(1);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_story_layout, parent, false);
            TopViewHolder vhItem = new TopViewHolder(v);

            return vhItem;

        } else if (viewType == TYPE_LIST_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
            StoryViewHolder vhItem = new StoryViewHolder(v);

            return vhItem;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TopViewHolder) {
            bindPagerData((TopViewHolder)holder);
        } else if (holder instanceof StoryViewHolder) {
            bindStoryData((StoryViewHolder)holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return latestFeed == null || latestFeed.getStories() == null || latestFeed.getStories().size() == 0 ? 0 : latestFeed.getStories()
                .size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_LIST_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.top_story_pager)
        AutoScrollViewPager topStoryPager;
        @Bind(R.id.top_pager_indicator)
        LinearLayout topPagerIndicator;

        public TopViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_story_title)
        TextView tvStoryTitle;
        @Bind(R.id.img_story_pic)
        ImageView imgStoryPic;
        @Bind(R.id.tv_story_desc)
        TextView tvStoryDesc;
        @Bind(R.id.img_story_multi_flag)
        ImageView imgStoryMultiFlag;

        public StoryViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    private void bindPagerData(final TopViewHolder holder) {
        if (topStoryAdapter == null) {
            topStoryAdapter = new TopStoryAdapter(context, latestFeed.getTopStories());
        }

        AutoScrollViewPager pager = holder.topStoryPager;

        pager.setAdapter(topStoryAdapter);
        pager.setCycle(true);
        pager.setInterval(3000);
        pager.startAutoScroll();
        pager.setOnPageChangeListener(new ViewPagerCompact.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setCurPage(holder, latestFeed.getTopStories().size(), position);
            }
        });

        topStoryAdapter.notifyDataSetChanged();
    }

    private void setCurPage(TopViewHolder holder, int pageCount, int page) {
        holder.topPagerIndicator.removeAllViews();

        for (int i = 0; i < pageCount; i++) {
            ImageView imgCur = new ImageView(context);
            imgCur.setPadding(8, 0, 8, 0);
            imgCur.setId(i);

            if (i == page) {
                imgCur.setImageResource(R.drawable.indicator_round__unselect_bg);
            } else {
                imgCur.setImageResource(R.drawable.indicator_round__select_bg);
            }

            holder.topPagerIndicator.addView(imgCur);
        }
    }

    private void bindStoryData(StoryViewHolder holder, int position) {
        int tPos = position - 1;

        List<Story> stories = latestFeed.getStories();
        //TOP STORY TITLE
        if (storyHeadPos.contains(position)) {
            holder.tvStoryTitle.setVisibility(View.VISIBLE);
            holder.tvStoryTitle.setText(position == 1? "今日热闻":latestFeed.getDate());
        }else{
            holder.tvStoryTitle.setVisibility(View.GONE);
        }

        holder.tvStoryDesc.setText(stories.get(tPos).getTitle());
        Glide
                .with(context)
                .load(stories.get(tPos).getImages().get(0))
                .dontAnimate()
                .placeholder(R.drawable.ic_logo)
                .error(R.drawable.ic_logo)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imgStoryPic);
        holder.imgStoryMultiFlag.setVisibility(stories.get(tPos).getMultipic()?View.VISIBLE:View.GONE);
    }
}
