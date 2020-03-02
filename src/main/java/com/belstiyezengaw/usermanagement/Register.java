package com.belstiyezengaw.usermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private Button button_createRegister;
    private RadioButton radioButton_male,radioButton_female;
    private RadioGroup radioGroup_gender;
    private FirebaseFirestore db;
    private TextView textView,textView2;
    private EditText text_fName,text_lName,text_uName,text_Email,text_password,text_mobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button_createRegister=findViewById(R.id.button_register);

        textView=findViewById(R.id.gender);


        text_fName=findViewById(R.id.editText_firstName);
        text_lName=findViewById(R.id.editText_lastName);
        text_uName=findViewById(R.id.editText_userName);
        text_Email=findViewById(R.id.editText_email);
        text_password=findViewById(R.id.editText_Password);
        text_mobile=findViewById(R.id.editText_mobile);

        radioButton_male=findViewById(R.id.male);
        radioButton_female=findViewById(R.id.female);

        radioGroup_gender=findViewById(R.id.radio);

        db = FirebaseFirestore.getInstance();

        button_createRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
                String first = text_fName.getText().toString().trim();
                String last = text_lName.getText().toString().trim();
                String username = text_uName.getText().toString().trim();
                String email = text_Email.getText().toString().trim();
                String password = text_password.getText().toString().trim();
                String mobile = text_mobile.getText().toString().trim();

                Map<String, Object> user = new HashMap<>();
                user.put("first",first);
                user.put("last",last);
                user.put("username",username);
                user.put("email",email);
                user.put("password",password);
                user.put("mobile",mobile);

// Add a new document with a generated ID
                db.collection("totalusers")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }

        });
    }
}

