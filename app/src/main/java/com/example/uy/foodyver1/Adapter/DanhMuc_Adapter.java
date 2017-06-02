package com.example.uy.foodyver1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uy.foodyver1.Model.DanhMuc_Model;
import com.example.uy.foodyver1.R;
import com.example.uy.foodyver1.View.Home;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by UY on 5/8/2017.
 */

public class DanhMuc_Adapter extends BaseAdapter {
    Context context;
    ArrayList<DanhMuc_Model> listDM;

    public DanhMuc_Adapter(Context context, ArrayList<DanhMuc_Model> listDM) {
        this.context = context;
        this.listDM = listDM;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listDM.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        final LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.home_odau_danhmuc_inside, null);
        //Ánh xạ
        TextView tvIDMD = (TextView) v.findViewById(R.id.tvIDODAUDM);
        TextView tvtenDM = (TextView) v.findViewById(R.id.tvODauDanhMuc);
        ImageView ImgDM = (ImageView) v.findViewById(R.id.imgODauDanhMuc);
        //
        DanhMuc_Model danhMucModel = listDM.get(position);
        //Settext cho TextView là ID Danh mục
        tvIDMD.setText(danhMucModel.ID+"");
        //Settext cho TextView là Tên Danh mục
        tvtenDM.setText(danhMucModel.Ten);
        //Dùng thư viện Picasso để Load ảnh theo link sau đó đưa vào ImageView
        Picasso.with(context).load(danhMucModel.HinhAnh).into(ImgDM);


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chuyển Sang Class Home
                Intent intent = new Intent(context, Home.class);
                //Truyền ID Danh Mục đi để Load quán ăn theo Danh mục (Sang trọng, Buffe, ...)
                intent.putExtra("IDDM", ""+listDM.get(position).ID);
                //Tuyền tên DM đi để Set tabhost là tên Danh mục khi danh mục đc click
                intent.putExtra("TenDM", ""+listDM.get(position).Ten);
                context.startActivity(intent);
            }
        });

        return v;
    }
}
