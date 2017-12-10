package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    EditText edit_name;
    EditText edit_Location;
    EditText edit_Time;
    EditText edit_About;
    EditText edit_Cost;
    EditText edit_address;
    ImageButton Search_address_btn;
    Button enroll;
    String Name, Location, Time, About, Cost, Group;
    String Group_title;
    private DatabaseReference mDatabaseMeeting;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private DatabaseReference mDatabaseUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmeeting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        Intent intent = getIntent();
        Group_title = intent.getStringExtra("Group_title");

        mDatabaseMeeting = FirebaseDatabase.getInstance().getReference("groups").child(Group_title).child("meetings");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();

        edit_name = (EditText)findViewById(R.id.edit_name);

        edit_Time = (EditText)findViewById(R.id.edit_time);
        edit_About = (EditText)findViewById(R.id.edit_about);
        edit_Cost = (EditText)findViewById(R.id.edit_cost);
        edit_address = (EditText)findViewById(R.id.address_result) ;
        unfocusable(edit_address);
        Search_address_btn = (ImageButton)findViewById(R.id.search_address_btn);

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
                    Cost = edit_Cost.getText().toString();
                    Group = Group_title;
                    writeNewMeeting(Cost,Name,Location,Time,About, Group);
                    finish();
                }
            }
        });
        Search_address_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewMeetingActivity.this,WebViewActivity.class);
                startActivityForResult(i, SEARCH_ADDRESS_ACTIVITY);
            }
        });
    }

    private void writeNewMeeting(String Cost, String Name, String Location, String Time, String About,String Group) {
        MeetingData meetingData = new MeetingData(Cost, Location, Time, About, Group);
        mDatabaseMeeting.child(Name+'('+Group+')').setValue(meetingData);
        mDatabaseUser.child(currentUser.getUid()).child("OrgMeeting").push().setValue(Name+"\n("+Group+")");
        mDatabaseUser.child(currentUser.getUid()).child("JoinedMeeting").push().setValue(Name+"\n("+Group+")");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        switch(requestCode){
            case SEARCH_ADDRESS_ACTIVITY:
                if(resultCode == RESULT_OK){
                    String data = intent.getExtras().getString("data");
                    if (data != null)
                        edit_address.setText(data);
                }
                break;
        }
    }

    public void unfocusable(EditText editText){
        editText.setFocusable(false);
        editText.setFocusableInTouchMode(false);
    }
}
