package com.example.uy.foodyver1.View;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uy.foodyver1.Adapter.DanhMuc_Adapter;
import com.example.uy.foodyver1.Model.DanhMuc_Model;
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

public class Home_ODau_MoiNhat extends Fragment {


    ListView lv;
    ArrayList<DanhMuc_Model> arrayList;
    DanhMuc_Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = inflater.inflate(R.layout.home_odau_moinhat, container, false);
        //Ánh xạ
        lv = (ListView)v.findViewById(R.id.listviewODauDanhMuc);
        arrayList = new ArrayList<DanhMuc_Model>();
        lv = (ListView) v.findViewById(R.id.listviewODauMoiNhat);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //hàm lấy ds (mới nhất, gần tôi, du khách, ...)
                new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListMN");
            }
        });

        return v;
    }

    //Đọc JSON để lấy dữ liệu về
    class ReadJSON extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String str = getXmlFromUrl (params[0]);
            return str;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                //Mảng chứa JSON
                JSONArray array = new JSONArray(s);
                for(int i=0;i<array.length();i++){
                    JSONObject mn = array.getJSONObject(i);
                    //add dữ liệu đọc được từ JSON về MODEL
                    arrayList.add(new DanhMuc_Model(
                            mn.getInt("IDN"),
                            mn.getString("Ten"),
                            mn.getString("HinhAnh")
                    ));
                }
                //Toast.makeText(getContext(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
                DanhMuc_Adapter adapter = new DanhMuc_Adapter(
                        getActivity(),
                        arrayList
                );
                //Set Adapter để show lên giao diện điện thoại
                lv.setAdapter(adapter);
            }catch (JSONException e){
                e.printStackTrace();
            }


        }
    }

    //Get file XML từ URL
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

