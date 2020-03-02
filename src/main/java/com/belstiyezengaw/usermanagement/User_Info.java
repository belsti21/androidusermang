package com.belstiyezengaw.usermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class User_Info extends AppCompatActivity {

    private TextView fName,lName,uName,eMail,password,mobile,gender;
    private String userId;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        fName=findViewById(R.id.tv1);
        lName=findViewById(R.id.tv2);
        uName=findViewById(R.id.tv3);
        eMail=findViewById(R.id.tv4);
        password=findViewById(R.id.tv5);
        mobile=findViewById(R.id.tv6);
        gender=findViewById(R.id.tv7);
        Intent intent=getIntent();
        userId=intent.getStringExtra("key");
        firestore=FirebaseFirestore.getInstance();
        DocumentReference documentReference=firestore.collection("totalusers").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                fName.setText(documentSnapshot.getString("firstName"));
                lName.setText(documentSnapshot.getString("lastName"));
                uName.setText(documentSnapshot.getString("userName"));
                eMail.setText(documentSnapshot.getString("email"));
                password.setText(documentSnapshot.getString("password"));
                mobile.setText(documentSnapshot.getString("mobile"));
                gender.setText(documentSnapshot.getString("Gender"));
            }
        });
    }
}

