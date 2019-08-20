package com.tizz.xiaomeng.Widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 13371 on 2017/8/28.
 */

public class NoScrollViewPager extends ViewPager {

    public NoScrollViewPager(Context context){
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0){
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0){
        return false;
    }
}
