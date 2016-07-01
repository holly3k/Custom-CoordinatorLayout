package com.example.myapplication;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ling on 2016/7/1.
 */

public class MyBehavior4 extends MyCoordinatorLayout.MyBehavior {

    public MyBehavior4(Context c, AttributeSet as) {
        super(c, as);
    }
    @Override
    public int layoutDependsOn() {
        return R.id.second;
    }

    @Override
    public void onStopNestedScroll(View watcher, View target) {
        FloatingActionButton fab = (FloatingActionButton) watcher;
        fab.show();
    }

    @Override
    public void onNestedPreScroll(View watcher, View target, int dx, int dy, int[] consumed) {
        FloatingActionButton fab = (FloatingActionButton) watcher;
        fab.hide();
    }
}
