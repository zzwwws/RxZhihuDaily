package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.common.Handlers;
import com.github.zzwwws.rxzhihudaily.common.util.ScreenUtil;
import com.github.zzwwws.rxzhihudaily.model.entities.StartImage;
import com.github.zzwwws.rxzhihudaily.presenter.impl.SplashImpl;
import com.github.zzwwws.rxzhihudaily.presenter.infr.SplashImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzw on 16/2/22.
 */
public class SplashActivity extends BaseActivity implements SplashImageView {
    @Bind(R.id.tv_img_source)
    TextView tvSource;
    @Bind(R.id.img_splash)
    ImageView imageView;

    private SplashImpl splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initData();
    }

    private void initData(){
        splash = new SplashImpl();
        splash.attachView(this);

        splash.getSplashImage(ScreenUtil.getImageDensity());
    }


    @Override
    public void showStartImage(StartImage startImage) {
        tvSource.setText(startImage.getText());
        Glide
                .with(this)
                .load(startImage.getImg())
                .dontAnimate()
                .placeholder(R.drawable.splash)
                .error(R.drawable.splash)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
        nextStep();
    }

    @Override
    public void nextStep() {
        Handlers.sharedHandler(this).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(0,0);
            }
        },2000);
    }
}
