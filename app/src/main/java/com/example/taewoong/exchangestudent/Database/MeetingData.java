package com.example.taewoong.exchangestudent.Database;

/**
 * Created by Taewoong on 2017-12-05.
 */

public class MeetingData {

    public String name;
    public String cost;
    public String location;
    public String time;
    public String about;

    public MeetingData() {
    }

    public MeetingData(String name, String cost, String location, String time, String about) {
        this.name = name;
        this.cost = cost;
        this.location = location;
        this.time = time;
        this.about = about;
    }
}