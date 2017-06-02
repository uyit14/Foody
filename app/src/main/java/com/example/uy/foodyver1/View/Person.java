package com.example.uy.foodyver1.View;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.uy.foodyver1.R;

/**
 * Created by UY on 3/14/2017.
 */

public class Person extends Activity {


    //Set for list 1
    String values1[] = {"Lịch sử đặt chỗ", "Lịch sử đặt giao hàng", "Lịch sử Coupon", "Sử dụng Ecard", "Lịch sử Eat-in/Take away"};
    int icons1[] = {R.drawable.ic_dc, R.drawable.ic_dgh, R.drawable.ic_coupon, R.drawable.ic_ec, R.drawable.ic_pos, };
    ListView list_person1;

    //Set for list 2
    String values2[] = {"Thông tin & liên hệ", "Tiền thưởng", "Thanh toán", "Thiết lập tài khoản"};
    int[] icons2 = {R.drawable.ic_call, R.drawable.ic_tienthuong, R.drawable.ic_thanhtoan, R.drawable.ic_tltk};
    ListView list_person2;

    //Set for list 3
    String values3[] = {"Mời bạn bè", "Góp ý", "Cài đặt chung"};
    int[] icons3 = {R.drawable.ic_moibb, R.drawable.ic_gopy, R.drawable.ic_tltk};
    ListView list_person3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person);

    }
}
