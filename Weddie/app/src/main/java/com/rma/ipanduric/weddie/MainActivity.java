package com.rma.ipanduric.weddie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bVidiSale, bIzracunajPotrebe, bGlazba, bFoto, bCvijece, bSastaviSL, bPodsjetnik,bDodajGoste;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetUpUI();
    }

    private void SetUpUI() {
        bVidiSale = (Button) findViewById(R.id.bVidiSale);
        bIzracunajPotrebe = (Button) findViewById(R.id.bIzracunajPotrebe);
        bGlazba = (Button) findViewById(R.id.bGlazba);
        bFoto = (Button) findViewById(R.id.bFoto);
        bCvijece = (Button) findViewById(R.id.bCvijece);
        bDodajGoste = (Button) findViewById(R.id.bDodajGoste);
        bSastaviSL = (Button) findViewById(R.id.bSastaviSL);
        bPodsjetnik = (Button) findViewById(R.id.bPodsjetnik);
        bVidiSale.setOnClickListener(this);
        bIzracunajPotrebe.setOnClickListener(this);
        bGlazba.setOnClickListener(this);
        bFoto.setOnClickListener(this);
        bCvijece.setOnClickListener(this);
        bSastaviSL.setOnClickListener(this);
        bPodsjetnik.setOnClickListener(this);
        bDodajGoste.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.bGlazba:
                intent.setClass(this,GlazbaActivity.class);
                break;
            case R.id.bVidiSale:
                intent.setClass(this, MapaActivity.class);
                break;
            case R.id.bFoto:
                intent.setClass(this,FotoActivity.class);
                break;
            case R.id.bCvijece:
                intent.setClass(this,CvijeceActivity.class);
                break;
            case R.id.bPodsjetnik:
                intent.setClass(this,ZadaciActivity.class);
                break;
            case R.id.bDodajGoste:
                intent.setClass(this, GostiActivity.class);
                break;
            case R.id.bIzracunajPotrebe:
                intent.setClass(this, PotrebeActivity.class);
                break;
            case R.id.bSastaviSL:
                intent.setClass(this, SLActivity.class);
                break;
        }
        this.startActivity(intent);
    }

}
