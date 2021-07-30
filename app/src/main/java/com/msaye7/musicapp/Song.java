package com.msaye7.musicapp;

/*
 * Created by Mohannad El-Sayeh on 29/07/2021
 */

import java.io.Serializable;

public class Song implements Serializable {

    private String name;
    private String artist;

    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }
}
