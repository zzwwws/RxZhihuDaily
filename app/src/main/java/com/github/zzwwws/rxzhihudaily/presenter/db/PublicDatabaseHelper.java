package com.github.zzwwws.rxzhihudaily.presenter.db;

import android.content.Context;

public class PublicDatabaseHelper {

	private static PublicDatabaseHelper instance;

	private PublicDatabase publicDb;

	public static PublicDatabaseHelper instance() {
		if (instance == null) {
			if (instance == null) {
				instance = new PublicDatabaseHelper();
			}
		}
		return instance;
	}

	public synchronized boolean open(Context context) {
		closeInner();
		return openInner(context);
	}

	private boolean openInner(Context context) {
		boolean opened = false;
		publicDb = new PublicDatabase(context);

		if (publicDb.open()) {
			opened = true;
		} else {
			closeInner();
		}

		return opened;
	}

	private void closeInner() {
		if (publicDb != null) {
			publicDb.close();
			publicDb = null;
		}
	}

	public synchronized void close() {
		closeInner();
	}

	public PublicDatabase getPublicDb() {
		return publicDb != null && publicDb.opened() ? publicDb : null;
	}
}
