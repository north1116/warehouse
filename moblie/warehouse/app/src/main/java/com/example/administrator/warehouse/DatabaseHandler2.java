package com.example.administrator.warehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Login.db";
    static final String TABLE_NAME = "Login";
    private static final int DATABASE_VERSION = 1;

    public String z1, z2;

    public static final String Username = "Username";
    public static final String Password = "Password";



    public DatabaseHandler2 (Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("my", "สร้าง ฐานข้อมูล");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "
                + TABLE_NAME
                //+ " (Username text PRIMARY KEY , "+ "Password text);");
                + "(Username text PRIMARY KEY," + "Password text);");
        Log.i("my", "สร้างตาราง ");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TABLE_NAME, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreates the database with a new version
        onCreate(db);
    }

    public long addRecord(String e1, String e2) {

        Log.d("my", "เพิ่มข้อมูล" + e1 + " " + e2);

        ContentValues values = new ContentValues();
        values.put(Username, e1);
        values.put(Password, e2);


        SQLiteDatabase db = this.getWritableDatabase();

        long row = db.insert(DatabaseHandler2.TABLE_NAME, null, values);
        Log.d(TABLE_NAME, "inserted at row " + row + " " + e1 + e2);
        db.close(); // Closing database connection
        return row;
    }

    public int getRecordCount() {
        String countQuery = "SELECT _id FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(countQuery, null);
        return cur.getCount();
    }
}

