package com.fic.cursoandroid2025g4.controller;

import android.content.Context;
import android.util.Log;

import com.fic.cursoandroid2025g4.data.repository.BaseRepository;
import com.fic.cursoandroid2025g4.data.repository.BookRepository;
import com.fic.cursoandroid2025g4.model.Book;
import com.fic.cursoandroid2025g4.model.BookDao;
import com.fic.cursoandroid2025g4.model.BookDatabase;


import java.util.List;

public class BookController {

    private final BookRepository bookRepository;

    public BookController(Context context) {
        this.bookRepository = new BookRepository();
    }

    //Create a book
    public void addBook(String title, String author, String status, BaseRepository.RepositoryCallback<Book> callback) {

        Book book = new Book();
        book.title = title;
        book.author = author;
        book.status = status;
        bookRepository.addBook(book, new BaseRepository.RepositoryCallback<Book>() {
            @Override
            public void onSuccess(Book result) {
                Log.i("BOOK_SAVE", "El libro se almacenó correctamente");
                callback.onSuccess(result);
            }

            @Override
            public void onError(Throwable error) {
                Log.e("BOOK_ERROR", error.getMessage());
                callback.onError(error);
            }
        });
    }

    //Get all books
    public void getAllBooks(BaseRepository.RepositoryCallback<List<Book>> callback){
        bookRepository.getAllBooks(callback);
    }

    //Update book
    public void updateBook(Book book,BaseRepository.RepositoryCallback<Book> callback){

        bookRepository.updateBook(book,callback);
    }

    //Delete book
    public void deleteBook(Book book, BaseRepository.RepositoryCallback<Void> callback){

        bookRepository.deleteBook(book, callback);
    }
}
