package com.example.administrator.warehouse;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
    DatabaseHandler db;
    SQLiteDatabase mdb;
    ArrayList<String> unit = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> place = new ArrayList<String>();
    ArrayList<EditText> data = new ArrayList<EditText>();
    TextView t1;
    EditText e2;
    EditText e5;
    EditText e6;
    EditText e8;
    EditText e9;
    EditText e10;
    String w1;
    boolean datastatus = false;
    public final String TAG = "North";
    String QR;
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
    Spinner unitdata;
    Spinner typedata;
    Spinner placedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        unitdata = (Spinner) findViewById(R.id.productunit);
        typedata = (Spinner) findViewById(R.id.producttype);
        placedata = (Spinner) findViewById(R.id.place);
        db = new DatabaseHandler(this);
        mdb = db.getWritableDatabase();
        CreateSpinner();
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, unit);
        unitdata.setAdapter(unitAdapter);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, type);
        typedata.setAdapter(typeAdapter);
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, place);
        placedata.setAdapter(placeAdapter);
        t1 = (TextView) findViewById(R.id.productId);
        e2 = (EditText) findViewById(R.id.productname);
        e5 = (EditText) findViewById(R.id.productcount);
        e6 = (EditText) findViewById(R.id.productprice);
        e8 = (EditText) findViewById(R.id.date);
        e9 = (EditText) findViewById(R.id.row);
        e10 = (EditText) findViewById(R.id.column);
        setTextview(w1);
        data.add(e2);
        data.add(e5);
        data.add(e6);
        data.add(e8);
        data.add(e9);
        data.add(e10);

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
        Cursor cursor = mdb.query(DatabaseHandler.TABLE_NAME, allattribute, null, null, null, null, null);
        Log.i(TAG, "StartSeach");
        cursor.moveToFirst();
        Log.i(TAG, "Start at " + cursor.getString(0));
        boolean find = false;
        int i = 0;
        int n = cursor.getCount();
        while (i < n) {
            w1 = cursor.getString(0);
            Log.i(TAG, "Seaching " + w1 + " at " + i);
            if (check.equals(w1)) {
                find = true;
                Log.i(TAG, "Find Data!! " + w1);
                break;
            }
            i++;
            if (!cursor.isLast()) {
                cursor.moveToNext();
            }
        }
        cursor.close();
        Context Read = getApplicationContext();
        if (find == true) {
            Toast.makeText(Read, "Data was found", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Showdata");
            setTextview(w1);
        } else {
            Toast.makeText(Read, "Data not be found", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "Failed");
        }
    }

   public void setTextview(String w1) {
        Toast.makeText(this,"ตั้งค่า text",Toast.LENGTH_SHORT).show();
        t1 =  (TextView) findViewById(R.id.productId);
        t1.setText(w1);
        //Log.i(TAG,"Show Done");
    }

    public void Updatedata(View v){
        db = new DatabaseHandler(this);
        mdb = db.getWritableDatabase();
        e8 = (EditText) findViewById(R.id.date);
        String[] database = new String[10];
        database[0] = t1.getText().toString().trim();
        database[1] = e2.getText().toString().trim();
        database[2] = unitdata.getSelectedItem().toString().trim();
        database[3] = typedata.getSelectedItem().toString().trim();
        database[4] = e5.getText().toString().trim();
        database[5] = e6.getText().toString().trim();
        database[6] = placedata.getSelectedItem().toString().trim();
        database[7] = e8.getText().toString().trim();
        database[8] = e9.getText().toString().trim();
        database[9] = e10.getText().toString().trim();
        for (int i=0;i<database.length;i++){
            if (database[i].trim().length()>0){
                datastatus = true;
            }
            else {
                datastatus = false;
                break;
            }
        }
        if (datastatus){
            db.updateContact(database[0],database[1],database[2],database[3],database[4],database[5],database[6],database[7],database[8],database[9]);
            Toast.makeText(this,"Edit done",Toast.LENGTH_SHORT).show();
            callBack(v);

        }
        else {
            Context Datacontext = getApplicationContext();
            Toast.makeText(Datacontext,"Please input all data",Toast.LENGTH_SHORT).show();
        }
    }

    public void callBack(View v){
        Intent itn = new Intent(this,Mainmenu.class);
        startActivity(itn);
    }

    public void CreateSpinner(){
        unit.add("ชิ้น");
        unit.add("ห่อ");
        unit.add("อัน");
        unit.add("ชุด");
        unit.add("กล่อง");
        unit.add("ตัว");
        type.add("อุปกรณ์การแพทย์");
        type.add("ของใช้");
        type.add("อิเล็กทรอนิกส์");
        type.add("สินค้าอุปโภคบริโภค");
        type.add("เครื่องสำอางค์");
        type.add("สินค้าอินเตอร์เทรน");
        place.add("คลังสินค้า");
        place.add("ร้านค้า");
    }

    public void Killapp(View v){
        Intent itn = new Intent(this,Mainmenu.class);
        startActivity(itn);
    }


}
