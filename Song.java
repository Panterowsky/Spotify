package com.company;

public class Song {
    private String title;
    private int minutes;
    private int seconds;

    public Song(String title, int minutes, int seconds) {
        this.title = title;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
