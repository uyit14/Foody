package com.example.uy.foodyver1.View;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.uy.foodyver1.Adapter.QuanAn_Adapter;
import com.example.uy.foodyver1.Model.QuanAn_Model;
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
 * Created by UY on 3/19/2017.
 */

public class Home_Home extends Fragment {

    ListView listViewQuanAn;
    ArrayList<QuanAn_Model> arrayList;
    QuanAn_Adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.home_home, container, false);
        listViewQuanAn = (ListView)v.findViewById(R.id.lvQuanAn);
        arrayList = new ArrayList<QuanAn_Model>();


        Intent intent = getActivity().getIntent();
        //Nhan id khi 1 item trong danh muc duoc click vao va lưu vao bien IDDanhMuc
        String IDDanhMuc = intent.getStringExtra("IDDM");
        //neu ID la o dau, danh muc thi show theo ID Danh muc (Sang trong, an chay, buffe, ...)
        if(IDDanhMuc!=null) { //Neu IDDanhMuc tra ve khong null
            //Ep  kieu String ve int de truy van trong CSDL
            final int IDDM = Integer.parseInt(IDDanhMuc);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Link tới host chứa dữ liệu, ở đây là DS Quán ăn theo ID Danh mục
                    new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListQuanAnbyIDDM?iddm="+IDDM+"");
                }
            });
        }

        //Nhan ID cua quan, huyen
        String IDQuanHuyen = intent.getStringExtra("IDQH");
        if(IDQuanHuyen!=null){
            final int IDQH = Integer.parseInt(IDQuanHuyen);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Link tới host chứa dữ liệu, ở đây là DS Quán ăn theo ID quận huyện
                    new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListQuanAnbyIDQH?idqh="+IDQH+"");
                }
            });
        }

        //Khai bao bien IDN de nhan ID khi click vao 1 item ben trang Home_ODau_DanhMuc
        int IDN = intent.getIntExtra("IDNEW", 0);
        //Neu ID la item 1(Moi nhat thi load ra 20 quan moi nhat da them vao CSDL)
        if(IDN==1) { //IDN la moi nhat
            new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListQuanAnMoiNhat?idn="+IDN+"");
        }

        //
        //Neu ID la item 2(Gan toi thi load ra cac quan o Quan Thu Duc)
        if(IDN==2) { //IDN
            new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListQuanAnbyIDQH?idqh=22");
        }

        //tab home, neu chua click vao dau thi hien tat ca cac quan theo thu tu
        if(IDDanhMuc==null && IDN==0 && IDQuanHuyen==null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new ReadJSON().execute("http://192.168.115.1/foodyserver/api/Foody/getListQuanAn");
                }
            });
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
                    //add ds quán ăn lấy được từ JSON vào model
                    arrayList.add(new QuanAn_Model(
                            mn.getInt("ID"),
                            mn.getString("Ten"),
                            mn.getInt("IDQH"),
                            mn.getInt("IDDM"),
                            mn.getString("HinhAnh"),
                            mn.getString("TenQuan"),
                            mn.getString("Diem")
                    ));
                }
                //Toast.makeText(getContext(), ""+arrayList.size(), Toast.LENGTH_SHORT).show();
                QuanAn_Adapter adapter = new QuanAn_Adapter(
                        getActivity(),
                        arrayList
                );
                //Set adapter vào listView
                listViewQuanAn.setAdapter(adapter);
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

