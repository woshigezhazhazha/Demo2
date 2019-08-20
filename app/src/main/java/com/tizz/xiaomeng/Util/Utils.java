package com.tizz.xiaomeng.Util;

import android.content.Context;

/**
 * Created by 13371 on 2017/8/28.
 */

public class Utils {


    //像素转换
    public static int dp2px(Context context,float dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * 2 + 0.5F);
    }


}
