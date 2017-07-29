package com.rma.ipanduric.weddie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ipand on 29.7.2017..
 */

public class OnAlarmReceiver extends BroadcastReceiver{

    private static final String TAG = ComponentInfo.class.getCanonicalName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received wake up from alarm manager.");
        Log.e("On Receive",context.toString());
        long rowid = intent.getExtras().getLong(ReminderManager.KEY_ROWID);
        WakeReminderIntentService.acquireStaticLock(context);
        Intent i = new Intent(context, ReminderService.class);
        i.putExtra(ReminderManager.KEY_ROWID, rowid);
        context.startService(i);
    }
}
