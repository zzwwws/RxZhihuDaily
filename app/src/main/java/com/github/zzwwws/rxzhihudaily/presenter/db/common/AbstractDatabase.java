package com.github.zzwwws.rxzhihudaily.presenter.db.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.zzwwws.rxzhihudaily.common.BaseApplication;
import com.github.zzwwws.rxzhihudaily.common.util.LogUtil;

/**
 * Created by zzwwws on 2016/3/8.
 */
public abstract class AbstractDatabase {
    private static final String TAG = "db";
    protected final int version;
    protected SQLiteDatabase database;
    private boolean upgrade;
    private Context context;
    private String uid;
    private String dbName;

    public AbstractDatabase(Context context, String uid, String dbName, int dbVersion) {
        this(context, uid, dbName, dbVersion, true);
    }

    public AbstractDatabase(Context context, String uid, String dbName, int dbVersion, boolean upgrade) {
        this.context = context;
        this.uid = uid;
        this.dbName = dbName;
        this.version = dbVersion;
        this.upgrade = upgrade;

        open();
    }

    protected static final String composeUserDbPath(String uid, String dbName) {
        return BaseApplication.get().getApplicationInfo().dataDir + "/" + uid + "/" + dbName;
    }

    public Context getContext(){
        return context;
    }

    public boolean open() {
        String dbPath = composeUserDbPath(uid, dbName);
        if (upgrade) {
            openOrUpdagrade(dbPath, dbName, version);
        } else {
            openOnly(dbPath, dbName, version);
        }
        return database != null;
    }

    public boolean opened() {
        return database != null;
    }

    private void openOnly(String dbPath, String dbName, int dbVersion) {
        try {
            database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
            if (database.getVersion() < dbVersion) {
                // need upgrade
                database.close();
                database = null;
            }
        } catch (Exception e) {
            LogUtil.i(TAG, "open database " + dbName + " only failed: " + e);
        }
    }

    private void openOrUpdagrade(String dbPath, String dbName, int dbVersion) {
        this.database = SQLiteDatabase.openOrCreateDatabase(dbPath, null);

        int old = database.getVersion();
        if (old != dbVersion) {
            database.beginTransaction();
            try {
                if (old == 0) {
                    LogUtil.i(TAG, "create database " + dbName);

                    onCreate();
                } else {
                    if (old < dbVersion) {
                        LogUtil.i(TAG, "upgrade database " + dbName + " from " + old + " to " + dbVersion);

                        onUpgrade(old, dbVersion);
                    }
                }
                database.setVersion(dbVersion);
                database.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtil.e(TAG, "create or upgrade database " + dbName + " error: " + e);
            } finally {
                database.endTransaction();
            }
        }

    }

    public void close() {
        if (database != null) {
            database.close();
            database = null;
        }
    }

    public void exeSQL(String sql) {
        if (database != null) {
            DatabaseHelper.exeSQL(database, sql);
        }
    }

    public Cursor rawQuery(String sql) {
        if (database != null) {
            return DatabaseHelper.rawQuery(database, sql);
        }
        return null;
    }

    public long insert(String table,String nullColumnHack,ContentValues values){
        if (database != null){
            return database.insert(table,nullColumnHack,values);
        }
        return -1;
    }

    protected abstract void onCreate();

    protected abstract void onUpgrade(int oldVersion, int newVersion);
}
