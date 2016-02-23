package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.common.util.WebUtils;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryExtraInfo;
import com.github.zzwwws.rxzhihudaily.presenter.impl.StoryDetailImpl;
import com.github.zzwwws.rxzhihudaily.presenter.infr.StoryDetailView;
import com.github.zzwwws.rxzhihudaily.presenter.ui.widget.ScrollPullDownHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzwwws on 2016/2/22.
 */
public class DetailActivity extends BaseActivity implements StoryDetailView, OnClickListener{

    @Bind(R.id.img_story_detail)
    ImageView imgStoryDetail;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @Bind(R.id.tv_top_detail_title)
    TextView tvTopDetailTitle;
    @Bind(R.id.top_story_detail)
    View topStoryDetail;
    @Bind(R.id.tv_top_detail_source)
    TextView tvTopDetailSource;
    @Bind(R.id.scrollView)
    NestedScrollView scrollView;

    private ScrollPullDownHelper scrollPullDownHelper;

    private String newsId;
    private StoryDetailImpl detailImpl;

    private ViewHolder toolbarHolder;
    private int commentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        newsId = getIntent().getStringExtra("id");
//        toolbar.setNavigationIcon(R.drawable.back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//
//            }
//        });
        LayoutInflater inflater = LayoutInflater.from(this);
        View customToolbar = inflater.inflate(R.layout.toolbar_story_detail, null);
        toolbarHolder = new ViewHolder(customToolbar, this);
        toolbar.addView(customToolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        initData();
    }

    private void initData() {
        initWebView();
        scrollPullDownHelper = new ScrollPullDownHelper();
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.light_toolbar));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.light_toolbar));

        detailImpl = new StoryDetailImpl();
        detailImpl.attachView(this);

        detailImpl.getExtraInfo(newsId);
        detailImpl.loadStoryDetail(newsId);
    }

    public void initWebView() {
        final WebSettings settings = webview.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            settings.setLoadsImagesAutomatically(true);//API大于19时，如果多张图片url相同时，只会加载一个IMAGE所以直接加载
        } else {
            settings.setLoadsImagesAutomatically(false);
        }
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (!settings.getLoadsImagesAutomatically()) {
                    settings.setLoadsImagesAutomatically(true);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
//        getMenuInflater().inflate(R.menu.menu_detail, this.menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showStoryDetail(StoryDetail detail) {
        tvTopDetailTitle.setText(detail.getTitle());
        tvTopDetailSource.setText(detail.getImageSource());
        Glide
                .with(this)
                .load(detail.getImage())
                .dontAnimate()
                .placeholder(R.drawable.image_top_default)
                .error(R.drawable.image_top_default)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imgStoryDetail);
        bindWebView(detail);
    }

    @Override
    public void showStoryExtraInfo(StoryExtraInfo info) {
        bindToolbar(info);
    }

    @Override
    public void showLike(boolean like) {

    }

    private void bindToolbar(StoryExtraInfo info) {
        commentCount = info.getComments();
        toolbarHolder.tvComment.setText(info.getComments() + "");
        toolbarHolder.tvPraise.setText(info.getPopularity() + "");
        toolbarHolder.tvComment.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        toolbarHolder.tvPraise.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    private void bindWebView(StoryDetail storyDetail) {
        if (TextUtils.isEmpty(storyDetail.getBody())) {

            webview.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            webview.loadUrl(storyDetail.getShareUrl());
        } else {

            String data = WebUtils.BuildHtmlWithCss(storyDetail.getBody(), storyDetail.getCss(), false);
            webview.loadDataWithBaseURL(null, data, WebUtils.MIME_TYPE, WebUtils.ENCODING, null);

            if (!TextUtils.isEmpty(storyDetail.getImage())) {
//                addScrollListener();
            }else{
                topStoryDetail.setVisibility(View.GONE);
            }
        }
    }

    private void addScrollListener() {
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                changeToolbarAlpha();
            }
        });
    }

    private void changeToolbarAlpha() {
        int scrollY = scrollView.getScrollY();
        int storyHeaderViewHeight = getStoryHeaderViewHeight();
        int toolbarHeight = toolbar.getHeight();
        float contentHeight = storyHeaderViewHeight - toolbarHeight;
        float ratio = Math.min(scrollY / contentHeight, 1.0f);
        toolbar.getBackground().setAlpha((int) (ratio * 0xFF));
        if (scrollY <= contentHeight) {
            toolbar.setY(0f);
            return;
        }

        if (scrollY + scrollView.getHeight() > coordinatorLayout.getMeasuredHeight() + storyHeaderViewHeight) {
            return;
        }

        boolean isPullingDown = scrollPullDownHelper.onScrollChanged(scrollY);
        float toolBarPositionY = isPullingDown ? 0 : (contentHeight - scrollY);
        if (scrollY < storyHeaderViewHeight + toolbarHeight) {
            toolBarPositionY = storyHeaderViewHeight - scrollY - toolbarHeight;
        }

        toolbar.setY(toolBarPositionY);
    }

    private int getStoryHeaderViewHeight() {
        return getResources().getDimensionPixelSize(R.dimen.top_story_detail);
    }


    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
            }
            return false;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lly_praise:
                break;
            case R.id.lly_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra("id", newsId);
                intent.putExtra("comment_count", commentCount);
                startActivity(intent);
                break;
            case R.id.img_collect:
                break;
            case R.id.img_share:
                break;
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

    static class ViewHolder implements OnClickListener {
        @Bind(R.id.img_praise)
        ImageView imgPraise;
        @Bind(R.id.tv_praise)
        TextView tvPraise;
        @Bind(R.id.lly_praise)
        LinearLayout llyPraise;
        @Bind(R.id.img_comment)
        ImageView imgComment;
        @Bind(R.id.tv_comment)
        TextView tvComment;
        @Bind(R.id.lly_comment)
        LinearLayout llyComment;
        @Bind(R.id.img_collect)
        ImageView imgCollect;
        @Bind(R.id.img_share)
        ImageView imgShare;
        @Bind(R.id.img_back)
        ImageView imgBack;
        OnClickListener listener;


        ViewHolder(View view, OnClickListener listener) {
            ButterKnife.bind(this, view);
            this.listener = listener;
        }

        @OnClick({R.id.lly_praise, R.id.lly_comment, R.id.img_collect, R.id.img_share, R.id.img_back})
        public void onClick(View view) {
            if(listener != null){
                listener.onClick(view);
            }
        }
    }
}
