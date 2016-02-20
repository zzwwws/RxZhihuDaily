package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.support.v7.widget.RecyclerView;

import com.github.zzwwws.rxzhihudaily.presenter.infr.RecyclerOnItemClickListener;

/**
 * Created by zzwwws on 2016/2/20.
 */
public abstract class BaseRecyclerAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T>{

    protected RecyclerOnItemClickListener listener;

    public abstract void setOnItemClickListener(RecyclerOnItemClickListener listener);
}
