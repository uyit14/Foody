package com.example.uy.foodyver1.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uy.foodyver1.R;

/**
 * Created by UY on 3/14/2017.
 */
//Class này chĩ gọi giao diện
public class Home_AnGi extends Fragment {
    private FragmentTabHost mtabhost;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View v = inflater.inflate(R.layout.home_odau_fragment, container, false);

        mtabhost = new FragmentTabHost(getActivity());
        mtabhost.setup(getActivity(), getChildFragmentManager(), R.layout.home_angi_fragment);
        Bundle arg1 = new Bundle();
        arg1.putInt("arg1 Tab 1", 1);
        Bundle arg2 = new Bundle();
        arg2.putInt("arg2 Tab 2", 2);
        Bundle arg3 = new Bundle();
        arg3.putInt("arg Tab 3", 3);
        Bundle arg4 = new Bundle();
        arg4.putInt("arg Tab 4", 4);

        //Set tab activity to tab menu
        mtabhost.addTab(mtabhost.newTabSpec("tab1").setIndicator("Mới nhất"),
                Home_AnGi_MoiNhat.class, arg1);
        mtabhost.addTab(mtabhost.newTabSpec("tab2").setIndicator("Danh mục"),
                Home_AnGi_DanhMuc.class, arg2);
        mtabhost.addTab(mtabhost.newTabSpec("tab3").setIndicator("Tỉnh thành"),
                Home_AnGi_TinhThanh.class, arg3);
        mtabhost.addTab(mtabhost.newTabSpec("tab4").setIndicator("Home"),
                Home_Home.class, arg4);

        for(int i=0; i<mtabhost.getTabWidget().getChildCount(); i++){
            TextView tv = (TextView) mtabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setAllCaps(false);

            mtabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffffff"));//unselect
            tv.setTextSize(16);

        }
        mtabhost.getTabWidget().getChildAt(3).setVisibility(View.GONE);
        mtabhost.setCurrentTab(3);
        setOnClickWidgetMoiNhat();
        setOnClickWidgetDanhMuc();
        setOnClickWidgetTinhThanh();

        //Khi vao danh muc, doi ten
        Intent intent = getActivity().getIntent();
        //Intent intent = getIntent();
        String TenDanhMuc = intent.getStringExtra("TenAnGiDM");
        if(TenDanhMuc!=null) {
            TextView tv = (TextView) mtabhost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
            tv.setText(TenDanhMuc);
        }
        return mtabhost;
    }

    //set onclick for tab MoiNhat
    public void setOnClickWidgetMoiNhat(){
        mtabhost.getTabWidget().getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mtabhost.getTabWidget().getChildAt(0).isSelected()){
                    mtabhost.setCurrentTab(3);
                    mtabhost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else {
                    mtabhost.setCurrentTab(0);
                    mtabhost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#eeeeef"));
                    mtabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ffffff"));
                    mtabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }

        });

    }
    //set onclick for tab Danh muc
    public void setOnClickWidgetDanhMuc(){
        mtabhost.getTabWidget().getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mtabhost.getTabWidget().getChildAt(1).isSelected()){
                    mtabhost.setCurrentTab(3);
                    mtabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else{
                    mtabhost.setCurrentTab(1);
                    mtabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#eeeeef"));
                    mtabhost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffffff"));
                    mtabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
    }

    //set onclick for tab Tinh Thanh
    public void setOnClickWidgetTinhThanh(){
        mtabhost.getTabWidget().getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mtabhost.getTabWidget().getChildAt(2).isSelected()){
                    mtabhost.setCurrentTab(3);
                    mtabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else{
                    mtabhost.setCurrentTab(2);
                    mtabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#eeeeef"));
                    mtabhost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffffff"));
                    mtabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mtabhost = null;
    }
}
