package com.example.taewoong.exchangestudent.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taewoong.exchangestudent.Database.UserData;
import com.example.taewoong.exchangestudent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class EditprofileActivity extends AppCompatActivity {

    String name;
    String defaultName;

    EditText editName;
    Button editPicture;
    Button editInterest;
    Button save;

    private DatabaseReference mDatabaseReferenceName;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        save = (Button)findViewById(R.id.button2);
        editName = (EditText)findViewById(R.id.editText9);

        Intent intent = getIntent();
        defaultName = intent.getStringExtra("nameForm");
        editName.setText(defaultName);
        mDatabaseReferenceName = FirebaseDatabase.getInstance().getReference("users");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editName.getText().toString().equals("")){
                    Toast.makeText(EditprofileActivity.this, "Plz fill in blank", Toast.LENGTH_LONG).show();
                }else if(editName.getText().toString().equals(defaultName)){
                    Toast.makeText(EditprofileActivity.this, "Plz fill the new Name", Toast.LENGTH_LONG).show();
                }else{
                    name = editName.getText().toString();
                    updateName(name);
                }
            }
        });
    }

        private void updateName(String newName){

            mDatabaseReferenceName.child(currentUser.getUid()).child("Name").setValue(name);
            finish();
    }

    }

