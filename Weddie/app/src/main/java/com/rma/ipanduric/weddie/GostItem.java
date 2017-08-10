package com.rma.ipanduric.weddie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GostItem {

    private int gID;
    private String gKategorija;
    private String gPrezime;
    private String gIme;
    private int gBroj;

    public GostItem (int ID, String kategorija, String prezime, String ime, int broj)
    {
        this.gKategorija = kategorija;
        this.gPrezime = prezime;
        this.gIme = ime;
        this.gBroj = broj;
        this.gID = ID;
    }

    public GostItem (String kategorija, String prezime, String ime, int broj)
    {
        this.gKategorija = kategorija;
        this.gPrezime = prezime;
        this.gIme = ime;
        this.gBroj = broj;

    }

    public String getgKategorija () {
        return gKategorija;
    }

    public String getgPrezime () {
        return gPrezime;
    }

    public String getgIme () {
        return gIme;
    }

    public int getgBroj() {
        return gBroj;
    }

    public int getgID() {
        return gID;
    }


}
