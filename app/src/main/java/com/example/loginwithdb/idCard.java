package com.example.loginwithdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class idCard extends AppCompatActivity {
    TextView idname,course,dob,bg,mobno,idemail,add;
    ImageView idimg;
    ImageButton downloadbtn;
    DBHelp db;
    private Bitmap bitmap;
    ConstraintLayout constraintLayout;

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
        downloadbtn = findViewById(R.id.downloadbtn);

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
        downloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("size", "size :"+constraintLayout.getWidth()+" "+constraintLayout.getHeight());
                bitmap = loadBitmap(constraintLayout,constraintLayout.getWidth(),constraintLayout.getHeight());
                createPdf();
            }
        });
    }

    private void createPdf() {
        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;
        int convetWidth = (int)width,convetHeight =(int)height;

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convetWidth,convetHeight,1).create();
        PdfDocument.Page page= document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        bitmap =Bitmap.createScaledBitmap(bitmap,convetWidth,convetHeight,true);
        canvas.drawBitmap(bitmap,0,0,null);
        document.finishPage(page);
        
        //Store id in file
        String loc = "/download/idcard.pdf";
        File file = new File(loc);
        try {
            document.writeTo(new FileOutputStream(file));
        }catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Something Wrong "+e.toString(), Toast.LENGTH_SHORT).show();
            
            document.close();
            Toast.makeText(this, "Successfully Downloaded ", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap loadBitmap(View v, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private Bitmap converByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }
}