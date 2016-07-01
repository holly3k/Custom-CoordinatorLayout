package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ling on 2016/6/28.
 */

public class MyBehavior2 extends MyCoordinatorLayout.MyBehavior{

    public void onDependentViewChanged(View child, View dependency){
        child.setY(dependency.getY()+dependency.getHeight());
    }

    public MyBehavior2(Context c, AttributeSet as) {

    }

    public int layoutDependsOn() {
        return R.id.first;
    }
}
