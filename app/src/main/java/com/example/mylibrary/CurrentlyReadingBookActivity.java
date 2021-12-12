package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingBookActivity extends AppCompatActivity {

    private RecyclerView currentlyReadingBookRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading_book);

        currentlyReadingBookRecView = findViewById(R.id.currentlyReadingBookRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(CurrentlyReadingBookActivity.this, "currentlyReading");
        currentlyReadingBookRecView.setAdapter(adapter);
        currentlyReadingBookRecView.setLayoutManager(new LinearLayoutManager(CurrentlyReadingBookActivity.this));

        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(CurrentlyReadingBookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}