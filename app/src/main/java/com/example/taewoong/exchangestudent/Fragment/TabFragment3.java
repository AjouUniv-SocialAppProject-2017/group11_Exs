package com.example.taewoong.exchangestudent.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Activity.EditprofileActivity;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class TabFragment3 extends Fragment{

    Button editprofile;
    TextView defaultName;
    String nameForm;
    String name;
    int REQUEST_ACT = 1;

    private DatabaseReference mDatabaseUserName;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


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
        defaultName = (TextView)view.findViewById(R.id.textView16);

        mDatabaseUserName = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        defaultName.setHint("이름을 입력해주세요.");
        nameForm =defaultName.getText().toString();
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditprofileActivity.class);
                intent.putExtra("nameForm",nameForm);
                startActivity(intent);
            }
        });
        return view;
    }
}