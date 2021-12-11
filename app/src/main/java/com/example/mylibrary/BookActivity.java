package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private ImageView imgBook;
    private TextView txtBookName, txtAuthorName, txtPages, txtDescription;
    private Button btnCurrentlyReading, btnWantToRead, btnAlreadyRead, btnAddToFavourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initView();
//        String longDesc = "KUALA LUMPUR: A senior citizen who threatened to kill a restaurant worker in Taman Cheras has been detained.\n" +
//                "\n" +
//                "Cheras OCPD Asst Comm Muhammad Idzam Jaafar said the 62-year-old suspect was detained at his rented room above a restaurant shoplot on Saturday (Dec 4).\n" +
//                "\n" +
//                "\"He has been remanded until Tuesday (Dec 7),\" he said when contacted on Sunday (Dec 5).\n" +
//                "\n" +
//                "The suspect, who works as a cook, had allegedly threatened to kill a restaurant worker after he was accused of throwing rubbish and water from the upper floor onto the restaurant below, he added.\n" +
//                "\n" +
//                "\"The suspect was armed with a knife when he made the threat.\n" +
//                "\n";
//
//        //TODO: GET DATA FROM ADAPTER
//        Books book = new Books(1, "Vue.js", "Maven Woo", 1350, "https://www.google.com/chrome/static/images/homepage/lpo-chrome_desktop.png","This is short description", longDesc);

        Intent intent = getIntent();
        if (null != intent) {
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (-1 != bookId){
                Books incomingBook = Utils.getInstance().findBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyBook(incomingBook);
                    handleWantToReadBook(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleFavouriteBook(incomingBook);
                }
            }
        }
    }

    private void handleWantToReadBook(Books book) {
        ArrayList<Books> wantToReadBooks = Utils.getInstance().getWantToReadBooks();

        boolean existWantToRead = false;

        for (Books b: wantToReadBooks) {
            if (book.getId() == b.getId()){
                existWantToRead = true;
            }
        }

        if (existWantToRead) {
            btnWantToRead.setEnabled(false);
        } else {
            btnWantToRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addWantToRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleCurrentlyReadingBook(Books book) {
        ArrayList<Books> currentlyReadingBooks = Utils.getInstance().getCurrentlyReadingBooks();

        boolean existCurrentlyReading = false;

        for (Books b: currentlyReadingBooks) {
            if (book.getId() == b.getId()){
                existCurrentlyReading = true;
            }
        }

        if (existCurrentlyReading) {
            btnCurrentlyReading.setEnabled(false);
        } else {
            btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addCurrentlyReading(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadingBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleFavouriteBook(Books book) {
        ArrayList<Books> favouriteBooks = Utils.getInstance().getFavouriteBooks();

        boolean existFavourite = false;

        for (Books b: favouriteBooks) {
            if (book.getId() == b.getId()){
                existFavourite = true;
            }
        }

        if (existFavourite) {
            btnAddToFavourite.setEnabled(false);
        } else {
            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addFavourite(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavouriteBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and Disable button
     * Add the book to Already Book ArrayList
     * @param book
     */
    private void handleAlreadyBook(Books book){
        ArrayList<Books> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();

        boolean existAlreadyRead = false;

        for (Books b: alreadyReadBooks) {
            if (book.getId() == b.getId()){
                existAlreadyRead = true;
            }
        }

        if (existAlreadyRead) {
            btnAlreadyRead.setEnabled(false);
        } else {
            btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addAlreadyRead(book)) {
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BookActivity.this, "Something wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setData(Books book){
        txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());

        Glide.with(this).asBitmap().load(book.getImageUrl()).into(imgBook);
    }

    private void initView() {
        imgBook = findViewById(R.id.imgBook_BA);
        txtBookName = findViewById(R.id.txtBookName_BA);
        txtAuthorName = findViewById(R.id.txtAuthorName_BA);
        txtPages = findViewById(R.id.txtPages_BA);
        txtDescription = findViewById(R.id.txtDescription_BA);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading_BA);
        btnWantToRead = findViewById(R.id.btnWantToRead_BA);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead_BA);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourite_BA);
    }
}