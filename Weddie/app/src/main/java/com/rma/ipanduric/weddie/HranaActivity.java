package com.rma.ipanduric.weddie;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
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

public class HranaActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvpPotrebe;
    ArrayList<PotrebeItem> potrebe;
    PotrebeAdapter potrebeAdapter;
    Button bIzracunaj, bSpremi;
    EditText etBroj;

    private String[] naziv;
    private String[] jedinica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odabrane_potrebe);
        lvpPotrebe = (ListView) findViewById(R.id.lvpPotrebe);
        etBroj = (EditText) findViewById(R.id.etBroj);
        bIzracunaj = (Button) findViewById(R.id.bIzracunaj);
        bSpremi = (Button) findViewById(R.id.bSpremi);
        bIzracunaj.setOnClickListener(this);
        bSpremi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bIzracunaj: {

                if ((etBroj.getText().toString().isEmpty())) {
                    Toast.makeText(this, "Unesite broj osoba!", Toast.LENGTH_SHORT).show();
                } else if (!(etBroj.getText().toString().isEmpty())) {
                    String no = etBroj.getText().toString();       //this will get a string
                    double broj = Double.parseDouble(no);
                    naziv = getResources().getStringArray(R.array.Hrana);
                    jedinica = getResources().getStringArray(R.array.hJedinice);
                    double[] rezultat = {Math.round(broj/57), Math.round(broj/45), Math.round(broj*0.875), Math.round(broj*0.625), (broj/5), Math.round(broj/13),
                            (broj/8), (broj/50), (broj/16), Math.round(broj/6), Math.round(broj/11), (broj/400),
                            Math.round(broj/33), Math.round(broj/26), Math.round(broj/2.5), (broj/5), (broj/200), (broj/100), Math.round(broj/800),
                            (broj/200), (broj/400), Math.round(broj/400), Math.round(broj/5), (broj/10), Math.round(broj/30)};

                    potrebe = new ArrayList<PotrebeItem>();
                    //add the items to array list
                    for (int i = 0; i < 25; i++) {
                        potrebe.add(new PotrebeItem(naziv[i], rezultat[i], jedinica[i]));
                    }

                    potrebeAdapter = new PotrebeAdapter(this, potrebe);
                    lvpPotrebe.setAdapter(potrebeAdapter);
                    bSpremi.setVisibility(View.VISIBLE);

                }
            }
            break;

            case R.id.bSpremi: {
                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXST);
                //https://www.sitepoint.com/requesting-runtime-permissions-in-android-m-and-n/
                getWholeListViewItemsToBitmap(lvpPotrebe);

            }
            break;
        }

    }

    //http://grishma102.blogspot.hr/2015/04/capture-whole-listview-and-create-image.html

    public void getWholeListViewItemsToBitmap(ListView p_ListView) {
        ListView listview = p_ListView;
        ListAdapter adapter = listview.getAdapter();
        int itemscount = adapter.getCount();
        int allitemsheight = 0;
        List<Bitmap> bmps = new ArrayList<Bitmap>();
        for (int i = 0; i < itemscount; i++) {
            View childView = adapter.getView(i, null, listview);
            childView.measure(
                    View.MeasureSpec.makeMeasureSpec(listview.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
            childView.setDrawingCacheEnabled(true);
            childView.buildDrawingCache();
            bmps.add(childView.getDrawingCache());
            allitemsheight += childView.getMeasuredHeight();
        }
        Bitmap bigbitmap = Bitmap.createBitmap(listview.getMeasuredWidth(), allitemsheight,
                Bitmap.Config.ARGB_8888);
        Canvas bigcanvas = new Canvas(bigbitmap);
        bigcanvas.drawColor(Color.WHITE);
        Paint paint = new Paint();
        int iHeight = 0;
        for (int i = 0; i < bmps.size(); i++) {
            Bitmap bmp = bmps.get(i);
            bigcanvas.drawBitmap(bmp, 0, iHeight, paint);
            iHeight += bmp.getHeight();
            bmp.recycle();
            bmp = null;
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + "_weddie";
        storeImage(bigbitmap, imageFileName + ".jpeg");
    }

    /**
     * Convert the bitmap into image and save it into the sdcard.
     *
     * @param imageData -Bitmap image.
     * @param filename  -Name of the image.
     * @return
     */

    public boolean storeImage(Bitmap imageData, String filename) {
        // get path to external storage (SD card)
        File sdIconStorageDir = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Weddie/");

        // create storage directories, if they don't exist
        sdIconStorageDir.mkdirs();
        try {
            String filePath = sdIconStorageDir.toString() + File.separator + filename;
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
            Toast.makeText(this, "Slika dostupna u mapi: " + filePath, Toast.LENGTH_LONG).show();
            // choose another format if PNG doesn't suit you
            imageData.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            bos.flush();
            bos.close();
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(sdIconStorageDir)));

        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        } catch (IOException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return false;
        }

        return true;
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(HranaActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(HranaActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(HranaActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(HranaActivity.this, new String[]{permission}, requestCode);
            }
        }
    }
    static final Integer WRITE_EXST = 0x3;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){




            Toast.makeText(this, "Pristup dozvoljen!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Pristup odbijen!", Toast.LENGTH_SHORT).show();
        }
    }



}

