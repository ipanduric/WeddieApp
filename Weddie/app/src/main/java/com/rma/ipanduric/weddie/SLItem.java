package com.rma.ipanduric.weddie;

/**
 * Created by ipand on 11.8.2017..
 */

public class SLItem {

    private String slNaziv;
    private int slID;

    public SLItem ( int ID, String naziv)
    {
        slID = ID;
        slNaziv = naziv;
    }
    public SLItem ( String naziv)
    {

       slNaziv = naziv;
    }


    public String getSlNaziv() {
        return slNaziv;
    }

    public int getSlID() {
        return slID;
    }
}
