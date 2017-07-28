package com.rma.ipanduric.weddie;

/**
 * Created by ipand on 28.7.2017..
 */

public class FotoItem {


    private String fIme;
    private String fWeb;
    private String fTel;
    private int fPic;

    public FotoItem ( String ime, String web, String tel, int pic)
    {

        fIme = ime;
        fWeb = web;
        fTel = tel;
        fPic = pic;
    }

    public String getfIme() {
        return fIme;
    }

    public String getfWeb() {
        return fWeb;
    }

    public String getfTel() {
        return fTel;
    }

    public int getfPic()  {return fPic;}
}


