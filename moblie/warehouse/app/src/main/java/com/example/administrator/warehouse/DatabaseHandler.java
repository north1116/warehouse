package com.example.administrator.warehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "warehouse.db";
    static final String TABLE_NAME = "Product";
    private static final int DATABASE_VERSION = 1;

    public String z1, z2;

    public static final String Serial = "Serial";
    public static final String Name = "Name";
    public static final String Unit = "Unit";
    public static final String Type = "Type";
    public static final String Count = "Count";
    public static final String Price = "Price";
    public static final String Location = "Location";
    public static final String Date = "Date";
    public static final String Row = "product_row";
    public static final String Column = "product_column";


    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("my", "สร้าง ฐานข้อมูล");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("CREATE TABLE "
                + TABLE_NAME
                + " (Serial text PRIMARY KEY , "+ "Name text," + "Unit text,"
               + "Type text,"+"Count text,"+"price text,"+"Location text,"+"Date text,"
                       +" product_row text ,"+"product_column text);");

        Log.i("my", "สร้างตาราง ");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TABLE_NAME, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreates the database with a new version
        onCreate(db);
    }

    public long addRecord(String e1, String e2,String e3,String e4,String e5,String  e6,String e7,String e8,String e9,String e10) {

        Log.d("my", "เพิ่มข้อมูล" + e1 + " " + e2);

        ContentValues values = new ContentValues();
        values.put(Serial, e1);
        values.put(Name, e2);
        values.put(Unit, e3);
        values.put(Type, e4);
        values.put(Count, e5);
        values.put(Price, e6);
        values.put(Location, e7);
        values.put(Date, e8);
        values.put(Row, e9);
        values.put(Column, e10);

        SQLiteDatabase db = this.getWritableDatabase();

        long row = db.insert(DatabaseHandler.TABLE_NAME, null, values);
        Log.d(TABLE_NAME, "inserted at row " + row + " " + e1 + e2);
        db.close(); // Closing database connection
        return row;
    }
    public int updateContact(String e1, String e2,String e3,String e4,String e5,String  e6,String e7,String e8,String e9,String e10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Name, e2);
        values.put(Unit, e3);
        values.put(Type, e4);
        values.put(Count, e5);
        values.put(Price, e6);
        values.put(Location, e7);
        values.put(Date, e8);
        values.put(Row, e9);
        values.put(Column, e10);

        // updating row
        return db.update(TABLE_NAME, values, "Serial = ?",
                new String[] { String.valueOf(e1) });
    }

    public int getRecordCount() {
        String countQuery = "SELECT _id FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(countQuery, null);
        return cur.getCount();
    }
}
/*package com.example.phasuwut.project_android_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydb6.db";
    static final String TABLE_NAME = "mytable";
    private static final int DATABASE_VERSION = 1;

    static final String COLUMN_NAME = "name";
    static final String COLUMN_AGE = "age";
public String z1,z2,z3,z4,z5,z6;

    public static final String COL_ISBN = "ISBN";
    public static final String COL_bookname = "bookname";
    public static final String COL_book_author = "book_author";
    public static final String COL_number = "number";
    public static final String COL_price = "price";
    public static final String COL_All_prices = "All_prices";

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("my", "สร้าง ฐานข้อมูล");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("my", "สร้างตาราง ");

        db.execSQL("CREATE TABLE "
                + TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_ISBN + " varchar(30), " +
                 COL_bookname + " varchar(50), " +
                 COL_book_author + " varchar(50), " +
                 COL_number + " varchar(50), " +
                 COL_price + " varchar(50), " +
                COL_All_prices + " varchar(50));");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(TABLE_NAME, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Recreates the database with a new version
        onCreate(db);
    }

    public long addRecord(String e1, String e2,String e3, String e4,String e5, String e6) {

        Log.d("my","เพิ่มข้อมูล" + e1 +" " +e2+" " +e3+" " +e4+" " +e5+" " +e6);
        ContentValues values = new ContentValues();
        values.put(COL_ISBN, e1); //
        values.put(COL_bookname, e2); //
        values.put(COL_book_author, e3); //
        values.put(COL_number, e4); //
        values.put(COL_price, e5); //
        values.put(COL_All_prices, e6); //


        SQLiteDatabase db = this.getWritableDatabase();

        long row = db.insert(DatabaseHandler.TABLE_NAME, null, values);
        Log.d(TABLE_NAME,"inserted at row " + row + " " + e1 + e2);
        db.close(); // Closing database connection
        return row;
    }

    public int getRecordCount() {
        String countQuery = "SELECT _id FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(countQuery, null);
        return cur.getCount();
    }

    public ArrayList<String> getAllTodoList(){
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<String> todoList = new ArrayList<String>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+";",null); //ดึงข้อมูลจากฐานข้อมูล
        cursor.moveToFirst();//ให้เคอร์เซอร์ชี้ไปที่ตำแหน่งแรก

        Log.i("my", "qqqqqqqqqq");
        while (!cursor.isAfterLast()){//วนรลูปเล็กคอดแรกไปยังเล็กคอดสุดท้าย
           // Log.i("my", "ppppppppp");
            //todoList.add(cursor.getString(1));
            //Log.i("my", cursor.getString(1));
            //cursor.moveToNext();//เลื่อนไปแถว  J

            ArrayList<String> todoList1 = new ArrayList<String>();
            todoList1.add(cursor.getString(0));
            todoList1.add(cursor.getString(1));
            todoList1.add(cursor.getString(2));
            todoList1.add(cursor.getString(3));
            todoList1.add(cursor.getString(4));
            todoList1.add(cursor.getString(5));
            todoList1.add(cursor.getString(6));

            todoList.add(todoList1.toString());
            cursor.moveToNext();//เลื่อนไปแถว  J



        }
        cursor.close();
        return todoList;
    }

    public ArrayList<String> getE_TodoList(String sa) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();


        String sqlSelect = "SELECT * FROM " + TABLE_NAME + " WHERE " + "ISBN" + "=?";
        Cursor cursor = db.rawQuery(sqlSelect, new String[]{sa});



        //Cursor cursor = db.rawQuery("SELECT * FROM " +  + ";", null); //ดึงข้อมูลจากฐานข้อมูล
        cursor.moveToFirst();//ให้เคอร์เซอร์ชี้ไปที่ตำแหน่งแรก

        while (!cursor.isAfterLast()){//วนรลูปเล็กคอดแรกไปยังเล็กคอดสุดท้าย
            // Log.i("my", "ppppppppp");
            //todoListนยฃ.add(cursor.getString(1));
            //Log.i("my", cursor.getString(1));
            //cursor.moveToNext();//เลื่อนไปแถว  J

            ArrayList<String> todoList1 = new ArrayList<String>();
            todoList1.add(cursor.getString(0));
            todoList1.add(cursor.getString(1));
            todoList1.add(cursor.getString(2));
            todoList1.add(cursor.getString(3));
            todoList1.add(cursor.getString(4));
            todoList1.add(cursor.getString(5));
            todoList1.add(cursor.getString(6));
            z1 =cursor.getString(1);
                    z2=cursor.getString(2);
            z3=cursor.getString(3);
                    z4=cursor.getString(4);
            z5=cursor.getString(5);
                    z6=cursor.getString(6);

            todoList.add(todoList1.toString());
            cursor.moveToNext();//เลื่อนไปแถว  J



        }
        cursor.close();
        return todoList;
    }

    public Cursor getE_Cursor(String sa) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> todoList = new ArrayList<String>();
        String[] column = {"_id",COL_ISBN,COL_bookname,
        COL_book_author,
        COL_number,
        COL_price,
        COL_All_prices};
        Cursor cursor = db.query(TABLE_NAME,column,COL_ISBN+" LIKE ?",new String[]{sa},null,null,null,null);
        Log.d("Boss",String.valueOf(cursor.getCount()));
        return cursor;
    };


    public int updateContact(String recID, String n2, String n3, String n4, String n5, String n6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_bookname,n2 );//1
        values.put(COL_book_author,n3 );//2
        values.put(COL_number, n4);//3
        values.put(COL_price, n5);//4
        values.put(COL_All_prices,n6);//5


        // updating row
        return db.update(TABLE_NAME, values, "ISBN = ?",
                new String[] { String.valueOf(recID) });
    }


    public void deleteRecord(String recID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "ISBN = ?",
                new String[] { String.valueOf(recID) });
        db.close();
    }




}
*/