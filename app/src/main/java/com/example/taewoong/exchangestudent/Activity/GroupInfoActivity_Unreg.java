package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Database.GroupData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Taewoong on 2017-12-06.
 */

public class GroupInfoActivity_Unreg extends AppCompatActivity {

    String Name;
    String About;
    String Genre;
    String Region;
    String userName;

    TextView group_name;
    EditText edit_about;
    EditText edit_genre;
    EditText edit_region;
    Button Join_btn;

    private DatabaseReference mGroupReference;
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupinfo_unreg);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        group_name = (TextView)findViewById(R.id.group_name);
        edit_about = (EditText)findViewById(R.id.edit_about);
        edit_genre = (EditText)findViewById(R.id.edit_genre);
        edit_region = (EditText)findViewById(R.id.edit_region);
        Join_btn = (Button)findViewById(R.id.btn_join);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        Name = intent.getStringExtra("Group_title");
        group_name.setText(Name);

        mDatabaseUser = FirebaseDatabase.getInstance().getReference("users");
        mGroupReference = FirebaseDatabase.getInstance().getReference("groups").child(Name);
        mGroupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GroupData groupData = dataSnapshot.getValue(GroupData.class);
                About = groupData.About;
                Genre = groupData.Genre;
                Region = groupData.Region;
                edit_about.setText(About);
                edit_genre.setText(Genre);
                edit_region.setText(Region);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseUser.child(currentUser.getUid()).child("JoinedGroup").push().setValue(Name);
                mDatabaseUser.child(currentUser.getUid()).child("Name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        userName = dataSnapshot.getValue().toString();
                        FirebaseDatabase.getInstance().getReference("groups").child(Name).child("Member").push().setValue(userName);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                finish();
            }
        });

    }
}
