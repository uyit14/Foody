package com.example.uy.foodyver1.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by UY on 3/14/2017.
 */

public class Home_PagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    //Class này chĩ có giao diện trống chứ chưa làm gì liên quan Load hay chức năng
    public Home_PagerAdapter(FragmentManager fm, List<Fragment> fragments){
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
