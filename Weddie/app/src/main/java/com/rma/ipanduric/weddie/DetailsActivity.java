package com.rma.ipanduric.weddie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.webkit.WebBackForwardList;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    TextView tvIme, tvWeb, tvTel, tvWeb1, tvTel1;
    ImageView ivWeb;
    Bundle extra1, extra2, extra3, extra4;
    public static final String IME = "ime";
    public static final String WEB = "web";
    public static final String TEL = "tel";
    public static final String PIC = "pic";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initialize();
        SetUp();

    }

    private void SetUp() {
        Intent startingIntent = this.getIntent();
        extra1 = startingIntent.getExtras();
        extra2 = startingIntent.getExtras();
        extra3 = startingIntent.getExtras();
        extra4 = startingIntent.getExtras();
        if(extra1 != null && extra2 != null && extra3 != null && extra4 != null) {
            try {
                extra1.containsKey(IME);
                extra2.containsKey(WEB);
                extra3.containsKey(TEL);
                extra4.containsKey(PIC);
                String ime = extra1.getString(IME);
                String web = extra2.getString(WEB);
                String tel = extra3.getString(TEL);
                int pic = extra4.getInt(PIC);
                tvIme.setText(ime);
                tvWeb.setText(web);
                Linkify.addLinks(tvWeb, Linkify.WEB_URLS);
                tvTel.setText(tel);
                ivWeb.setImageResource(pic);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initialize() {
        tvIme = (TextView) findViewById(R.id.tvIme);
        tvWeb = (TextView) findViewById(R.id.tvWeb);
        tvTel = (TextView) findViewById(R.id.tvTel);
        ivWeb = (ImageView) findViewById(R.id.ivPic);
        tvTel1 = (TextView) findViewById(R.id.tvTel1);
        tvWeb1 = (TextView) findViewById(R.id.tvWeb1);

    }
}
