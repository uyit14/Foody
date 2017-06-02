package com.example.uy.foodyver1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uy.foodyver1.Model.QuanHuyen_Model;
import com.example.uy.foodyver1.R;
import com.example.uy.foodyver1.View.Home;

import java.util.ArrayList;

/**
 * Created by UY on 3/24/2017.
 */

public class QuanHuyen_Adapter extends BaseAdapter {
    Context context;
    ArrayList<QuanHuyen_Model> list;

    public QuanHuyen_Adapter(Context context, ArrayList<QuanHuyen_Model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Ánh xạ cho Textview
        View row = inflater.inflate(R.layout.home_odau_tinhthanh_inside, null);
        TextView tvIDQH = (TextView) row.findViewById(R.id.tv_IDQH);
        TextView tvTen = (TextView) row.findViewById(R.id.tv_tenQH);
        TextView tvID = (TextView) row.findViewById(R.id.tvIDTinh);
        //
        QuanHuyen_Model quanHuyenModel = list.get(position);
        //Set text cho textview
        tvIDQH.setText(quanHuyenModel.IDQH+"");
        tvTen.setText(quanHuyenModel.TenQH);
        tvID.setText(quanHuyenModel.ID+"");
        //Set One click for item
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Truyền đi ID quận Huyện khi quận huyện được click vào để Load quán ăn theo Quận Huyện
                Intent intent = new Intent(context, Home.class);
                intent.putExtra("IDQH", ""+list.get(position).IDQH);
                //Truyền đi tên Quận Huyện
                intent.putExtra("TenQH", ""+list.get(position).TenQH);
                context.startActivity(intent);
            }
        });

        return row;
    }
}
