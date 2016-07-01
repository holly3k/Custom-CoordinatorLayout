package com.example.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by ling on 2016/6/28.
 */

public class MyCoordinatorLayout extends FrameLayout implements NestedScrollingParent {
    public MyCoordinatorLayout(Context context) {
        super(context);
    }

    public MyCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        final ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                dispatchOnDependentViewChanged();
                return true;
            }
        });

    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
            if(lp.behavior!=null && lp.behavior.layoutDependsOn() == target.getId()) {
                lp.behavior.onNestedScroll(target, dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed);
            }
        }
    }

    @Override
    public void onStopNestedScroll(View target) {
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
            if(lp.behavior!=null && lp.behavior.layoutDependsOn() == target.getId()) {
                lp.behavior.onStopNestedScroll(child,target);
            }
        }
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
            if(lp.behavior!=null && lp.behavior.layoutDependsOn() == target.getId()) {
                lp.behavior.onNestedPreScroll(child, target, dx, dy, consumed);
            }
        }
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthSize, heightSize;
//
//        //Get the width based on the measure specs
//        widthSize = getDefaultSize(0, widthMeasureSpec);
//
//        //Get the height based on measure specs
//        heightSize = getDefaultSize(0, heightMeasureSpec);
//
//        int majorDimension = Math.min(widthSize, heightSize);
//        //Measure all child views
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//
//        //MUST call this to save our own dimensions
//        setMeasuredDimension(widthSize, heightSize);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        for (int i=0; i < getChildCount(); i++) {
//            View child = getChildAt(i);
//            View lastChild = getChildAt(i-1);
//
//            if(lastChild==null) {
//                child.layout(l,
//                        t,
//                        l+child.getMeasuredWidth(),
//                        t+child.getMeasuredHeight());
//            } else {
//                child.layout(lastChild.getLeft(),
//                        lastChild.getBottom(),
//                        lastChild.getLeft()+child.getMeasuredWidth(),
//                        lastChild.getBottom()+child.getMeasuredHeight());
//            }
//        }
//    }

    //
    private void dispatchOnDependentViewChanged() {
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
            if(lp.behavior!=null) {
                lp.behavior.onDependentViewChanged(child,findViewById(lp.behavior.layoutDependsOn()));
            }
        }
    }
    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new MyLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MyLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MyLayoutParams(getContext(), attrs);
    }
    class MyLayoutParams extends FrameLayout.LayoutParams{
        MyBehavior behavior;

        public MyLayoutParams(ViewGroup.LayoutParams p){
            super(p);
        }

        public MyLayoutParams(int width, int height){
            super(width,height);
        }

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.getTheme ().obtainStyledAttributes(attrs,R.styleable.MyCoordinatorLayout_LayoutParams,0,0);
            try {
                String fullName = a.getString(R.styleable.MyCoordinatorLayout_LayoutParams_behavior_class);
//                fullName = "com.example.myapplication.MyBehavior2";
                if(fullName != null) {
                    Class clazz = Class.forName(fullName, true, c.getClassLoader());
                    Constructor constructor = clazz.getConstructor(new Class[]{Context.class, AttributeSet.class});
                    constructor.setAccessible(true);
                    behavior = (MyBehavior)constructor.newInstance(c, attrs);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                a.recycle();
            }
        }
    }
    public static abstract class MyBehavior{
        public MyBehavior(){};
        public MyBehavior(Context c, AttributeSet as){};
        public abstract int layoutDependsOn();
        public void onDependentViewChanged(View child, View dependency){};
        public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) { }
        public void onStopNestedScroll(View watcher, View target){};
        public void onNestedPreScroll(View watcher, View target, int dx, int dy, int[] consumed){};
    }
}
