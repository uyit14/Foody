package com.example.uy.foodyver1;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by UY on 3/21/2017.
 */
//TRANG LOAD QUÁN ĂN THEO LOẠI, TỈNH THÀNH, QUẬN HUYỆN LÀ   View/Home_Home
public class Database {
    public static SQLiteDatabase initDatabase(Activity activity, String databaseName){
        try {
            //DataDir: duong dan den database
            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName; //DataDir: duong dan den database
            File f = new File(outFileName);
                InputStream e = activity.getAssets().open(databaseName); //Mo database trong assets
                File folder = new File(activity.getApplicationInfo().dataDir + "/databases/"); //Tao floder chua database
                if (!folder.exists()) { //Neu floder ton tai
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName); //Doc dlieu tu InPut e vao OutPut myoutput
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }
}
