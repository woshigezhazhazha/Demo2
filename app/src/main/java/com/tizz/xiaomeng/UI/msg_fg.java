package com.tizz.xiaomeng.UI;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tizz.xiaomeng.R;

/**
 * Created by 13371 on 2017/8/5.
 */

public class msg_fg extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.msg_fragment,container,false);
        return view;
    }
}
