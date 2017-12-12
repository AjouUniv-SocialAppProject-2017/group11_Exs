package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Database.MeetingData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class MeetingInfoActivity_Reg extends AppCompatActivity{

    static final ArrayList<String> List_member = new ArrayList<String>();

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

    ImageButton chatImage;

    ListView member_listview;

    private DatabaseReference mMeetingReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetinginfo_reg);
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
        member_listview = (ListView)findViewById(R.id.memberForm);

        Intent intent = getIntent();
        Meeting_Name = intent.getStringExtra("Meeting_title");
        Group_Name = intent.getStringExtra("Group_title");
        meeting_name_view.setText(Meeting_Name + '(' + Group_Name +')');

        chatImage = (ImageButton)findViewById(R.id.chattingImage);
        chatImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                intent.putExtra("Meeting_title",Meeting_Name);
                intent.putExtra("Group_title",Group_Name);
                startActivity(intent);
            }
        });

        mMeetingReference = FirebaseDatabase.getInstance().getReference("groups").child(Group_Name).child("meetings").child(Meeting_Name+'('+Group_Name+')');

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
        dataSetting();
    }
    private void dataSetting(){
        mMeetingReference.child("Member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                    Log.e("dsp",dsp.getValue().toString());
                    List_member.add(dsp.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, List_member) ;
        member_listview.setAdapter(adapter);
    }

}

