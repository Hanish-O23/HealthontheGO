package com.example.hanish.health_on_the_go;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Disease_Activity extends AppCompatActivity {
    String dis;
    SharedPreferences sp;
    TextView tvDisName,tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_);


        tvDisName = (TextView) findViewById(R.id.tvDisName);
        sp = getSharedPreferences("D1",MODE_PRIVATE);
        String dis = sp.getString("Disease", "");
        tvDisName.setText(dis);







    }
}
