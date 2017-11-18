package com.example.taewoong.exchangestudent.Fragment;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class item {
    int image;
    String title;

    public int getImage() {
        return this.image;
    }
    public String getTitle() {
        return this.title;
    }

    item(int image, String title) {
        this.image = image;
        this.title = title;
    }
}
