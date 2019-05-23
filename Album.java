package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Album {
    private String name;
    private String artist;
    private List<Song> songs;

    public Album(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void printSongsInAlbum(){
        if (this.songs.isEmpty()){
            System.out.println("This album is empty.");
        } else {
            System.out.println(this.getName());

            for (int i = 0; i < this.songs.size(); i++) {
                System.out.println(i + 1 + ": " + this.songs.get(i).getTitle() + " " + this.songs.get(i).getMinutes()
                        + ":" + this.songs.get(i).getSeconds());
            }
        }
    }

    public void addToAlbum(String title, int minutes, int seconds){
        if (this.findSong(title) == null){
            this.songs.add(new Song(title,minutes,seconds));
        } else{
            System.out.println("This album already contain song: " + title);
        }
    }

    public Song findSong(String name){ //in album
        for (Song checkedSong : this.songs){
            if (checkedSong.getTitle().equals(name)){
                return checkedSong;
            }
        } return null;
    }






}
