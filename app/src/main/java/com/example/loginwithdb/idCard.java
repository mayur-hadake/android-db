package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class idCard extends AppCompatActivity {
    TextView idname,course,dob,bg,mobno,idemail,add;
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
        }

        /*Cursor cursor = db.getname(id);
        cursor.moveToNext();
        String fname = cursor.getString(0);

        Cursor m = db.getmname(id);
        m.moveToNext();
        String mname = m.getString(0);

        Cursor l = db.getlname(id);
        l.moveToNext();
        String lnm = l.getString(0);
        String fullname = fname+" "+mname+" "+lnm;
        name.setText(fullname);

        Cursor c = db.getcourse(id);
        c.moveToNext();
        String crc = c.getString(0);
        course.setText(crc);

        Cursor d = db.getdob(id);
        d.moveToNext();
        String bod = d.getString(0);
        dob.setText(bod);

        Cursor b = db.getbg(id);
        b.moveToNext();
        String bld = b.getString(0);
        bg.setText(bld);

        Cursor mo = db.getmob(id);
        mo.moveToNext();
        String mob = mo.getString(0);
        mobno.setText(mob);

        Cursor email = db.getemail(id);
        email.moveToNext();
        String eml = email.getString(0);
        idemail.setText(eml);

        Cursor a = db.getadd(id);
        a.moveToNext();
        String ad = a.getString(0);
        add.setText(ad);*/
    }
}