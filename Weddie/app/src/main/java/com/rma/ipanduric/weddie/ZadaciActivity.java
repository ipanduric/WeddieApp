package com.rma.ipanduric.weddie;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ZadaciActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUEST_REMINDER = 1;
    ListView lvZadaciList;
    TextView tvNoZad;
    ReminderAdapter mReminderAdapter;
    Button bDodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadaci);
        this.SetUpUI();
    }

    private void SetUpUI() {
        lvZadaciList = (ListView) findViewById(R.id.lvZadaci);
        tvNoZad = (TextView) findViewById(R.id.tvNoZad);
        bDodaj = (Button) findViewById(R.id.bdodajZadatak);
        bDodaj.setOnClickListener(this);
        this.SetUpLV();
    }


    private void SetUpLV() {
        if (Database.getInstance(this).getAllZadaci().isEmpty()) {
            tvNoZad.setVisibility(View.VISIBLE);
        } else {
            ArrayList<ZadatakItem> zadaci = Database.getInstance(this).getAllZadaci();
            tvNoZad.setVisibility(View.GONE);
            mReminderAdapter = new ReminderAdapter(zadaci, this);
            lvZadaciList.setAdapter(mReminderAdapter);
        }


        lvZadaciList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ZadaciActivity.this);
                dialogBuilder.setMessage("Želite li obrisati zadatak?");
                dialogBuilder.setCancelable(true);
                dialogBuilder.setPositiveButton(
                        "Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ZadatakItem zadatak = (ZadatakItem) mReminderAdapter.getItem(position);
                                Database.getInstance(getApplicationContext()).obrisiZadatak((ZadatakItem) mReminderAdapter.getItem(position));
                                mReminderAdapter.deleteAt(position);
                                dialog.cancel();
                                new ReminderManager(getApplicationContext()).ponistiZadatak(zadatak.getID());
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
        Intent intent = new Intent(this, DodajZadatakActivity.class);
        startActivityForResult(intent, REQUEST_REMINDER);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_REMINDER:
                if (resultCode == RESULT_OK) {
                    SetUpLV();
                    break;
                }
        }
    }


}
