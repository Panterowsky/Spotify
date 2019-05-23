package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Market {
    private ArrayList<Album> albumsOnMarket;
    private ArrayList<User> users;

    public Market() {
        this.albumsOnMarket = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addUser(User user){
        if (this.users.isEmpty()){
            this.users.add(user);
            return;
        }
        for (User userek : this.users){
            if (userek.getNick().equals(user.getNick())){
                System.out.println("This nick is already reserved.");
            } else{
                this.users.add(user);
                System.out.println(user.getNick() + " added.");
            }
        }
    }

    public void buyAlbum(String albumName, User user){
        Album album = findAlbumOnMarket(albumName);
        if (album == null){
            System.out.println("This album doesn't exist.");
        } else if (user == null){
            System.out.println("This user doesn't exist.");
        } else{
            user.buyAlbum(album);
        }
    }


    public User.Playlist selectPlaylist(User user) {
        Scanner scanner = new Scanner(System.in);
        if (user.getPlaylists().isEmpty()) {
            System.out.println("You have to create your playlist at first!!");
            return null;
        } else {
            System.out.println("Your playlist:");
            user.printAvailablePlaylists();
            System.out.println("Write a number of playlist:");
            int choice;
            choice = scanner.nextInt();
            User.Playlist playlist = user.returnPlaylist(choice - 1);
            return playlist;
        }
    }

    public User.Playlist choosePlaylist(User user, int choice){

        User.Playlist playlist = user.returnPlaylist(choice-1);
        return playlist;
    }

    public void printPlaylist(String playListName, String userName){
        User user = findUserOnMarket(userName);
        if (user == null){
            System.out.println("This user doesn't exist.");
            return;
        }
        User.Playlist playlist = user.findPlaylist(playListName);
        if (playlist == null){
            System.out.println("This playlist doesn't exist.");
            return;
        }
            playlist.printPlaylist();
    }


    public void printAlbumsOnMarket(){
        if (this.albumsOnMarket.isEmpty()){
            System.out.println("Market doesn't contain any albums.");
        }
        for (Album album : albumsOnMarket){
            System.out.println("Album title:" + album.getName());
            album.printSongsInAlbum();
        }
    }

    public void addSongsToAlbum(String title, int minutes, int seconds, String albumTitle){
        if (findAlbumOnMarket(albumTitle) == null){
            System.out.println("This album doesn't exist.");
        } else{
            Album album = findAlbumOnMarket(albumTitle);
            album.addToAlbum(title,minutes,seconds);
            System.out.println(title + " added to album: " + album.getName());
        }
    }

    public Album findAlbumOnMarket(String title){
        for (Album album : this.albumsOnMarket){
            if (album.getName().equals(title)){
                return album;
            }
        }
        return null;
    }

    private User findUserOnMarket(String username){
        System.out.println(this.users.size());
        for (User user : this.users){
            if (user.getNick().equals(username)){
                return user;
            }
        } return null;
    }

    public void addNewAlbumToMarket(String name, String artist){
        if (findAlbumOnMarket(name) != null){
            System.out.println("Album with this title already exist on the market.");
        }else{
            this.albumsOnMarket.add(new Album(name,artist));
        }
    }

    }






