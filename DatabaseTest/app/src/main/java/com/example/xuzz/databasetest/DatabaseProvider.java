package com.example.xuzz.databasetest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by xuzz on 2016/3/7.
 */
public class DatabaseProvider extends ContentProvider {

    MyDatabaseOpenHelper dbHelper;
    SQLiteDatabase db;

    public static final String AUTHORY = "com.example.xuzz.database.provider";

    private static  UriMatcher uriMatcher;

    static
    {
        uriMatcher=new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORY,"book",Book.BOOK_DIR);
        uriMatcher.addURI(AUTHORY,"book/*",Book.BOOK_ITEM);
        uriMatcher.addURI(AUTHORY,"category",Category.CATEGORY_DIR);
        uriMatcher.addURI(AUTHORY,"category/*",Category.CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseOpenHelper(getContext(),"BookStore.db",null,3);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        db=  dbHelper.getReadableDatabase();
        Cursor cursor= null;
        switch (uriMatcher.match(uri))
        {
            case Book.BOOK_DIR:
                cursor = db.query(Book.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Book.BOOK_ITEM:
                String bookID = uri.getPathSegments().get(1);
                cursor = db.query(Book.TABLE_NAME,projection,Book.ID+"=?",new String[]{bookID},null,null,sortOrder);
                break;
            case Category.CATEGORY_DIR:
                cursor = db.query(Category.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Category.CATEGORY_ITEM:
                String categoryID = uri.getPathSegments().get(1);
                cursor = db.query(Category.TABLE_NAME,projection,Category.ID+"=?",new String[]{categoryID},null,null,sortOrder);
                break;
            default:
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        String type = null;
        switch (uriMatcher.match(uri))
        {
            case Book.BOOK_DIR:
                type = Book.CONTENT_TYPE;
                break;
            case Book.BOOK_ITEM:
                type=Book.CONTENT_TYPE;
                break;
            case Category.CATEGORY_DIR:
                type=Category.CONTENT_TYPE;
                break;
            case Category.CATEGORY_ITEM:
                type=Category.CONTENT_ITEM_TYPE;
                break;
            default:
        }
        return type;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri uriReturn = null;
        db  = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri))
        {
            case Book.BOOK_DIR:
            case Book.BOOK_ITEM:
                long bookID = db.insert(Book.TABLE_NAME, null, values);

                uriReturn =Uri.parse(Book.CONTENT_URI+"/"+bookID);
                break;
            case Category.CATEGORY_DIR:
            case Category.CATEGORY_ITEM:
                long cID = db.insert(Category.TABLE_NAME,null,values);
                uriReturn = Uri.parse(Category.CONTENT_URI+"/"+cID);
                break;
            default:
                break;
        }

        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int affectRows = 0;
        db  = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri))
        {
            case Book.BOOK_DIR:
                affectRows = db.delete(Book.TABLE_NAME,selection,selectionArgs);
                break;
            case Book.BOOK_ITEM:
                String bookID = uri.getPathSegments().get(1);
                affectRows = db.delete(Book.TABLE_NAME,Book.ID+"=?",new String[]{bookID});
                break;
            case Category.CATEGORY_DIR:
                affectRows = db.delete(Category.TABLE_NAME,selection,selectionArgs);
                break;
            case Category.CATEGORY_ITEM:
                String cID = uri.getPathSegments().get(1);
                affectRows = db.delete(Category.TABLE_NAME,Category.ID+"=?",new String[]{cID});
                break;
            default:
                break;
        }
        return affectRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int affectRows = 0;
        db  = dbHelper.getWritableDatabase();
        switch (uriMatcher.match(uri))
        {
            case Book.BOOK_DIR:
                affectRows = db.update(Book.TABLE_NAME, values, selection, selectionArgs);
                break;
            case Book.BOOK_ITEM:
                String bookID = uri.getPathSegments().get(1);
                affectRows = db.update(Book.TABLE_NAME, values,Book.ID+"=?", new String[]{bookID});
                break;
            case Category.CATEGORY_DIR:
                affectRows = db.update(Category.TABLE_NAME, values, selection, selectionArgs);
                break;
            case Category.CATEGORY_ITEM:
                String cID = uri.getPathSegments().get(1);
                affectRows = db.update(Category.TABLE_NAME, values, Category.ID+"=?", new String[]{cID});
                break;
            default:
                break;
        }
        return affectRows;
    }
}
