package com.tizz.xiaomeng.UI.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tizz.xiaomeng.Constants;
import com.tizz.xiaomeng.GlobalParams;
import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.UI.AlbumActivity;
import com.tizz.xiaomeng.UI.MyCareAty;
import com.tizz.xiaomeng.UI.MyInfoActivity;
import com.tizz.xiaomeng.UI.SettingActivity;
import com.tizz.xiaomeng.UI.ShowAboutUsAty;
import com.tizz.xiaomeng.utils.Utils;
import com.tizz.xiaomeng.utils.userUtils;

/**
 * Created by 13371 on 2017/7/15.
 */

public class fragment_me extends Fragment implements View.OnClickListener {

    private Activity ctx;
    private View layout;
    private TextView tvName,tvAccount,;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if(layout==null){
            ctx=this.getActivity();
            layout=ctx.getLayoutInflater().inflate(R.layout.fragment_me,null);
            initViews();
            initData();
            setOnListener();
        }else{
            ViewGroup parent=(ViewGroup)layout.getParent();
            if(parent!=null){
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void setOnListener(){

        layout.findViewById(R.id.view_user).setOnClickListener(this);
        layout.findViewById(R.id.txt_album).setOnClickListener(this);
        layout.findViewById(R.id.txt_setting).setOnClickListener(this);
        layout.findViewById(R.id.txt_aboutUs).setOnClickListener(this);
        layout.findViewById(R.id.txt_myLike).setOnClickListener(this);
    }

    private void initViews(){
        tvName=(TextView)layout.findViewById(R.id.tvname);
        tvAccount=(TextView)layout.findViewById(R.id.tvmsg);
        String id=Utils.getValue(getActivity(), Constants.User_ID);
        tvAccount.setText(getString(R.string.xiaomeng_id)+":"+id);
        if(GlobalParams.QuanInfos!=null){
            String name= userUtils.getUserName(ctx);
            if(name!=null&&!TextUtils.isEmpty(name)){
                tvName.setText(name);
            }
        }


    }

    private void initData(){}



    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.view_user:
                Utils.start_Activity(getActivity(),MyInfoActivity.class);
                break;
            case R.id.txt_album:
                Utils.start_Activity(getActivity(),AlbumActivity.class);
                break;
            case R.id.txt_setting:
                Utils.start_Activity(getActivity(),SettingActivity.class);
                break;
            case R.id.txt_aboutUs:
                Utils.start_Activity(getActivity(),ShowAboutUsAty.class);
                break;
            case R.id.txt_myLike:
                Utils.start_Activity(getActivity(),MyCareAty.class);
                break;
            default:
                break;
        }
    }
}
