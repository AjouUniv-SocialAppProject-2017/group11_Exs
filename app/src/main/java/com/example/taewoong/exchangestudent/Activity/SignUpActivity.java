package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
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

import com.example.taewoong.exchangestudent.Database.UserData;
import com.example.taewoong.exchangestudent.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class SignUpActivity extends AppCompatActivity{

    EditText id;
    EditText password;
    EditText rePassword;
    Button signupBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseUsers;
    FirebaseUser user;
    String UID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.rePassword);
        signupBtn = (Button) findViewById(R.id.signupBtn);


        mAuth = FirebaseAuth.getInstance();
        mDatabaseUsers = FirebaseDatabase.getInstance().getReference("users");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        signupBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                clickSignUp();
            }
        });




    }


    boolean isEmptyEditField() {
        boolean empty = false;
        if (id.getText().toString().equals("") || password.getText().toString().equals("") ||
                rePassword.getText().toString().equals("")) {
            empty = true;
        }
        return empty;

    }

    boolean isValidPW(){
        if(password.getText().toString().equals(rePassword.getText().toString())){
            return false;
        }
        return true;
    }

    void clickSignUp() {
        if (isEmptyEditField()) {
            Toast.makeText(this, "You should fill fields", Toast.LENGTH_LONG).show();
        }
        else if (isValidPW()){
            Toast.makeText(this,"You should check your password" , Toast.LENGTH_LONG).show();
        }else{
            mAuth.createUserWithEmailAndPassword(id.getText().toString(), password.getText().toString()).
                    addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(SignUpActivity.this, "SignUp Failed", Toast.LENGTH_LONG).show();
                            }
                            else{
                                user = mAuth.getCurrentUser();
                                UID = user.getUid();
                                writeNewUser(id.getText().toString(),UID);
                                Toast.makeText(SignUpActivity.this, "SignUp Success", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                    });

        }
    }

    private void writeNewUser(String userEmail,String UID) {
        UserData userdata = new UserData(userEmail);
        String userEmailID;
        mDatabaseUsers.child(UID).setValue(userdata);
    }
}

