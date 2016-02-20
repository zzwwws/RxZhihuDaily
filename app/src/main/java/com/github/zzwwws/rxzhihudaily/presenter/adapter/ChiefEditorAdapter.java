package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.Editor;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/20.
 */
public class ChiefEditorAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Editor> editors;

    public ChiefEditorAdapter(Context context, List<Editor> editors) {
        this.context = context;
        this.editors = editors;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return editors.size();
    }

    @Override
    public Object getItem(int position) {
        return editors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.chief_editor_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (holder != null) {
            holder.imgChiefEditorItem.setTag(editors.get(position).getUrl());
            Glide
                    .with(context)
                    .load(editors.get(position).getAvatar())
                    .dontAnimate()
                    .placeholder(R.drawable.comment_avatar)
                    .error(R.drawable.comment_avatar)
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .into(holder.imgChiefEditorItem);
        }
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.img_chief_editor_item)
        ImageView imgChiefEditorItem;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
