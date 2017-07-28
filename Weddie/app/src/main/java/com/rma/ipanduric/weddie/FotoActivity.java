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

public class FotoActivity extends AppCompatActivity {

    private ListView lvFoto;
    ArrayList<FotoItem> foto;
    EditText etfSearch;
    private String[] ime;
    private String[] web;
    private String[] tel;
    private int[] pic = {R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece,
            R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece,
            R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece,
            R.drawable.bcvijece, R.drawable.bcvijece, R.drawable.bcvijece};


    public static final String IME = "ime";
    public static final String WEB = "web";
    public static final String TEL = "tel";
    public static final String PIC = "pic";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        ime = getResources().getStringArray(R.array.fIme);
        web = getResources().getStringArray(R.array.fWeb);
        tel = getResources().getStringArray(R.array.fTel);

        foto = new ArrayList<FotoItem>();

        //add the items to array list
        for (int i = 0; i < 26; i++) {
            foto.add(new FotoItem(ime[i], web[i], tel[i], pic[i]));
        }
        SetUpAdapter();
        lvClicked();
    }



    private void SetUpAdapter() {

        lvFoto = (ListView) findViewById(R.id.lvFoto);
        final FotoAdapter fotoAdapter = new FotoAdapter(this, foto);
        lvFoto.setAdapter(fotoAdapter);

        etfSearch = (EditText) findViewById(R.id.etfSearch);
        etfSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                String text = etfSearch.getText().toString().toLowerCase(Locale.getDefault());
                fotoAdapter.filter(text);
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
        this.lvFoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FotoAdapter adapter = (FotoAdapter) parent.getAdapter();
                FotoItem item = (FotoItem) adapter.getItem(position);
                String ime = item.getfIme();
                String web = item.getfWeb();
                String tel = item.getfTel();
                int pic = item.getfPic();
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
