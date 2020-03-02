package com.belstiyezengaw.usermanagement;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText user_Name,password;
    private Button button_login;
    private Object String;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_Name = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        button_login=findViewById(R.id.button_login);

        mAuth = FirebaseAuth.getInstance();

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = user_Name.getText().toString().trim();
                final String passwd = password.getText().toString().trim();
                 checkLogin(username,passwd);
             Intent intent=new Intent(getApplicationContext(),UserPage.class);
             startActivity(intent);
                if(TextUtils.isEmpty(username)){

                    return;
                }
                if (TextUtils.isEmpty(passwd)){

                    return;
                }
                if(password.length()>=6){

                }

            }
        });
            }


    public void checkLogin(String username,String passwd){
        mAuth.signInWithEmailAndPassword(username, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        } else {
                            Toast.makeText(getApplicationContext(), "please enter correctly", Toast.LENGTH_SHORT).show();
                            ;
                        }
                    }
                });
    }

    @Override
    public void onStart(){
        super.onStart();
    }
}

