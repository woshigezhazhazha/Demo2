package com.tizz.xiaomeng.UI.me_fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.UI.me_fragment.setting.SettingActivity;
import com.tizz.xiaomeng.UI.me_fragment.user_info.UserInfoActivity;

/**
 * Created by 13371 on 2017/8/5.
 */

public class me_fg extends Fragment implements View.OnClickListener {

    private TextView tv_settings,tv_myCare;
    private View layout;
    private Activity activity;
    private LinearLayout userInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if(layout==null){
            activity=this.getActivity();
            layout=activity.getLayoutInflater().inflate(R.layout.me_fragment,null);

            initViews();
            setOnListener();
            initDrawables();
        }else{
            ViewGroup parent=(ViewGroup)layout.getParent();
            if(parent!=null){
                parent.removeView(parent);
            }
        }
        return layout;
    }

    public void initViews(){
        tv_settings=(TextView)layout.findViewById(R.id.txt_setting);
        tv_myCare=(TextView)layout.findViewById(R.id.txt_myCare);
        userInfo=(LinearLayout)layout.findViewById(R.id.ll_userInfo);
    }

    public void initDrawables(){
        Drawable d2=getResources().getDrawable(R.drawable.icon_like);
        Drawable d3=getResources().getDrawable(R.drawable.icon_setting);
        d2.setBounds(0,0,80,80);
        d3.setBounds(0,0,80,80);
        tv_myCare.setCompoundDrawables(d2,null,null,null);
        tv_settings.setCompoundDrawables(d3,null,null,null);
    }

    public void setOnListener(){
        tv_settings.setOnClickListener(this);
        tv_myCare.setOnClickListener(this);
        userInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.ll_userInfo:
                Intent k=new Intent(getActivity(), UserInfoActivity.class);
                startActivity(k);
                break;
            case R.id.txt_setting:
                Intent i=new Intent(getActivity(),SettingActivity.class);
                startActivity(i);
                break;
            case R.id.txt_myCare:
                Intent n=new Intent(getActivity(),MyCareActivity.class);
                startActivity(n);
                break;
            default:
                break;

        }
    }



}
