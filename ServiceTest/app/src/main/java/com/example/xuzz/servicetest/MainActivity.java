package com.example.xuzz.servicetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button button1_start_service;
    private Button button2_stop_service;
    private Button button3_start_intentservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1_start_service = (Button) findViewById(R.id.button1_start_service);
        button2_stop_service = (Button) findViewById(R.id.button2_stop_service);
        button3_start_intentservice= (Button) findViewById(R.id.button3_start_intentservice);

        button1_start_service.setOnClickListener(this);
        button2_stop_service.setOnClickListener(this);
        button3_start_intentservice.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        switch (v.getId())
        {
            case R.id.button1_start_service:
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
                break;
            case R.id.button2_stop_service:
                Intent intent2 = new Intent(MainActivity.this,MyService.class);
                stopService(intent2);
                break;
            case R.id.button3_start_intentservice:
                Log.d("MainActivity", "主线程的id是：" + Thread.currentThread().getId());
                Intent intent3 = new Intent(MainActivity.this,MyIntentService.class);
                startService(intent3);
                break;
        }
    }
}
