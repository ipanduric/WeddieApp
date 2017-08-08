package com.rma.ipanduric.weddie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import javax.xml.validation.Schema;

/**
 * Created by ipand on 29.7.2017..
 */

public class Database extends SQLiteOpenHelper {

    private static Database mDatabase = null;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ZACACI);
        db.execSQL(CREATE_TABLE_GOSTI);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_ZADACI);
        db.execSQL(DROP_TABLE_GOSTI);
    }

    static final String CREATE_TABLE_ZACACI = "CREATE TABLE " + Schema.TABLE_ZADACI +
            " (" + Schema.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + Schema.NAZIV + " TEXT," + Schema.OPIS + " TEXT,"  + Schema.DATUM  + " TEXT);";
    static final String DROP_TABLE_ZADACI = "DROP TABLE IF EXISTS " + Schema.TABLE_ZADACI;
    static final String SELECT_ALL_ZADACI = "SELECT " + Schema.ID + "," + Schema.NAZIV + ","+ Schema.OPIS + "," + Schema.DATUM  + " FROM " + Schema.TABLE_ZADACI;

    static final String CREATE_TABLE_GOSTI = "CREATE TABLE " + Schema.TABLE_GOSTI +
            " (" + Schema.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + Schema.KATEGORIJA + " TEXT," + Schema.PREZIME + " TEXT,"  + Schema.IME + " TEXT," +  Schema.BROJ + " TEXT);";

    static final String DROP_TABLE_GOSTI = "DROP TABLE IF EXISTS " + Schema.TABLE_GOSTI;

    static final String SELECT_ALL_GOSTI = "SELECT " + Schema.ID + "," + Schema.KATEGORIJA + "," + Schema.PREZIME + "," + Schema.IME + ","
            + Schema.BROJ + " FROM " + Schema.TABLE_GOSTI;

    private Database (Context context){
        super(context.getApplicationContext(),Schema.DATABASE_NAME,null,Schema.SCHEMA_VERSION);
    }

    public static synchronized Database getInstance(Context context){
        if(mDatabase == null){
            mDatabase = new Database(context);
        }
        return mDatabase;
    }





    public static class Schema {
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "Weddie.db";
        static final String TABLE_ZADACI = "zadaci";
        static final String TABLE_GOSTI = "gosti";
        static final String ID ="id";
        static final String NAZIV = "naziv";
        static final String OPIS = "opis";
        static final String DATUM = "datum";
        static final String PREZIME = "prezime";
        static final String IME = "ime";
        static final String KATEGORIJA = "kategorija";
        static final String BROJ = "broj";
    }

    public void dodajZadatak (ZadatakItem zadatakItem){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.NAZIV, zadatakItem.getzNaziv());
        contentValues.put(Schema.OPIS, zadatakItem.getzOpis());
        contentValues.put(Schema.DATUM, zadatakItem.getzVrijeme());
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.insert(Schema.TABLE_ZADACI, Schema.DATUM,contentValues);
        writableDatabase.close();
    }

    public ArrayList<ZadatakItem> getAllZadaci(){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor taskCursor = writableDatabase.rawQuery(SELECT_ALL_ZADACI, null);
        ArrayList<ZadatakItem> zadaci = new ArrayList<>();
        if(taskCursor.moveToFirst()){
            do {
                int ID = taskCursor.getInt(0);
                String naziv = taskCursor.getString(1);
                String opis = taskCursor.getString(2);
                String datum = taskCursor.getString(3);
                zadaci.add(new ZadatakItem(ID, naziv, opis, datum));
            } while (taskCursor.moveToNext());
        }
        taskCursor.close();
        writableDatabase.close();
        return zadaci;
        }

    public void obrisiZadatak (ZadatakItem zadatakItem) {
        int id = zadatakItem.getID();
        String[] arg = new String[]{String.valueOf(id)};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Schema.TABLE_ZADACI, Schema.ID + "=?",arg);
        db.close();
    }

    public void dodajGosta(GostItem gostItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.KATEGORIJA, gostItem.getgKategorija());
        contentValues.put(Schema.PREZIME, gostItem.getgPrezime());
        contentValues.put(Schema.IME, gostItem.getgIme());
        contentValues.put(Schema.BROJ, gostItem.getgBroj());
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.insert(Schema.TABLE_GOSTI, Schema.KATEGORIJA,contentValues);
        writableDatabase.close();
    }

    public ArrayList<GostItem> getAllGosti() {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor gostCursor = writableDatabase.rawQuery(SELECT_ALL_GOSTI,null);
        ArrayList<GostItem> gost = new ArrayList<>();
        if (gostCursor.moveToFirst()) {
            do {
                int ID = gostCursor.getInt(0);
                String kategorija = gostCursor.getString(1);
                String prezime = gostCursor.getString(2);
                String ime = gostCursor.getString(3);
                String broj = gostCursor.getString(4);
                gost.add(new GostItem(ID, kategorija, prezime, ime, broj));
            } while (gostCursor.moveToNext());
        }

        gostCursor.close();
        writableDatabase.close();
        return gost;
    }

    public void obrisiGosta (GostItem gostItem)
    {
        int id = gostItem.getgID();
        String[] arg = new String[]{String.valueOf(id)};
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Schema.TABLE_GOSTI, Schema.ID + "=?",arg);
        db.close();
    }

}

