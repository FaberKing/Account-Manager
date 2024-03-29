package com.example.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Query insert data
    public void insert(String nama, String username, String password, String email ) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAMA, nama);
        contentValue.put(DatabaseHelper.USERNAME, username);
        contentValue.put(DatabaseHelper.PASSWORD, password);
        contentValue.put(DatabaseHelper.EMAIL, email);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    // Query ambil/read data
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAMA, DatabaseHelper.USERNAME, DatabaseHelper.PASSWORD, DatabaseHelper.EMAIL };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Query update data
    public int update(long _id, String nama, String username, String password, String email) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAMA, nama);
        contentValues.put(DatabaseHelper.USERNAME, username);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        contentValues.put(DatabaseHelper.EMAIL, email);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    // Query delete data
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }


}