package com.rma.ipanduric.weddie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CvijeceItem {

    private String cIme;
    private String cAdr;
    private String cTel;
    private String cLat, cLong;



    public CvijeceItem ( String ime, String adr, String tel, String lat, String lon)
    {
        cIme = ime;
        cAdr = adr;
        cTel = tel;
        cLat = lat;
        cLong = lon;

    }

    public String getcIme() {
        return cIme;
    }

    public String getcAdr() {
        return cAdr;
    }

    public String getcTel() {
        return cTel;
    }

    public String getcLat() {return cLat;}

    public String getcLong() {return cLong;}

}
