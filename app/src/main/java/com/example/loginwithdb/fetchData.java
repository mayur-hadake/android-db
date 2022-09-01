package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Toast;

import java.util.ArrayList;

public class fetchData extends AppCompatActivity implements selectListner{

    RecyclerView recyclerView;
    ArrayList<model> dataholder= new ArrayList<>();
    DBHelp dbHelp = new DBHelp(this);
    byte []dp = new byte[0];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

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
//            if (mn.isEmpty()){
//                name = fn+" "+ln;
//            }
            model obj;
            obj = new model(name,mobno,email,bitmap);
            dataholder.add(obj);
        }
        myAdapter adapter= new myAdapter(dataholder,this);
        recyclerView.setAdapter(adapter);
    }
    private Bitmap converByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    @Override
    public void onItemClicked(model mod) {
        Cursor res = dbHelp.getidbyemail(mod.getEmail());
        res.moveToNext();
        String id = res.getString(0);
        Intent intent = new Intent(getApplicationContext(),idCard.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }
    public void onAcptBtnClickList(model mod){
        boolean res = dbHelp.upstudstatus(mod.getEmail(),1);
        Toast.makeText(this, "Id Card Accepted ", Toast.LENGTH_SHORT).show();

    }
    public void onDltBtnClickList(model mod){
        boolean res = dbHelp.upstudstatus(mod.getEmail(),2);
        Toast.makeText(this, "Id Card Rejected ", Toast.LENGTH_SHORT).show();
    }
}