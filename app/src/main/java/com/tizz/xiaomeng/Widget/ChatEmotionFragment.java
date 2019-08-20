package com.tizz.xiaomeng.Widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.tizz.xiaomeng.Adapter.EmotionGridViewAdapter;
import com.tizz.xiaomeng.Adapter.EmotionPagerAdapter;
import com.tizz.xiaomeng.Base.BaseApplication;
import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.Util.EmotionUtil;
import com.tizz.xiaomeng.Util.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 13371 on 2017/8/27.
 */

public class ChatEmotionFragment extends Fragment {
    @Bind(R.id.fragment_chat_vp)
    ViewPager fragmentChatVp;
    @Bind(R.id.fragment_chat_group)
    IndicatorView fragmentChatGroup;

    private View rootView;
    private EmotionPagerAdapter emotionPagerAdapter;


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup
                             container, @Nullable Bundle savedInstanceState){
        if (rootView==null){
            rootView=inflater.inflate(R.layout.fragment_chat_emotion,container,false);
            ButterKnife.bind(this,rootView);
            initWidgets();
        }
        return rootView;
    }

    private void initWidgets(){

        fragmentChatVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            int oldPagePos=0;

            @Override
            public void onPageScrolled(int position,float positionOffset,int positionPixels){

            }

            @Override
            public void onPageSelected(int position){
                fragmentChatGroup.playByStartPointToNext(oldPagePos,position);
                oldPagePos=position;
            }

            @Override
            public void onPageScrollStateChanged(int state){

            }
        });
        initEmotion();

    }



    private void initEmotion(){
        int screenWidth= BaseApplication.screenWidth;
        int space= Utils.dp2px(getContext(),12);
        int itemWidth=(screenWidth-space*8)/7;
        int gvHeight=itemWidth*3+space*6;

        List<GridView> emotionViews=new ArrayList<>();
        List<String> emotionNames=new ArrayList<>();

        for (String emojiName: EmotionUtil.EMOTION_STATIC_MAP.keySet()){
            emotionNames.add(emojiName);

            if (emotionNames.size()==23){
                GridView gv=createEmotionGridView(emotionNames,screenWidth,space,
                        itemWidth,gvHeight);
                emotionViews.add(gv);
                emotionNames=new ArrayList<>();
            }
        }
        if (emotionNames.size()>0){
            GridView gv=createEmotionGridView(emotionNames,screenWidth,space,
                    itemWidth,gvHeight);
            emotionViews.add(gv);
        }
        fragmentChatGroup.initIndicator(emotionViews.size());
        emotionPagerAdapter=new EmotionPagerAdapter(emotionViews);
        fragmentChatVp.setAdapter(emotionPagerAdapter);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(
                screenWidth,gvHeight);
        fragmentChatVp.setLayoutParams(params);
    }

    private GridView createEmotionGridView(List<String> emotionNames,int gvWidth,int
                                       padding,int itemWidth,int gvHeight){
        GridView gv=new GridView(getContext());
        gv.setSelector(android.R.color.transparent);
        gv.setNumColumns(8);
        gv.setPadding(padding,padding,padding,padding);
        gv.setVerticalSpacing(padding*2);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(gvWidth,gvHeight);
        gv.setLayoutParams(params);

        EmotionGridViewAdapter adapter=new EmotionGridViewAdapter(getContext(),
                emotionNames,itemWidth);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(EmotionUtil.getInstance
                (getContext()).getOnItemClickListener());
        return gv;
    }
}
