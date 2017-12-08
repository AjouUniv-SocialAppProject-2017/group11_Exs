package com.example.taewoong.exchangestudent.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taewoong.exchangestudent.Database.MeetingData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class NewMeetingActivity extends AppCompatActivity{

    EditText edit_name;
    EditText edit_Location;
    EditText edit_Time;
    EditText edit_About;
    Button enroll;
    String Name, Location, Time, About, Host;
    private DatabaseReference mDatabaseMeeting;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creategroup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        mDatabaseMeeting = FirebaseDatabase.getInstance().getReference("meetings");
        mAuth = FirebaseAuth.getInstance();

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_Location = (EditText)findViewById(R.id.edit_Location);
        edit_Time = (EditText)findViewById(R.id.edit_time);
        edit_About = (EditText)findViewById(R.id.edit_about);
        enroll = (Button)findViewById(R.id.btn_enroll);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit_name.getText().toString().equals("")||edit_Location.getText().toString().equals("")||edit_Time.getText().toString().equals("")||edit_About.getText().toString().equals("")){
                    Toast.makeText(NewMeetingActivity.this, "Plz fill in all the blanks", Toast.LENGTH_LONG).show();
                }else{
                    currentUser = mAuth.getCurrentUser();
                    Name = edit_name.getText().toString();
                    Location = edit_Location.getText().toString();
                    Time = edit_Time.getText().toString();
                    About = edit_About.getText().toString();
                    Host = currentUser.getEmail();

                    writeNewMeeting(Name,Host,Location,Time,About);
                    finish();
                }
            }
        });

    }

    private void writeNewMeeting(String Name, String Host, String Location, String Time, String About) {
        MeetingData meetingData = new MeetingData(Host, Location, Time, About);
        mDatabaseMeeting.child(Name).setValue(meetingData);
    }
}
