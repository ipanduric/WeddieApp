package com.rma.ipanduric.weddie;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by ipand on 29.7.2017..
 */

public class ReminderManager {

    public static final String KEY_ROWID = "id";
    private static final String TAG = "tag";
    private Context mContext;
    private AlarmManager mAlarmManager;


    public ReminderManager(Context context) {
        mContext = context;
        mAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    }


    public void postaviZadatak(int taskId, Calendar when) {
        Log.i(TAG, "Ušlo u funkciju");
        Intent i = new Intent(mContext, OnAlarmReceiver.class);
        i.putExtra(ReminderManager.KEY_ROWID, (long)taskId);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.set(AlarmManager.RTC_WAKEUP, when.getTimeInMillis(), pi);


    }

    public void ponistiZadatak(int taskId){
        Intent i = new Intent(mContext, OnAlarmReceiver.class);
        i.putExtra(KEY_ROWID, (long)taskId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, taskId, i, PendingIntent.FLAG_UPDATE_CURRENT);
        mAlarmManager.cancel(pendingIntent);
    }
}
