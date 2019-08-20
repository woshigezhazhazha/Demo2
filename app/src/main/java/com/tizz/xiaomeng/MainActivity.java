package com.tizz.xiaomeng;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tizz.xiaomeng.UI.Meng.QuanActivity;
import com.tizz.xiaomeng.UI.discovery_fragment.SearchActivity;
import com.tizz.xiaomeng.UI.discovery_fragment.discovery_fg;
import com.tizz.xiaomeng.UI.me_fragment.me_fg;
import com.tizz.xiaomeng.UI.msg_fg;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Fragment fg1,fg2,fg3;
    private ImageView search;
    private ImageView add;
    private PopupWindow mPopupWindow;
    private TextView popCreateMeng;
    private String[] titles={"消息","发现","我"};
    private List<Fragment> list;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        initTabViews();
    }

    public void initViews(){
        add=(ImageView)findViewById(R.id.img_add);
        search=(ImageView)findViewById(R.id.img_search);
        search.setOnClickListener(this);
        add.setOnClickListener(this);
    }


    public void initTabViews(){
        fg1=new msg_fg();
        fg2=new discovery_fg();
        fg3=new me_fg();
        viewPager=(ViewPager)findViewById(R.id.main_viewpager);
        tabLayout=(TabLayout)findViewById(R.id.tab_title);
        list=new ArrayList<>();
        list.add(fg1);
        list.add(fg2);
        list.add(fg3);
        adapter=new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_add:
                initPopWindow();
                break;
            case R.id.pop_createMeng:
                Intent m=new Intent(MainActivity.this, QuanActivity.class);
                startActivity(m);
                mPopupWindow.dismiss();
                break;
            case R.id.img_search:
                Intent n=new Intent(MainActivity.this, SearchActivity.class);
                startActivity(n);
                break;
        }
    }

    public void initPopWindow(){
        View parent=View.inflate(this,R.layout.common_title_layout,null);
        View convertView= LayoutInflater.from(MainActivity.this).inflate(R.layout.title_popup,null);
        mPopupWindow=new PopupWindow(convertView);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setBackgroundAlpha(0.6f);

        popCreateMeng=(TextView)convertView.findViewById(R.id.pop_createMeng);
        popCreateMeng.setOnClickListener(this);

        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(parent, Gravity.RIGHT|Gravity.TOP,
                0,0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });
    }

    //设置背景透明度
    public void setBackgroundAlpha(float bgAlpha) {
        Context mContext=this;
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        //获取page对应的title
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
