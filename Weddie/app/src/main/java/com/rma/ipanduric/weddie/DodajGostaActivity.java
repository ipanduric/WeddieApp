package com.rma.ipanduric.weddie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.wearable.CapabilityApi;

import static com.rma.ipanduric.weddie.GostiActivity.OUTPUT_BROJ;
import static com.rma.ipanduric.weddie.GostiActivity.OUTPUT_IME;
import static com.rma.ipanduric.weddie.GostiActivity.OUTPUT_KATEGORIJA;
import static com.rma.ipanduric.weddie.GostiActivity.OUTPUT_PREZIME;

public class DodajGostaActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etPrezime, etIme, etBroj;
    Spinner sKategorija;
    Button bDodaj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_gosta);
        this.SetUpUI();
    }

    private void SetUpUI() {
        etPrezime = (EditText) findViewById(R.id.etgPrezime);
        etIme = (EditText) findViewById(R.id.etgIme);
        etBroj = (EditText) findViewById(R.id.etBroj);
        sKategorija = (Spinner) findViewById(R.id.sKategorija);
        bDodaj = (Button) findViewById(R.id.bgDodaj);
        bDodaj.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String prezime = null, ime = null, broj = null, kategorija;

        if (etPrezime.getText().toString().isEmpty()) {
            Toast.makeText(this, "Molim Vas, unesite prezime!", Toast.LENGTH_SHORT).show();
        } else {
            prezime = etPrezime.getText().toString();
        }

        if (!(etPrezime.getText().toString().isEmpty())) {
            ime = etIme.getText().toString();
            broj = etBroj.getText().toString();
            kategorija = sKategorija.getSelectedItem().toString();
            Intent intent = new Intent();
            intent.putExtra(OUTPUT_KATEGORIJA, kategorija);
            intent.putExtra(OUTPUT_PREZIME, prezime);
            intent.putExtra(OUTPUT_IME, ime);
            intent.putExtra(OUTPUT_BROJ, broj);
            this.setResult(RESULT_OK, intent);
            this.finish();
        }
    }
}
