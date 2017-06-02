package com.example.uy.foodyver1.View;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.uy.foodyver1.R;

/**
 * Created by UY on 3/14/2017.
 */

@SuppressWarnings("deprecation")
public class Search extends TabActivity {

    TabHost mtabhost;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

    }

}
