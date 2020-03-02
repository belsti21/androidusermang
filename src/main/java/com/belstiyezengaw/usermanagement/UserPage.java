package com.belstiyezengaw.usermanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class UserPage<RecyclerAdapter> extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<UserModel> users=new ArrayList<>();
    private FirestoreRecyclerAdapter<UserModel,UserView> adapter;
    String userId;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        recyclerView = findViewById(R.id.rvActivity_userPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        Query query = db.collection("users").orderBy("username", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query, UserModel.class).build();
        adapter = new FirestoreRecyclerAdapter<UserModel, UserView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final UserView holder, int position, @NonNull UserModel model) {
                holder.textView.setText(model.getUserName());
                users.add(model);
                holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        getSnapshots().getSnapshot(holder.getAdapterPosition()).getReference().delete();
                        return true;
                    }
                });
            }
            @NonNull
            @Override
            public UserView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_user_info, parent, false);
                return new UserView(view);

            }
        };
        recyclerView.setAdapter(adapter);
    }
    private class UserView extends RecyclerView.ViewHolder {
        public TextView textView;
        public UserView(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.single_user_info);
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null){
            adapter.stopListening();
        }
    }
    private void logout(){
        firebaseAuth.signOut();
        startActivity(new Intent(UserPage.this,MainActivity.class));
        finish();
    }
}
