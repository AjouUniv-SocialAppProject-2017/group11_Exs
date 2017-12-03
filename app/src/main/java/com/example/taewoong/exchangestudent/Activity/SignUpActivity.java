package com.example.taewoong.exchangestudent.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taewoong.exchangestudent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class SignUpActivity extends AppCompatActivity {

    EditText id;
    EditText password;
    EditText rePassword;
    EditText nationality;
    Button signupBtn;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.rePassword);
        nationality = (EditText) findViewById(R.id.nationality);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        mAuth = FirebaseAuth.getInstance();



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
                rePassword.getText().toString().equals("") || nationality.getText().toString().equals("")) {
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
                                Toast.makeText(SignUpActivity.this, "SignUp Success", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
    }

}

