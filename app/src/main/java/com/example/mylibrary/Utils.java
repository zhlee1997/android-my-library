package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Books> allBooks;
    private static ArrayList<Books> alreadyReadBooks;
    private static ArrayList<Books> wantToReadBooks;
    private static ArrayList<Books> currentlyReadingBooks;
    private static ArrayList<Books> favouriteBooks;

    private Utils() {
        if (null == allBooks){
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if (null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if (null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if (null == favouriteBooks){
            favouriteBooks = new ArrayList<>();
        }
    }

    public static ArrayList<Books> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Books> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Books> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Books> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Books> getFavouriteBooks() {
        return favouriteBooks;
    }

    private void initData() {
        allBooks.add(new Books(1, "Vue.js", "Maven Woo", 1350, "https://www.google.com/chrome/static/images/homepage/lpo-chrome_desktop.png","This is short vue description", "This is long vue description"));
        allBooks.add(new Books(2, "React.js", "Maven James", 1350, "https://www.google.com/chrome/static/images/homepage/lpo-chrome_desktop.png","This is short react description", "This is long react description"));
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public Books findBookById (int id) {
        for (Books b: allBooks) {
            if (id == b.getId()){
                return b;
            }
        }
        return null;
    }

    public boolean addAlreadyRead(Books book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addWantToRead(Books book) {
        return wantToReadBooks.add(book);
    }

    public boolean addCurrentlyReading(Books book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean addFavourite(Books book) {
        return favouriteBooks.add(book);
    }

    public boolean removeAlreadyRead(Books book) {
        return alreadyReadBooks.remove(book);
    }

    public boolean removeWantToRead(Books book) {
        return wantToReadBooks.remove(book);
    }

    public boolean removeCurrentlyReading(Books book) {
        return currentlyReadingBooks.remove(book);
    }

    public boolean removeFavourite(Books book) {
        return favouriteBooks.remove(book);
    }
}
