package com.rma.ipanduric.weddie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CvijeceItem {

    private String cIme;
    private String cAdr;
    private String cTel;


    public CvijeceItem ( String ime, String adr, String tel)
    {
        cIme = ime;
        cAdr = adr;
        cTel = tel;

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

}
