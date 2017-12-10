package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
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
    String myName;

    EditText editName;
    Button editPicture;
    Button editInterest;
    Button save;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        editName = (EditText)findViewById(R.id.editText9);
        Intent intent = getIntent();
        name = intent.getStringExtra("myName");
        editName.setHint(name);


        UserProfileChangeRequest profileUpdates =
                new UserProfileChangeRequest.Builder().
                        setDisplayName(name).
                        setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg")).build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditprofileActivity.this, "업데이트 완료",Toast.LENGTH_LONG).show();
                            }
                    }
                });
    }
}
