package com.tizz.xiaomeng.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tizz.xiaomeng.R;
import com.tizz.xiaomeng.Util.EmotionUtil;

import java.util.List;

/**
 * Created by 13371 on 2017/8/28.
 */

public class EmotionGridViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> emotionNames;
    private int itemWidth;

    public EmotionGridViewAdapter(Context context,List<String> emotionNames, int itemWidth){
        this.context=context;
        this.emotionNames=emotionNames;
        this.itemWidth=itemWidth;
    }

    @Override
    public int getCount(){
        return emotionNames.size()+1;
    }

    @Override
    public String getItem(int position){
        return  emotionNames.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView iv_emotion=new ImageView(context);
        iv_emotion.setPadding(itemWidth/8,itemWidth/8,itemWidth/8,itemWidth/8);
        AbsListView.LayoutParams params=new AbsListView.LayoutParams(itemWidth,itemWidth);
        iv_emotion.setLayoutParams(params);

        if (position==getCount()-1){
            iv_emotion.setImageResource(R.drawable.compose_emotion_delete);
        }else{
            String emotionName=emotionNames.get(position);
            iv_emotion.setImageResource(EmotionUtil.EMOTION_STATIC_MAP.get(emotionName));
        }
        return iv_emotion;
    }


}