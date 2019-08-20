package com.tizz.xiaomeng.UI.Meng;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tizz.xiaomeng.Adapter.ViewPagerAdapter;
import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.Util.EmotionUtil;
import com.tizz.xiaomeng.Widget.ChatEmotionFragment;
import com.tizz.xiaomeng.Widget.EmotionInputDetector;
import com.tizz.xiaomeng.Widget.NoScrollViewPager;

import java.util.ArrayList;


public class chat_fragment extends Fragment implements View.OnTouchListener{


    private TextView chatList;
    private EditText editText;
    private ImageView emotionButton;
    private Button btnSend;
    private NoScrollViewPager viewPager;
    private RelativeLayout emotionLayout;

    private EmotionInputDetector mDetector;
    private ArrayList<Fragment> fragments;
    private ChatEmotionFragment chatEmotionFragment;
    private ViewPagerAdapter adapter;
    private LinearLayout linearLayout;
    private Context context;
    private Fragment fragment=this;


    private View layout;
    private Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        if (layout==null){
            activity=this.getActivity();
            layout=activity.getLayoutInflater().inflate(R.layout.chat_fragment,null);

            initViews();

        }else{
            ViewGroup parent=(ViewGroup)layout.getParent();
            if (parent!=null){
                parent.removeView(parent);
            }
        }
        return layout;

    }

    private void initViews(){

        chatList=(TextView)layout.findViewById(R.id.chat_list);
        editText=(EditText)layout.findViewById(R.id.et_chatInfo);
        emotionButton=(ImageView) layout.findViewById(R.id.iv_emotion);
        btnSend=(Button)layout.findViewById(R.id.btn_sendInfo);
        viewPager=(NoScrollViewPager)layout.findViewById(R.id.emotion_viewpager);
        emotionLayout=(RelativeLayout)layout.findViewById(R.id.emotion_icons);
        linearLayout=(LinearLayout)layout.findViewById(R.id.ll_chatAty);
        linearLayout.setOnTouchListener(this);
        fragments=new ArrayList<>();
        chatEmotionFragment=new ChatEmotionFragment();
        fragments.add(chatEmotionFragment);
        adapter=new ViewPagerAdapter(getChildFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

        mDetector=EmotionInputDetector.with(activity)
                .setEmotionView(emotionLayout)
                .setViewPager(viewPager)
                .bindToContent(chatList)
                .bindToEditText(editText)
                .bindToEmotionButton(emotionButton)
                .bindToSendButton(btnSend)
                .build();

        EmotionUtil emotionUtil=EmotionUtil.getInstance(activity);
        emotionUtil.attachToEditText(editText);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.requestFocus();

        //关闭输入法
        InputMethodManager imm=(InputMethodManager)activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(linearLayout.getWindowToken(),0);
        return false;
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
    }

}
