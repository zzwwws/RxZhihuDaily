package com.github.zzwwws.rxzhihudaily.common.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;

/**
 * Created by zzwwws on 2016/2/3.
 */
public class StrictModeUtil {

    public static void startStrictMode(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            startThreadStrictMode();
            startVmStrictMode();
        }
    }

    @TargetApi(9)
    private static final void startThreadStrictMode(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().permitDiskReads().permitDiskWrites().penaltyLog().build());
    }

    @TargetApi(9)
    private static final void startVmStrictMode(){
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
    }
}
