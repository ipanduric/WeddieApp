package com.rma.ipanduric.weddie;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DodajZadatakActivity extends AppCompatActivity {

    private static final int DATE_PICKER_DIALOG = 0;
    private static final int TIME_PICKER_DIALOG = 1;
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String TAG = "tag";

    EditText mNaziv, mOpis;
    Button mDatum, mVrijeme, mPotvrdi;
    Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zadatak);
        this.SetUpUI();
    }

    private void SetUpUI() {
        mCalendar = Calendar.getInstance();
        mNaziv = (EditText) findViewById(R.id.etNaziv);
        mOpis = (EditText) findViewById(R.id.etOpis);
        mDatum = (Button) findViewById(R.id.bDatum);
        mVrijeme = (Button) findViewById(R.id.bVrijeme);
        mPotvrdi = (Button) findViewById(R.id.bPotvrdi);
        setUpCal();
    }

    private void setUpCal() {

        mDatum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_DIALOG);
            }
        });


        mVrijeme.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog(TIME_PICKER_DIALOG);
            }
        });

        mPotvrdi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                spremi();
                setResult(RESULT_OK);
                Toast.makeText(DodajZadatakActivity.this, "Spremljeno", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
            case DATE_PICKER_DIALOG:
                return showDatePicker();
            case TIME_PICKER_DIALOG:
                return showTimePicker();
        }
        return super.onCreateDialog(id);
    }

    private DatePickerDialog showDatePicker() {

        DatePickerDialog datePicker = new DatePickerDialog(DodajZadatakActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateButtonText();
            }
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        return datePicker;
    }

    private TimePickerDialog showTimePicker() {
        TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                mCalendar.set(Calendar.MINUTE, minute);
                updateTimeButtonText();
            }
        }, mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE), true);

        return timePicker;
    }

    private void updateTimeButtonText() {
        SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
        String timeForButton = timeFormat.format(mCalendar.getTime());
        mVrijeme.setText(timeForButton);
    }

    private void updateDateButtonText() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String dateForButton = dateFormat.format(mCalendar.getTime());
        mDatum.setText(dateForButton);
    }



    private void spremi() {
        String naziv = mNaziv.getText().toString();
        String opis = mOpis.getText().toString();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        dateTimeFormat.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
        String zadatakDatum = dateTimeFormat.format(mCalendar.getTime());
        ZadatakItem zadatak = new ZadatakItem(naziv,opis,zadatakDatum);
        Database.getInstance(getApplicationContext()).dodajZadatak(zadatak);
        int ID = Database.getInstance(this).getAllZadaci().get(Database.getInstance(this).getAllZadaci().size()-1).getID();
        Log.i(TAG, "Do tu je došlo");
        new ReminderManager(this).postaviZadatak(ID, mCalendar);
        Log.i(TAG, "Prošlo i ovdje");

    }


}
