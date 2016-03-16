package com.example.xuzz.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NotifyActivity extends AppCompatActivity implements View.OnClickListener {

    private Notification.Builder notify;
    private NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);

        ((Button)findViewById(R.id.btnShowNotify)).setOnClickListener(this);
        ((Button)findViewById(R.id.btnCancelNotify)).setOnClickListener(this);

        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);


        notify=new Notification.Builder(this);

        notify.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL);

        notify.setContentTitle("显示于屏幕顶端状态栏的文本")
                    .setContentText("take me to your heart")
                    .setSmallIcon(R.drawable.warning_square_blue)
                    .setTicker("Micheal learn to rock");


        Intent i = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);

        notify.setContentIntent(pi);

        notificationManager.notify(0,notify.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        notificationManager.cancel(0);
    }
}
