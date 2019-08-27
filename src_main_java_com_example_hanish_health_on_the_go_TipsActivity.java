package com.example.hanish.health_on_the_go;

import android.support.v7.app.AppComactivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

public class TipsActivity extends AppCompatActivity {
    TextView tip1, tip2, tip3, tip4;
    String Tip[] = {"Don't Eat Uncovered food", "Wash Your Hands Properly before eating food", "Don't skip your Meals", "Don't dehydrate your body"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Random rand = new Random();
        tip1 = (TextView) findViewById(R.id.tvTip1);
        tip2 = (TextView) findViewById(R.id.tvTip2);
        tip3 = (TextView) findViewById(R.id.tvTip3);
        tip4 = (TextView) findViewById(R.id.tvTip4);


        Database_acess database_acess =Database_acess.getInstance(getApplicationContext());
        database_acess.open();
        Health_databasehandler hdb = new Health_databasehandler(this);
        String qstn = Tip[rand.nextInt(10)];
        tip1.setText(Tip[0]);
        tip2.setText(Tip[1]);
        tip3.setText(Tip[2]);
        tip4.setText(Tip[3]);

        database_acess.close();
    }

    }

