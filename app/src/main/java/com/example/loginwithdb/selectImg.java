package com.example.loginwithdb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class selectImg extends AppCompatActivity {

    TextView tvwel;
    DBHelp dbHelper;
    String name = "",id;
    Button upload,upimg,show,gotoid;
    ImageView img1,img2,idimg;
    byte[] imgpp,bytepp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvwel = findViewById(R.id.tvwel);
        upload = findViewById(R.id.Upload);
        upimg = findViewById(R.id.upimg);
        img1 = findViewById(R.id.img);
        img2 = findViewById(R.id.img2);
        show = findViewById(R.id.show);
        gotoid = findViewById(R.id.gotoid);
        idimg = findViewById(R.id.idimg);

        dbHelper = new DBHelp(selectImg.this);

        Intent i = getIntent();
        id = i.getStringExtra("id");

        Cursor cursor = dbHelper.getname(id);
        cursor.moveToNext();
        String name = cursor.getString(0);
        tvwel.setText("Welcome " + name);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Cursor cursor = dbHelper.getname(id);
//                cursor.moveToNext();
//                String name = cursor.getString(0);
//                tvwel.setText("" + name);
//            }
//        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePictureFromGallery();
            }
        });
        upimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgpp = convertImgToByteArray(img1);
                if(dbHelper.saveimg(id,imgpp)==true){
                    Toast.makeText(selectImg.this, "Save Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(selectImg.this, "failed to save", Toast.LENGTH_SHORT).show();
                }
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bytepp = dbHelper.get(id);
                if (bytepp!=null){
                    Bitmap bitmap = converByteArrayToBitmap(bytepp);
                    img2.setImageBitmap(bitmap);
                }
            }
        });
        gotoid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectImg.this,idCard.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }
    private Bitmap converByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    private byte[] convertImgToByteArray(ImageView imageView){
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void takePictureFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK){
                    Uri selectImgUri = data.getData();
                    img1.setImageURI(selectImgUri);
                    //idimg.setImageURI(selectImgUri);

                }
        }
    }
}