package com.fic.cursoandroid2025g4.data.remote;

import com.fic.cursoandroid2025g4.model.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("books")
    Call<List<Book>> getBooks();

    @POST("books")
    Call<Book> addBook(@Body Book book);

    @PUT("books/{id}")
    Call<Book> updateBook(@Path("id") int id, @Body Book book);

    @DELETE("books/{id}")
    Call<Void> deleteBook(@Path("id") int id);
}
