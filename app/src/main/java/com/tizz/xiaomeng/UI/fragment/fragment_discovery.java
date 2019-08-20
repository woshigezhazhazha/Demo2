package com.tizz.xiaomeng.UI.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.UI.nearMengActivity;
import com.tizz.xiaomeng.UI.nearQuanActivity;
import com.tizz.xiaomeng.utils.Utils;

/**
 * Created by 13371 on 2017/7/15.
 */

public class fragment_discovery extends Fragment implements View.OnClickListener{

    private Activity ctx;
    private View layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if(layout==null){
            ctx=this.getActivity();
            layout=ctx.getLayoutInflater().inflate(R.layout.fragment_discovery,null);
            initViews();
            initData();
            setOnListener();
        }else{
            ViewGroup parent=(ViewGroup) layout.getParent();
            if(parent!=null){
                parent.removeView(layout);
            }
        }
        return layout;
    }

    private void initViews(){

    }

    private void initData(){

    }

    private void setOnListener(){
        layout.findViewById(R.id.txt_nearMeng).setOnClickListener(this);
        layout.findViewById(R.id.txt_nearQuan).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.txt_nearMeng:
                Utils.start_Activity(getActivity(),nearMengActivity.class);
                break;
            case R.id.txt_nearQuan:
                Utils.start_Activity(getActivity(),nearQuanActivity.class);
                break;
        }
    }


}
