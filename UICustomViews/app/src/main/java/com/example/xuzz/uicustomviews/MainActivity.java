package com.example.xuzz.uicustomviews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initTestMenu();
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

        //CustomListView 自定义Adapter的练习
        //ChatView 自定义聊天界面的练习
    String[] menus = {"ListView","CustomListView","ChatView"};
    private void initTestMenu()
    {
        ListView testMenu = (ListView) findViewById(R.id.testMenu);

        ArrayAdapter<String> menuAdapter;
        menuAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,menus);

        testMenu.setAdapter(menuAdapter);
        testMenu.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Toast.makeText(MainActivity.this,menus[position],Toast.LENGTH_SHORT).show();
        switch (position)
        {
            case 1:
                Intent intent = new Intent(MainActivity.this,CustomListViewActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_out_right,android.R.anim.slide_in_left);
                break;
            case 2:
                Intent chatIntent = new Intent(MainActivity.this,ChatActivity.class);
                startActivity(chatIntent);
                break;
            default:
                break;
        }

    }
}
