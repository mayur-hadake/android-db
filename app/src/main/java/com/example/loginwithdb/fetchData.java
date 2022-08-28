package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;

import java.util.ArrayList;

public class fetchData extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<model> dataholder= new ArrayList<>();
    byte []dp = new byte[0];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);
        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DBHelp(this).studVarification();
        while (cursor.moveToNext()){
            String fn= cursor.getString(1);
            String mn= cursor.getString(2);
            String ln= cursor.getString(3);
            String name = fn+" "+mn+" "+ln;
            String mobno = cursor.getString(9);
            String email = cursor.getString(4);
            dp = cursor.getBlob(11);
            Bitmap bitmap = converByteArrayToBitmap(dp);
            Log.d("mobile no getting", ""+mobno);
            Log.d("name getting", ""+name);
            Log.d("email getting", ""+email);
            if (mobno.equals(null)){
                mobno ="9988776655";
            }
            model obj;
            obj = new model(name,mobno,email,bitmap);
            dataholder.add(obj);
        }
        myAdapter adapter= new myAdapter(dataholder);
        recyclerView.setAdapter(adapter);
    }
    private Bitmap converByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}