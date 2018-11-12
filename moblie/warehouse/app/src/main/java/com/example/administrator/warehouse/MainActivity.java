package com.example.administrator.warehouse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db = new DatabaseHandler(this);
        setContentView(R.layout.activity_main);

    }
    public void login(View v){
        Intent itn = new Intent(this,Mainmenu.class);
        startActivity(itn);
    }
    public void Killapp(View v){
        System.exit(1);
    }
}
