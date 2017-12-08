package com.example.taewoong.exchangestudent.Database;

/**
 * Created by Taewoong on 2017-12-06.
 */

public class GroupData {
    public String Host;
    public String Region;
    public String Genre;
    public String About;

    public GroupData(){

    }

    public GroupData(String Host, String Region, String Genre, String About){
        this.Host = Host;
        this.Region = Region;
        this.Genre = Genre;
        this.About = About;
    }
}
