package com.github.zzwwws.rxzhihudaily.presenter.cache;

import android.database.Cursor;

import com.github.zzwwws.rxzhihudaily.presenter.db.PublicDatabase;
import com.github.zzwwws.rxzhihudaily.presenter.db.PublicDatabaseHelper;
import com.github.zzwwws.rxzhihudaily.presenter.db.common.DatabaseHelper;

public class Settings {


    private static PublicDatabase getDb() {
        return PublicDatabaseHelper.instance().getPublicDb();
    }

    private static String queryUserSetting(String key) {
        PublicDatabase db = getDb();
        String value = "";
        if (db != null) {
            Cursor mCursor = db
                    .rawQuery("select pValue from settings where pKey='" + key
                            + "'");
            if (mCursor != null && mCursor.moveToNext()) {
                value = mCursor.getString(0);
            }
            if (mCursor != null && (!mCursor.isClosed()))
                mCursor.close();
        }
        return value;
    }

    private static boolean saveUserSetting(String key, String value) {
        PublicDatabase db = getDb();
        boolean ret = false;
        if (db != null) {
            String sql = "insert or replace into settings (pKey, pValue) values('"
                    + DatabaseHelper.escapeQuotes(key)
                    + "','"
                    + DatabaseHelper.escapeQuotes(value) + "')";
            db.exeSQL(sql);
            ret = true;
        }
        return ret;
    }

}
