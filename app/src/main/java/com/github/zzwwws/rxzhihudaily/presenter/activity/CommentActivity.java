package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.Comment;
import com.github.zzwwws.rxzhihudaily.presenter.adapter.CommentAdapter;
import com.github.zzwwws.rxzhihudaily.presenter.impl.CommentImpl;
import com.github.zzwwws.rxzhihudaily.presenter.infr.CommentView;
import com.github.zzwwws.rxzhihudaily.presenter.infr.RecyclerOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/22.
 */
public class CommentActivity extends BaseActivity implements CommentView, View.OnClickListener, RecyclerOnItemClickListener {
    @Bind(R.id.tool_bar)
    Toolbar toolbar;
    @Bind(R.id.comment_recycler)
    RecyclerView commentRecycler;

    private int commentCount;
    private String id;
    private CommentImpl commentImpl;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        id = getIntent().getStringExtra("id");
        commentCount = getIntent().getIntExtra("comment_count",0);
        initData();
    }

    private void initData(){
        toolbar.setTitle(String.format(getString(R.string.comment_count), commentCount));
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        commentAdapter = new CommentAdapter(this, new ArrayList<Comment>());
        commentAdapter.setOnItemClickListener(this);
        commentRecycler.setHasFixedSize(true);
        commentRecycler.setAdapter(commentAdapter);

        LinearLayoutManager menuLayoutManager = new LinearLayoutManager(this);
        commentRecycler.setLayoutManager(menuLayoutManager);
        commentImpl = new CommentImpl();
        commentImpl.attachView(this);

        commentImpl.loadLongComments(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_comment, this.menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void showLongComments(List<Comment> longComments) {
        commentAdapter.loadLongComments(longComments, commentCount);
    }

    @Override
    public void loadShortComments(List<Comment> shortComments) {
        commentAdapter.loadShortComments(shortComments);
    }

    @Override
    public void onCommentClick(View view, Comment comment) {

    }

    @Override
    public void onItemClickListener(View v, Object obj) {
        if(obj instanceof Comment){
            onCommentClick(v, (Comment)obj);
        }else if(obj instanceof Boolean){
            boolean fold = (Boolean)obj;
            if(fold){
                commentImpl.loadShortComments(id);
            }else {
                commentAdapter.foldShortComments();
            }
        }
    }
}
