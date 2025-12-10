package com.fic.cursoandroid2025g4.data.repository;

import com.fic.cursoandroid2025g4.data.remote.ApiClient;
import com.fic.cursoandroid2025g4.data.remote.ApiService;
import com.fic.cursoandroid2025g4.model.Book;

import java.util.List;

public class BookRepository extends BaseRepository {

    private final ApiService apiService;

    public BookRepository() {
        this.apiService = ApiClient.getApiService();
    }

    public void getAllBooks(RepositoryCallback<List<Book>> callback){
        executeAsync(apiService.getBooks(),callback);
    }

    public void addBook(Book book,RepositoryCallback<Book> callback){
        executeAsync(apiService.addBook(book),callback);
    }

    public void updateBook(Book book,RepositoryCallback<Book> callback){
        executeAsync(apiService.updateBook(book.id,book),callback);
    }

    public void deleteBook(Book book,RepositoryCallback<Void> callback){
        executeAsync(apiService.deleteBook(book.id),callback);
    }
}
