package com.rma.ipanduric.weddie;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GostiActivity extends AppCompatActivity implements View.OnClickListener {


    public static final  String OUTPUT_KATEGORIJA = "kategorija";
    public static final  String OUTPUT_PREZIME = "prezime";
    public static final  String OUTPUT_IME = "ime";
    public static final  String OUTPUT_BROJ = "broj";
    public static final int REQUEST_CODE_GOST = 1;

    ListView lvGosti;
    GostiAdapter gostiAdapter;
    Button bDodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gosti);
        SetUpUI();
    }

    private void SetUpUI() {
        lvGosti = (ListView) findViewById(R.id.lvGosti);
        bDodaj = (Button) findViewById(R.id.bDodajGosta);
        bDodaj.setOnClickListener(this);

        this.SetUpLV();



    }


    private void SetUpLV() {


            ArrayList<GostItem> gosti = Database.getInstance(this).getAllGosti();
            gostiAdapter = new GostiAdapter(gosti);
            lvGosti.setAdapter(gostiAdapter);


        lvGosti.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GostiActivity.this);
                dialogBuilder.setMessage("Želite li obrisati gosta?");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton(
                        "Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Database.getInstance(getApplicationContext()).obrisiGosta((GostItem) gostiAdapter.getItem(position));
                                gostiAdapter.deleteAt(position);
                                dialog.cancel();
                            }
                        });

                dialogBuilder.setNegativeButton(
                        "Ne",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });
    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DodajGostaActivity.class);
        startActivityForResult(intent, REQUEST_CODE_GOST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GOST && resultCode == RESULT_OK) {

            String kategorija = data.getStringExtra(OUTPUT_KATEGORIJA);
            String prezime = data.getStringExtra(OUTPUT_PREZIME);
            String ime = data.getStringExtra(OUTPUT_IME);
            String broj = data.getStringExtra(OUTPUT_BROJ);
            ArrayList<GostItem> gosti = new ArrayList<>();
            GostItem gost = new GostItem(kategorija, prezime, ime, broj);
            Database.getInstance(getApplicationContext()).dodajGosta(gost);
            gostiAdapter.dodajNovogGosta(new GostItem(kategorija, prezime, ime, broj));

        }
    }


}





