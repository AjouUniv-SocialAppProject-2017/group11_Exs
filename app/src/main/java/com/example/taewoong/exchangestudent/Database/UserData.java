package com.example.taewoong.exchangestudent.Database;

import java.util.ArrayList;

/**
 * Created by Taewoong on 2017-12-05.
 */

public class UserData {
    public String userEmail;
    public String userEmailID; // email 주소에서 @ 이전까지의 값.
    public ArrayList<String> Interest;
    public ArrayList<String> Joined_meeting;
    public String UID;
    public String userName;
    public String picUrl;




    public UserData(){

    }
    public UserData(String userName){
        this.userName = userName;
    }

    public UserData(String userName, String picUrl){
        this.userName = userName;
        this.picUrl = picUrl;
    }


}