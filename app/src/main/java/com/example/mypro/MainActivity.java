package com.example.mypro;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText text1,text2,deep;
    Button Login;
    TextView forgetpass,signup;

    private FirebaseAuth.AuthStateListener firebaseAuthStateListener;
    private FirebaseAuth firebaseAuth;
    public void deep(View abc){
        startActivity(new Intent(getApplicationContext(),Register.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=(EditText)findViewById(R.id.text1);
        text2=(EditText)findViewById(R.id.text2);
        signup=(TextView) findViewById(R.id.activity);
        forgetpass=(TextView) findViewById(R.id.forget);
        Login=(Button)findViewById(R.id.login) ;

        firebaseAuth=firebaseAuth.getInstance();



        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Forgetpassword.class));
            }
        });
        firebaseAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(getApplicationContext(),Forgetpassword.class));

                }
            }
        };

        Login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String e = text1.getText().toString().trim();
                String pa = text2.getText().toString().trim();
                if (TextUtils.isEmpty(e) || TextUtils.isEmpty(pa)) {
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    firebaseAuth.signInWithEmailAndPassword(e, pa).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "sign in problem", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                Toast.makeText(MainActivity.this,"user logged in",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                return;
                            }
                        }
                    });
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });
    }
}

