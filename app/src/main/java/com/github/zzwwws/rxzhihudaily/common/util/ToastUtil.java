package com.github.zzwwws.rxzhihudaily.common.util;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.github.zzwwws.rxzhihudaily.common.BaseApplication;
import com.github.zzwwws.rxzhihudaily.common.Handlers;


public class ToastUtil {
    private static final int FAVORITE_ALERT_TIMES = 20;
    /**
     * handler to show toasts safely
     */
    private static Handler mHandler = null;
    private static Toast toast = null;
    private static Toast customToast = null;

    public static Toast makeToast(Context mContext, int resId) {
        return Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
    }

    public static Toast makeToast(Context mContext, String text) {
        return Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context mContext, int resId) {
        final Context context = (mContext == null ? BaseApplication.get() : mContext);
        final int resid = resId;
        sharedHandler(context).post(new Runnable() {

            @Override
            public void run() {
                if (toast != null) {
                    toast.setText(resid);
                    toast.setDuration(Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(context.getApplicationContext(), resid, Toast.LENGTH_SHORT);
                }

                toast.show();
            }
        });

    }

    public static void showToast(Context mContext, String text) {
        final Context context = (mContext == null ? BaseApplication.get() : mContext);
        final String msg = text;
        sharedHandler(context).post(new Runnable() {

            @Override
            public void run() {
                if (toast != null) {
                    toast.setText(msg);
                    toast.setDuration(Toast.LENGTH_SHORT);
                } else {
                    toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
                }
                toast.show();
            }
        });
    }

    public static void showLongToast(Context mContext, int resId) {
        final Context context = (mContext == null ? BaseApplication.get() : mContext);
        final int resid = resId;
        sharedHandler(context).post(new Runnable() {

            @Override
            public void run() {
                if (toast != null) {
                    toast.setText(resid);
                    toast.setDuration(Toast.LENGTH_LONG);
                } else {
                    toast = Toast.makeText(context.getApplicationContext(), resid, Toast.LENGTH_LONG);
                }
                toast.show();
            }
        });

    }

    public static void showLongToast(Context mContext, String text) {
        final Context context = (mContext == null ? BaseApplication.get() : mContext);
        final String msg = text;
        sharedHandler(context).post(new Runnable() {

            @Override
            public void run() {
                if (toast != null) {
                    toast.setText(msg);
                    toast.setDuration(Toast.LENGTH_LONG);
                } else {
                    toast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG);
                }
                toast.show();
            }
        });
    }

    private static Handler sharedHandler(Context context) {
        if (context == null) {
            context = BaseApplication.get();
        }
        if (mHandler == null) {
            mHandler = Handlers.sharedHandler(context);
        }

        return mHandler;
    }
}
