package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class idCard extends AppCompatActivity {
    TextView idname,course,dob,bg,mobno,idemail,add;
    ImageView idimg;
    Button showdp;
    DBHelp db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_id_card);


        idname = findViewById(R.id.idname);
        course = findViewById(R.id.idcourse);
        dob = findViewById(R.id.iddob);
        bg = findViewById(R.id.idbg);
        mobno = findViewById(R.id.idmob);
        idemail = findViewById(R.id.idemail);
        add = findViewById(R.id.idadd);
        idimg = findViewById(R.id.idimg);
        showdp = findViewById(R.id.dp);
        byte []dp = new byte[0];

        db = new DBHelp(idCard.this);

        Intent i = getIntent();
        String id = i.getStringExtra("id");

        Cursor allinfo = db.getAllInfo(id);
        while (allinfo.moveToNext()){
            String fname = allinfo.getString(1);
            String mname = allinfo.getString(2);
            String lname = allinfo.getString(3);
            String fullname = fname+" "+mname+" "+lname;
            idname.setText(fullname);
            String eml = allinfo.getString(4);
            idemail.setText(eml);
            String crc = allinfo.getString(6);
            course.setText(crc);
            String bod = allinfo.getString(7);
            dob.setText(bod);
            String bld = allinfo.getString(8);
            bg.setText(bld);
            String mon = allinfo.getString(9);
            mobno.setText(mon);
            String ad = allinfo.getString(10);
            add.setText(ad);
            dp = allinfo.getBlob(11);
            if (dp!=null){
                Bitmap bitmap = converByteArrayToBitmap(dp);
                idimg.setImageBitmap(bitmap);
            }else {
                idimg.setImageResource(R.drawable.ic_person);
            }
        }
        showdp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //byte[] bytepp = DBHelp.get(id);
                /*if (dp!=null){
                    Bitmap bitmap = converByteArrayToBitmap(dp);
                    idimg.setImageBitmap(bitmap);
                }*/
            }
        });
    }
    private Bitmap converByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}