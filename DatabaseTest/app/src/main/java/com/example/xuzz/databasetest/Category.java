package com.example.xuzz.databasetest;

import android.net.Uri;

/**
 * Created by xuzz on 2016/3/7.
 */
public class Category {

    public static final  String TABLE_NAME = "category";

    public static final String ID = "id";
    public static final String NAME = "category_name";
    public static final String CODE = "category_price";


    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    public static final String AUTHORY = "com.example.xuzz.database.provider";

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+AUTHORY+".category";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+AUTHORY+".category";

    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORY+"/category");

    public static final String CREATE_CATEGORY= "create table "+TABLE_NAME+"("
            +ID+" integer primary key autoincrement,"
            +NAME+" text,"
            +CODE+" integer"
            +")";
}
