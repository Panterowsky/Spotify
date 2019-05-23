package com.company;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class User {
    private String nick;
    private List<Album> albums;
    private List<Playlist> playlists;

    public User(String nick) {
        this.nick = nick;
        this.albums = new ArrayList<Album>();
        this.playlists = new ArrayList<>();
    }

    public String getNick() {
        return nick;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void printOwnedPlaylists(){
        if (this.playlists.isEmpty()){
            System.out.println("You don't have any playlists yet.");
        } else{
            for (Playlist playlist : this.playlists){
                playlist.printPlaylist();
            }


        }
    }

    public void printOwnedAlbums(){
        if (this.albums.isEmpty()){
            System.out.println("The list of owned albums is empty. You can buy albums in our shop.");
        } else {
            for (Album album : this.albums){
                album.printSongsInAlbum();
            }
        }
    }


    public void playAlbum() {

        Scanner scanner = new Scanner(System.in);

        printOwnedAlbums();
        if (!this.albums.isEmpty()) {
            System.out.println("Please enter a title of album:");
            String albumName = scanner.next();

            Album album = findAlbum(albumName);
            //Album albumMarket = spotify.findAlbumOnMarket(albumName);


            if (album == null) {
                System.out.println("You don't have this album.");
            } else {
                if (album.getSongs().isEmpty()) {
                    System.out.println("This album is empty.");
                } else {
                    for (Song song : album.getSongs()) {
                        System.out.println("Now playing: " + album.getSongs().indexOf(song) + song.toString());
                        try {
                            TimeUnit.SECONDS.sleep(song.getMinutes());
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }
    }


    public void removePlaylist(){
        Scanner scanner = new Scanner(System.in);
        this.printAvailablePlaylists();
        if (this.playlists.isEmpty()) return;
        System.out.println("Write the number of the list you want to delete. ");
        int choice = scanner.nextInt()-1;
        if (choice < 0 || choice >= this.playlists.size()){
            System.out.println("Entered number is not correct.");
            return;
        }


        System.out.println("Playlist: " + this.playlists.get(choice).getName() + " removed.");
        this.playlists.remove(choice);
    }

    public Playlist returnPlaylist(int choice){
        if (choice >= 0 && choice < playlists.size()){
            return playlists.get(choice);
        } else {
            System.out.println("You are out of range.");
            return null;
        }
    }

    public void printAvailablePlaylists(){
        if (this.playlists.isEmpty()){
            System.out.println("You haven't got any playlists."); }
            else{
                for (Playlist playlist : this.playlists){
                    System.out.println(playlists.indexOf(playlist)+1 + ": " + playlist.getName());
            }
        }
    }

    public Playlist findPlaylist(String playlistName){
        for (Playlist playlist : this.playlists){
            if (playlist.getName().equals(playlistName)){
                return playlist;
            }
        } return null;
    }

    public boolean addToPlaylist(String songTitle, Playlist playlist){
        if (playlist == null){
            System.out.println("This playlist doesn't exist.");
            return false;
        }
        if (this.albums.isEmpty()){
            System.out.println("You haven't bought any album!");
            return false;
        }

        System.out.println("SiZE: " + this.albums.size());

        for (Album checkedAlbum : this.getAlbums()){
            if (checkedAlbum == null){
                System.out.println(this.albums.size());
                System.out.println("NULL");
            }

            Song checkedSong = checkedAlbum.findSong(songTitle);
            {
                if (checkedSong != null) {
                    playlist.addToPlaylist(checkedSong);
                    //System.out.println("Song added.");
                    return true;
                }
            }
        }
        System.out.println("dasd");
        return false;

    }

    public void createNewPlaylist(String name){
        Playlist checkedPlaylist = findPlayList(name);
        if (checkedPlaylist == null){
            this.playlists.add(new Playlist(name));
            System.out.println("New list created.");
        } else {
            System.out.println("This playlist already exist.");
        }
    }

    private Album findAlbum(Album album) {
        for (Album checkedAlbum : this.albums) {
            if (checkedAlbum.equals(album)) {
                return checkedAlbum;}
            }

        return null;
    }

    private Album findAlbum(String name){
        for(Album checkedAlbum : this.albums){
            if (checkedAlbum.getName().equals(name)){
                return checkedAlbum;
            }
        } return null;
    }

    private Playlist findPlayList(String name){
        for(Playlist checkedPlaylist : this.playlists){
            if (checkedPlaylist.getName().equals(name)){
                return checkedPlaylist;
            }
        } return null;
    }

    public void buyAlbum(Album album){

        if (findAlbum(album) == null){
            this.albums.add(album);
            System.out.println("Album purchased.");
        } else{
            System.out.println("User already purchased this album.");
        }
    }



//=======================================================================================================

    public class Playlist{

        private String name;
        private List<Song> songs;
        private int iterationValue;

        public Playlist(String name) {
            this.name = name;
            this.songs = new LinkedList<>();
            this.iterationValue = 0;
        }

        public List<Song> getSongs() {
            return songs;
        }

        public String getName() {
            return name;
        }

        public void removeSong(){

            if (this.songs.isEmpty()){
                System.out.println("This playlist doesn't contain any song.");
                return;
            }

            Scanner scanner = new Scanner(System.in);

            printPlaylist();
            System.out.println("Please enter a number of song, which you want to delete. ");
            int choice = scanner.nextInt()-1;
            scanner.next();

            if (choice < 0 || choice > this.songs.size()){
                System.out.println("Number is out of range.");
                return;
            }

            for (Song song : this.songs){
                if (song.getTitle().equals(this.songs.get(choice).getTitle())){
                    this.songs.remove(song);
                    System.out.println("Title removed.");
                    return;
                }
            }
            System.out.println("This playlist doesn't contain this song.");
        }

        public boolean playNextSong(boolean goForward, User user){

            if (this.songs.isEmpty()){
                System.out.println("This playlist doesn't contain any song.");
                return goForward;
            }


            int value;
            if (this.iterationValue > this.songs.size()){
                value = this.songs.size();
            } else{
                value = this.iterationValue;
            }

            ListIterator<Song> songListIterator = this.songs.listIterator(value);

            if (this.songs == null){
                System.out.println("NULL POINTER");
                return goForward;
            }

            if (!goForward){
                if(songListIterator.hasNext()){
                    goForward = true;
                    songListIterator.next();
                }
            }

            if (songListIterator.hasNext()){
                System.out.println("Now playing: " + songListIterator.next().toString());
            } else{
                System.out.println("It's the last song on the playlist.");
                goForward = false;
            }
            this.iterationValue = songListIterator.nextIndex();
            return goForward;
        }

        public boolean replaySong(boolean goForward, User user) {

            if (this.songs.isEmpty()){
                System.out.println("This playlist doesn't contain any song.");
                return goForward;
            }

            int value;
            if (this.iterationValue > this.songs.size()){
                value = this.songs.size();
            } else{
                value = this.iterationValue;
            }

            ListIterator<Song> songListIterator = this.songs.listIterator(value);

            if (goForward) {
                if (songListIterator.hasPrevious()) {
                    System.out.println("Now playing again: " + songListIterator.previous().toString());
                    this.iterationValue = songListIterator.nextIndex()+1;
                    goForward = true;
                } else {
                    System.out.println("We are at start of the list.");
                }
            } else {
                if (songListIterator.hasNext()) {
                    System.out.println("Now replaying: " + songListIterator.next().toString());
                    this.iterationValue = songListIterator.previousIndex();
                    goForward = false;
                } else {
                    System.out.println("You reached the end of the list.");
                }
            }
            return goForward;
        }


        public boolean playPreviousSong(boolean goForward, User user){
            if (this.songs.isEmpty()){
                System.out.println("This playlist doesn't contain any song.");
                return goForward;
            }
            int value;
            if (this.iterationValue > this.songs.size()){
                value = this.songs.size();
            } else{
                value = this.iterationValue;
            }

            ListIterator<Song> songListIterator = this.songs.listIterator(value);

            if (this.songs == null){
                System.out.println("NULL POINTER");
                return goForward;
            }

            if (goForward){
                if(songListIterator.hasPrevious()){
                    songListIterator.previous();
                }
                goForward = false;
            }

            if (songListIterator.hasPrevious()){
                System.out.println("Now playing: " + songListIterator.previous().toString());
            } else{
                System.out.println("It's the first.");
                goForward = true;
            }
            this.iterationValue = songListIterator.nextIndex();
            return goForward;
        }


        public void printPlaylist(){
            if (this.songs.isEmpty()){
                System.out.println("Playlist" + this.getName() + " is empty.");
            } else{
                System.out.println(this.getName() + ":");
                for (Song song : songs) {
                    if (this.songs.listIterator().hasNext()) {
                        System.out.println(this.songs.indexOf(song)+1 + song.toString());
                    }
                }
            }
        }

        public boolean addToPlaylist(Song song){
            for (Song checkedSong : this.songs){
                if (checkedSong.getTitle().equals(song.getTitle())){
                    System.out.println("Playlist already contain this song.");
                    return false;
                }
            } songs.add(song);
            System.out.println("Song added to the playlist.");
            return true;
        }

        public void playSingle(){
            ListIterator<Song> playListIterator = this.songs.listIterator();
            if (this.songs.size() == 0){
                System.out.println("This playlist is empty.");
            }
        }

        public void playPlaylist(){
            //Playlist playList = findPlayList(playListName);
            if (this.songs.size() == 0){
                System.out.println("Playlist doesn't contain any song.");
                return;
            }
            ListIterator<Song> playListIterator = this.songs.listIterator();


                while (playListIterator.hasNext()){
                System.out.println("Now playing: " + playListIterator.next().toString());
                try {
                    TimeUnit.SECONDS.sleep(playListIterator.previous().getMinutes());
                }
                catch (InterruptedException e){
            }
                playListIterator.next();
        }
        }



    }

}
