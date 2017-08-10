package com.rma.ipanduric.weddie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class CvijeceActivity extends AppCompatActivity {

    private ListView lvCvijece;
    ArrayList<CvijeceItem> cvijece;
    EditText etcSearch;
    private String[] ime;
    private String[] adr;
    private String[] tel;
    private String[] lat;
    private String[] lon;


    public static final String IME = "ime";
    public static final String ADR = "adr";
    public static final String TEL = "tel";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cvijece);
        ime = getResources().getStringArray(R.array.cIme);
        adr = getResources().getStringArray(R.array.cAdr);
        tel = getResources().getStringArray(R.array.cTel);
        lat = getResources().getStringArray(R.array.cLatitude);
        lon = getResources().getStringArray(R.array.cLongitude);
        cvijece = new ArrayList<CvijeceItem>();

        //add the items to array list
        for (int i = 0; i < 40; i++) {
            cvijece.add(new CvijeceItem(ime[i], adr[i], tel[i], lat[i], lon[i]));
        }
        SetUpAdapter();
        lvClicked();
    }

    private void lvClicked() {
        this.lvCvijece.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CvijeceAdapter adapter = (CvijeceAdapter) parent.getAdapter();
                CvijeceItem item = (CvijeceItem) adapter.getItem(position);
                String ime = item.getcIme();
                String adr = item.getcAdr();
                String tel = item.getcTel();
                String lat = item.getcLat();
                String lon = item.getcLong();
                Intent intent = new Intent(getApplicationContext(), CvDetailsActivity.class);
                intent.putExtra(IME, ime);
                intent.putExtra(ADR, adr);
                intent.putExtra(TEL, tel);
                intent.putExtra(LATITUDE, lat);
                intent.putExtra(LONGITUDE, lon);
                startActivity(intent);
            }
        });
        }


    private void SetUpAdapter() {

        lvCvijece = (ListView) findViewById(R.id.lvCvijece);
        final CvijeceAdapter cvijeceAdapter = new CvijeceAdapter(this, cvijece);
        lvCvijece.setAdapter(cvijeceAdapter);

        etcSearch = (EditText) findViewById(R.id.etcSearch);
        etcSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = etcSearch.getText().toString().toLowerCase(Locale.getDefault());
                cvijeceAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }
}
