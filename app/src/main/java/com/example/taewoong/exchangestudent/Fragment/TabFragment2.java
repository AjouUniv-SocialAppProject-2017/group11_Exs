package com.example.taewoong.exchangestudent.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter_group;
import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter_group_owned;
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

public class TabFragment2 extends Fragment{
    private DatabaseReference mMyGroupReference;
    private DatabaseReference mOrgGroupReference;
    private FirebaseAuth mAuth;
    List<item> items1;
    List<item> items2;
    RecyclerAdapter_group mAdaptor1;
    RecyclerAdapter_group_owned mAdaptor2;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;

    int ran[] = {R.drawable.cardview1, R.drawable.cardview2,R.drawable.cardview3,R.drawable.cardview4,R.drawable.cardview5,
            R.drawable.cardview6,R.drawable.cardview7,R.drawable.cardview8,R.drawable.cardview9,R.drawable.cardview10,R.drawable.cardview11};

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

        mMyGroupReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int index = (int)(Math.random()*10);
                items1.add(new item(ran[index],dataSnapshot.getValue().toString()));
                recyclerView1.getAdapter().notifyDataSetChanged();
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
        mAdaptor1 = new RecyclerAdapter_group(getActivity().getApplicationContext(),items1, R.layout.tab_fragment1);
        recyclerView1.setAdapter(mAdaptor1);
        ItemTouchHelper itemDecor1 = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT, ItemTouchHelper.UP) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                mAdaptor1.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                items1.remove(fromPos);
                mAdaptor1.notifyItemRemoved(fromPos);
            }
        });
        itemDecor1.attachToRecyclerView(recyclerView1);

        //두번째 cardView
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);

        items2 = new ArrayList<>();

        mOrgGroupReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int index = (int)(Math.random()*10);
                items2.add(new item(ran[index],dataSnapshot.getValue().toString()));
                recyclerView2.getAdapter().notifyDataSetChanged();
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

        mAdaptor2 = new RecyclerAdapter_group_owned(getActivity().getApplicationContext(),items2, R.layout.tab_fragment1);
        recyclerView2.setAdapter(mAdaptor2);
        ItemTouchHelper itemDecor2 = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT, ItemTouchHelper.UP) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                mAdaptor2.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                items2.remove(fromPos);
                mAdaptor2.notifyItemRemoved(fromPos);
            }
        });
        itemDecor2.attachToRecyclerView(recyclerView2);
        return view;
    }

    @Override
    public void onStart() {
        recyclerView2.setAdapter(new RecyclerAdapter_group_owned(getActivity().getApplicationContext(),items2, R.layout.tab_fragment1));
        recyclerView1.setAdapter(new RecyclerAdapter_group(getActivity().getApplicationContext(),items1, R.layout.tab_fragment1));
        super.onStart();
    }

    @Override
    public void onResume() {
        recyclerView2.setAdapter(new RecyclerAdapter_group_owned(getActivity().getApplicationContext(),items2, R.layout.tab_fragment1));
        recyclerView1.setAdapter(new RecyclerAdapter_group(getActivity().getApplicationContext(),items1, R.layout.tab_fragment1));
        super.onResume();
    }
}
