package com.github.zzwwws.rxzhihudaily.presenter.ui.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.github.zzwwws.rxzhihudaily.R;


/**
 * Created by zzwwws on 2016/2/22.
 */
public class ScrollToolBarBehavior extends AppBarLayout.Behavior{

    private Context context;

    public ScrollToolBarBehavior(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.context = context;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
       super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if(child.getChildCount() > 0){
            ViewGroup viewGroup = (ViewGroup)child.getChildAt(0);
            Toolbar toolbar = (Toolbar)viewGroup.getChildAt(1);
            setToolBarStatus(toolbar, dyConsumed);
        }
    }

    private void setToolBarStatus(Toolbar toolbar, float scrollY){
        int toolbarHeight = toolbar.getHeight();

        float contentHeight = getStoryHeaderViewHeight() - toolbarHeight;
        float ratio = Math.min(scrollY / contentHeight, 1.0f);
        toolbar.setAlpha((int) (ratio * 0xFF));
    }


    private int getStoryHeaderViewHeight() {
        return context.getResources().getDimensionPixelSize(R.dimen.top_story_detail);
    }
}
