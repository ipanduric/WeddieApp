package com.rma.ipanduric.weddie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ZadatakItem {

    private String zNaziv;
    private String zOpis;
    private String zVrijeme;
    private int zID;

    public ZadatakItem(int ID, String naziv, String opis,  String vrijeme){
        this.zID = ID;
        this.zNaziv = naziv;
        this.zOpis = opis;
        this.zVrijeme = vrijeme;

    }
    public ZadatakItem(String naziv, String opis,  String vrijeme){
        this.zNaziv = naziv;
        this.zOpis = opis;
        this.zVrijeme = vrijeme;

    }

    public int getID(){return  zID;}
    public String getzNaziv() { return zNaziv; }
    public String getzOpis() { return zOpis; }
    public String getzVrijeme(){return zVrijeme;}
}
