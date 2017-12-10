package com.example.taewoong.exchangestudent.Database;

import java.util.ArrayList;

/**
 * Created by Taewoong on 2017-12-05.
 */

public class UserData {
    public String userEmail;
    public String UID;
    public String userName;
    public String picUrl;




    public UserData(){

    }
    public UserData(String userName){
        this.userEmail = userName;
    }

    public UserData(String userEmail, String picUrl){
        this.userEmail = userName;
        this.picUrl = picUrl;
    }


}