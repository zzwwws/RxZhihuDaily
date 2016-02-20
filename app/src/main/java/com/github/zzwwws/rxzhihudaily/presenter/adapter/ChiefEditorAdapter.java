package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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

    public void setData(List<Editor> editors) {
        this.editors = editors;
        notifyDataSetChanged();
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

        final ViewHolder Hohholder = holder;
        if (Hohholder != null) {
            Glide
                    .with(context)
                    .load(editors.get(position).getAvatar())
                    .asBitmap()
                    .centerCrop()
                    .into(new BitmapImageViewTarget(Hohholder.imgChiefEditorItem) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            Hohholder.imgChiefEditorItem.setImageDrawable(circularBitmapDrawable);
                        }
                    });
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
