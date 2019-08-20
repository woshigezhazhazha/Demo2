package com.tizz.xiaomeng.UI.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.UI.MainActivity;
import com.tizz.xiaomeng.UI.MengActivity;
import com.tizz.xiaomeng.adapter.newMessageAdapter;
import com.tizz.xiaomeng.bean.MengMsgInfo;
import com.tizz.xiaomeng.utils.Utils;
import com.tizz.xiaomeng.utils.netUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

import static android.view.View.GONE;

I

/**
 * Created by 13371 on 2017/7/15.
 */

public class fragment_message extends Fragment implements OnClickListener,
        OnItemClickListener {

    private List<EMConversation> conversationList = new ArrayList<EMConversation>();
    private Activity ctx;
    private View layout, layout_head;
    private MainActivity parentActivity;
    private ListView lvContact;
    public TextView errorText;
    public RelativeLayout errorItem;
    private newMessageAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (layout == null) {
            ctx = this.getActivity();
            parentActivity = (MainActivity) getActivity();
            layout = ctx.getLayoutInflater().inflate(R.layout.fragment_message, null);
        }
        return layout;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_error_item:
                netUtils.openSetNetWork(getActivity());
                break;
            default:
                break;
        }
    }

    public void onItemClick(AdapterView<?> arg0,View arg1,int position,
                            long arg3){
        if(adapter.publicMsg!=null && position==0){
            Utils.start_Activity(getActivity(),MengActivity.class);
        }else{

        }
    }


}
