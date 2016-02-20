package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.zzwwws.rxzhihudaily.presenter.fragment.BaseFragment;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class BaseActivity extends AppCompatActivity {

    protected Menu menu;

    protected void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    protected void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    protected void setOptionTitle(int id, String title) {
        MenuItem item = menu.findItem(id);
        item.setTitle(title);
    }

    protected void setOptionIcon(int id, int iconRes) {
        MenuItem item = menu.findItem(id);
        item.setIcon(iconRes);
    }

    protected void switchContent(BaseFragment from, BaseFragment to, Bundle bundle) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (from != null)
            transaction.remove(from);
        if (!to.isAdded()) {
            to.setArguments(bundle);
            transaction.add(to.getFragmentId(), to);
        } else {
            if (bundle != null) {
                to.setUIFragmentBundle(bundle);
            }
            transaction.show(to);
        }
        try {
            transaction.commit();
        } catch (Exception e) {

        }
    }

}
