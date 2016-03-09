package com.example.xuzz.activitytest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class result1Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result1);

        final EditText txtName = (EditText) findViewById(R.id.txtName);
        Button btnWaitResult = (Button) findViewById(R.id.btnWaitResult);
        btnWaitResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(result1Activity.this);
                dialog.setTitle("提醒")
                .setMessage("确认跳转吗？")
                .setCancelable(false)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent goIntent = new Intent(result1Activity.this, result2Activity.class);

                        String strInfo = txtName.getText().toString() + ",i am from result1Activity";
                        goIntent.putExtra("from", strInfo);

                        startActivityForResult(goIntent, 1);
                    }
                }).setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result1, menu);
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
    protected  void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        TextView txtWaitResult = (TextView) findViewById(R.id.txtWaitResult);
        switch (requestCode)
        {
            case 1:
                switch (resultCode)
                {
                    case RESULT_OK:
                        txtWaitResult.setText(data.getStringExtra("data_return"));
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
