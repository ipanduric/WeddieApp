package com.rma.ipanduric.weddie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ipand on 29.7.2017..
 */

public class ReminderService extends WakeReminderIntentService {

    public ReminderService() {
        super("ReminderService");
    }

    @Override
    void doReminderWork(Intent intent) {
        Log.d("ReminderService", "Doing work.");
        Long rowId = intent.getExtras().getLong(ReminderManager.KEY_ROWID);

        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, ZadaciActivity.class);
        notificationIntent.putExtra(ReminderManager.KEY_ROWID, rowId);
        ArrayList<ZadatakItem> zadaci = Database.getInstance(this).getAllZadaci();
        String naziv,opis;
        naziv="";
        opis ="";
        int i=0;
        for(i=0;i<zadaci.size();i++){
            if((long)(zadaci.get(i).getID())==rowId){
                naziv = zadaci.get(i).getzNaziv();
                opis = zadaci.get(i).getzOpis();
            }
        }

        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ReminderService.this);
               builder.setAutoCancel(true).setSmallIcon(R.drawable.logo_round)
                .setContentTitle(naziv)
                .setSmallIcon(R.drawable.logo_round)
                .setContentText(opis)
                .setContentIntent(pi)
                .setLights(Color.WHITE, 2000, 1000)
                .setVibrate(new long[]{500,600,700,800})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Notification notification = builder.getNotification();
        mgr.notify(0, notification);
    }
}
