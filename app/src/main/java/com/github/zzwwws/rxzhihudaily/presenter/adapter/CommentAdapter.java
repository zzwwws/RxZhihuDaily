package com.github.zzwwws.rxzhihudaily.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.common.util.TextViewUtil;
import com.github.zzwwws.rxzhihudaily.common.util.TimeUtil;
import com.github.zzwwws.rxzhihudaily.model.entities.Comment;
import com.github.zzwwws.rxzhihudaily.presenter.infr.RecyclerOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zzwwws on 2016/2/15.
 */
public class CommentAdapter extends BaseRecyclerAdapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0x01;
    private static final int TYPE_ITEM = 0x02;
    private static final int TYPE_EMPTY = 0x03;

    private static final int TYPE_COMMENT_NORMAL = 10;
    private static final int TYPE_COMMENT_LONG_HEADER = 11;
    private static final int TYPE_COMMENT_SHORT_HEADER = 12;
    private static final int TYPE_COMMENT_EMPTY = 13;

    private Context context;
    private List<Comment> comments = new ArrayList<>();
    private List<Comment> longComments = new ArrayList<>();
    private List<Comment> shortComments = new ArrayList<>();
    private boolean fold = true;
    private int shortCount = 0;

    private RecyclerOnItemClickListener listener;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
        initComments();
    }

    public void loadLongComments(List<Comment> comments, int totalCount){
        shortCount = totalCount - comments.size();
        if(comments.size() == 0){
            Comment emptyComment = new Comment();
            emptyComment.setType(TYPE_COMMENT_EMPTY);
            this.comments.add(1, emptyComment);
        }else{
            longComments.clear();
            longComments.addAll(comments);
            if(!this.comments.containsAll(longComments)){
                this.comments.addAll(1, longComments);
            }
        }
        notifyDataSetChanged();
    }

    public void loadShortComments(List<Comment> comments){
        if(shortComments == null || shortComments.size() == 0){
            shortComments = new ArrayList<>();
            shortComments.addAll(comments);
        }
        this.comments.addAll(shortComments);
        fold = false;
        notifyDataSetChanged();
    }

    public void foldShortComments(){
        this.comments.removeAll(shortComments);
        this.fold = !fold;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item, parent, false);

            RecyclerView.ViewHolder vhItem = new ItemViewHolder(v);

            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_header, parent, false);

            RecyclerView.ViewHolder vhHeader = new HeadViewHolder(v);

            return vhHeader;
        } else if(viewType == TYPE_EMPTY){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_list_item_empty, parent, false);
            return new EmptyViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            bindItemData(itemViewHolder, position);
        }else if(holder instanceof HeadViewHolder){
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            bindHeaderData(headViewHolder, position);
        }
    }

    private void bindItemData(ItemViewHolder holder, final int position){
        Glide
                .with(context)
                .load(comments.get(position).getAvatar())
                .dontAnimate()
                .placeholder(R.drawable.comment_avatar)
                .error(R.drawable.comment_avatar)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.circleView);
        holder.tvCommentName.setText(comments.get(position).getAuthor());
        TextViewUtil.setTextAndEmojiSpannable(holder.tvCommentContent, comments.get(position).getContent());
        holder.tvCommentPraised.setText(comments.get(position).getLikes() + "");
        holder.tvCommentTime.setText(TimeUtil.getCommentDataStringDisplay(comments.get(position).getTime()));
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClickListener(v, comments.get(position));
                }
            }
        });
    }

    private void bindHeaderData(HeadViewHolder holder, int position){
        if(position == 0){
            holder.tvCommentCount.setText(String.format(context.getString(R.string.comment_count_long), longComments.size()));
            holder.imgCommentFold.setVisibility(View.GONE);
        }else{
            holder.tvCommentCount.setText(String.format(context.getString(R.string.comment_count_short), shortCount));
            holder.imgCommentFold.setVisibility(View.VISIBLE);
            holder.imgCommentFold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        listener.onItemClickListener(v, fold);
                    }
                }
            });
        }
    }

    private void initComments(){
        Comment longHeader = new Comment();
        longHeader.setType(TYPE_COMMENT_LONG_HEADER);
        Comment shortHeader = new Comment();
        shortHeader.setType(TYPE_COMMENT_SHORT_HEADER);
        comments.clear();
        comments.add(longHeader);
        comments.add(shortHeader);
    }
    @Override
    public int getItemViewType(int position) {
        return getType(comments.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    private int getType(int type) {
        switch (type){
            case TYPE_COMMENT_EMPTY:
                return TYPE_EMPTY;
            case TYPE_COMMENT_LONG_HEADER:
            case TYPE_COMMENT_SHORT_HEADER:
                return TYPE_HEADER;
            case TYPE_COMMENT_NORMAL:
                return TYPE_ITEM;
            default:
                return TYPE_ITEM;
        }
    }

    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener listener) {
        this.listener = listener;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.view_split)
        View viewSplit;
        @Bind(R.id.circleView)
        CircleImageView circleView;
        @Bind(R.id.tv_comment_name)
        TextView tvCommentName;
        @Bind(R.id.tv_comment_praised)
        TextView tvCommentPraised;
        @Bind(R.id.tv_comment_content)
        TextView tvCommentContent;
        @Bind(R.id.tv_comment_time)
        TextView tvCommentTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public View getView() {
            return this.itemView;
        }
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_comment_count)
        TextView tvCommentCount;
        @Bind(R.id.img_comment_fold)
        ImageView imgCommentFold;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
