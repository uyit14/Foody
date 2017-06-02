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

import com.example.uy.foodyver1.Adapter.TinhThanh_Adapter;
import com.example.uy.foodyver1.R;

/**
 * Created by UY on 3/14/2017.
 */

@SuppressWarnings("deprecation")
public class Home_ODau extends Fragment {
    private FragmentTabHost mtabhost;
    TinhThanh_Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mtabhost = new FragmentTabHost(getActivity());
        mtabhost.setup(getActivity(), getChildFragmentManager(), R.layout.home_odau_fragment);

        Bundle arg1 = new Bundle();
        arg1.putInt("arg1 Tab 1", 1);
        Bundle arg2 = new Bundle();
        arg2.putInt("arg2 Tab 2", 2);
        Bundle arg3 = new Bundle();
        arg3.putInt("arg Tab 3", 3);
        Bundle arg4 = new Bundle();
        arg4.putInt("arg Tab 4", 4);


        //Set 4 tab Mới nhất, danh mục, tỉnh thành
        mtabhost.addTab(mtabhost.newTabSpec("tab1").setIndicator("Mới nhất"),
                Home_ODau_MoiNhat.class, arg1);
        mtabhost.addTab(mtabhost.newTabSpec("tab2").setIndicator("Danh mục"),
                Home_ODau_DanhMuc.class, arg2);
        mtabhost.addTab(mtabhost.newTabSpec("tab3").setIndicator("TP.HCM"),
                Home_ODau_TinhThanh.class, arg3);
        //Đây là tab hiện quán ăn, sẽ đc GONE(ẩn) đi, khi nào cần thiết mới đc gọi ra
        mtabhost.addTab(mtabhost.newTabSpec("tab4").setIndicator("Home"),
                Home_Home.class, arg4);

        for(int i=0; i<mtabhost.getTabWidget().getChildCount(); i++){
            //Ép về texrview  bằng cách gán title là texview
            TextView tv = (TextView) mtabhost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            //Set chứ thường
            tv.setAllCaps(false);

            mtabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#ffffffff")); //unselect
            tv.setTextSize(16);
        }
        mtabhost.getTabWidget().getChildAt(3).setVisibility(View.GONE);
        mtabhost.setCurrentTab(3); //tab first show
        setOnClickWidgetMoiNhat();
        setOnClickWidgetDanhMuc();
        setOnClickWidgetTinhThanh();

        //set ten Danh muc khi click vào list danh muc
        Intent intent = getActivity().getIntent();

        //Set Tab 0 là item(Mới nhất, gần tôi...) khi click item bat ki trong tab 0
        String TenNew = intent.getStringExtra("TENNEW");
        if(TenNew!=null){
            TextView tv1 = (TextView) mtabhost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
            //Toast.makeText(getContext(), ""+TenNew, Toast.LENGTH_SHORT).show();
            tv1.setText(TenNew);
        }
        //Set Tab 1 là item(Sang trọng, nhà hàng, ăn vặt,  ...) khi click item bat ki trong tab 1
        String TenDanhMuc = intent.getStringExtra("TenDM");
        if(TenDanhMuc!=null) {
            TextView tv = (TextView) mtabhost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
            tv.setText(TenDanhMuc);
        }
        //Set Tab 2 la ten Tinh Thanh khi click vao tinh Thanh Bat ki
        String tenTinhThanh = intent.getStringExtra("TenTinhThanh");
        if(tenTinhThanh!=null){
            TextView tv = (TextView) mtabhost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
            tv.setText(tenTinhThanh);
            mtabhost.setCurrentTab(2);
        }

        //Set Tab 2 la ten Quan, Huyen tih click vao quan huyen bat ki
        String tenQuanHuyen = intent.getStringExtra("TenQH");
        if(tenQuanHuyen!=null){
            TextView tv = (TextView) mtabhost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
            tv.setText(tenQuanHuyen);
            mtabhost.setCurrentTab(3);
        }
        return mtabhost;
    }

    //Set sự kiện click cho Mới nhất, gần tôi, ...
    public void setOnClickWidgetMoiNhat(){
        mtabhost.getTabWidget().getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nếu Tab đó đang là CurrentTab thì hủy CurrentTab cho tab đó
                if(mtabhost.getTabWidget().getChildAt(0).isSelected()){
                    //Sau đó set CurrentTab cho Tab 3 (là tab chứa ds quán ăn)
                    mtabhost.setCurrentTab(3);
                    //Set màu cho Tab mới nhất giống tab Danh mục và tỉnh thành
                    mtabhost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else {
                    //Ngược lại nếu Tab Mới nhất không được CurrentTab, thì CurrentTab cho tab này
                    mtabhost.setCurrentTab(0);
                    //Set màu nên cho tab đang được CurrentTab
                    mtabhost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#eeeeef"));
                    //Các tab không đc CurrentTab thì màu nền khác
                    mtabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ffffff"));
                    mtabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }

        });

    }
    //Tương tự cho tab Danh mục và Tỉnh Thành
    public void setOnClickWidgetDanhMuc(){
        mtabhost.getTabWidget().getChildAt(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Nếu Tab đó đang là CurrentTab thì hủy CurrentTab cho tab đó
                if(mtabhost.getTabWidget().getChildAt(1).isSelected()){
                    //Sau đó set CurrentTab cho Tab 3 (là tab chứa ds quán ăn)
                    mtabhost.setCurrentTab(3);
                    mtabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else{
                    //Ngược lại nếu Tab Mới nhất không được CurrentTab, thì CurrentTab cho tab này
                    mtabhost.setCurrentTab(1);
                    //Set màu nên cho tab đang được CurrentTab
                    mtabhost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#eeeeef"));
                    //Các tab không đc CurrentTab thì màu nền khác
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
                    //Nếu Tab đó đang là CurrentTab thì hủy CurrentTab cho tab đó
                    mtabhost.setCurrentTab(3);
                    mtabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else{
                    //Ngược lại nếu Tab Mới nhất không được CurrentTab, thì CurrentTab cho tab này
                    mtabhost.setCurrentTab(2);
                    //Set màu nên cho tab đang được CurrentTab
                    mtabhost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#eeeeef"));
                    //Các tab không đc CurrentTab thì màu nền khác
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
