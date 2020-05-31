package com.example.mypro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registeruser extends AppCompatActivity {
    public EditText name,email,pass,phone;
    TextView back;

    Button signup;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
                name=(EditText)findViewById(R.id.editText);
                email=(EditText)findViewById(R.id.edittext1);
                pass=(EditText)findViewById(R.id.edittext2);
                phone=(EditText)findViewById(R.id.pass1);
                back=(TextView)findViewById(R.id.text);
                signup=(Button)findViewById(R.id.button3);
                firebaseAuth=FirebaseAuth.getInstance();
                if (firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String n=name.getText().toString().trim();
                        String e=email.getText().toString().trim();
                        String ph=phone.getText().toString().trim();
                        String pa=pass.getText().toString().trim();
                        if(TextUtils.isEmpty(n)){
                            Toast.makeText(Registeruser.this, "please enter user name", Toast.LENGTH_SHORT).show();
                        }
                        if(TextUtils.isEmpty(e)){
                            Toast.makeText(Registeruser.this, "please enter email adress", Toast.LENGTH_SHORT).show();
                        }
                        if(TextUtils.isEmpty(ph)){
                            Toast.makeText(Registeruser.this, "please enter phone number", Toast.LENGTH_SHORT).show();
                        }
                        if(TextUtils.isEmpty(pa)){
                            Toast.makeText(Registeruser.this, "please enter password", Toast.LENGTH_SHORT).show();
                        }
                        firebaseAuth.createUserWithEmailAndPassword(e,pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(Registeruser.this, "user registerd", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),task.getException()+"user registration fail",Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }

                });

            }
            public void onclick(View activity){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }

