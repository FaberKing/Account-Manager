package com.example.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "tbl_contact";

    // Nama kolom dalam tabel
    public static final String _ID = "_id";
    public static final String NAMA = "nama";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";



    // Nama Database
    static final String DB_NAME = "CBD.DB";

    // Versi Database
    static final int DB_VERSION = 1;

    // Membuat query tabel
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAMA + " TEXT NOT NULL, " + USERNAME + " TEXT, " + PASSWORD +
            " TEXT, " + EMAIL + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Proses Reset data
    public void prosesResetData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String hapusTabel= "DROP TABLE " + TABLE_NAME;
        db.execSQL(hapusTabel);
        onCreate(db);

    }
}
