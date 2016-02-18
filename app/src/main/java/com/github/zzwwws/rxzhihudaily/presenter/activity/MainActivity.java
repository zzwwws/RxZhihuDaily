package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.model.entities.LatestFeed;
import com.github.zzwwws.rxzhihudaily.presenter.adapter.MenuAdapter;
import com.github.zzwwws.rxzhihudaily.presenter.impl.FetchFeedImpl;
import com.github.zzwwws.rxzhihudaily.presenter.impl.RecyclerLoadingView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/4.
 */
public class MainActivity extends AppCompatActivity implements RecyclerLoadingView {

    @Bind(R.id.tool_bar)
    Toolbar toolbar;
    @Bind(R.id.RecyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.DrawerLayout)
    DrawerLayout drawerLayout;

    private String topics[] = new String[]{};
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ActionBarDrawerToggle drawerToggle;
    private FetchFeedImpl fetchImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        initDrawer();
        initData();
    }

    private void initData() {
        topics = this.getResources().getStringArray(R.array.menu_topic_type);
        adapter = new MenuAdapter(this, topics);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);

        fetchImpl = new FetchFeedImpl();
        fetchImpl.attachView(this);

        testRxJava();
    }

    private void initDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void testRxJava() {
        fetchImpl.loadingNew();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.message:
                    break;
                case R.id.action_search:
                    break;
                case R.id.action_settings:
                    break;
            }
            return false;
        }
    };

    @Override
    public void loadingNew(LatestFeed latestFeed) {
        Toast.makeText(this, latestFeed.getDate(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadingPast() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(int layoutId) {

    }
}
