package com.example.xuzz.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xuzz on 2016/3/8.
 */
public class MyIntentService extends IntentService {

    public MyIntentService()
    {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for(int i = 0;i<3;i++) {
                     //打印当前线程的id
                     Log.d("MyIntentService","IntentService线程的id是："+Thread.currentThread().getId());
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException e) {
                         // TODO Auto-generated catch block
                         e.printStackTrace();
                     }
                 }
    }

    @Override
    public void onDestroy()
    {
        Log.d("MyIntentService", "onDestory");
        super.onDestroy();
    }
}
