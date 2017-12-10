package com.example.taewoong.exchangestudent.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Activity.EditprofileActivity;
import com.example.taewoong.exchangestudent.R;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class TabFragment3 extends Fragment{

    Button editprofile;
    TextView Txv_name;
    String name;

    public TabFragment3(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.tab_fragment3,container,false);
        editprofile = (Button)view.findViewById(R.id.editprofile);
        Txv_name = (TextView)view.findViewById(R.id.textView16);
        name = Txv_name.getText().toString();
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditprofileActivity.class);
                intent.putExtra("myName",name);
                startActivity(intent);
            }
        });
        return view;
    }
}
