package com.tizz.xiaomeng.UI.discovery_fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tizz.xiaomeng.R;

/**
 * Created by 13371 on 2017/8/5.
 */

public class discovery_fg extends Fragment implements View.OnClickListener{

    private Activity ctx;
    private View layout;
    private TextView nearMeng,nearQuan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if(layout==null){
            ctx=this.getActivity();
            layout=ctx.getLayoutInflater().inflate(R.layout.discovery_fragment,null);

            initViews();
            setOnListener();
            initDrawable();
        }else{
            ViewGroup parent=(ViewGroup)layout.getParent();
            if(parent!=null){
                parent.removeView(parent);
            }
        }
        return layout;
    }

    public void initViews(){
        nearMeng=(TextView)layout.findViewById(R.id.txt_nearMeng);
        nearQuan=(TextView)layout.findViewById(R.id.txt_nearQuan);
    }

    public void initDrawable(){
        Drawable d1=getResources().getDrawable(R.drawable.icon_nearmeng);
        Drawable d2=getResources().getDrawable(R.drawable.icon_nearquan);
        d1.setBounds(0,0,80,80);
        d2.setBounds(0,0,80,80);
        nearMeng.setCompoundDrawables(d1,null,null,null);
        nearQuan.setCompoundDrawables(d2,null,null,null);
    }

    public void setOnListener(){
        nearMeng.setOnClickListener(this);
        nearQuan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.ll_search:
                Intent i=new Intent(getActivity(),SearchActivity.class);
                startActivity(i);
                break;
            case R.id.txt_nearMeng:
                Intent m=new Intent(getActivity(), NearMengActivity.class);
                startActivity(m);
                break;
            case R.id.txt_nearQuan:
                Intent n=new Intent(getActivity(), NearQuanActivity.class);
                startActivity(n);
                break;
            default:
                break;
        }
    }
}
