package com.example.taewoong.exchangestudent.Database;

/**
 * Created by Taewoong on 2017-12-05.
 */

public class MeetingData {
    public String Location;
    public String Time;
    public String About;
    public String Host;

    public MeetingData(){

    }

    public MeetingData(String Host, String Location, String Time, String About){
        this.Host = Host;
        this.Location = Location;
        this.Time = Time;
        this.About = About;
    }
}