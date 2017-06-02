package com.example.uy.foodyver1.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.uy.foodyver1.R;

/**
 * Created by UY on 3/15/2017.
 */

public class Home_AnGi_TinhThanh extends Fragment {
    Button btnDoiTinhThanh;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.home_angi_tinhthanh, container, false);
        btnDoiTinhThanh = (Button) v.findViewById(R.id.btnDoiTinhThanh);

        btnDoiTinhThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TinhThanh.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
