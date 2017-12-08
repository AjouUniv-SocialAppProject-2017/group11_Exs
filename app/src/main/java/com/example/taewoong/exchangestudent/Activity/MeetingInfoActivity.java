package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

import com.example.taewoong.exchangestudent.Database.GroupData;
import com.example.taewoong.exchangestudent.Database.MeetingData;
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

    String Name;
    String Time;
    String Location;
    String Cost;
    String About;


    EditText meeting_name;
    EditText meeting_time;
    EditText meeting_location;
    EditText meeting_cost;
    EditText meeting_about;

    private DatabaseReference mMeetingReference;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetinginfo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        meeting_name = (EditText)findViewById(R.id.nameForm);
        meeting_time = (EditText)findViewById(R.id.timeForm);
        meeting_location = (EditText)findViewById(R.id.locationForm);
        meeting_cost = (EditText)findViewById(R.id.costForm);
        meeting_about = (EditText)findViewById(R.id.aboutForm);

        mMeetingReference = FirebaseDatabase.getInstance().getReference("groups").child("도깨비").child("meetings");

        mMeetingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MeetingData meetingData = dataSnapshot.getValue(MeetingData.class);
                Name = meetingData.name;
                Time = meetingData.time;
                Location = meetingData.location;
                Cost = meetingData.cost;
                About = meetingData.about;
                Log.e("read", Name + " " + Time + " "
                        + Location + " " + Cost + " " + About + " " );
                meeting_name.setText(Name);
                meeting_time.setText(Time);
                meeting_location.setText(Location);
                meeting_cost.setText(Cost);
                meeting_about.setText(About);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}

