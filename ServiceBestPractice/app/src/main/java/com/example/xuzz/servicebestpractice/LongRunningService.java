package com.example.xuzz.servicebestpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

/**
 * Created by xuzz on 2016/3/8.
 */
public class LongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent,int flag,int startID)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed at " + new Date().
                        toString());
            };
        });

        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour = 60*60*1000;
        long triggerTime = SystemClock.elapsedRealtime()+anHour;

        Intent rec = new Intent(this,AlarmReceiver.class);
        PendingIntent pRec = PendingIntent.getBroadcast(this,0,rec,0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerTime,pRec);
        return super.onStartCommand(intent,flag,startID);
    }
}
