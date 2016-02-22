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
import android.widget.ImageView;
import android.widget.TextView;

import com.github.zzwwws.rxzhihudaily.R;
import com.github.zzwwws.rxzhihudaily.common.util.ToastUtil;
import com.github.zzwwws.rxzhihudaily.model.entities.TopicWrapper;
import com.github.zzwwws.rxzhihudaily.presenter.adapter.MenuAdapter;
import com.github.zzwwws.rxzhihudaily.presenter.fragment.BaseFragment;
import com.github.zzwwws.rxzhihudaily.presenter.fragment.HomeFragment;
import com.github.zzwwws.rxzhihudaily.presenter.fragment.TopicFragment;
import com.github.zzwwws.rxzhihudaily.presenter.impl.MenuImpl;
import com.github.zzwwws.rxzhihudaily.presenter.infr.MenuRecyclerView;
import com.github.zzwwws.rxzhihudaily.presenter.infr.RecyclerOnItemClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zzwwws on 2016/2/4.
 */
public class MainActivity extends BaseActivity implements MenuRecyclerView, RecyclerOnItemClickListener{
    @Bind(R.id.tool_bar)
    Toolbar toolbar;
    @Bind(R.id.menu_recycler)
    RecyclerView menuRecyclerView;
    @Bind(R.id.DrawerLayout)
    DrawerLayout drawerLayout;

    private final int HOME_PAGE = 0x00;
    private final int TOPIC_PAGE = 0x01;
    private String topics[] = new String[]{};
    private MenuAdapter menuAdapter;
    private LinearLayoutManager menuLayoutManager;
    private ActionBarDrawerToggle drawerToggle;

    private HomeFragment homeFragment;
    private TopicFragment topicFragment;
    
    private MenuImpl menuImpl;
    private int currentPage = HOME_PAGE;

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

        menuImpl = new MenuImpl();
        menuImpl.attachView(this);

        topics = this.getResources().getStringArray(R.array.menu_topic_type);
        menuAdapter = new MenuAdapter(this, menuImpl.getDefaultTopicList(topics));
        menuAdapter.setOnItemClickListener(this);
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setAdapter(menuAdapter);

        menuLayoutManager = new LinearLayoutManager(this);
        menuRecyclerView.setLayoutManager(menuLayoutManager);
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);

        if(homeFragment == null){
            homeFragment = new HomeFragment();
        }
        switchContent(topicFragment, homeFragment, null);

        menuImpl.loadTopics();
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
        if(this.menu != null){
            this.menu.clear();
        }
        this.menu = menu;
        if(currentPage == HOME_PAGE){
            getMenuInflater().inflate(R.menu.menu_main, this.menu);
        }else{
            getMenuInflater().inflate(R.menu.menu_topic, this.menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void bindTopics(List<TopicWrapper> topics) {
        if(topics == null || topics.size() == 0){
            topics = menuImpl.getDefaultTopicList(this.topics);
        }
        menuAdapter.initData(topics);
    }

    @Override
    public void downLoadOffLine() {

    }

    @Override
    public void authLogin() {

    }

    @Override
    public void onItemClickListener(View v, int pos) {
        switch (v.getId()){
            case R.id.lly_header_home:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                drawerLayout.closeDrawers();
                switchContent(topicFragment, homeFragment, null);
                switchToolbarTheme(currentPage, getString(R.string.homepage));
                break;
            default:
                if(v instanceof TextView){
                    TopicWrapper topic= (TopicWrapper)v.getTag();
                    if(topicFragment == null){
                        topicFragment = new TopicFragment();
                    }
                    topicFragment.setTopicId(topic.getId() + "");
                    drawerLayout.closeDrawers();

                    //replace or transaction
                    if(currentPage == HOME_PAGE){
                        switchContent(homeFragment, topicFragment, null);
                    }else {
                        topicFragment.replace(topic.getId()+"");
                    }
                    switchToolbarTheme(currentPage, topic.getName());
                }else if(v instanceof ImageView){
                    // TODO: 2016/2/20 add favorite
                }
                break;
        }
    }

    @Override
    public void onItemClickListener(View v, String id) {

    }

    @Override
    protected void switchContent(BaseFragment from, BaseFragment to, Bundle bundle) {
        super.switchContent(from, to, bundle);
        currentPage = from == homeFragment?TOPIC_PAGE:HOME_PAGE;
    }

    private void switchToolbarTheme(int currentPage, String name){
        toolbar.setTitle(name);
        invalidateOptionsMenu();
    }


    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.message:
                    ToastUtil.showToast(MainActivity.this,"message");
                    break;
                case R.id.action_search:
                    ToastUtil.showToast(MainActivity.this,"action_search");
                    break;
                case R.id.action_settings:
                    ToastUtil.showToast(MainActivity.this,"action_settings");
                    break;
                case R.id.attention:
                    ToastUtil.showToast(MainActivity.this,"attention");
                    break;
            }
            return false;
        }
    };
}
