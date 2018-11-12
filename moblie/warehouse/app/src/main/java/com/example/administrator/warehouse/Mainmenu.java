package com.example.administrator.warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }
    public void killapp(View v){
        System.exit(1);
    }
    public void Inbound(View v){
        Intent itn = new Intent(this,Inbound.class);
        startActivity(itn);
    }
    public void Inventory(View v){
        Intent itn = new Intent(this,Inventory.class);
        startActivity(itn);
    }
    public void Outbound(View v){
        Intent itn = new Intent(this,Outbound.class);
        startActivity(itn);
    }
    public void Edit(View v){
        Intent itn = new Intent(this,Edit.class);
        startActivity(itn);
    }
    public void Seach(View v){
        Intent itn = new Intent(this,Seach.class);
        startActivity(itn);
    }

}
