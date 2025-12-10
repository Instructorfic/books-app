package com.fic.cursoandroid2025g4.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fic.cursoandroid2025g4.R;
import com.fic.cursoandroid2025g4.model.Book;

import java.util.ArrayList;
import java.util.List;

public  class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private final List<Book> bookList = new ArrayList<>();


    public void setData(List<Book> books){
        bookList.clear();
        if(books != null){
            bookList.addAll(books);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book,parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.txtTitle.setText(book.title);
        holder.txtAuthor.setText(book.author);
        holder.txtStatus.setText(book.status);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public static class BookViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtAuthor, txtStatus;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.tvTitle);
            txtAuthor = itemView.findViewById(R.id.tvAuthor);
            txtStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
