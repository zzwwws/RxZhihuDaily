package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.zzwwws.rxzhihudaily.R;

/**
 * Created by zzwwws on 2016/2/15.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0x01;
    private static final int TYPE_TOPICS = 0x02;

    private Context context;
    private String[] mTopics;

    public MenuAdapter(Context context, String[] topics){
        this.context = context;
        this.mTopics = topics;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_TOPICS) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rows, parent, false);

            ViewHolder vhItem = new ViewHolder(v, viewType);

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);

            ViewHolder vhHeader = new ViewHolder(v, viewType);

            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.holderId == 1) {
            holder.textView.setText(mTopics[position - 1]);
            holder.imageView.setImageResource(R.drawable.menu_follow);
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
        return mTopics.length + 1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private int holderId;
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View itemView, int viewType){
            super(itemView);

            if(viewType == TYPE_TOPICS){
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderId = 1;
            }
        }
    }
}
