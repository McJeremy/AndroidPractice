package com.example.xuzz.databasetest;

import android.net.Uri;

/**
 * Created by xuzz on 2016/3/7.
 */
public class Book {

    public static final String TABLE_NAME ="book";

    public static final String ID = "id";
    public static final String AUTHOR = "author";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String PAGES = "pages";

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;

    public static final String AUTHORY = "com.example.xuzz.database.provider";

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd."+AUTHORY+".book";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."+AUTHORY+".book";

    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORY+"/book");

    public static final String CREATE_BOOK = "create table "+TABLE_NAME+"("
            +ID+" integer primary key autoincrement,"
            +AUTHOR+" text,"
            +PRICE+" real,"
            +PAGES+" integer,"
            +NAME+" text"
            +")";
}
