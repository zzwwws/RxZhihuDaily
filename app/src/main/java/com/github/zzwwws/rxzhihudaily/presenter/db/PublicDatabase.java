package com.github.zzwwws.rxzhihudaily.presenter.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.zzwwws.rxzhihudaily.presenter.db.common.AbstractDatabaseEx;
import com.github.zzwwws.rxzhihudaily.presenter.db.common.DatabaseRevision;

public class PublicDatabase extends AbstractDatabaseEx {

    public PublicDatabase(Context context) {
        super(context, null, "public.db", PublicDatabaseRevision.VERSION);
    }

    @Override
    protected DatabaseRevision getDatabaseRevision() {
        return PublicDatabaseRevision.INSTANCE;
    }


    public static class PublicDatabaseRevision extends DatabaseRevision {

        static final int VERSION = 1;

        static final PublicDatabaseRevision INSTANCE = new PublicDatabaseRevision();

        private PublicDatabaseRevision() {
            super(tables());
        }

        private static Table[] tables() {
            return new Table[]{settings(), token()};
        }

        private static Table settings() {
            return new Table("settings").appendVersion(new Version(1) {

                @Override
                protected String[] getCreateSQLs(SQLiteDatabase db) {
                    return new String[]{"CREATE TABLE IF NOT EXISTS "
                            + "settings "
                            + "(pKey Varchar(30) not null PRIMARY KEY,"
                            + "pValue Varchar(120)," + "pExtra Varchar(120));",};
                }

                @Override
                protected String[] getUpgradeSQLs(SQLiteDatabase db,
                                                  Version version) {
                    return null;
                }

                @Override
                protected void test(SQLiteDatabase db) {

                }
            });
        }

        private static Table token() {
            return new Table("nos_token").appendVersion(new Version(1) {

                @Override
                protected String[] getCreateSQLs(SQLiteDatabase db) {
                    return new String[] {
                            "CREATE TABLE IF NOT EXISTS " +
                                    "nos_token" +
                                    " (" +
                                    "_id integer PRIMARY KEY AUTOINCREMENT," +
                                    "token TEXT NOT NULL);"
                    };
                }

                @Override
                protected String[] getUpgradeSQLs(SQLiteDatabase db,
                                                  Version version) {
                    return null;
                }

                @Override
                protected void test(SQLiteDatabase db) {

                }
            });
        }

    }
}
