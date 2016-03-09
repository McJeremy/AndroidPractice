package com.example.xuzz.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by xuzz on 2016/3/8.
 */
public class MyService extends Service {

    public static final String TAG = "My Service";
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy()
    {
        Log.d(TAG, "onDestory");
        super.onDestroy();


    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startID)
    {   Log.d(TAG,"onStartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ////

                stopSelf();
            }
        });
        return super.onStartCommand(intent,flags,startID);
    }
}
