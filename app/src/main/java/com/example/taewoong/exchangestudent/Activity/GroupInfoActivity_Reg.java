package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Adaptor.MyAdapter;
import com.example.taewoong.exchangestudent.Database.GroupData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Taewoong on 2017-12-06.
 */

public class GroupInfoActivity_Reg extends AppCompatActivity {

    public String Name;
    public String About;
    String Genre;
    String Region;
    String[] list;

    TextView group_name;
    EditText edit_about;
    EditText edit_genre;
    EditText edit_region;
    Button newMeeting;

    public ListView mListView;
    ArrayAdapter<String> adapter;
    private DatabaseReference mDatabaseMemberReference;

    private DatabaseReference mGroupReference;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupinfo_reg);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        group_name = (TextView)findViewById(R.id.group_name);
        edit_about = (EditText)findViewById(R.id.edit_about);
        edit_genre = (EditText)findViewById(R.id.edit_genre);
        edit_region = (EditText)findViewById(R.id.edit_region);
        newMeeting = (Button)findViewById(R.id.newMeeting_btn);

        Intent intent = getIntent();
        Name = intent.getStringExtra("Group_title");
        group_name.setText(Name);

        mListView = (ListView)findViewById(R.id.listView);
        dataSetting(Name, mListView);

        newMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewMeetingActivity.class);
                intent.putExtra("Group_title",Name);
                startActivity(intent);
            }
        });



        mGroupReference = FirebaseDatabase.getInstance().getReference("groups").child(Name);
        mGroupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GroupData groupData = dataSnapshot.getValue(GroupData.class);
                About = groupData.About;
                Genre = groupData.Genre;
                Region = groupData.Region;
                Log.e("read",About + " " + Genre + " " + Region);
                edit_about.setText(About);
                edit_genre.setText(Genre);
                edit_region.setText(Region);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void dataSetting(String name, ListView mListView) {
        mDatabaseMemberReference = FirebaseDatabase.getInstance().getReference("groups").child(name).child("Member");
        mDatabaseMemberReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                    Log.e("dsp",dsp.getValue()+"");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //mListView.setAdapter();

    }
}
