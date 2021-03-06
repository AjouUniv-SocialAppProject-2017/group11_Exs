package com.example.taewoong.exchangestudent.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.taewoong.exchangestudent.Activity.CategorizedGroupActivityMain;
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
import java.util.Random;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class TabFragment1 extends Fragment {
    Button newgroup_btn;
    private DatabaseReference mMyMeetingReference;
    private FirebaseAuth mAuth;
    RecyclerView recyclerView;
    RecyclerAdapter_meeting mAdaptor;

    ImageView test1Btn;
    ImageView test2Btn;
    ImageView test3Btn;
    ImageView test4Btn;
    ImageView test5Btn;
    ImageView test6Btn;
    ImageView test7Btn;
    ImageView test8Btn;
    ImageView test9Btn;
    ImageView test10Btn;

    int ran[] = {R.drawable.cardview1, R.drawable.cardview2,R.drawable.cardview3,R.drawable.cardview4,R.drawable.cardview5,
            R.drawable.cardview6,R.drawable.cardview7,R.drawable.cardview8,R.drawable.cardview9,R.drawable.cardview10,R.drawable.cardview11};


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

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        mAuth = FirebaseAuth.getInstance();
        mMyMeetingReference = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid()).child("JoinedMeeting");
        items = new ArrayList<>();

        mMyMeetingReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                int index = (int)(Math.random()*10);
                items.add(new item(ran[index],dataSnapshot.getValue().toString()));
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

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
            }
        });
        test1Btn = (ImageView)view.findViewById(R.id.imageView);
        test2Btn = (ImageView)view.findViewById(R.id.layou);
        test3Btn = (ImageView)view.findViewById(R.id.imageView3);
        test4Btn = (ImageView)view.findViewById(R.id.imageView7);
        test5Btn = (ImageView)view.findViewById(R.id.imageView9);
        test6Btn = (ImageView)view.findViewById(R.id.imageView4);
        test7Btn = (ImageView)view.findViewById(R.id.imageView5);
        test8Btn = (ImageView)view.findViewById(R.id.imageView6);
        test9Btn = (ImageView)view.findViewById(R.id.imageView8);
        test10Btn = (ImageView)view.findViewById(R.id.imageView10);
        test1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Cook");
                startActivity(intent);
           }
        });
        test2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Religion");
                startActivity(intent);
            }
        });
        test3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Movie");
                startActivity(intent);
            }
        });
        test4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Trip");
                startActivity(intent);
            }
        });
        test5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","pet");
                startActivity(intent);
            }
        });
        test6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Music");
                startActivity(intent);
            }
        });
        test7Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Food");
                startActivity(intent);
            }
        });
        test8Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Study");
                startActivity(intent);
            }
        });
        test9Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Art");
                startActivity(intent);
            }
        });
        test10Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre","Etc");
                startActivity(intent);
            }
        });
        mAdaptor = new RecyclerAdapter_meeting(getActivity().getApplicationContext(),items, R.layout.tab_fragment1);
        recyclerView.setAdapter(mAdaptor);
        ItemTouchHelper itemDecor = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT, ItemTouchHelper.UP) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                mAdaptor.notifyItemMoved(fromPos, toPos);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                items.remove(fromPos);
                mAdaptor.notifyItemRemoved(fromPos);
            }
        });
        itemDecor.attachToRecyclerView(recyclerView);
        return view;
    }
}
