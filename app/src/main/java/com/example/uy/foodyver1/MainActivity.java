package com.example.uy.foodyver1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.example.uy.foodyver1.View.Collection;
import com.example.uy.foodyver1.View.Home;
import com.example.uy.foodyver1.View.Notifications;
import com.example.uy.foodyver1.View.Person;
import com.example.uy.foodyver1.View.Search;

//TRANG LOAD QUÁN ĂN THEO LOẠI, TỈNH THÀNH, QUẬN HUYỆN LÀ   View/Home_Home
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    //declare tabhost
    TabHost mtabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Assign id to Tabhost
        mtabhost = (TabHost) findViewById(android.R.id.tabhost);

        // Creating tab menu.
        TabHost.TabSpec tab1 = mtabhost.newTabSpec("Tab 1");
        TabHost.TabSpec tab2 = mtabhost.newTabSpec("Tab 2");
        TabHost.TabSpec tab3 = mtabhost.newTabSpec("Tab 3");
        TabHost.TabSpec tab4 = mtabhost.newTabSpec("Tab 4");
        TabHost.TabSpec tab5 = mtabhost.newTabSpec("Tab 5");

        //Setting up tab
        tab1.setIndicator("", getResources().getDrawable(R.drawable.bgr_tab_1_main));
        tab2.setIndicator("", getResources().getDrawable(R.drawable.bgr_tab_2_main));
        tab3.setIndicator("", getResources().getDrawable(R.drawable.bgr_tab_3_main));
        tab4.setIndicator("", getResources().getDrawable(R.drawable.bgr_tab_4_main));
        tab5.setIndicator("", getResources().getDrawable(R.drawable.bgr_tab_5_main));

        //Set tab activity to tab menu.
        tab1.setContent(new Intent(this,Home.class));
        tab2.setContent(new Intent(this,Collection.class));
        tab3.setContent(new Intent(this,Search.class));
        tab4.setContent(new Intent(this,Notifications.class));
        tab5.setContent(new Intent(this,Person.class));

        //Adding tab to tabhost view.
        mtabhost.addTab(tab1);
        mtabhost.addTab(tab2);
        mtabhost.addTab(tab3);
        mtabhost.addTab(tab4);
        mtabhost.addTab(tab5);

    }
}
