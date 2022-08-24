package com.example.loginwithdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginPage extends AppCompatActivity {

    EditText edLemail,edLpass;
    Button LoginP,Reg;
    DBHelp dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        edLemail = findViewById(R.id.edLemail);
        edLpass = findViewById(R.id.edLpass);
        LoginP = findViewById(R.id.LoginP);
        Reg =findViewById(R.id.Reg);
        dbHelper = new DBHelp(this);

        LoginP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edLemail.getText().toString()).isEmpty()){
                    edLemail.setError("Enter Email");
                }
                else if ((edLpass.getText().toString()).isEmpty()){
                    edLpass.setError("Enter Password");
                }
                else{
                    boolean ins = dbHelper.checkidpass(edLemail.getText().toString(),edLpass.getText().toString());
                    if (ins==true){
                        /*Toast.makeText(loginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(loginPage.this,homeActivity.class);
                        i.putExtra("email",edLemail.getText().toString());
                        startActivity(i);*/

                    }
                    else {
                        Toast.makeText(loginPage.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                    Cursor res = dbHelper.getid(edLemail.getText().toString(),edLpass.getText().toString());
                    if (res.getCount()==0){
                        Toast.makeText(loginPage.this, "No data exist in DB", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        res.moveToNext();
                        String id = res.getString(0);
                        edLemail.setError(""+id.toString());
                        Intent i = new Intent(loginPage.this,otherInfo.class);
                        i.putExtra("id",id);
                        startActivity(i);

                    }
                }
            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}