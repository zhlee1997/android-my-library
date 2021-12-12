package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavouriteBookActivity extends AppCompatActivity {

    private RecyclerView favouriteBookRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_book);

        favouriteBookRecView = findViewById(R.id.favouriteBookRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(FavouriteBookActivity.this, "favourite");
        favouriteBookRecView.setAdapter(adapter);
        favouriteBookRecView.setLayoutManager(new LinearLayoutManager(FavouriteBookActivity.this));

        adapter.setBooks(Utils.getInstance(this).getFavouriteBooks());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(FavouriteBookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}