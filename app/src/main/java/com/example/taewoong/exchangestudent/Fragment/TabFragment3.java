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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taewoong.exchangestudent.Activity.CategorizedGroupActivityMain;
import com.example.taewoong.exchangestudent.Activity.EditprofileActivity;
import com.example.taewoong.exchangestudent.Activity.GroupInfoActivity_Unreg;
import com.example.taewoong.exchangestudent.Database.UserData;
import com.example.taewoong.exchangestudent.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class TabFragment3 extends Fragment{

    Button editprofile;
    TextView defaultName;
    String nameForm;
    String name;
    String profilePictureName;
    int REQUEST_ACT = 1;

    ImageView profile_image;
    Button interest1, interest2, interest3, interest4, interest5;

    private DatabaseReference mDatabaseUserName;
    private DatabaseReference mEditProfileReference;
    private DatabaseReference mProfilePicture;
    private DatabaseReference mMyInterestReference;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    FirebaseStorage storage;
    StorageReference storageRef;

    String genre;

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
        profile_image = (ImageView)view.findViewById(R.id.imageView2);

        interest1 = (Button)view.findViewById(R.id.textView19);
        interest2 = (Button)view.findViewById(R.id.textView20);
        interest3 = (Button)view.findViewById(R.id.textView21);
        interest4 = (Button)view.findViewById(R.id.textView22);
        interest5 = (Button)view.findViewById(R.id.textView23);

        mDatabaseUserName = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        storage = FirebaseStorage.getInstance();

        mProfilePicture = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid()).child("profileUrl");
        mProfilePicture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                profilePictureName = dataSnapshot.getValue().toString();
                storageRef = storage.getReferenceFromUrl("gs://lte-ajou.appspot.com/").child("images");
                Glide.with(getContext() /* context */)
                        .using(new FirebaseImageLoader())
                        .load(storageRef.child(profilePictureName))
                        .into(profile_image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        mEditProfileReference = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid()).child("Name");
        mEditProfileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue().toString();
                defaultName.setText(name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mMyInterestReference = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid()).child("Interest");
        mMyInterestReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = 0;
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    if(count == 0){
                        count ++;
                        Log.e("interest1",interest1+"");
                        interest1.setText(dsp.getValue().toString());
                    }else if(count == 1){
                        count ++;
                        interest2.setText(dsp.getValue().toString());
                    }else if(count == 2){
                        count ++;
                        interest3.setText(dsp.getValue().toString());
                    }else if(count == 3){
                        count ++;
                        interest4.setText(dsp.getValue().toString());
                    }else if(count == 4){
                        count ++;
                        interest5.setText(dsp.getValue().toString());
                    }else{
                        Log.e("5",dsp.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        interest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre = interest1.getText().toString().replace("#","");
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre",genre);
                startActivity(intent);
            }
        });

        interest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre = interest2.getText().toString().replace("#","");
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre",genre);
                startActivity(intent);
            }
        });

        interest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre = interest3.getText().toString().replace("#","");
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre",genre);
                startActivity(intent);
            }
        });

        interest4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre = interest4.getText().toString().replace("#","");
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre",genre);
                startActivity(intent);
            }
        });

        interest5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genre = interest5.getText().toString().replace("#","");
                Intent intent = new Intent(getContext(), CategorizedGroupActivityMain.class);
                intent.putExtra("Genre",genre);
                startActivity(intent);
            }
        });

        defaultName.setHint("이름을 설정해주세요.");
        defaultName.setText(mDatabaseUserName.child(currentUser.getUid()).child("Name").toString());
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditprofileActivity.class);
                intent.putExtra("nameForm",name);
                startActivity(intent);
            }
        });
        return view;
    }
}