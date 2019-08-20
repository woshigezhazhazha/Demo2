package com.tizz.xiaomeng.Base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by 13371 on 2017/8/18.
 */

public class BaseApplication extends Application {
    private static Context mContext;
    private static BaseApplication mInstance;

    public static int screenWidth;
    public static int screenHeight;
    public static float screenDensity;

    public void onCreate(){
        super.onCreate();
        mContext=getApplicationContext();
        mInstance=this;
        initScreenSize();
    }

    public static Context getContext(){
        return mContext;
    }

    public static Context getInstance(){
        return mInstance;
    }

    private void initScreenSize(){
        DisplayMetrics curMetrics=getApplicationContext().getResources().getDisplayMetrics();
        screenWidth=curMetrics.widthPixels;
        screenHeight=curMetrics.heightPixels;
        screenDensity=curMetrics.density;
    }
}
