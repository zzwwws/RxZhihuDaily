package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.TopStory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/18.
 */
public class TopStoryAdapter extends PagerAdapter {

    private Context context;
    private List<TopStory> topStories;

    public TopStoryAdapter(Context context, List<TopStory> topStories) {
        this.context = context;
        this.topStories = topStories;
    }

    @Override
    public int getCount() {
        return topStories == null ? 0 : topStories.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.top_story_item, null, false);
        ViewHolder viewHolder = new ViewHolder(convertView);
        bindData(viewHolder, position);
        removeFromParentView(convertView);
        container.addView(convertView);
        return convertView;
    }

    private void bindData(ViewHolder holder, int pos){
        Glide
                .with(context)
                .load(topStories.get(pos).getImage())
                .dontAnimate()
                .placeholder(R.drawable.image_top_default)
                .error(R.drawable.image_top_default)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imgTopStory);
        holder.tvTopStory.setText(topStories.get(pos).getTitle());
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        removeFromParentView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public static void removeFromParentView(View view) {
        if (view == null) {
            return;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup vp = (ViewGroup) view.getParent();
            vp.removeView(view);
        }
    }

    public static class ViewHolder{
        @Bind(R.id.img_top_story)
        ImageView imgTopStory;
        @Bind(R.id.tv_top_story)
        TextView tvTopStory;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
