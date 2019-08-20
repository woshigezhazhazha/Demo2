package com.tizz.xiaomeng.UI.Meng;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.tizz.xiaomeng.R;

import java.util.ArrayList;
import java.util.List;

public class QuanActivity extends FragmentActivity implements View.OnClickListener{

    private TextView title;
    private LinearLayout back;
    private LinearLayout more;
    private PopupWindow mPopupWindow;
    private TextView care;
    private TextView cancelCare;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list;
    private String[] titles={"帖子","聊天"};
    private MyViewPagerAdapter adapter;
    Fragment f1=new tiezi_fragment();
    Fragment f2=new chat_fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan);
        initViews();
        setOnListener();
    }

    public void initViews(){
        Intent intent=getIntent();
        String str=intent.getStringExtra("QuanName");
        title=(TextView)findViewById(R.id.txt_title);
        title.setText(str);

        back=(LinearLayout)findViewById(R.id.img_back);
        more=(LinearLayout)findViewById(R.id.ll_more);
        more.setVisibility(View.VISIBLE);
        viewPager=(ViewPager)findViewById(R.id.vp_quan);
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);

        list=new ArrayList<>();

        list.add(f1);
        list.add(f2);

        adapter=new MyViewPagerAdapter(
                getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    public void setOnListener(){
        back.setOnClickListener(this);
        more.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.img_back:
                QuanActivity.this.finish();
                break;
            case R.id.ll_more:
                showPopupWindow();
                break;
            case R.id.quan_care:
                Toast toast=Toast.makeText(QuanActivity.this,"正在关注...",
                        Toast.LENGTH_SHORT);
                toast.show();
                mPopupWindow.dismiss();
                break;
            case R.id.quan_cancelCare:
                Toast toast1=Toast.makeText(QuanActivity.this,"正在取消关注...",
                        Toast.LENGTH_SHORT);
                toast1.show();
                mPopupWindow.dismiss();
                break;
        }
    }



    public void showPopupWindow(){
        View parent=View.inflate(this,R.layout.common_title_layout,null);
        View convertView= LayoutInflater.from(QuanActivity.this).inflate(R.layout.title_popup_quan,null);
        mPopupWindow=new PopupWindow(convertView);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setBackgroundAlpha(0.6f);

        care=(TextView)convertView.findViewById(R.id.quan_care);
        cancelCare=(TextView)convertView.findViewById(R.id.quan_cancelCare);
        care.setOnClickListener(this);
        cancelCare.setOnClickListener(this);

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

    //适配器
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
