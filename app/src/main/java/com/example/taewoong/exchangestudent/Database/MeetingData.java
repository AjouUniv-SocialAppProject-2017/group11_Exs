package com.example.taewoong.exchangestudent.Database;

/**
 * Created by Taewoong on 2017-12-05.
 */

public class MeetingData {
    String Location;
    String Genre;
    String Time;
    String About;
    String Host;

    public MeetingData(){

    }

    public MeetingData(String Host, String Location, String Genre, String Time, String About){
        this.Host = Host;
        this.Location = Location;
        this.Genre = Genre;
        this.Time = Time;
        this.About = About;
    }
}
