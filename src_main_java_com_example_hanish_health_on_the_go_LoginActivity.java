package com.example.hanish.health_on_the_go;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    TextView tvcreateAccount;
    EditText etlogin_emailid,etlogin_password;
    CheckBox cbShow;
    private static final String TAG ="Main activity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvcreateAccount = (TextView)findViewById(R.id.tvcreateAccount);
        etlogin_emailid = (EditText) findViewById(R.id.etlogin_emailid);
        etlogin_password = (EditText) findViewById(R.id.etlogin_password);
        btnlogin = (Button)findViewById(R.id.btnlogin);
        cbShow = (CheckBox) findViewById(R.id.cbshow);
        if (cbShow.isChecked()) {
            // show password
            etlogin_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }

        cbShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    // show password
                    etlogin_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    etlogin_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
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


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etlogin_emailid.getText().toString();
                String password = etlogin_password.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Logged in as"+mAuth.getCurrentUser(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this,homeactivity.class);
                            startActivity(i);
                            finish();

                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
        tvcreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,SignUp.class);
                startActivity(i);
            }
        });

    }



    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
