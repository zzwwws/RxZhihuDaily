package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.Other;
import com.github.zzwwws.rxzhihudaily.presenter.infr.RecyclerOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zzwwws on 2016/2/15.
 */
public class MenuAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0x01;
    private static final int TYPE_TOPICS = 0x02;

    private Context context;
    private List<Other> mTopics;

    public MenuAdapter(Context context, List<Other> topics) {
        this.context = context;
        this.mTopics = topics;
    }

    public void initData(List<Other> topics) {
        this.mTopics = topics;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_TOPICS) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rows, parent, false);

            RecyclerView.ViewHolder vhItem = new MenuViewHolder(v, listener);

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);

            RecyclerView.ViewHolder vhHeader = new HeadViewHolder(v, listener);

            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MenuViewHolder) {
            MenuViewHolder menuViewHolder = (MenuViewHolder) holder;
            menuViewHolder.rowText.setText(mTopics.get(position - 1).getName());
            menuViewHolder.rowIcon.setImageResource(R.drawable.menu_follow);
            menuViewHolder.rowText.setTag(mTopics.get(position - 1).getId() + "");
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_TOPICS;
    }

    @Override
    public int getItemCount() {
        return mTopics.size() + 1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener listener) {
        this.listener = listener;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.rowText)
        TextView rowText;
        @Bind(R.id.rowIcon)
        ImageView rowIcon;
        private RecyclerOnItemClickListener listener;

        public MenuViewHolder(View itemView, RecyclerOnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
        }

        @OnClick({R.id.rowText, R.id.rowIcon})
        public void onClick(View view) {
            if (this.listener != null) {
                this.listener.onItemClickListener(view, getAdapterPosition());
            }
        }
    }

    public static class HeadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.circleView)
        CircleImageView circleView;
        @Bind(R.id.tv_head_name)
        TextView tvHeadName;
        @Bind(R.id.tv_head_collect)
        TextView tvHeadCollect;
        @Bind(R.id.tv_head_download)
        TextView tvHeadDownload;
        @Bind(R.id.lly_header_home)
        LinearLayout llyHeaderHome;

        private RecyclerOnItemClickListener listener;

        public HeadViewHolder(View itemView, RecyclerOnItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            this.listener = listener;
        }

        @OnClick({R.id.circleView, R.id.tv_head_name, R.id.tv_head_collect, R.id.tv_head_download, R.id.lly_header_home})
        public void onClick(View view) {
            listener.onItemClickListener(view, 0);
        }
    }
}
