package com.example.taewoong.exchangestudent.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Database.ChatData;
import com.example.taewoong.exchangestudent.R;
import com.google.api.translate.Translate;
import com.google.api.translate.TranslateV2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class ChatActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    Button sendButton;
    ArrayAdapter adapter;
    String Group_Name;
    String Meeting_Name;
    String userName;
    TextView txv;
    private DatabaseReference mDatabaseCurrentUser;
    private DatabaseReference mDatabaseCurrentMeeting;
    private DatabaseReference mDatabaseCurrentMeetingMessage;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        Intent intent = getIntent();
        Group_Name = intent.getStringExtra("Group_title");
        Meeting_Name = intent.getStringExtra("Meeting_title");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        listView = (ListView)findViewById(R.id.chatting);
        editText = (EditText)findViewById(R.id.edit_chat);
        sendButton = (Button)findViewById(R.id.send_btn);

        mDatabaseCurrentUser = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid()).child("Name");

        mDatabaseCurrentUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);

        mDatabaseCurrentMeeting = FirebaseDatabase.getInstance().getReference("groups").child(Group_Name).child("meetings").child(Meeting_Name+'('+Group_Name+')');
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatData chatData = new ChatData(userName,editText.getText().toString());
                mDatabaseCurrentMeeting.child("message").push().setValue(chatData);
                editText.setText("");
            }
        });

        mDatabaseCurrentMeeting.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatData chatData = dataSnapshot.getValue(ChatData.class);
                adapter.add(chatData.getUserName() + ": " + chatData.getMessage());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                txv = (TextView)view.findViewById(android.R.id.text1);
                String from = txv.getText().toString();
                NaverTranslateTask asyncTask = new NaverTranslateTask();
                asyncTask.execute(from);
            }
        });

    }

    public class NaverTranslateTask extends AsyncTask<String, Void, String>{

        public String ResultText;

        String clientId = "uzgfB2CTINnaerMFnWBQ";
        String clientSecret = "RLmw4YIWHg";

        String sourceLang = "ko";
        String targetLang = "en";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String sourceText = strings[0];

            try {
                //String text = URLEncoder.encode("만나서 반갑습니다.", "UTF-8");
                String text = URLEncoder.encode(sourceText, "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/language/translate";
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                // post request
                String postParams = "source="+sourceLang+"&target="+targetLang+"&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(postParams);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if(responseCode==200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                //System.out.println(response.toString());
                return response.toString();

            } catch (Exception e) {
                //System.out.println(e);
                return null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //최종 결과 처리부
            //Log.d("background result", s.toString()); //네이버에 보내주는 응답결과가 JSON 데이터이다.

            //JSON데이터를 자바객체로 변환해야 한다.
            //Gson을 사용할 것이다.

            Gson gson = new GsonBuilder().create();
            JsonParser parser = new JsonParser();
            JsonElement rootObj = parser.parse(s.toString())
                    //원하는 데이터 까지 찾아 들어간다.
                    .getAsJsonObject().get("message")
                    .getAsJsonObject().get("result");
            //안드로이드 객체에 담기
            TranslatedItem items = gson.fromJson(rootObj.toString(), TranslatedItem.class);
            //Log.d("result", items.getTranslatedText());
            //번역결과를 텍스트뷰에 넣는다.
            txv.setText(items.getTranslatedText());
        }
        private class TranslatedItem {
            String translatedText;

            public String getTranslatedText() {
                return translatedText;
            }
        }
    }
}
