package com.example.taewoong.exchangestudent.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taewoong.exchangestudent.Database.GroupData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Taewoong on 2017-12-06.
 */



public class NewGroupActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseMeeting;
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    EditText edit_name;
    EditText edit_region;
    EditText edit_genre;
    EditText edit_about;
    Button enroll;

    String name;
    String region;
    String genre;
    String about;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creategroup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        mDatabaseMeeting = FirebaseDatabase.getInstance().getReference("groups");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_region = (EditText)findViewById(R.id.edit_region);
        edit_genre = (EditText)findViewById(R.id.edit_Genre);
        edit_about = (EditText)findViewById(R.id.edit_about);
        enroll = (Button)findViewById(R.id.btn_enroll);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_name.getText().toString().equals("")||edit_region.getText().toString().equals("")||edit_genre.getText().toString().equals("")||edit_about.getText().toString().equals("")){
                    Toast.makeText(NewGroupActivity.this, "Plz fill in all the blanks", Toast.LENGTH_LONG).show();
                }else{
                    currentUser = mAuth.getCurrentUser();
                    name = edit_name.getText().toString();
                    region = edit_region.getText().toString();
                    genre = edit_genre.getText().toString();
                    about = edit_about.getText().toString();
                    writeNewUser(name, currentUser.getEmail(),region,genre,about);
                    finish();
                }
            }
        });
    }

    private void writeNewUser(String Name, String Host, String Region,String Genre, String About) {
        GroupData groupData = new GroupData(Host, Region, Genre, About);
        mDatabaseMeeting.child(Name).setValue(groupData);
        mDatabaseUser.child(currentUser.getUid()).child("OrgGroup").push().setValue(Name);
    }
}
