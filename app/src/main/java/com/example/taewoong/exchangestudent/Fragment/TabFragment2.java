package com.example.taewoong.exchangestudent.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter_group;
import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter_meeting;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
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

public class TabFragment2 extends Fragment{
    private DatabaseReference mMyGroupReference;
    private DatabaseReference mOrgGroupReference;
    private FirebaseAuth mAuth;
    List<item> items1;
    List<item> items2;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;
    public TabFragment2(){

    }



    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab_fragment2,container,false);

        mAuth = FirebaseAuth.getInstance();
        mMyGroupReference = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid()).child("JoinedGroup");
        mOrgGroupReference = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid()).child("OrgGroup");


        //첫번째 cardView
        recyclerView1 = view.findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager1);

        items1 = new ArrayList<>();

        mMyGroupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    items1.add(new item(R.drawable.a,dsp.getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView1.setAdapter(new RecyclerAdapter_group(getActivity().getApplicationContext(),items1, R.layout.tab_fragment1));

        //두번째 cardView
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);

        items2 = new ArrayList<>();
        mOrgGroupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    items2.add(new item(R.drawable.a,dsp.getValue(String.class)));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView2.setAdapter(new RecyclerAdapter_group(getActivity().getApplicationContext(),items2, R.layout.tab_fragment1));
        return view;
    }

    @Override
    public void onStart() {
        recyclerView2.setAdapter(new RecyclerAdapter_group(getActivity().getApplicationContext(),items2, R.layout.tab_fragment1));
        recyclerView1.setAdapter(new RecyclerAdapter_group(getActivity().getApplicationContext(),items1, R.layout.tab_fragment1));
        super.onStart();
    }

}
