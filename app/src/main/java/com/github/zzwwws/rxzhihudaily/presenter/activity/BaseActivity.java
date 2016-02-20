package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.github.zzwwws.rxzhihudaily.presenter.fragment.BaseFragment;

/**
 * Created by zzwwws on 2016/2/19.
 */
public class BaseActivity extends AppCompatActivity{

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
