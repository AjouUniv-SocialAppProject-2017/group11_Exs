package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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

    String Meeting_Name;
    String Time;
    String Location;
    String Cost;
    String About;


    TextView meeting_name_view;
    EditText meeting_time;
    EditText meeting_location;
    EditText meeting_cost;
    EditText meeting_about;

    private DatabaseReference mMeetingReference;
    private DatabaseReference mGroupReference;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetinginfo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        meeting_name_view = (TextView)findViewById(R.id.name);
        meeting_time = (EditText)findViewById(R.id.timeForm);
        meeting_location = (EditText)findViewById(R.id.locationForm);
        meeting_cost = (EditText)findViewById(R.id.costForm);
        meeting_about = (EditText)findViewById(R.id.aboutForm);

        Intent intent = getIntent();
        Meeting_Name = intent.getStringExtra("Meeting_title");
        meeting_name_view.setText(Meeting_Name);

        mGroupReference = FirebaseDatabase.getInstance().getReference("groups");
        mGroupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Here","here");
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    Log.e("dsp",dsp.getKey());
//                    if(dsp.getKey() == Meeting_Name){
//                        mMeetingReference = mGroupReference.child(dsp.getKey()).child("meetings").child(Meeting_Name);
//                        Log.e("dataSnapshot",dsp.getKey()+" "+Meeting_Name);
//                        mMeetingReference.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                MeetingData meetingData = dataSnapshot.getValue(MeetingData.class);
//                                Time = meetingData.Time;
//                                Location = meetingData.Location;
//                                Cost = meetingData.Cost;
//                                About = meetingData.About;
//                                meeting_time.setText(Time);
//                                meeting_location.setText(Location);
//                                meeting_cost.setText(Cost);
//                                meeting_about.setText(About);
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//                        break;
//                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}

