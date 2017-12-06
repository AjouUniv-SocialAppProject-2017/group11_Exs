package com.example.taewoong.exchangestudent.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.taewoong.exchangestudent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class MeetingInfoActivity extends AppCompatActivity{

    EditText dateInput;
    EditText timeInput;
    EditText costInput;
    EditText organizerInput;
    EditText topicInput;


    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference meetingAbout = mDatabase.child("Meetings").child("About");
    DatabaseReference meetingGenre = mDatabase.child("Meetings").child("Genre");
    DatabaseReference meetingHost = mDatabase.child("Meetings").child("Host");
    DatabaseReference meetingLoaction = mDatabase.child("Meetings").child("Location");
    DatabaseReference meetingTime = mDatabase.child("Meetings").child("Time");



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetinginfo);

        dateInput = (EditText) findViewById(R.id.date);
        timeInput = (EditText) findViewById(R.id.time);
        costInput = (EditText) findViewById(R.id.cost);
        organizerInput = (EditText) findViewById(R.id.organizer);
        topicInput = (EditText) findViewById(R.id.topic);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onStart() {
        super.onStart();

        meetingAbout.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String date = dataSnapshot.getValue(String.class);
                dateInput.setText(date);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        meetingGenre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String date = dataSnapshot.getValue(String.class);
                dateInput.setText(date);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        meetingHost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String date = dataSnapshot.getValue(String.class);
                dateInput.setText(date);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        meetingLoaction.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String date = dataSnapshot.getValue(String.class);
                dateInput.setText(date);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        meetingTime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String date = dataSnapshot.getValue(String.class);
                dateInput.setText(date);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
