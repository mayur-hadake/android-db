/*package com.example.loginwithdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {

        super(context, "Student", null  , 1);
    }
    Byte [] imgInBytes;
    private Object Context;
    Context context;



    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL("Create Table Student (sid Integer Primary key autoincrement,sfname TEXT," +
                "smname TEXT,slname TEXT, semail TEXT, spass TEXT,scourse TEXT,sdob TEXT,sbg TEXT," +
                "                smobno TEXT,semail TEXT,sadd TEXT,simage BLOB)");*/
        /*db.execSQL("create table studimg(imgid integer primary key,image BLOB)");
        db.execSQL("create table studinfo(studid integer primary key,studfname text,studmname text," +
                "studlname text,studcourse text,studdob text,studbg text," +
                "studmobno text,studemail text,studadd text)");*/
        /*db.execSQL("create table studentInfo(sid Integer Primary key autoincrement,sfname TEXT," +
                "                smname TEXT,slname TEXT, semail TEXT, spass TEXT,scourse TEXT,sdob TEXT,sbg TEXT," +
                "                smobno TEXT,semail TEXT,sadd TEXT,simage BLOB)");*/
/*    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //db.execSQL("DROP table if exists Student");
        db.execSQL("Drop table if exists studimg");
        db.execSQL("Drop table if exists studinfo");
        //db.execSQL("drop table if exists studentInfo");
    }

    public boolean insertdata(String fname,String lname, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sfname",fname);
        contentValues.put("slname",lname);
        contentValues.put("semail",email);
        contentValues.put("spass",pass);
        long res = db.insert("studinfo",null,contentValues);
        if (res ==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean upstudinfo(String id,String mname,String course,String dob,String bg,String mobno,String email,String add){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("smname",mname);
        contentValues.put("scourse",course);
        contentValues.put("sdob",dob);
        contentValues.put("sbg",bg);
        contentValues.put("smobno",mobno);
        contentValues.put("semail",email);
        contentValues.put("sadd",add);
        Cursor cursor = db.rawQuery("select * from studinfo where sid = ?",new String[] {id});
        if (cursor.getCount()>0) {
            long res = db.update("studinfo", contentValues, "sid=?", new String[]{id});
            if (res == -1) {
                return false;
            } else
                return true;
        }
        else {
            return false;
        }
    }

    //Student info inserting in db
    /*public boolean insertinfo(String id,String fname,String mname,String lname,
                              String course,String dob,String bg,String mobno,String email,
                              String add){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("studid",id);
        contentValues.put("studfname",fname);
        contentValues.put("studmname",mname);
        contentValues.put("studlname",lname);
        contentValues.put("studcourse",course);
        contentValues.put("studdob",dob);
        contentValues.put("studbg",bg);
        contentValues.put("studmobno",mobno);
        contentValues.put("studemail",email);
        contentValues.put("studadd",add);
        long res = db.insert("studinfo",null,contentValues);
        //Toast.makeText(context, "data added successfully", Toast.LENGTH_SHORT).show();
        if (res ==-1){
            return false;
        }
        else {
            return true;
        }
    }*/

/*
    public boolean checkuser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from studinfo where semail=?",new String[]{email});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkidpass(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from studinfo where semail=? and spass=?",new String[]{email,pass});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
    public Cursor getid(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sid from studinfo where semail=? and spass=?",new String[]{email,pass});
        return cursor;
    }
    public Cursor getname(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorname = db.rawQuery("select sfname from studinfo where sid=?",new String[] {id});
        return cursorname;
    }
    public Cursor getlname(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorlname = db.rawQuery("select slname from studinfo where sid=?",new String[] {id});
        return cursorlname;
    }
    public Cursor getemail(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursoremail = db.rawQuery("select semail from studinfo where sid=?",new String[] {id});
        return cursoremail;
    }

    public boolean saveimg(String id) {

        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put("imgid", id);
            //contentValues.put("image", imgpp);
            long studimg = db.insert("studimg",null,contentValues);
            if (studimg==-1){
                return false;
            }
            else {
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public byte [] get(String id){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("select image from studimg where imgid=?",new String[] {id});
            if (cursor.moveToFirst()){
                return cursor.getBlob(0);
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
*/