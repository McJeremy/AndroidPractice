package com.example.xuzz.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by xuzz on 2016/3/7.
 */
public class MyDatabaseOpenHelper extends SQLiteOpenHelper {

    private Context mcontext;



    public MyDatabaseOpenHelper(Context context,String name,SQLiteDatabase.CursorFactory cursorFactory,int version)
    {
        super(context,name,cursorFactory,version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Book.CREATE_BOOK);
        db.execSQL(Category.CREATE_CATEGORY);
        //Toast.makeText(mcontext,"Create Database Book succeeded!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists category");
        onCreate(db);
    }
}
