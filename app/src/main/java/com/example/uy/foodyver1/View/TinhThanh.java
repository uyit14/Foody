package com.example.uy.foodyver1.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.uy.foodyver1.Adapter.TinhThanh_Adapter;
import com.example.uy.foodyver1.Model.TinhThanh_Model;
import com.example.uy.foodyver1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by UY on 3/21/2017.
 */
//TRANG LOAD QUÁN ĂN THEO LOẠI, TỈNH THÀNH, QUẬN HUYỆN LÀ   View/Home_Home

public class TinhThanh extends AppCompatActivity{

    ListView listView;
    Button btn_xong;
    ArrayList<TinhThanh_Model> arrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_doitinhthanh);
        //Ánh xạ
        btn_xong = (Button) findViewById(R.id.btn_doitinhthanh_xong);
        listView = (ListView)findViewById(R.id.lvtinhthanh);
        arrayList = new ArrayList<TinhThanh_Model>();
        //set onclick for btn Xong
        btn_xong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //tro ve trang truoc do
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Gọi hàm lấy tỉnh thành trong Webservice, 192.168.115.1 là địa chỉ localhost của máy
                new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListTinh");
            }
        });

    }

    class ReadJSON extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String str = getXmlFromUrl (params[0]);
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                //Đọc JSON vào array
                JSONArray array = new JSONArray(s);
                for(int i=0;i<array.length();i++){
                    JSONObject city = array.getJSONObject(i);
                    //Add ID và tên Tỉnh thành lấy được từ JSON vào Model
                    arrayList.add(new TinhThanh_Model(
                       city.getInt("ID"), //ID là kiểu int
                            city.getString("Ten") //Tên là kiểu String
                    ));
                }
                TinhThanh_Adapter adapter = new TinhThanh_Adapter(
                        getApplicationContext(),
                        arrayList
                );
                //Set adapter cho để show lên giao diện
                listView.setAdapter(adapter);
                //Toast.makeText(getApplicationContext(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
            }catch (JSONException e){
                e.printStackTrace();
            }

        }
    }

    //Get file XMl từ URL của host (bài này là localhost)
    private static String getXmlFromUrl (String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);
            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}
