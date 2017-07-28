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

public class GlazbaActivity extends AppCompatActivity {

    private ListView lvGlazba;
    ArrayList<GlazbaItem> glazba;
    EditText etSearch;
    private String[] ime;
    private String[] web;
    private String[] tel;
    private int[] pic = {R.drawable.allegro, R.drawable.alkotest, R.drawable.babylon, R.drawable.bestman, R.drawable.bolero, R.drawable.bolero, R.drawable.cappuccino, R.drawable.djprof, R.drawable.essekeri, R.drawable.gsvox, R.drawable.jovalius,
            R.drawable.krivispoj, R.drawable.lyra, R.drawable.quattro, R.drawable.passage, R.drawable.slavoniaband, R.drawable.standard,
            R.drawable.still, R.drawable.stoposto, R.drawable.stravianae, R.drawable.bosutski, R.drawable.dyaco, R.drawable.dzentlmeni, R.drawable.fijaker,
            R.drawable.garavi, R.drawable.kas, R.drawable.lege, R.drawable.libero, R.drawable.trecasmjena, R.drawable.viva, R.drawable.west};


    public static final String IME = "ime";
    public static final String WEB = "web";
    public static final String TEL = "tel";
    public static final String PIC = "pic";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glazba);
        ime = getResources().getStringArray(R.array.gIme);
        web = getResources().getStringArray(R.array.gWeb);
        tel = getResources().getStringArray(R.array.gTel);

        glazba = new ArrayList<GlazbaItem>();

        //add the items to array list
        for (int i = 0; i < 31; i++) {
            glazba.add(new GlazbaItem(ime[i], web[i], tel[i], pic[i]));
        }
        SetUpAdapter();
        lvClicked();
    }

    private void SetUpAdapter() {

        lvGlazba = (ListView) findViewById(R.id.lvGlazba);
        final GlazbaAdapter glazbaAdapter = new GlazbaAdapter(this, glazba);
        lvGlazba.setAdapter(glazbaAdapter);

        //https://stackoverflow.com/questions/42720130/how-to-filter-from-list-view-items-containing-image-and-text-in-custom-adapter-b

        etSearch = (EditText) findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = etSearch.getText().toString().toLowerCase(Locale.getDefault());
                glazbaAdapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }


    private void lvClicked() {
        this.lvGlazba.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GlazbaAdapter adapter = (GlazbaAdapter) parent.getAdapter();
                GlazbaItem item = (GlazbaItem) adapter.getItem(position);
                String ime = item.getgIme();
                String web = item.getgWeb();
                String tel = item.getgTel();
                int pic = item.getgPic();
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra(IME, ime);
                intent.putExtra(WEB, web);
                intent.putExtra(TEL, tel);
                intent.putExtra(PIC, pic);
                startActivity(intent);
            }
        });
    }




}
