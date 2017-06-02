package com.example.uy.foodyver1.View;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.uy.foodyver1.Adapter.Collection_PagerAdapter;
import com.example.uy.foodyver1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UY on 3/15/2017.
 */
//TRANG LOAD QUÁN ĂN LÀ TRANG View/Home_Home

public class Collection extends AppCompatActivity implements ViewPager.OnPageChangeListener, TabHost.OnTabChangeListener {

    //Khai bao tabhost
    private TabHost mTabHost;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection);
        //Goi ham
        intialiseViewPager();
        initialiseTabHost();
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }


    //
    //Set viewpager (để kéo qua, kéo lại)
    private void intialiseViewPager() {
        //Khai bao Viewpager
        mViewPager = (ViewPager) findViewById(R.id.wiewpager);

        List<Fragment> listFragments = new ArrayList<Fragment>();
        //Add 2 viewpager vao list
        listFragments.add(new Collection_DiaDiem());
        listFragments.add(new Collection_HinhAnh());

        Collection_PagerAdapter pagerAdapter = new Collection_PagerAdapter(getSupportFragmentManager(), listFragments);

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(this);

    }

    //
    //Set up cho tabhost
    private void initialiseTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        //ten tab se hien ra de theo tac
        String[] tabName = {"Bộ sưu tập địa điểm", "Bộ sưu tập ảnh"};
        for(int i=0;i<tabName.length;i++){
            TabHost.TabSpec tabSpec;
            //tao tab host
            tabSpec = mTabHost.newTabSpec(tabName[i]);
            //cai dat cho tab host
            tabSpec.setIndicator(tabName[i]);
            //cai dat hoat dong cho tabhost
            tabSpec.setContent(new Collection.TabFactory(getApplicationContext()));
            //add tab
            mTabHost.addTab(tabSpec);
        }
        mTabHost.setOnTabChangedListener(this);
        TabWidget widget = mTabHost.getTabWidget();
        for(int i=0; i<mTabHost.getTabWidget().getChildCount(); i++){
            //Ep tab ve listview bang title
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            //Chu thuong
            tv.setAllCaps(false);
            //kieu chu
            tv.setTextSize(16);
            //Mau chu
            tv.setTextColor(Color.parseColor("#000000"));
            //design tab khi selected va unselected
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
