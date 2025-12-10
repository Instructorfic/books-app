package com.fic.cursoandroid2025g4.controller;

import android.content.Context;
import android.util.Log;

import com.fic.cursoandroid2025g4.model.Book;
import com.fic.cursoandroid2025g4.model.BookDao;
import com.fic.cursoandroid2025g4.model.BookDatabase;


import java.util.List;

public class BookController {

    private final BookDao bookDao;

    public BookController(Context context) {
        BookDatabase database = BookDatabase.getInstance(context);
        bookDao = database.bookDao();
    }

    //Create a book
    public boolean addBook(String title, String author, String status){
        try{
            Book book = new Book();
            book.title = title;
            book.author = author;
            book.status = status;
            bookDao.insert(book);
            Log.i("BOOK_SAVE","El libro se almacenó correctamente");
            return true;

        }catch (Exception e){
            Log.e("BOOK_ERROR",e.getMessage());
            return false;
        }

    }

    //Get all books
    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public Book getBookById(int id){
        return bookDao.findById(id);
    }

    //Update book
    public void updateBook(Book book){
        bookDao.update(book);
    }

    //Delete book
    public void deleteBook(Book book){
        bookDao.delete(book);
    }
}
