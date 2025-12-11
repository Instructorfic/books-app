package com.fic.cursoandroid2025g4;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fic.cursoandroid2025g4.controller.BookController;
import com.fic.cursoandroid2025g4.data.repository.BaseRepository;
import com.fic.cursoandroid2025g4.model.Book;
import com.fic.cursoandroid2025g4.view.BookAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookActivity extends AppCompatActivity {

    private BookAdapter bookAdapter;
    private BookController bookController;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);

        RecyclerView recyclerViewBooks = findViewById(R.id.rvBooks);
        recyclerViewBooks.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout = findViewById(R.id.srBooks);
        bookAdapter = new BookAdapter();
        recyclerViewBooks.setAdapter(bookAdapter);

        bookController = new BookController(this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchBooks();
            }
        });

        fetchBooks();

        FloatingActionButton fabAddBook = findViewById(R.id.fabAddBook);

        fabAddBook.setOnClickListener(view -> {
            showAddBookActivity();
        });


    }

    private void fetchBooks(){
        bookController.getAllBooks(new BaseRepository.RepositoryCallback<List<Book>>() {
            @Override
            public void onSuccess(List<Book> result) {
                bookAdapter.setData(result);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable error) {
                swipeRefreshLayout.setRefreshing(false);
                Log.e("BOOK_ACTIVITY","Ocurrió un error al obtener el listado de libros: " + error.getMessage());
            }
        });
    }

    private void showAddBookActivity(){
        Intent intent = new Intent(BookActivity.this,AddBookActivity.class);
        startActivity(intent);
    }
}