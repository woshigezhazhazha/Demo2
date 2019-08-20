package com.tizz.xiaomeng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.tizz.xiaomeng.R;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by 13371 on 2017/7/16.
 */

public class newMessageAdapter extends BaseAdapter {

    protected Context context;
    private List<EMConversation> conversationList;
    private NetClient netClient;
    private String userId;
    public publicMsgInfo publicMsg=null;
    private Hashtable<String,String> chatRecord=new Hashtable<String, String>();

    public newMessageAdapter(Context ctx,List<EMConversation> objects){
        context=ctx;
        conversationList=objects;
        netClient=new netClient(ctx);
        userId=UserUtils.getUserId(context);
    }

    public void setPublicMsg(publicMsgInfo Msg){publicMsg=Msg;}

    public publicMsgInfo getPublicMsg(){return publicMsg;}

    public Hashtable<String,String> getChatRecord(){return chatRecord;}

    @Override
    public int getCount(){return conversationList.size();}

    @Override
    public Object getItem(int position){return null;}

    @Override
    public long getItemId(int position){return position;}

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate
                    (R.layout.layout_item_msg,parent,false);

        }
    }

}
