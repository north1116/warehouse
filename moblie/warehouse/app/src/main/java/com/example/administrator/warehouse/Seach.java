package com.example.administrator.warehouse;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Seach extends AppCompatActivity {
    public final String TAG = "North";
    String QR;
    boolean datastatus = false;
    String[] allattribute = {
            DatabaseHandler.Serial,
            DatabaseHandler.Name,
            DatabaseHandler.Unit,
            DatabaseHandler.Type,
            DatabaseHandler.Count,
            DatabaseHandler.Price,
            DatabaseHandler.Location,
            DatabaseHandler.Date,
            DatabaseHandler.Row,
            DatabaseHandler.Column
    };
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    DatabaseHandler db;
    SQLiteDatabase mdb;
    String e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        setTextview(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10);
    }

    public void ScanBarcode(View v) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scan");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult re =IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(re !=null){
            if(re.getContents()==null){
                Toast.makeText(this,"ไม่ออก",Toast.LENGTH_SHORT).show();
            }
            else{
                try {
                    QR = re.getContents().toString();
                    String[] alldata = QR.split(",");
                    Toast.makeText(this, re.getContents(), Toast.LENGTH_SHORT).show();
                    Log.i(TAG,"Seaching database "+alldata[0]);
                    showData(alldata[0]);
                    //t1.setText(alldata[0]);
                    ///  t3.setText(re.getContents().toString());

                }catch (Exception e){
                    Log.i("my",e.getMessage());
                }

            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    public  void showData(String check) {
        db = new DatabaseHandler(this);
        mdb = db.getWritableDatabase();
        Cursor cursor = mdb.query(DatabaseHandler.TABLE_NAME,allattribute , null,null,null,null,null);
        Log.i(TAG,"StartSeach");
        cursor.moveToFirst();
        Log.i(TAG,"Start at "+ cursor.getString(0));
        boolean find = false;
        int i= 0;
        int n = cursor.getCount();
        while (i<n){
            e1 = cursor.getString(0);
            Log.i(TAG,"Seaching "+e1+ " at "+i);
            if (check.equals(e1)){
                e2 = cursor.getString(1);
                e3 = cursor.getString(2);
                e4 = cursor.getString(3);
                e5 = cursor.getString(4);
                e6 = cursor.getString(5);
                e7 = cursor.getString(6);
                e8 = cursor.getString(7);
                e9 = cursor.getString(8);
                e10 = cursor.getString(9);
                find = true;
                Log.i(TAG,"Find Data!!"+e1);
                break;
            }
            i++;
            if (!cursor.isLast()){
                cursor.moveToNext();
            }
        }
        cursor.close();
        Context Read = getApplicationContext();
        if (find==true){
            Toast.makeText(Read,"Data was found",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"Showdata");
            setTextview(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10);
        }
        else {
            Toast.makeText(Read,"Data not be found",Toast.LENGTH_SHORT).show();
            Log.i(TAG,"Failed");
        }
    }

    public void setTextview(String e1,String e2,String e3,String e4,String e5,String e6,String e7,String e8,String e9,String e10) {
        Toast.makeText(this,"ตั้งค่า text",Toast.LENGTH_SHORT).show();
        t1 =  (TextView) findViewById(R.id.productId);
        t2 =  (TextView) findViewById(R.id.productname);
        t3 =  (TextView) findViewById(R.id.productunit);
        t4 =  (TextView) findViewById(R.id.producttype);
        t5 =  (TextView) findViewById(R.id.productcount);
        t6 =  (TextView) findViewById(R.id.productprice);
        t7 = (TextView) findViewById(R.id.place);
        t8 = (TextView) findViewById(R.id.date);
        t9 =  (TextView) findViewById(R.id.row);
        t10 =  (TextView) findViewById(R.id.column);
        t1.setText(e1);
        t2.setText(e2);
        t3.setText(e3);
        t4.setText(e4);
        t5.setText(e5);
        t6.setText(e6);
        t7.setText(e7);
        t8.setText(e8);
        t9.setText(e9);
        t10.setText(e10);
        //Log.i(TAG,"Show Done");
    }
    public void callBack(View v){
        Intent itn = new Intent(this,Mainmenu.class);
        startActivity(itn);
    }

}
