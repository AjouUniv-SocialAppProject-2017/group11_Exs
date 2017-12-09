package com.example.taewoong.exchangestudent.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.taewoong.exchangestudent.Activity.NewGroupActivity;
import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter_group;
import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter_meeting;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class TabFragment1 extends Fragment {
    Button newgroup_btn;
    private DatabaseReference mMyMeetingReference;
    private FirebaseAuth mAuth;
    private ChildEventListener mChildEventListener;


    List<item> items;
    public TabFragment1(){
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab_fragment1,container,false);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        mAuth = FirebaseAuth.getInstance();
        mMyMeetingReference = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid()).child("JoinedMeeting");
        items = new ArrayList<>();
        mMyMeetingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    items.add(new item(R.drawable.a,dsp.getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        newgroup_btn = (Button)view.findViewById(R.id.newgroup);
        newgroup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewGroupActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        recyclerView.setAdapter(new RecyclerAdapter_meeting(getActivity().getApplicationContext(),items, R.layout.tab_fragment1));
        return view;
    }

    @Override
    public void onResume() {
        Log.e("Resumed","Resumed!");
        RecyclerView recyclerView = getView().findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new RecyclerAdapter_meeting(getActivity().getApplicationContext(),items, R.layout.tab_fragment1));
        super.onResume();
    }
}
