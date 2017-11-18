package com.example.taewoong.exchangestudent.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.taewoong.exchangestudent.Adaptor.RecyclerAdapter;
import com.example.taewoong.exchangestudent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class TabFragment2 extends Fragment{
    final int ITEM_SIZE = 5;
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

        //첫번째 cardView
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(layoutManager1);
        List<item> items = new ArrayList<>();
        item[] item = new item[ITEM_SIZE];
        item[0] = new item(R.drawable.a, "Lag Exchange\n11월 17일-19일");
        item[1] = new item(R.drawable.b, "Palace tour\n11월 17일(금)");
        item[2] = new item(R.drawable.c, "DMZ tour\n11월 18일(토)");
        item[3] = new item(R.drawable.d, "Camping\n11월 20일-26일");
        item[4] = new item(R.drawable.e, "Tea time\n11월 21일(화)");

        for (int i = 0; i < ITEM_SIZE; i++) {
            items.add(item[i]);
        }

        recyclerView1.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(),items, R.layout.tab_fragment1));

        //두번째 cardView
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.recyclerView2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);
        List<item> items2 = new ArrayList<>();
        item[] item2 = new item[1];
        item2[0] = new item(R.drawable.a, "Lag Exchange\n11월 17일-19일");

        for (int i = 0; i < 1; i++) {
            items2.add(item2[i]);
        }

        recyclerView2.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(),items2, R.layout.tab_fragment1));

        return view;
    }
}
