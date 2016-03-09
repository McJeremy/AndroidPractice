package com.example.xuzz.databasetest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyDatabaseOpenHelper dbHelper ;
    SQLiteDatabase db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseOpenHelper(this,"BookStore.db",null,3);

        Button btnCreateDB = (Button)findViewById(R.id.btnCreateDB);
        btnCreateDB.setOnClickListener(this);
        Button btnUpdateDB = (Button)findViewById(R.id.btnUpdateDB);
        btnUpdateDB.setOnClickListener(this);
        Button btnInsertData = (Button)findViewById(R.id.btnInsertData);
        btnInsertData.setOnClickListener(this);
        Button btnUpdateData = (Button)findViewById(R.id.btnUpdateData);
        btnUpdateData.setOnClickListener(this);
        Button btnDelData = (Button)findViewById(R.id.btnDelData);
        btnDelData.setOnClickListener(this);
        Button btnQueryData = (Button)findViewById(R.id.btnQueryData);
        btnQueryData.setOnClickListener(this);
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
            case R.id.btnCreateDB:
                db = dbHelper.getWritableDatabase();       //如果不存在会自动创建
                break;
            case R.id.btnUpdateDB:
                db = dbHelper.getWritableDatabase();       //如果不存在会自动创建
                break;
            case R.id.btnInsertData:
                db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("book", null, values);
                values.clear();
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("book", null, values);

                break;
            case R.id.btnUpdateData:
                db = dbHelper.getWritableDatabase();
                db.beginTransaction();
                try {
                    ContentValues updateValue = new ContentValues();
                    updateValue.put("price", 10.99);
                    db.update("book", updateValue, "name=?", new String[]{"The Da Vinci Code"});

                    db.setTransactionSuccessful();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                    db.endTransaction();
                }
                break;
            case R.id.btnDelData:
                db = dbHelper.getWritableDatabase();
                db.delete("book", "pages>?", new String[]{"500"});
                break;
            case R.id.btnQueryData:
                db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("book",null,null,null,null,null,null);
                if(cursor.moveToFirst())
                {
                    do {

                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String autorh = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));

                        Log.d("MainActivity","book name is :"+name);
                    }while(cursor.moveToNext());
                }
                cursor.close();
                break;
            default:
        }
    }
}
