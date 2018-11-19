package com.example.administrator.warehouse;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;
    DatabaseHandler2 db2;
    SQLiteDatabase mdb2;
    EditText user,pass;
    String check1,check2;
    Button login2;
    String e1,e2;
    public final String TAG = "North";

    String[] Allattribute = {
            DatabaseHandler2.Username,
            DatabaseHandler2.Password
    };
    boolean Status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        /*db = new DatabaseHandler(this);
        db2 = new DatabaseHandler2(this);
        db.addRecord("0001","มาม่า","ห่อ","ินค้าอุปโภคบริโภค","5","7.00","คลังสินค้า","12/11/2561","1","1");
        db2.addRecord("north1116","123456789");*/
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login2 = (Button) findViewById(R.id.login);
    }
    public void login(View v){
        db2 = new DatabaseHandler2(this);
        check1 = user.getText().toString().trim();
        check2 = pass.getText().toString().trim();
        mdb2 = db2.getWritableDatabase();
        Cursor cursor2 = mdb2.query(DatabaseHandler2.TABLE_NAME,Allattribute , null,null,null,null,null);

        int i =0;
        int n = cursor2.getCount();
        Log.i(TAG,"StartSeach" + " " +n + " time");
        cursor2.moveToFirst();

        //Log.i("north",cursor2.getString(1));
        while (i<n){
            Log.i(TAG,i+" time");
            e1 = cursor2.getString(0);
            e2 = cursor2.getString(1);
            if(check1.equals(e1)&&check2.equals(e2)){
                Log.i(TAG,"User found");
                Status = true;
                break;
            }
            else {
                Log.i(TAG,"User Not found");
            }
            i++;
            if (!cursor2.isLast()){
                cursor2.moveToNext();
            }
        }
        cursor2.close();
        if (Status==true){
            Toast.makeText(this,"Login Complete!",Toast.LENGTH_SHORT);
            Intent itn = new Intent(this,Mainmenu.class);
            startActivity(itn);
        }
        else {
            Context Logincontext = getApplicationContext();
            Toast.makeText(Logincontext,"Invalid Username or password",Toast.LENGTH_SHORT);
        }
        /*
        Intent itn = new Intent(this,Mainmenu.class);
        startActivity(itn);
        Toast.makeText(this,"Login Complete!",Toast.LENGTH_SHORT);*/
    }
    public void Killapp(View v){
        onDestroy();
    }
}
