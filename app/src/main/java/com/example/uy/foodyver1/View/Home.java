package com.example.uy.foodyver1.View;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.uy.foodyver1.Adapter.Home_PagerAdapter;
import com.example.uy.foodyver1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UY on 3/14/2017.
 */

public class Home extends FragmentActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    //declear tabhost and viewpager
    private TabHost mTabHost;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);;

        intialiseViewPager();
        initialiseTabHost();
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }


    //
    //Initialise ViewPager (để kéo qua kéo lại)
    private void intialiseViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.wiewpager);
        List<Fragment> listFragments = new ArrayList<Fragment>();
        //add frament An gi va O Dau vao list
        listFragments.add(new Home_ODau());
        listFragments.add(new Home_AnGi());
        Home_PagerAdapter pagerAdapter = new Home_PagerAdapter(getSupportFragmentManager(), listFragments);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    //
    //Initialise the tab host
    private void initialiseTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        String[] tabName = {"Ở đâu", "Ăn gì"};
        for(int i=0;i<tabName.length;i++){
            TabHost.TabSpec tabSpec;
            tabSpec = mTabHost.newTabSpec(tabName[i]);
            tabSpec.setIndicator(tabName[i]);
            tabSpec.setContent(new TabFactory(getApplicationContext()));
            mTabHost.addTab(tabSpec);
        }
        mTabHost.setOnTabChangedListener(this);
        mTabHost.getTabWidget().getChildAt(0).setSelected(true);
        TabWidget widget = mTabHost.getTabWidget();
        for(int i=0; i<mTabHost.getTabWidget().getChildCount(); i++){
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setAllCaps(false);
            tv.setTextSize(16);
                tv.setTextColor(Color.parseColor("#000000"));
            //selected unselect for tabhost
            widget.getChildAt(i).setBackgroundResource(R.drawable.home_angiodau_design);
        }
    }

    //
    public class TabFactory implements TabHost.TabContentFactory{
        private final Context mContext;
        public TabFactory(Context context){
            mContext = context;
        }
        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    //
    public void onTabChanged(String tag) {
        //TabInfo newTab = this.mapTabInfo.get(tag);
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    //
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        // TODO Auto-generated method stub

    }

    //
    @Override
    public void onPageSelected(int position) {
        // TODO Auto-generated method stub
        this.mTabHost.setCurrentTab(position);
    }

    //
    @Override
    public void onPageScrollStateChanged(int state) {
        // TODO Auto-generated method stub

    }
}
