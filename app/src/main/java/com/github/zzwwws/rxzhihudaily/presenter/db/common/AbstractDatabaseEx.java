package com.github.zzwwws.rxzhihudaily.presenter.db.common;

import android.content.Context;

public abstract class AbstractDatabaseEx extends AbstractDatabase {		
	public AbstractDatabaseEx(Context context, String uid, String dbName, int dbVersion) {
		super(context, uid, dbName, dbVersion);
	}

    public AbstractDatabaseEx(Context context, String uid, String dbName, int dbVersion, boolean upgrade) {
        super(context, uid, dbName, dbVersion, upgrade);
    }
	
	protected abstract DatabaseRevision getDatabaseRevision();
			
	@Override
	protected final void onCreate() {
		getDatabaseRevision().onCreate(database, version);
	}
	
	@Override
	protected final void onUpgrade(int oldVersion, int newVersion) {
		getDatabaseRevision().onUpgrade(database, oldVersion, newVersion);
	}
}
