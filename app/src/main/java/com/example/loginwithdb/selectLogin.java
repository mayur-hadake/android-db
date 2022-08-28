package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class selectLogin extends AppCompatActivity {
    Button teacher,student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);
        student = findViewById(R.id.studLogIn);
        teacher = findViewById(R.id.techLogIn);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectLogin.this,loginPage.class);
                startActivity(intent);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectLogin.this,fetchData.class);
                startActivity(intent);
            }
        });
    }
}