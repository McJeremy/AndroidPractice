package com.example.xuzz.uicustomviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity {

    private List<Fruit> fruits = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);

        initFruits();

        ListView fruitList =(ListView) findViewById(R.id.fruit_list_view);
        FruitAdapter fruitAdapter = new FruitAdapter(CustomListViewActivity.this,R.layout.fruit_item,fruits);
        fruitList.setAdapter(fruitAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom_list_view, menu);
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

    private void initFruits ()
    {
        Fruit f = new Fruit("Apple",R.drawable.msnonline);
        fruits.add(f);

        Fruit f1 = new Fruit("Banana",R.drawable.noqq);
        fruits.add(f1);

        Fruit f3 = new Fruit("Pear ",R.drawable.nomsn);
        fruits.add(f3);
    }
}
