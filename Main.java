package com.company;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
        boolean goForward = true;
        User.Playlist currentPlaylist = null;
        int choice;
        User currentUser = null;



        Market spofity = new Market();

            spofity.addNewAlbumToMarket("name1", "artist1");

        spofity.printAlbumsOnMarket();
        spofity.addSongsToAlbum("song1", 5, 11, "name1");
        spofity.addSongsToAlbum("song2", 6, 11, "name1");
        spofity.addSongsToAlbum("song3", 7, 11, "name1");
        spofity.addSongsToAlbum("song4", 8, 11, "name1");
        spofity.addSongsToAlbum("song5", 9, 11, "name1");

        spofity.addNewAlbumToMarket("name2", "artist2");
        spofity.addNewAlbumToMarket("name3", "artist3");

        spofity.addSongsToAlbum("piosenka1", 5, 11, "name2");
        spofity.addSongsToAlbum("piosenka2", 6, 11, "name2");
        spofity.addSongsToAlbum("piosenka3", 7, 11, "name2");
        spofity.addSongsToAlbum("piosenka4", 8, 11, "name2");
        spofity.addSongsToAlbum("piosenka5", 9, 11, "name2");

        spofity.addSongsToAlbum("piesn1", 5, 11, "name3");
        spofity.addSongsToAlbum("piesn2", 6, 11, "name3");
        spofity.addSongsToAlbum("piesn3", 7, 11, "name3");
        spofity.addSongsToAlbum("piesn4", 8, 11, "name3");
        spofity.addSongsToAlbum("piesn5", 9, 11, "name3");




       // spofity.printAlbumsOnMarket();

        User user = new User("Marcin");
        spofity.addUser(user);
        currentUser = user;
       // spofity.buyAlbum("name1", currentUser);


/*
        currentUser.createNewPlaylist("playlist1");
        currentUser.addToPlaylist("song1", currentPlaylist);
        currentUser.addToPlaylist("song1", currentPlaylist);
        currentUser.addToPlaylist("song2", currentPlaylist);
        currentUser.addToPlaylist("song3", currentPlaylist);
        currentUser.addToPlaylist("song4", currentPlaylist);
        currentUser.addToPlaylist("song5", currentPlaylist);

        spofity.printPlaylist("playlist1", "Marcin"); */
        printMenu();
        while(!quit) {
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("++++++++++++++++GOOD BYE++++++++++++++++++++++++++++++");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    }
                    catch (InterruptedException e){
                    }
                    quit = true;
                    break;
                case 1:
                    System.out.println("Please enter name of playlist.");
                    String name = scanner.nextLine();
                    currentUser.createNewPlaylist(name);
                    break;

                case 2:
                    currentUser.removePlaylist();
                    break;

                case 3:
                        User.Playlist somePlaylist = spofity.selectPlaylist(currentUser);
                        if (somePlaylist == null){
                        } else {
                            currentPlaylist = somePlaylist;
                            System.out.println("current playlist: " + currentPlaylist.getName());
                        }
                    break;
                case 4:

                    if (currentPlaylist == null){
                        System.out.println("You have to choose your playlist at first!!");
                    } else
                    goForward = currentPlaylist.playNextSong(goForward,currentUser);
                    break;

                case 5:
                    if (currentPlaylist == null){
                        System.out.println("You have to choose your playlist at first!!");
                    } else
                    goForward = currentPlaylist.playPreviousSong(goForward,currentUser);
                    break;

                case 6:
                    if (currentPlaylist == null){
                        System.out.println("You have to choose your playlist at first!!");
                    } else
                    goForward = currentPlaylist.replaySong(goForward,currentUser);
                    break;

                case 7: //removing song
                    if (currentPlaylist == null){
                        System.out.println("You have to choose your playlist at first!!");
                    } else
                    currentPlaylist.removeSong();
                    break;

                case 8: //play entire playlist
                    if (currentPlaylist == null){
                        System.out.println("You have to choose your playlist at first!!");
                    } else
                    currentPlaylist.playPlaylist();
                    break;

                case 9: //play entire album
                    currentUser.playAlbum();
                    break;

                case 10: //buy album
                    spofity.printAlbumsOnMarket();
                    System.out.println("Enter a title of album");
                    String albumName = scanner.next();
                    spofity.buyAlbum(albumName, currentUser);
                    break;
                case 11: //display owned albums
                    currentUser.printOwnedAlbums();
                    break;
                case 12: //adding songs to your playlist
                    if (currentPlaylist == null){
                        System.out.println("You have to choose your playlist before u add them songs.");
                    } else {
                        currentUser.printOwnedAlbums();
                        System.out.println("Enter a title of song to add to playlist: " + currentPlaylist.getName());
                        String title = scanner.next();
                        currentUser.addToPlaylist(title, currentPlaylist);
                    }
                    break;
                case 13:
                    currentUser.printOwnedPlaylists();
                    break;
                case 14:
                    printMenu();
                    break;
            }

        }
    }

    public static void printMenu(){

        System.out.println("Press 0 to quit application.");
        System.out.println("Press 1 to create new playlist.");
        System.out.println("Press 2 to remove playlist.");
        System.out.println("Press 3 to select playlist.");
        System.out.println("Press 4 to play next song.");
        System.out.println("Press 5 to play previous song.");
        System.out.println("Press 6 to replay song.");
        System.out.println("Press 7 to remove song from the playlist.");
        System.out.println("Press 8 to play entire playlist.");
        System.out.println("Press 9 to choose and play entire album.");
        System.out.println("Press 10 to buy album."); //wyswietla albumy które są do kupienia a nie są posiadane
        System.out.println("Press 11 to display your albums.");
        System.out.println("Press 12 to add song to your playlist."); //zaznaczonej
        System.out.println("Press 13 to print your playlists.");
        System.out.println("Press 14 to print menu again.");
        System.out.println("\nYour choice: (1-13)");

        // Tworzenie piosenek i albumów jest realizowane ręcznie.
    }

}