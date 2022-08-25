package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class otherInfo extends AppCompatActivity {

    DBHelp db;
    EditText edMname,dob,mobno,add;
    TextView fname,lname,Oemail;
    Spinner bg,course;
    String selectBg,selectCouse,id;
    Button submit,imgbtn,edit;
    String [] scourse ={"select course","M.Sc. (Computer Science)","B.Sc.(Computer Science"};
    ArrayList acourse = new ArrayList(Arrays.asList(scourse));
    String [] sbg ={"select Blood Group","A+","B+"};
    ArrayList abg = new ArrayList(Arrays.asList(sbg));
    byte [] dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);

        fname = findViewById(R.id.tvfn);
        lname = findViewById(R.id.tvln);
        edMname = findViewById(R.id.edMname);
        dob = findViewById(R.id.edDob);
        mobno = findViewById(R.id.edmno);
        add =findViewById(R.id.add);
        submit = findViewById(R.id.infobtn);
        bg = findViewById(R.id.bg);
        Oemail= findViewById(R.id.Oemail);
        course = findViewById(R.id.course);
        imgbtn = findViewById(R.id.imgbtn);
        edit = findViewById(R.id.edit);

        db = new DBHelp(otherInfo.this);

        Intent i = getIntent();
        id = i.getStringExtra("id");
        edit.setEnabled(false);

        Cursor cursor = db.getname(id);
        cursor.moveToNext();
        String name = cursor.getString(0);
        fname.setText(name);

        Cursor m = db.getmname(id);
        m.moveToNext();
        String mname = m.getString(0);
        if(mname!=null){
           edMname.setEnabled(false);
           mobno.setEnabled(false);
           dob.setEnabled(false);
           add.setEnabled(false);
           submit.setEnabled(false);
           edit.setEnabled(true);
            submit.setText("Update");
        }
        edMname.setText(mname);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edMname.setEnabled(true);
                mobno.setEnabled(true);
                dob.setEnabled(true);
                add.setEnabled(true);
                submit.setEnabled(true);
            }
        });

        Cursor l = db.getlname(id);
        l.moveToNext();
        String lnm = l.getString(0);
        lname.setText(lnm);

        Cursor email = db.getemail(id);
        email.moveToNext();
        String eml = email.getString(0);
        Oemail.setText(eml);

        Cursor c = db.getcourse(id);
        c.moveToNext();
        String crc = c.getString(0);


        Cursor d = db.getdob(id);
        d.moveToNext();
        String bod = d.getString(0);
        dob.setText(bod);

        Cursor mo = db.getmob(id);
        mo.moveToNext();
        String mob = mo.getString(0);
        mobno.setText(mob);

        Cursor a = db.getadd(id);
        a.moveToNext();
        String ad = a.getString(0);
        add.setText(ad);

        //adapter for course
        ArrayAdapter cad = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,acourse);
        course.setAdapter(cad);
        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectCouse = (String) acourse.get(i);
                Toast.makeText(otherInfo.this, selectCouse+" is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Adapter for Blood group
        ArrayAdapter bad = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,abg);
        bg.setAdapter(bad);
        bg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectBg = (String) abg.get(i);
                Toast.makeText(otherInfo.this, selectBg+" is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectCouse.equals("")){
                    Toast.makeText(otherInfo.this, "Course is not selected", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(otherInfo.this, "getting value " +selectCouse, Toast.LENGTH_SHORT).show();
                    String mname = edMname.getText().toString();

                    //Toast.makeText(otherInfo.this, "id="+id+" name="+name+" mname="+mname+" lname="+lnm, Toast.LENGTH_LONG).show();
                    //Toast.makeText(otherInfo.this, "course="+selectCouse+" dob="+dob.getText().toString()+" bg="+selectBg, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(otherInfo.this, "mobno="+mobno.getText().toString()+" email="+eml+" add="+add.getText().toString(), Toast.LENGTH_SHORT).show();
                    add.setError(id+" "+name+" "+mname+" "+lnm+" "+selectCouse+" "+dob.getText().toString()+" "+selectBg+" "+mobno.getText().toString()+" "+eml+" "+add.getText().toString());
                    
                    boolean res= db.upstudinfo(id,mname, selectCouse,dob.getText().toString(),selectBg,mobno.getText().toString(),eml, add.getText().toString());
                    if(res==true){
                        Toast.makeText(otherInfo.this, "Data successfully inserted", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(otherInfo.this,selectImg.class);
                        intent.putExtra("id",id);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(otherInfo.this, "Error occur", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        /*imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(otherInfo.this, "id is :"+id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(otherInfo.this,selectImg.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });*/

    }
}