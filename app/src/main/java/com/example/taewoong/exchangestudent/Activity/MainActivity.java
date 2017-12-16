package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.taewoong.exchangestudent.Adaptor.TabPagerAdapter;
import com.example.taewoong.exchangestudent.Database.UserData;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button logout;
    private FirebaseAuth mAuth;
    private TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Adding Toolbar to the activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        // Initializing the TabLayout
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.group_pink));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.meeting_gray));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.profile_gray));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.logout);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);

        // Initializing ViewPager
        viewPager = findViewById(R.id.pager);

        // Creating TabPagerAdapter adapter
        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(3);

        // Set TabSelectedListener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.group_pink);
                        toolbar_title.setText("Meeting");
                        break;
                    case 1:
                        tab.setIcon(R.drawable.meeting_pink);
                        toolbar_title.setText("Group");
                        break;
                    case 2:
                        tab.setIcon(R.drawable.profile_pink);
                        toolbar_title.setText("Profile");
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(R.drawable.group_gray);
                        toolbar_title.setText("Meeting");
                        break;
                    case 1:
                        tab.setIcon(R.drawable.meeting_gray);
                        toolbar_title.setText("Group");
                        break;
                    case 2:
                        tab.setIcon(R.drawable.profile_gray);
                        toolbar_title.setText("Profile");
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
