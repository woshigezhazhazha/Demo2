package com.tizz.xiaomeng.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 13371 on 2017/8/28.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;
    String[] titles;
   public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list){
        super(fm);
        this.list=list;
    }

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        return list.get(position);
    }

    @Override
    public int getCount(){
        return list!=null?list.size():0;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return titles[position];
    }

}
