package com.github.zzwwws.rxzhihudaily.common;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class AppConfig {

    public static final boolean DEBUG = true;

    public static final String BASE_URL = "http://news-at.zhihu.com/api/";

    public static final String CACHE_DIR_NAME = "cache_file";

    public static final long CACHE_MAX_SIZE = 20 * 1024 * 1024;

    public static final int CACHE_TIME_DEFAULT = 60 * 60 * 24 * 28;

    public static final int CACHE_TIME_NETWORK_UNAVAILABLE = 60 * 60 * 24 * 28;

    public static final int CACHE_TIME_NETWORK_AVAILABLE = 0 * 60 ;
}
