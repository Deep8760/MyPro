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
import com.google.firebase.auth.FirebaseAuth;

public class Forgetpassword extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText useremail;
    Button resetpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        useremail=(EditText)findViewById(R.id.edit);
        resetpass=(Button)findViewById(R.id.button);
        firebaseAuth=firebaseAuth.getInstance();
        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=useremail.getText().toString().trim();
                if(TextUtils.isEmpty(email)){

                    Toast.makeText(Forgetpassword.this, "Fields are empty please enter the email", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Forgetpassword.this,"password sent to email adress",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Forgetpassword.this,MainActivity.class));
                            }
                            else{
                                String message=task.getException().getMessage();
                                Toast.makeText(Forgetpassword.this,"error",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }
    }

