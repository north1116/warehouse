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
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class Edit extends AppCompatActivity {
    ArrayList<String> unit = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<String> place = new ArrayList<String>();
    ArrayList<EditText> data = new ArrayList<EditText>();
    TextView text1;
    EditText edit2;
    EditText edit3;
    EditText edit4;
    EditText edit5;
    EditText edit6;
    EditText edit7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final ImageView imageView = (ImageView) findViewById(R.id.QRCode);
        imageView.setVisibility(View.INVISIBLE);
        final Spinner unitdata = (Spinner) findViewById(R.id.productunit);
        final Spinner typedata = (Spinner) findViewById(R.id.producttype);
        final Spinner placedata = (Spinner) findViewById(R.id.place);

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
        text1 = (TextView) findViewById(R.id.productId);
        edit2 = (EditText) findViewById(R.id.productname);
        edit3 = (EditText) findViewById(R.id.productcount);
        edit4 = (EditText) findViewById(R.id.productprice);
        edit5 = (EditText) findViewById(R.id.date);
        edit6 = (EditText) findViewById(R.id.row);
        edit7 = (EditText) findViewById(R.id.column);
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
                QRdata += text1.getText().toString().trim() + ",";
                for (int i = 1; i < data.size(); i++) {
                    if (i == 2) {
                        QRdata += unitdata.getSelectedItem().toString().trim() + ",";
                        QRdata += typedata.getSelectedItem().toString().trim() + ",";
                    }

                    if (i == 4) {
                        QRdata += placedata.getSelectedItem().toString().trim() + ",";
                    }
                    QRdata += data.get(i).getText().toString().trim();
                    if (i < data.size() - 1) {
                        QRdata += ",";
                    }

                }
                Context context = getApplicationContext();
                Toast.makeText(context, QRdata, Toast.LENGTH_SHORT).show();
                if (QRdata != null) {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    try {
                        BitMatrix code = multiFormatWriter.encode(QRdata, BarcodeFormat.QR_CODE, 300, 300);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap bitmap = encoder.createBitmap(code);
                        imageView.setImageBitmap(bitmap);
                        imageView.setVisibility(View.VISIBLE);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
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
    public void CreateSpinner(){
        unit.add("ชิ้น");
        unit.add("ห่อ");
        unit.add("อัน");
        type.add("ขนม");
        type.add("ของใช้");
        place.add("คลังสินค้า");
        place.add("ร้านค้า");
    }
    public void Killapp(View v){
        System.exit(1);
    }
}
