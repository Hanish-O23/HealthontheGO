package com.example.hanish.health_on_the_go;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hanish.health_on_the_go.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homeactivity extends AppCompatActivity {
    CardView cvLogout,cvReport,cvCamera,cvDisease,cvHealth;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final static String TAG = "homeactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        cvLogout = (CardView) findViewById(R.id.cvLogout);
        cvHealth = (CardView) findViewById(R.id.cvHealthTips);
        cvDisease = (CardView) findViewById(R.id.cvDisease);
        mAuth = FirebaseAuth.getInstance();

        cvReport = (CardView)findViewById(R.id.cvReport);
        cvCamera = (CardView) findViewById(R.id.cvLeaderboard);

        cvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(homeactivity.this, LoginActivity.class);
                startActivity(i);
                Toast.makeText(homeactivity.this, "Logged Out.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        cvHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeactivity.this,TipsActivity.class);
                startActivity(i);
            }
        });
        cvDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeactivity.this,Questionaire.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideleft,R.anim.slideleft);
            }
        });
        cvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeactivity.this,camera.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideleft,R.anim.slideleft);
            }
        });
        cvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homeactivity.this,Report.class);
                startActivity(i);
            }
        });

    }
}

