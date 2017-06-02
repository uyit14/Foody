package com.example.uy.foodyver1.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.uy.foodyver1.MainActivity;
import com.example.uy.foodyver1.R;

/**
 * Created by UY on 5/9/2017.
 */

public class Welcome extends AppCompatActivity {
    //Trang chay dau tien cua chuong trinh
    private static int SPLASH_TIME_OUT = 2000; //thoi gian ket thuc viec chay

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        // chay het thoi gian 2s tu dong chuyen sang trang MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(Welcome.this, MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}