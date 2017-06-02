package com.example.uy.foodyver1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uy.foodyver1.Model.TinhThanh_Model;
import com.example.uy.foodyver1.R;
import com.example.uy.foodyver1.View.Home;

import java.util.ArrayList;

/**
 * Created by UY on 3/21/2017.
 */

public class TinhThanh_Adapter extends BaseAdapter {

    Context context;
    ArrayList<TinhThanh_Model> listTinhThanh;

    public TinhThanh_Adapter(Context context, ArrayList<TinhThanh_Model> listTinhThanh) {
        this.context = context;
        this.listTinhThanh = listTinhThanh;
    }

    @Override
    public int getCount() {
        return listTinhThanh.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.btn_doitinhthanh_inside, null);
        //Ánh xạ textviews
        TextView tvID = (TextView) v.findViewById(R.id.tvid);
        TextView tvTen = (TextView) v.findViewById(R.id.tvtentinhthanh);


        TinhThanh_Model tinhThanhModel = listTinhThanh.get(position);
        //Set text texview
        tvTen.setText(tinhThanhModel.Ten);
        tvID.setText(tinhThanhModel.ID+"");

        //Set event click cho mỗi rowview khi đc click
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chuyển sang class Home
                Intent intent = new Intent(context, Home.class);
                //Truyền đi ID tỉnh thành để Load Quận, Huyện theo ID Tỉnh Thành
                intent.putExtra("IDTinhThanh", ""+listTinhThanh.get(position).ID);
                //Truyền đi tên Tỉnh Thành để set cho tabhost khi tỉnh thành đc chọn
                intent.putExtra("TenTinhThanh", listTinhThanh.get(position).Ten);
                context.startActivity(intent);
            }
        });
        return v;
    }
}
