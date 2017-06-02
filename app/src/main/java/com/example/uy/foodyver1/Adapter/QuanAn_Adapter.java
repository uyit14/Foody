package com.example.uy.foodyver1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uy.foodyver1.Model.QuanAn_Model;
import com.example.uy.foodyver1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by UY on 5/8/2017.
 */

public class QuanAn_Adapter extends BaseAdapter {
    Context context;
    ArrayList<QuanAn_Model> listQuanAn;

    public QuanAn_Adapter(Context context, ArrayList<QuanAn_Model> listQuanAn) {
        this.context = context;
        this.listQuanAn = listQuanAn;
    }

    @Override
    public int getCount() {
        return listQuanAn.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.home_list_inside, null);
        //Ánh xạ cho textview và ImageView
        TextView tvIDCTDM = (TextView) v.findViewById(R.id.tvIDCTDanhMuc_inside);
        TextView tvTenDM = (TextView) v.findViewById(R.id.tvTenCTDanhMuc_inside);
        TextView tvDiem = (TextView) v.findViewById(R.id.tvdiem);
        ImageView ImgAnh = (ImageView)v.findViewById(R.id.Img_anh_inside);
        TextView tvAdress = (TextView)v.findViewById(R.id.tvAddress_inside);
        //
        QuanAn_Model model = listQuanAn.get(position);
        //Set text cho Textview là ID quán ăn, tên quán, ...
        tvIDCTDM.setText(model.ID+"");
        tvTenDM.setText(model.TenQuan);
        tvDiem.setText(model.Diem);
        tvAdress.setText(model.Ten);
        //Sử dụng thư viện Picasso để load ảnh từ link sau đó đưa vào ImageView
        Picasso.with(context).load(model.HinhAnh).into(ImgAnh);
        return v;
    }
}
