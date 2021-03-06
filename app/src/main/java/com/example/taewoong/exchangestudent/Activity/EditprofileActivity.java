package com.example.taewoong.exchangestudent.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taewoong.exchangestudent.Database.UserData;
import com.example.taewoong.exchangestudent.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class EditprofileActivity extends AppCompatActivity {

    private static final String TAG = "EditprofileActivity";

    String name;
    String defaultName;
    String profilePictureName;


    EditText editName;
    ImageView picture;
    Button editPicture;
    Button editInterest;
    Button save;

    TextView interest1, interest2, interest3, interest4, interest5;


    private DatabaseReference mDatabaseReferenceName;
    private DatabaseReference mDatabaseReferencePic;
    private DatabaseReference mMyInterestReference;
    private DatabaseReference mProfilePicture;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    StorageReference storageRef;
    FirebaseStorage storage;



    private Uri filePath;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);


        save = (Button) findViewById(R.id.button2);
        editName = (EditText) findViewById(R.id.editText9);
        editPicture = (Button) findViewById(R.id.button5);
        picture = (ImageView) findViewById(R.id.imageView11);
        editInterest = (Button)findViewById(R.id.btn_editprofile);

        interest1 = (TextView)findViewById(R.id.textView28);
        interest2 = (TextView)findViewById(R.id.textView26);
        interest3 = (TextView)findViewById(R.id.textView27);
        interest4 = (TextView)findViewById(R.id.textView29);
        interest5 = (TextView)findViewById(R.id.textView32);

        editInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditInterestActivity.class);
                startActivity(intent);
            }
        });

        editPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이미지를 선택
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
            }
        });

        Intent intent = getIntent();
        defaultName = intent.getStringExtra("nameForm");
        editName.setText(defaultName);
        mDatabaseReferenceName = FirebaseDatabase.getInstance().getReference("users");


        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        storage = FirebaseStorage.getInstance();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
                if (editName.getText().toString().equals("")) {
                    Toast.makeText(EditprofileActivity.this, "Plz fill in blank", Toast.LENGTH_LONG).show();
                } else if (editName.getText().toString().equals(defaultName)) {
                    Toast.makeText(EditprofileActivity.this, "Plz fill the new Name", Toast.LENGTH_LONG).show();
                } else {
                    name = editName.getText().toString();
                    updateName(name);
                }
            }
        });

        mProfilePicture = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid()).child("profileUrl");
        mProfilePicture.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    profilePictureName = dataSnapshot.getValue().toString();
                }catch (Exception e){
                    profilePictureName = "default.png";
                }
                storageRef = storage.getReferenceFromUrl("gs://lte-ajou.appspot.com/").child("images");
                Glide.with(getApplicationContext() /* context */)
                        .using(new FirebaseImageLoader())
                        .load(storageRef.child(profilePictureName))
                        .into(picture);
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
                try {
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        if (count == 0) {
                            count++;
                            interest1.setText(dsp.getValue().toString());
                        } else if (count == 1) {
                            count++;
                            interest2.setText(dsp.getValue().toString());
                        } else if (count == 2) {
                            count++;
                            interest3.setText(dsp.getValue().toString());
                        } else if (count == 3) {
                            count++;
                            interest4.setText(dsp.getValue().toString());
                        } else if (count == 4) {
                            count++;
                            interest5.setText(dsp.getValue().toString());
                        } else {
                            Log.e("5", dsp.getValue().toString());
                        }
                    }
                }catch(Exception e){

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateName(String newName) {
        mDatabaseReferenceName.child(currentUser.getUid()).child("Name").setValue(name);
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request코드가 0이고 OK를 선택했고 data에 뭔가가 들어 있다면
        if (requestCode == 0 && resultCode == RESULT_OK) {
            filePath = data.getData();
            Log.d(TAG, "uri:" + String.valueOf(filePath));
            try {
                //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                picture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadFile() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();
            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            final String filename = formatter.format(now) + ".png";
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = storage.getReferenceFromUrl("gs://lte-ajou.appspot.com").child("images/" + filename);
            //올라가거라..."gs://lte-ajou.appspot.com"
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                            mDatabaseReferencePic = FirebaseDatabase.getInstance().getReference("users").child(currentUser.getUid()).child("profileUrl");
                            mDatabaseReferencePic.setValue(filename);
                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }


}

