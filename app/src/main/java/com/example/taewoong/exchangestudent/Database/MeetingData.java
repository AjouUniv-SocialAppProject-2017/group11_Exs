package com.example.taewoong.exchangestudent.Database;

/**
 * Created by Taewoong on 2017-12-05.
 */

public class MeetingData {

    public String name;
    public String Cost;
    public String Location;
    public String Time;
    public String About;
    public String Group;

    public MeetingData() {
    }

    public MeetingData(String cost, String location, String time, String about,String group) {
        this.Cost = cost;
        this.Location = location;
        this.Time = time;
        this.About = about;
        this.Group = group;
    }
}