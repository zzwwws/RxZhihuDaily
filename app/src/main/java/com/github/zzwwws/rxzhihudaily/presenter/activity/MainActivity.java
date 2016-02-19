package com.github.zzwwws.rxzhihudaily.presenter.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.presenter.adapter.MenuAdapter;
import com.github.zzwwws.rxzhihudaily.presenter.fragment.HomeFragment;
import com.github.zzwwws.rxzhihudaily.presenter.fragment.TopicFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/4.
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.tool_bar)
    Toolbar toolbar;
    @Bind(R.id.menu_recycler)
    RecyclerView menuRecyclerView;
    @Bind(R.id.DrawerLayout)
    DrawerLayout drawerLayout;

    private String topics[] = new String[]{};
    private RecyclerView.Adapter menuAdapter;
    private LinearLayoutManager menuLayoutManager;
    private ActionBarDrawerToggle drawerToggle;

    private HomeFragment homeFragment;
    private TopicFragment topicFragment;

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
        menuAdapter = new MenuAdapter(this, topics);
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setAdapter(menuAdapter);
        menuLayoutManager = new LinearLayoutManager(this);
        menuRecyclerView.setLayoutManager(menuLayoutManager);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);

        switchContent(null, new HomeFragment(), null);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }
}
