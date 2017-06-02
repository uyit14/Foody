package com.example.uy.foodyver1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by UY on 3/15/2017.
 */

//Clas này chĩ show giao diện chứ chưa làm chức năng hay Load gì hết!
public class Collection_PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public Collection_PagerAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position){
        return this.fragments.get(position);
    }

    @Override
    public  int getCount(){
        return this.fragments.size();
    }
}
