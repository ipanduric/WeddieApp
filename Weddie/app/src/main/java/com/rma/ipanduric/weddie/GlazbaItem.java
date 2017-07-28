package com.rma.ipanduric.weddie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GlazbaItem  {
    private String gIme;
    private String gWeb;
    private String gTel;
    private int gPic;

    public GlazbaItem ( String ime, String web, String tel, int pic)
    {
        gIme = ime;
        gWeb = web;
        gTel = tel;
        gPic = pic;
    }

    public String getgIme() {
        return gIme;
    }

    public String getgWeb() {
        return gWeb;
    }

    public String getgTel() {
        return gTel;
    }

    public int getgPic()  {return gPic;}
}
