package com.example.hanish.health_on_the_go;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.hanish.health_on_the_go.R.string.email;

public class SignUp extends AppCompatActivity {
    Button btnSignup;
    EditText etName, etuserEmailId, etPhone, etpassword, etconfirmpassword;
    TextView tvalready_user;
    CheckBox cbTerms;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    SharedPreferences sp;


    private static   String TAG = "SignUp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnSignup = (Button) findViewById(R.id.btnsignup);
        etconfirmpassword = (EditText) findViewById(R.id.etconfirmpassword);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etName = (EditText) findViewById(R.id.etfullName);
        etPhone = (EditText) findViewById(R.id.etmobileNumber);
        etuserEmailId = (EditText) findViewById(R.id.etuserEmailId);
        tvalready_user = (TextView) findViewById(R.id.already_user);
        cbTerms = (CheckBox) findViewById(R.id.cbterms);
        mAuth = FirebaseAuth.getInstance();
        sp = getSharedPreferences("D1", MODE_PRIVATE);

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

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = etName.getText().toString();
                String num = etPhone.getText().toString();
                String password = etpassword.getText().toString();
                String cpassword = etconfirmpassword.getText().toString();
                String email = etuserEmailId.getText().toString();
                Toast.makeText(SignUp.this, ""+name+email+password, Toast.LENGTH_SHORT).show();

                if (name.length() == 0) {
                    etName.setError("Name is too short");
                    etName.requestFocus();
                    return;
                }
                if (email.length() == 0) {
                    etuserEmailId.setError("Enter approriate email id");
                    etuserEmailId.requestFocus();
                    return;
                }
                if (num.length() == 0) {
                    etPhone.setError("Enter a Number");
                    etPhone.requestFocus();
                    return;
                }
                if (num.length() != 10) {
                    etPhone.setError("Invalid Number");
                    etPhone.setText("");
                    etPhone.requestFocus();
                    return;
                }

                if (password.length() < 7) {
                    etpassword.setError("password is too short");
                    etpassword.requestFocus();
                    return;
                }
                if (!password.equals(cpassword)) {
                    etpassword.setError("Password doesn't matches");
                    etpassword.setText("");
                    etconfirmpassword.setText("");
                    etpassword.requestFocus();
                    return;
                }
                if (!cbTerms.isChecked()) {
                    Toast.makeText(SignUp.this, "Please tick the terms and conditions ", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name", name);
                            editor.commit();
                            Toast.makeText(SignUp.this, "You are Registered", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this,LoginActivity.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(SignUp.this, "Unsucessfull"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                }
            });

        tvalready_user.setOnClickListener(new View.OnClickListener()

                                          {
                                              @Override
                                              public void onClick (View view){
                                                  Intent i = new Intent(SignUp.this, LoginActivity.class);
                                                  startActivity(i);
                                              }
                                          }

        );

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

