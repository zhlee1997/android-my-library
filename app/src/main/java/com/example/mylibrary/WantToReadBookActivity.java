package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class WantToReadBookActivity extends AppCompatActivity {

    private RecyclerView wantToReadBookRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read_book);

        wantToReadBookRecView = findViewById(R.id.wantToReadBookRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(WantToReadBookActivity.this, "wantToRead");
        wantToReadBookRecView.setAdapter(adapter);
        wantToReadBookRecView.setLayoutManager(new LinearLayoutManager(WantToReadBookActivity.this));

        adapter.setBooks(Utils.getWantToReadBooks());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(WantToReadBookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}