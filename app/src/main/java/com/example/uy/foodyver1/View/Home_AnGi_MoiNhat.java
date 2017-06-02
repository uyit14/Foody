package com.example.uy.foodyver1.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uy.foodyver1.R;

/**
 * Created by UY on 3/15/2017.
 */

public class Home_AnGi_MoiNhat extends Fragment {


    public static String [] titlesList = {"Mới nhất", "Gần tôi", "Xem nhiều", "Du khách"};
    public static int [] images = {R.drawable.ic_new1, R.drawable.ic_gantoi, R.drawable.ic_new1, R.drawable.ic_dukhach};
    ListView lv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.home_angi_moinhat, container, false);
        return v;
    }
}
