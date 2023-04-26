package com.fit2081.week2lab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdaptBooks extends RecyclerView.Adapter<RecyclerAdaptBooks.ViewHolder>{
    private ArrayList<Book> books = new ArrayList<>();
//    public void setBooks(ArrayList<Book> content){
//        this.books = content;
//    }
    @Override
    public int getItemCount(){
        return books.size();
    }

    public void setBooks(ArrayList<Book> bookList) {this.books = bookList;}

    public void addBook(Book book){
        books.add(book);
        notifyItemInserted(getItemCount()-1);
    }
    public void removeBooks(String which){
        switch (which){
            case "last":
                books.remove(getItemCount()-1);
                notifyItemRemoved(getItemCount());
            case "all":
                books.clear();
                notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_book, parent, false);
        return new ViewHolder(cardView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Book book = books.get(position);
        holder.bookNameText.setText(book.getBookName()); //method declared in Book.java
        holder.bookAuthorText.setText(book.getBookAuthor()); //method declared in Book.java
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView bookNameText;
        TextView bookAuthorText;
        ViewHolder(@NonNull View itemView){
            super(itemView);
            bookNameText = itemView.findViewById(R.id.recyclerTitle);
            bookAuthorText = itemView.findViewById(R.id.recyclerAuthor);
        }
    }
}