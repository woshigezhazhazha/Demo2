package com.tizz.xiaomeng.UI.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tizz.xiaomeng.R;

/**
 * Created by 13371 on 2017/8/4.
 */

public class fragment_msg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fg_msg,container,false);
        return view;
    }
}
