package com.example.mypro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity {
    EditText text1, text2;
    Button signnup,login;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        text1 = (EditText) findViewById(R.id.email);
        text2 = (EditText) findViewById(R.id.pass);

        signnup = (Button) findViewById(R.id.button3);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
            signnup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = text1.getText().toString().trim();
                    String pas = text2.getText().toString().trim();

                    if (TextUtils.isEmpty(email)) {
                        text1.setError("enter email");
                        return;
                    }
                    if (TextUtils.isEmpty(pas)) {
                        text2.setError("enter password");
                        return;

                    }

                    firebaseAuth.createUserWithEmailAndPassword(email, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "registered", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), task.getException() + "not sucessfull", Toast.LENGTH_LONG).show();
                            }
                        }

                    });


                }
            });

        }

    }


