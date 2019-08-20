package com.tizz.xiaomeng;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by 13371 on 2017/8/4.
 */

public class App  {

    public static App instance;
    private List<Activity> mList=new LinkedList<Activity>();

    public synchronized static App getInstance2(){
        if(instance==null){
            instance=new App();
        }
        return instance;
    }

    public void exit(){
        try{
            for(Activity activity:mList){
                if(activity!=null){
                    activity.finish();
                }
            }
            }catch(Exception e){
            e.printStackTrace();
            }finally{
            System.exit(0);
        }
    }
}
