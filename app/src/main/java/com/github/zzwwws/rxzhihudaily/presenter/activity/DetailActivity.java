package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryDetail;
import com.github.zzwwws.rxzhihudaily.model.entities.StoryExtraInfo;
import com.github.zzwwws.rxzhihudaily.presenter.impl.StoryDetailImpl;
import com.github.zzwwws.rxzhihudaily.presenter.infr.StoryDetailView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/22.
 */
public class DetailActivity extends BaseActivity implements StoryDetailView {

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
    @Bind(R.id.tv_top_detail_source)
    TextView tvTopDetailSource;

    private String newsId;
    private StoryDetailImpl detailImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        newsId = getIntent().getStringExtra("id");
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        initData();
    }

    private void initData() {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setDatabaseEnabled(true);
        webview.getSettings().setAppCacheEnabled(true);
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.light_toolbar));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.light_toolbar));

        detailImpl = new StoryDetailImpl();
        detailImpl.attachView(this);

        detailImpl.getExtraInfo(newsId);
        detailImpl.loadStoryDetail(newsId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_detail, this.menu);
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

        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + detail.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webview.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);

    }

    @Override
    public void showStoryExtraInfo(StoryExtraInfo info) {
    }

    @Override
    public void showLike(boolean like) {

    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
            }
            return false;
        }
    };
}
