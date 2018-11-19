package com.example.administrator.warehouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class Inbound extends AppCompatActivity {

    DatabaseHandler db;
    Spinner unitdata;
    Spinner typedata;
    Spinner placedata;
    ArrayList<String> unit = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> place = new ArrayList<String>();
    ArrayList<EditText> data = new ArrayList<EditText>();
    EditText edit1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;
    boolean datastatus = false;
    boolean QRStatus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbound);
        final ImageView iv1 = (ImageView) findViewById(R.id.QRCode);
        iv1.setVisibility(View.INVISIBLE);
        unitdata = (Spinner) findViewById(R.id.productunit);
        typedata = (Spinner) findViewById(R.id.producttype);
        placedata = (Spinner) findViewById(R.id.place);
        CreateSpinner();
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,unit);
        unitdata.setAdapter(unitAdapter);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,type);
        typedata.setAdapter(typeAdapter);
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,place);
        placedata.setAdapter(placeAdapter);
        db = new DatabaseHandler(this);

        edit1 = (EditText) findViewById(R.id.productId);
        edit2 = (EditText) findViewById(R.id.productname);
        edit3 = (EditText) findViewById(R.id.productcount);
        edit4 = (EditText) findViewById(R.id.productprice);
        edit5 = (EditText) findViewById(R.id.date);
        edit6 = (EditText) findViewById(R.id.row);
        edit7 = (EditText) findViewById(R.id.column);
        edit1.setText(null);
        edit2.setText(null);
        edit3.setText(null);
        edit4.setText(null);
        edit5.setText(null);
        edit6.setText(null);
        edit7.setText(null);
        data.add(edit1);
        data.add(edit2);
        data.add(edit3);
        data.add(edit4);
        data.add(edit5);
        data.add(edit6);
        data.add(edit7);
        Button CreateQRCode = (Button) findViewById(R.id.QRCodeButton);
        CreateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String QRdata = new String();
                for (int i=0;i<data.size();i++){
                    if(data.get(i).getText().toString().trim().length()>0){
                        QRStatus = true;

                    }
                    else {
                        QRStatus = false;
                        break;
                    }

                }
                for (int i=0;i<data.size();i++){
                    if(i==2){
                        QRdata += unitdata.getSelectedItem().toString()+ ",";
                        QRdata += typedata.getSelectedItem().toString()+ ",";
                    }

                    if (i==4){
                        QRdata += placedata.getSelectedItem().toString()+ ",";
                    }
                    QRdata += data.get(i).getText().toString();
                    if (i<data.size()-1){
                        QRdata += ",";
                    }

                }
                /*Context context = getApplicationContext();
                Toast.makeText(context,QRdata,Toast.LENGTH_SHORT).show();*/

                if (QRStatus==true){
                    if (QRdata != null){
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        try {
                            BitMatrix code = multiFormatWriter.encode(QRdata, BarcodeFormat.QR_CODE,300,300);
                            BarcodeEncoder encoder = new BarcodeEncoder();
                            Bitmap bitmap = encoder.createBitmap(code);
                            iv1.setImageBitmap(bitmap);
                            iv1.setVisibility(View.VISIBLE);
                        }catch (WriterException e){
                            e.printStackTrace();
                        }
                    }
                }
                else {
                    Context QRcontext = getApplicationContext();
                    Toast.makeText(QRcontext,"Please input all data",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button adddatabase = (Button) findViewById(R.id.button11);
        adddatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] database = new String[10];
                database[0] = data.get(0).getText().toString().trim();
                database[1] = data.get(1).getText().toString().trim();
                database[2] = unitdata.getSelectedItem().toString().trim();
                database[3] = typedata.getSelectedItem().toString().trim();
                database[4] = data.get(2).getText().toString().trim();
                database[5] = data.get(3).getText().toString().trim();
                database[6] = placedata.getSelectedItem().toString().trim();
                database[7] = data.get(4).getText().toString().trim();
                database[8] = data.get(5).getText().toString().trim();
                database[9] = data.get(6).getText().toString().trim();
                for (int i=0;i<data.size();i++){
                    if (data.get(i).getText().toString().trim().length()>0){
                        datastatus = true;
                    }
                    else {
                        datastatus = false;
                        break;
                    }
                }
                if (datastatus){
                    db.addRecord(database[0],database[1],database[2],database[3],database[4],database[5],database[6],database[7],database[8],database[9]);
                    Context Datacontext = getApplicationContext();
                    Toast.makeText(Datacontext,"add",Toast.LENGTH_SHORT).show();
                    callBack(v);

                }
                else {
                    Context Datacontext = getApplicationContext();
                    Toast.makeText(Datacontext,"Please input all data",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Killapp(View v){
        System.exit(1);
    }
    public void CreateSpinner(){
        unit.add("ชิ้น");
        unit.add("ห่อ");
        unit.add("อัน");
        unit.add("ชุด");
        unit.add("กล่อง");
        unit.add("ตัว");
        type.add("อุปกรณ์ทางการแพทย์");
        type.add("ของใช้");
        type.add("อุปกรณ์อิเล็กทรอนิกส์");
        type.add("สินค้าสำหรับอุปโภคบริโภค");
        type.add("เครื่องสำอางค์");
        type.add("สินค้าอินเตอร์เทรน");
        place.add("คลังสินค้า");
        place.add("ร้านค้า");
    }
    public void callBack(View v){
        Intent itn = new Intent(this,Mainmenu.class);
        startActivity(itn);
    }
    /*public void addDatabase(View v){
        for (int i=0;i<data.size();i++){
            if(i==2){
                QRdata += unitdata.getSelectedItem().toString().trim()+ ",";
                QRdata += typedata.getSelectedItem().toString().trim()+ ",";
            }

            if (i==4){
                QRdata += placedata.getSelectedItem().toString().trim()+ ",";
            }
            QRdata += data.get(i).getText().toString().trim();
            if (i<data.size()-1){
                QRdata += ",";
            }

        }
    }*/
   /*public void ScanBarcode(View v) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scan");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.setBarcodeImageEnabled(false);
        intentIntegrator.initiateScan();
    }*/


    /*@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult re =IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(re !=null){
            if(re.getContents()==null){


            }
            else{

            }
        }
        else{

        }
        }*/
}
