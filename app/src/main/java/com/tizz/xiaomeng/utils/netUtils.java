package com.tizz.xiaomeng.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 13371 on 2017/7/16.
 */

public class netUtils {
    public static void openSetNetWork(Context context) {
            context.startActivity(new Intent(
                    android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }

    public static boolean isNetworkAvailable(Context context){
        NetworkInfo info = getNetworkInfo(context);
        if(info!=null){
            return info.isAvailable();
        }
        return false;
    }

    private static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
}
