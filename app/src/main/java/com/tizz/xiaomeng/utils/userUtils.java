package com.tizz.xiaomeng.utils;

import android.content.Context;
import android.text.TextUtils;

import com.tizz.xiaomeng.Constants;

import org.w3c.dom.Text;

/**
 * Created by 13371 on 2017/8/3.
 */

public class userUtils {

    public static String getUserName(Context context){
        User user=getUserModel(context);
        if(user!=null){
            return user.getUserName();
        }else
            return "";
    }

    public static User getUserModel(Context context){
        User user=null;
        String jsondata=Utils.getValue(Context, Constants.UserInfo);
        if(!TextUtils.isEmpty(jsondata)){
            user=JSON.parseObject(jsondata,User.class);
            return user;
        }

    }


}
