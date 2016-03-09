package com.example.xuzz.androidthreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView txtView ;

    private static final int MESSAGE_WHAT = 1;

    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            if(msg.what==MESSAGE_WHAT)
            {
                txtView.setText("Nice to meet you !");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtView=(TextView)findViewById(R.id.txtView);
        Button btnChangeTxt = (Button)findViewById(R.id.btnChangeTxt);
        btnChangeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what=MESSAGE_WHAT;
                        handler.sendMessage(msg);
                        /*
                        //下面的这句，在子线程里更新UI，会导致挂掉。
                        //android不支持在子线程里更新UI。用Handler来异步处理
                        txtView.setText("Nice to meet you !");
                        */
                    }
                }).start();
            }
        });
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
}
