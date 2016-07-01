package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ling on 2016/6/29.
 */

public class MyBehavior3 extends MyCoordinatorLayout.MyBehavior {
    int offsetTotal = 0;
    boolean scrolling = false;

    public MyBehavior3(Context c, AttributeSet as) {
        super(c, as);
    }

    @Override
    public int layoutDependsOn() {
        return R.id.second;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        offset(target, dyConsumed);
    }


    public void offset(View child,int dy){
        int old = offsetTotal;
        int top = offsetTotal - dy;
        top = Math.max(top, -child.getHeight());
        top = Math.min(top, 0);
        offsetTotal = top;
        if (old == offsetTotal){
            scrolling = false;
            return;
        }
        int delta = offsetTotal-old;
        child.offsetTopAndBottom(delta);
        scrolling = true;
    }


}
