package com.rma.ipanduric.weddie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

public class CvDetailsActivity extends AppCompatActivity {

    TextView tvIme, tvAdr, tvTel, tvAdr1, tvTel1;
    Bundle extra1, extra2, extra3;
    public static final String IME = "ime";
    public static final String ADR = "adr";
    public static final String TEL = "tel";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cv_details);
        initialize();
        SetUp();

    }

    private void SetUp() {
        Intent startingIntent = this.getIntent();
        extra1 = startingIntent.getExtras();
        extra2 = startingIntent.getExtras();
        extra3 = startingIntent.getExtras();
        if(extra1 != null && extra2 != null && extra3 != null) {
            try {
                extra1.containsKey(IME);
                extra2.containsKey(ADR);
                extra3.containsKey(TEL);
                String ime = extra1.getString(IME);
                String adr = extra2.getString(ADR);
                String tel = extra3.getString(TEL);
                tvIme.setText(ime);
                tvAdr.setText(adr);
                tvTel.setText(tel);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize() {
        tvIme = (TextView) findViewById(R.id.tvIme);
        tvAdr = (TextView) findViewById(R.id.tvAdr);
        tvTel = (TextView) findViewById(R.id.tvTel);
        tvTel1 = (TextView) findViewById(R.id.tvTel1);
        tvAdr1 = (TextView) findViewById(R.id.tvAdr1);

    }
}
