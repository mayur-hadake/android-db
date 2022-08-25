package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static Pattern Password_Pattern =
            Pattern.compile("^"+
                    "(?=.*[a-zA-Z])"+
                    "(?=.*[0-9])"+
                    "(?=.*[@#$%^&+=])"+
                    ".{6,}"+
                    "$");
    EditText fname,lname,email,pass,cpass;
    Button submit,gLogin,mb;
    DBHelp dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.edFname);
        lname = findViewById(R.id.edLname);
        email = findViewById(R.id.edmail);
        pass = findViewById(R.id.edpass);
        cpass = findViewById(R.id.edCpass);
        submit = findViewById(R.id.submit);
        gLogin = findViewById(R.id.login);
        mb = findViewById(R.id.mb);
        dbHelper = new DBHelp(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((fname.getText().toString()).isEmpty()){
                    fname.setError("Enter First Name");
                }
                else if ((lname.getText().toString()).isEmpty()){
                    lname.setError("Enter Last Name");
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Enter valid Email");
                }
                else if (!Password_Pattern.matcher(pass.getText().toString()).matches()){
                    pass.setError("Enter Strong Password");
                }
                else if (!(pass.getText().toString()).equals(cpass.getText().toString())){
                    cpass.setError("Enter same password");
                }
                else{
                    boolean ins = dbHelper.insertdata(fname.getText().toString(),lname.getText().toString(),email.getText().toString(),cpass.getText().toString());
                    if (ins==true){
                        Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),loginPage.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Registration UnSuccessful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        gLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),selectLogin.class);
                startActivity(intent);
            }
        });
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dbHelper!=null){
                    dbHelper.dt();
                }
                else {
                    Toast.makeText(MainActivity.this, "Database is empty", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }
}