package com.rma.ipanduric.weddie;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PotrebeActivity extends AppCompatActivity implements View.OnClickListener {


    Button bHrana, bPice, bOstalo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potrebe);
        bHrana = (Button) findViewById(R.id.bHrana);
        bPice = (Button) findViewById(R.id.bPice);
        bOstalo = (Button) findViewById(R.id.bOstalo);
        bHrana.setOnClickListener(this);
        bPice.setOnClickListener(this);
        bOstalo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.bHrana:
                intent.setClass(this,HranaActivity.class);
                break;
            case R.id.bPice:
                intent.setClass(this, PiceActivity.class);
                break;
            case R.id.bOstalo:
                intent.setClass(this,OstaloActivity.class);
                break;
        }
        this.startActivity(intent);
    }

}