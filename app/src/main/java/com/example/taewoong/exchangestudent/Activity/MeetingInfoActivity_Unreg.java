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
import android.widget.ListView;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Database.MeetingData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class MeetingInfoActivity_Unreg extends AppCompatActivity{

    String Meeting_Name;
    String Group_Name;
    String Time;
    String Location;
    String Cost;
    String About;

    TextView meeting_name_view;
    EditText meeting_time;
    EditText meeting_location;
    EditText meeting_cost;
    EditText meeting_about;

    Button join;

    private DatabaseReference mMeetingReference;
    private DatabaseReference mDatabaseUser;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetinginfo_unreg);
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

        join = (Button)findViewById(R.id.Join_Button);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Intent intent = getIntent();
        Meeting_Name = intent.getStringExtra("Meeting_title");
        Group_Name = intent.getStringExtra("Group_title");
        meeting_name_view.setText(Meeting_Name + '(' + Group_Name +')');

        mMeetingReference = FirebaseDatabase.getInstance().getReference("groups").child(Group_Name).child("meetings").child(Meeting_Name+'('+Group_Name+')');
        mDatabaseUser = FirebaseDatabase.getInstance().getReference("users");

        mMeetingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("dataSnapshot",dataSnapshot.getValue()+"");
                MeetingData meetingData = dataSnapshot.getValue(MeetingData.class);
                Time = meetingData.Time;
                Location = meetingData.Location;
                Cost = meetingData.Cost;
                About = meetingData.About;
                meeting_time.setText(Time);
                meeting_location.setText(Location);
                meeting_cost.setText(Cost);
                meeting_about.setText(About);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseUser.child(currentUser.getUid()).child("JoinedMeeting").push().setValue(Meeting_Name + '(' + Group_Name +')');
                mDatabaseUser.child(currentUser.getUid()).child("Name").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String userName = dataSnapshot.getValue().toString();
                        FirebaseDatabase.getInstance().getReference("groups").child(Group_Name).child("meetings").child(Meeting_Name + '(' + Group_Name +')').child("Member").push().setValue(userName);
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

