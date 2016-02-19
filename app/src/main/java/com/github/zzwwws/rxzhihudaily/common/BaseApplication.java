package com.github.zzwwws.rxzhihudaily.common;

import android.app.Application;

import com.github.zzwwws.rxzhihudaily.common.util.ScreenUtil;
import com.github.zzwwws.rxzhihudaily.common.util.StrictModeUtil;

public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = (BaseApplication) getApplicationContext();

        if (AppConfig.DEBUG) {
            StrictModeUtil.startStrictMode();
        }
        ScreenUtil.GetInfo(instance);
    }
}
