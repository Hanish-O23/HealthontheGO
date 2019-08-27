package com.example.hanish.health_on_the_go;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


public class Report extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        SharedPreferences sp = getSharedPreferences("D1", MODE_PRIVATE);
        String n = sp.getString("name", "User");



    }
}
