package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.taewoong.exchangestudent.Adaptor.MyAdapter;
import com.example.taewoong.exchangestudent.Database.GenreData;
import com.example.taewoong.exchangestudent.Database.GroupData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by wonseoklee on 2017. 12. 9..
 */

public class CategorizedGroupActivityMain extends AppCompatActivity {


    MyAdapter mMyAdapter = new MyAdapter();

    private ListView mListView;

    String Genre;
    TextView toolbarText;

    String Group_Host;
    String Group_Region;
    String Group_Genre;
    String Group_About;


    private DatabaseReference mDatabaseGenreReference;
    private DatabaseReference mDatabaseGroupReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorizedgroupmain);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        final MyAdapter mMyAdapter = new MyAdapter();

        /*툴바에 들어갈 내용*/
        toolbarText = (TextView)findViewById(R.id.toolbarContent);

        Intent intent = getIntent();
        Genre = intent.getStringExtra("Genre");
        toolbarText.setText(Genre);

        mDatabaseGenreReference = FirebaseDatabase.getInstance().getReference("genre");
        mDatabaseGroupReference = FirebaseDatabase.getInstance().getReference("groups");
        mDatabaseGenreReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(final DataSnapshot dsp : dataSnapshot.getChildren()){
                    mDatabaseGroupReference.child(dsp.getValue().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            GroupData groupData = new GroupData();
                            groupData = dataSnapshot.getValue(GroupData.class);
                            Group_Host = groupData.Host;
                            Group_Region = groupData.Region;
                            Group_Genre = groupData.Genre;
                            Group_About = groupData.About;
                            if(Group_Genre.equals(Genre)){
                                Log.e("Here","Here");
                                mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon),dsp.getValue().toString(),Group_About);
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

}