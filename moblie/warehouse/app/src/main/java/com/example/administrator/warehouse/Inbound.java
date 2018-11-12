package com.example.administrator.warehouse;

import android.content.Context;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbound);
        final ImageView iv1 = (ImageView) findViewById(R.id.QRCode);
        iv1.setVisibility(View.INVISIBLE);
        final Spinner unitdata = (Spinner) findViewById(R.id.productunit);
        final Spinner typedata = (Spinner) findViewById(R.id.producttype);
        final Spinner placedata = (Spinner) findViewById(R.id.place);
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
        edit1 = (EditText) findViewById(R.id.productId);
        edit2 = (EditText) findViewById(R.id.productname);
        edit3 = (EditText) findViewById(R.id.productcount);
        edit4 = (EditText) findViewById(R.id.productprice);
        edit5 = (EditText) findViewById(R.id.date);
        edit6 = (EditText) findViewById(R.id.row);
        edit7 = (EditText) findViewById(R.id.column);
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
                Context context = getApplicationContext();
                Toast.makeText(context,QRdata,Toast.LENGTH_SHORT).show();
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
        });
    }
    public void Killapp(View v){
        System.exit(1);
    }
    public void CreateSpinner(){
        unit.add("ชิ้น");
        unit.add("ห่อ");
        unit.add("อัน");
        type.add("ขนม");
        type.add("ของใช้");
        place.add("คลังสินค้า");
        place.add("ร้านค้า");
    }
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
