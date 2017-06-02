package com.example.uy.foodyver1.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.uy.foodyver1.Adapter.QuanHuyen_Adapter;
import com.example.uy.foodyver1.Model.QuanHuyen_Model;
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
 * Created by UY on 3/14/2017.
 */

public class Home_ODau_TinhThanh extends Fragment {

    Button btnDoiTinhThanh;
    Button btntentinhthanh;
    ListView lv_qh;
    ArrayList<QuanHuyen_Model> arrayList;
    QuanHuyen_Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ánh xạ
        View v = inflater.inflate(R.layout.home_odau_tinhthanh, container, false);
        lv_qh = (ListView)v.findViewById(R.id.list_quanhuyen);
        arrayList = new ArrayList<QuanHuyen_Model>();

        btnDoiTinhThanh = (Button) v.findViewById(R.id.btnDoiTinhThanh);
        btntentinhthanh = (Button) v.findViewById(R.id.btntenTinhThanh);

        //Khi click vào thì chuyển qua layout cho phép đổi tỉnh thành
        btnDoiTinhThanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Chuyển qua layout cho phép người dùng đổi tỉnh thành
                Intent intent = new Intent(getContext(), TinhThanh.class);
                startActivity(intent);
            }
        });

        Intent intent = getActivity().getIntent();
        String tenTinhThanh1 = intent.getStringExtra("TenTinhThanh");
        //Nhận ID tỉnh thành khi bất cứ tỉnh thành nào đc click vào
        String IDTinhTT = intent.getStringExtra("IDTinhThanh");

        //Nếu chưa có tính thành nào đượcc click thì trả về mặc định các quận của TPHCM
        if(IDTinhTT==null) {
            new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListQHbyID/1");
        }

        //Load ds quận huyện theo ID tỉnh thành nhận đc
        if(IDTinhTT!=null) {
            int IDTT = Integer.parseInt(IDTinhTT);
            new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListQHbyID/"+IDTT+"");
        }

        return v;
    }

    //clas đọc JSON từ host
    class ReadJSON extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String str = getXmlFromUrl (params[0]);
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                //mang nhận ds quán ăn từ JSON
                JSONArray array = new JSONArray(s);
                for(int i=0;i<array.length();i++){
                    JSONObject mn = array.getJSONObject(i);
                    //add ds quán ăn vào model
                    arrayList.add(new QuanHuyen_Model(
                            mn.getInt("IDQH"),
                            mn.getString("TenQH"),
                            mn.getInt("ID")
                    ));
                }
                //Toast.makeText(getContext(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
                QuanHuyen_Adapter adapter = new QuanHuyen_Adapter(
                        getActivity(),
                        arrayList
                );
                //set adapter để show lên giao diện
                lv_qh.setAdapter(adapter);
            }catch (JSONException e){
                e.printStackTrace();
            }


        }
    }

    //get XML file from URL
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
