package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBookActivity extends AppCompatActivity {
    
    private RecyclerView alreadyReadBookRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        alreadyReadBookRecView = findViewById(R.id.alreadyReadBookRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(AlreadyReadBookActivity.this, "alreadyRead");
        alreadyReadBookRecView.setAdapter(adapter);
        alreadyReadBookRecView.setLayoutManager(new LinearLayoutManager(AlreadyReadBookActivity.this));

        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(AlreadyReadBookActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}