package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOK_KEY = "all_books";
    private static final String ALREADY_READ_BOOK_KEY = "already_read_books";
    private static final String WANT_TO_READ_BOOK_KEY = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOK_KEY = "currently_reading_books";
    private static final String FAVOURITE_BOOK_KEY = "favourite_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;

    private static ArrayList<Books> allBooks;
    private static ArrayList<Books> alreadyReadBooks;
    private static ArrayList<Books> wantToReadBooks;
    private static ArrayList<Books> currentlyReadingBooks;
    private static ArrayList<Books> favouriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (null == getAllBooks()){
            initData();
        }

        if (null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOK_KEY, gson.toJson(new ArrayList<Books>()));
            editor.commit();
        }

        if (null == getWantToReadBooks()){
            editor.putString(WANT_TO_READ_BOOK_KEY, gson.toJson(new ArrayList<Books>()));
            editor.commit();
        }

        if (null == currentlyReadingBooks){
            editor.putString(CURRENTLY_READING_BOOK_KEY, gson.toJson(new ArrayList<Books>()));
            editor.commit();
        }

        if (null == favouriteBooks){
            editor.putString(FAVOURITE_BOOK_KEY, gson.toJson(new ArrayList<Books>()));
            editor.commit();
        }
    }

    public ArrayList<Books> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(ALL_BOOK_KEY, null), type);
        return books;
    }

    public ArrayList<Books> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOK_KEY, null), type);
        return books;
    }

    public ArrayList<Books> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOK_KEY, null), type);
        return books;
    }

    public ArrayList<Books> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOK_KEY, null), type);
        return books;
    }

    public  ArrayList<Books> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Books>>(){}.getType();
        ArrayList<Books> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOK_KEY, null), type);
        return books;
    }

    private void initData() {

        ArrayList<Books> books = new ArrayList<>();
        books.add(new Books(1, "Vue.js", "Maven Woo", 1350, "https://www.google.com/chrome/static/images/homepage/lpo-chrome_desktop.png","This is short vue description", "This is long vue description"));
        books.add(new Books(2, "React.js", "Maven James", 1350, "https://www.google.com/chrome/static/images/homepage/lpo-chrome_desktop.png","This is short react description", "This is long react description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOK_KEY, gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context) {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils(context);
            return instance;
        }
    }

    public Books findBookById (int id) {
        ArrayList<Books> books = getAllBooks();
        if (books != null) {
            for (Books b: books) {
                if (id == b.getId()){
                    return b;
                }
            }
        }
        return null;
    }

    public boolean addAlreadyRead(Books book) {
        ArrayList<Books> books = getAlreadyReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOK_KEY);
                editor.putString(ALREADY_READ_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addWantToRead(Books book) {
        ArrayList<Books> books = getWantToReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOK_KEY);
                editor.putString(WANT_TO_READ_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addCurrentlyReading(Books book) {
        ArrayList<Books> books = getCurrentlyReadingBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOK_KEY);
                editor.putString(CURRENTLY_READING_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addFavourite(Books book) {
        ArrayList<Books> books = getFavouriteBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOK_KEY);
                editor.putString(FAVOURITE_BOOK_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeAlreadyRead(Books book) {
        ArrayList<Books> books = getAlreadyReadBooks();
        if (books != null) {
            // cannot use books.remove(book), because reference not same
            for (Books b:books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOK_KEY);
                        editor.putString(ALREADY_READ_BOOK_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeWantToRead(Books book) {
        ArrayList<Books> books = getWantToReadBooks();
        if (books != null) {
            // cannot use books.remove(book), because reference not same
            for (Books b:books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOK_KEY);
                        editor.putString(WANT_TO_READ_BOOK_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeCurrentlyReading(Books book) {
        ArrayList<Books> books = getCurrentlyReadingBooks();
        if (books != null) {
            // cannot use books.remove(book), because reference not same
            for (Books b:books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOK_KEY);
                        editor.putString(CURRENTLY_READING_BOOK_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFavourite(Books book) {
        ArrayList<Books> books = getFavouriteBooks();
        if (books != null) {
            // cannot use books.remove(book), because reference not same
            for (Books b:books) {
                if (b.getId() == book.getId()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOK_KEY);
                        editor.putString(FAVOURITE_BOOK_KEY, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
