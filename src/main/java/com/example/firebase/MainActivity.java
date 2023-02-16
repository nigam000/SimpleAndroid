package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.firebase.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText msg;
    ImageButton imageView;
    ListView listView;
    chat chat1;
    ArrayList<String> msglist;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        msg=findViewById(R.id.editText);
        imageView=findViewById(R.id.imageButton);
        Firebase.setAndroidContext(this);
        final Firebase ref=new Firebase("https://chatapplication-3e5c4-default-rtdb.firebaseio.com/chat");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chat chat= new chat("nigam",msg.getText().toString());
                ref.push().setValue(chat);
                msg.setText("");
            }
        });
        listView=(ListView)findViewById(R.id.listview);
        chat1=new chat();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference mref=firebaseDatabase.getReference("chat");
        msglist= new ArrayList<>();
        adapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.list_layout,R.id.textView,msglist);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                msglist.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    chat1=ds.getValue(chat.class);
                    msglist.add(chat1.getUsername()+":\n"+"  "+chat1.getMessage());

                }listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}