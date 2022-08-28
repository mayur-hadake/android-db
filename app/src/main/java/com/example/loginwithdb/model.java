package com.example.loginwithdb;

import android.graphics.Bitmap;

public class model {
    String name,mobno,email;
    Bitmap bitmap;

    public model(String name, String mobno, String email, Bitmap bitmap) {
        this.name = name;
        this.mobno = mobno;
        this.email = email;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobno() {
        return mobno;
    }

    public void setMobno(String mobno) {
        this.mobno = mobno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
