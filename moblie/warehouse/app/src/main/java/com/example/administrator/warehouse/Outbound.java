package com.example.administrator.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;

public class Outbound extends AppCompatActivity {

    ArrayList<String> place = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outbound);
        final Spinner placedata = (Spinner) findViewById(R.id.place);
        place.add("คลังสินค้า");
        place.add("ร้านค้า");
        ArrayAdapter<String> placeAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_dropdown_item_1line,place);
        placedata.setAdapter(placeAdapter);
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
    public void Killapp(View v){
        System.exit(1);
    }
}
