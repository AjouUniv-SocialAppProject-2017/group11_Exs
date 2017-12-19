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
import android.widget.Spinner;
import android.widget.Toast;

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



public class NewGroupActivity extends AppCompatActivity {
    private DatabaseReference mDatabaseMeeting;
    private DatabaseReference mDatabaseUser;
    private DatabaseReference mDatabaseGenre;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    EditText edit_name;
    Spinner spinner_region;
    Spinner spinner_genre;
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
        mDatabaseGenre = FirebaseDatabase.getInstance().getReference("genre");

        mAuth = FirebaseAuth.getInstance();

        edit_name = (EditText)findViewById(R.id.edit_name);
        spinner_region = (Spinner) findViewById(R.id.edit_region);
        spinner_genre = (Spinner)findViewById(R.id.edit_Genre);
        edit_about = (EditText)findViewById(R.id.edit_about);
        enroll = (Button)findViewById(R.id.btn_enroll);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_name.getText().toString().equals("")||spinner_region.getSelectedItem().toString().equals("")||spinner_genre.getSelectedItem().toString().equals("")||edit_about.getText().toString().equals("")){
                    Toast.makeText(NewGroupActivity.this, "Plz fill in all the blanks", Toast.LENGTH_LONG).show();
                }else{
                    currentUser = mAuth.getCurrentUser();
                    name = edit_name.getText().toString();
                    region = spinner_region.getSelectedItem().toString();
                    genre = spinner_genre.getSelectedItem().toString();
                    about = edit_about.getText().toString();
                    writeNewUser(name, currentUser.getEmail(),region,genre,about);
                    finish();
                }
            }
        });
    }

    private void writeNewUser(final String Name, String Host, String Region, String Genre, String About) {
        GroupData groupData = new GroupData(Host, Region, Genre, About);
        mDatabaseMeeting.child(Name).setValue(groupData);
        mDatabaseUser.child(currentUser.getUid()).child("OrgGroup").push().setValue(Name);
        mDatabaseUser.child(currentUser.getUid()).child("JoinedGroup").push().setValue(Name);
        mDatabaseGenre.child(Genre).push().setValue(Name);
        mDatabaseUser.child(currentUser.getUid()).child("Name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.getValue().toString();
                FirebaseDatabase.getInstance().getReference("groups").child(Name).child("Member").push().setValue(userName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
