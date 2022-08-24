package com.example.loginwithdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelp extends SQLiteOpenHelper {
    public DBHelp(Context context) {
        super(context, "idgen", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(stid int primary key autoincrement,stfname TEXT," +
                "                stmname TEXT,stlname TEXT, stemail TEXT, stpass TEXT,stcourse TEXT,stdob TEXT,stbg TEXT," +
                "                stmobno TEXT,stemail TEXT,stadd TEXT,stimage BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists info");
    }
    public boolean insertdata(String fname,String lname, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("stfname",fname);
        contentValues.put("stlname",lname);
        contentValues.put("stemail",email);
        contentValues.put("stpass",pass);
        long res = db.insert("info",null,contentValues);
        if (res ==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public void dt(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("drop table if exists studimg");
    }

    public Cursor getname(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorname = db.rawQuery("select stfname from info where stid=?",new String[] {id});
        return cursorname;
    }
    public Cursor getlname(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorlname = db.rawQuery("select stlname from info where stid=?",new String[] {id});
        return cursorlname;
    }
    public Cursor getemail(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursoremail = db.rawQuery("select stemail from info where stid=?",new String[] {id});
        return cursoremail;
    }
    public boolean upstudinfo(String id,String mname,String course,String dob,String bg,String mobno,String email,String add){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("stmname",mname);
        contentValues.put("stcourse",course);
        contentValues.put("stdob",dob);
        contentValues.put("stbg",bg);
        contentValues.put("stmobno",mobno);
        contentValues.put("stemail",email);
        contentValues.put("stadd",add);
        Cursor cursor = db.rawQuery("select * from info where stid = ?",new String[] {id});
        if (cursor.getCount()>0) {
            long res = db.update("info", contentValues, "stid=?", new String[]{id});
            if (res == -1) {
                return false;
            } else
                return true;
        }
        else {
            return false;
        }
    }
    public boolean checkidpass(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from info where stemail=? and stpass=?",new String[]{email,pass});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
    public Cursor getid(String email,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select stid from info where stemail=? and stpass=?",new String[]{email,pass});
        return cursor;
    }

    public boolean saveimg(String id) {

        try{
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            //contentValues.put("stimage", id);
            //contentValues.put("image", imgpp);
            long studimg = db.insert("stimage",null,contentValues);
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
            Cursor cursor = db.rawQuery("select stimage from info where stid=?",new String[] {id});
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
