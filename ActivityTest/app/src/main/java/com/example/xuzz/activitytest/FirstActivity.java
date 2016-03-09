package com.example.xuzz.activitytest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by xuzz on 2016/3/3.
 */
public class FirstActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.first_layout);

        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,"xuzz,Welcome you !",Toast.LENGTH_SHORT).show();
            }
        });

        Button button_goto2 = (Button) findViewById(R.id.button_goto2);
        button_goto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(FirstActivity.this,"即将关闭 !",Toast.LENGTH_SHORT).show();

             //   finish();
                Intent goto2 = new Intent(FirstActivity.this,result1Activity.class);
                startActivity(goto2);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.add:
                Toast.makeText(FirstActivity.this,"click Add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove:
                Toast.makeText(FirstActivity.this,"click Remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
