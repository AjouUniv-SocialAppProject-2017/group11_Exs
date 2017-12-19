package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Database.GroupData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Taewoong on 2017-12-06.
 */

public class GroupInfoActivity_Reg extends AppCompatActivity {
    static final ArrayList<String> List_member = new ArrayList<String>();
    static final ArrayList<String> List_Meeting = new ArrayList<String>();

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

    public ListView mListView_Member;
    public ListView mListView_Meeting;
    ArrayAdapter<String> adapter;
    private DatabaseReference mDatabaseMemberReference;
    private DatabaseReference mDatabaseMeetingReference;

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

        mListView_Member = (ListView)findViewById(R.id.memberlist);
        mListView_Meeting = (ListView)findViewById(R.id.MeetingList);
        mListView_Meeting.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        mListView_Member.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        dataSetting_Member(Name, mListView_Member);
        dataSetting_Meeting(Name, mListView_Meeting);

        newMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewMeetingActivity.class);
                intent.putExtra("Group_title",Name);
                startActivity(intent);
                finish();
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

    private void dataSetting_Member(String name, ListView mListView) {
        mDatabaseMemberReference = FirebaseDatabase.getInstance().getReference("groups").child(name).child("Member");
        List_member.clear();
        mDatabaseMemberReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                    List_member.add(dsp.getValue().toString());
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, List_member) ;
                    adapter.notifyDataSetChanged();
                    mListView_Member.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void dataSetting_Meeting(String name, final ListView mListView){
        mDatabaseMeetingReference = FirebaseDatabase.getInstance().getReference("groups").child(name).child("meetings");
        List_Meeting.clear();
        mDatabaseMeetingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp:dataSnapshot.getChildren()){
                    String Meeting_name = dsp.getKey().split("\\(")[0];
                    List_Meeting.add(Meeting_name);
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, List_Meeting) ;
                    adapter.notifyDataSetChanged();
                    mListView_Meeting.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mListView_Meeting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Meeting_name = List_Meeting.get(i);
                Intent intent = new Intent(getApplicationContext(),MeetingInfoActivity_Unreg.class);
                intent.putExtra("Meeting_title", Meeting_name);
                intent.putExtra("Group_title", Name);
                startActivity(intent);
                finish();
            }
        });
    }
}
