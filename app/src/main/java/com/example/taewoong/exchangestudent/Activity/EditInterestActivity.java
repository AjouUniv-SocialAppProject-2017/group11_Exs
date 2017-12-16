package com.example.taewoong.exchangestudent.Activity;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Taewoong on 2017-12-15.
 */

public class EditInterestActivity extends AppCompatActivity {

    TextView txv_cook;
    TextView txv_religion;
    TextView txv_movie;
    TextView txv_trip;
    TextView txv_pet;
    TextView txv_music;
    TextView txv_food;
    TextView txv_study;
    TextView txv_art;
    TextView txv_etc;
    ImageButton btn_cook, btn_religion, btn_movie, btn_trip,
            btn_pet, btn_music, btn_food, btn_study, btn_art, btn_etc;
    Button btn_edit;

    private DatabaseReference mMyInterestReference;
    private FirebaseAuth mAuth;

    boolean cook, religion, movie, trip, pet, music, food, study, art, etc;

    int count = 0;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinterest);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        mAuth = FirebaseAuth.getInstance();;
        mMyInterestReference = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid()).child("Interest");

        txv_art = (TextView)findViewById(R.id.txv_art);
        txv_etc = (TextView)findViewById(R.id.txv_etc);
        txv_study = (TextView)findViewById(R.id.txv_study);
        txv_food = (TextView)findViewById(R.id.txv_food);
        txv_pet = (TextView)findViewById(R.id.txv_pet);
        txv_music = (TextView)findViewById(R.id.txv_music);
        txv_trip = (TextView)findViewById(R.id.txv_trip);
        txv_movie = (TextView)findViewById(R.id.txv_movie);
        txv_religion = (TextView)findViewById(R.id.txv_religion);
        txv_cook = (TextView)findViewById(R.id.txv_cook);

        txv_art.setVisibility(View.INVISIBLE);
        txv_etc.setVisibility(View.INVISIBLE);
        txv_study.setVisibility(View.INVISIBLE);
        txv_food.setVisibility(View.INVISIBLE);
        txv_pet.setVisibility(View.INVISIBLE);
        txv_music.setVisibility(View.INVISIBLE);
        txv_trip.setVisibility(View.INVISIBLE);
        txv_movie.setVisibility(View.INVISIBLE);
        txv_religion.setVisibility(View.INVISIBLE);
        txv_cook.setVisibility(View.INVISIBLE);

        btn_art = (ImageButton)findViewById(R.id.btn_art);
        btn_cook = (ImageButton)findViewById(R.id.btn_cook);
        btn_edit = (Button)findViewById(R.id.btn_edit);
        btn_etc = (ImageButton) findViewById(R.id.btn_etc);
        btn_food = (ImageButton) findViewById(R.id.btn_food);
        btn_movie = (ImageButton)findViewById(R.id.btn_movie);
        btn_music = (ImageButton)findViewById(R.id.btn_music);
        btn_pet = (ImageButton)findViewById(R.id.btn_pet);
        btn_religion = (ImageButton)findViewById(R.id.btn_religion);
        btn_study = (ImageButton)findViewById(R.id.btn_study);
        btn_trip = (ImageButton)findViewById(R.id.btn_trip);

        cook = false;religion = false; movie = false; trip = false; pet = false; music = false; food = false; study = false; art = false; etc = false;

        btn_art.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(art == false && count < 5) {
                    txv_art.setVisibility(View.VISIBLE);
                    art = true;
                    mMyInterestReference.child(count+"").setValue("#Art");
                    count ++;
                }
            }
        });
        btn_cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cook == false && count < 5) {
                    txv_cook.setVisibility(View.VISIBLE);
                    cook = true;
                    mMyInterestReference.child(count+"").setValue("#Cook");
                    count ++;
                }
            }
        });
        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etc == false && count < 5) {
                    txv_etc.setVisibility(View.VISIBLE);
                    etc = true;
                    mMyInterestReference.child(count+"").setValue("#Etc");
                    count ++;
                }
            }
        });
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(food == false && count < 5) {
                    txv_food.setVisibility(View.VISIBLE);
                    food = true;
                    mMyInterestReference.child(count+"").setValue("#Food");
                    count ++;
                }
            }
        });
        btn_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movie == false && count < 5) {
                    txv_movie.setVisibility(View.VISIBLE);
                    movie = true;
                    mMyInterestReference.child(count+"").setValue("#Movie");
                    count ++;
                }
            }
        });
        btn_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music == false && count < 5) {
                    txv_music.setVisibility(View.VISIBLE);
                    music = true;
                    mMyInterestReference.child(count+"").setValue("#Music");
                    count ++;
                }
            }
        });
        btn_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pet == false && count < 5) {
                    txv_pet.setVisibility(View.VISIBLE);
                    pet = true;
                    mMyInterestReference.child(count+"").setValue("#Pet");
                    count ++;
                }
            }
        });
        btn_religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(religion == false && count < 5) {
                    txv_religion.setVisibility(View.VISIBLE);
                    religion = true;
                    mMyInterestReference.child(count+"").setValue("#Religion");
                    count ++;
                }
            }
        });
        btn_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(study == false && count < 5) {
                    txv_study.setVisibility(View.VISIBLE);
                    study = true;
                    mMyInterestReference.child(count+"").setValue("#Study");
                    count ++;
                }
            }
        });
        btn_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trip == false && count < 5) {
                    txv_trip.setVisibility(View.VISIBLE);
                    trip = true;
                    mMyInterestReference.child(count+"").setValue("#Trip");
                    count ++;
                }
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
