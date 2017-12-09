package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.taewoong.exchangestudent.Adaptor.MyAdapter;
import com.example.taewoong.exchangestudent.R;
import com.google.firebase.database.DatabaseReference;


/**
 * Created by wonseoklee on 2017. 12. 9..
 */

public class CategorizedGroupActivityMain extends AppCompatActivity {


    MyAdapter mMyAdapter = new MyAdapter();

    private ListView mListView;

    String Genre;
    String Name;
    TextView toolbarText;

    private DatabaseReference mCategorizedReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorizedgroupmain);

        /* 위젯과 멤버변수 참조 획득 */
        mListView = (ListView)findViewById(R.id.listView);

        /* 아이템 추가 및 어댑터 등록 */
        dataSetting();
    }

    private void dataSetting(){

        MyAdapter mMyAdapter = new MyAdapter();

        /*툴바에 들어갈 내용*/
        toolbarText = (TextView)findViewById(R.id.toolbarContent);

        Intent intent = getIntent();
        Genre = intent.getStringExtra("genreCook");
        toolbarText.setText(Genre);


        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon), "name_" + i, "contents_" + i);
        }

        /* 리스트뷰에 어댑터 등록 */
        mListView.setAdapter(mMyAdapter);
    }

}