package com.example.taewoong.exchangestudent.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.taewoong.exchangestudent.Activity.NewMeetingActivity;
import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter;
import com.example.taewoong.exchangestudent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class TabFragment1 extends Fragment {
    Button newmeeting_btn;

    final int ITEM_SIZE = 5;
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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        newmeeting_btn = (Button)view.findViewById(R.id.newmeeting);

        List<item> items = new ArrayList<>();
        item[] item = new item[ITEM_SIZE];
        item[0] = new item(R.drawable.a, "Lag Exchange\n11월 17일-19일");
        item[1] = new item(R.drawable.b, "Palace tour\n11월 17일(금)");
        item[2] = new item(R.drawable.c, "DMZ tour\n11월 18일(토)");
        item[3] = new item(R.drawable.d, "Camping\n11월 20일-26일");
        item[4] = new item(R.drawable.e, "Tea time\n11월 21일(화)");

        newmeeting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewMeetingActivity.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }

        recyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(),items, R.layout.tab_fragment1));
        return view;
    }
}
