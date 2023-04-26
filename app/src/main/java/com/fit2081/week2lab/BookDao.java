package com.fit2081.week2lab;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BookDao {
    @Query("select * from books")
    LiveData<List<Book>> getAllBooks();

    @Query("select * from books where bookName= :name")
    List<Book> getBookById(String name);

    @Insert
    void addBook(Book book);
    @Query("delete from books where bookName= :name")
    void deleteBook(String name);
    @Query("delete FROM books")
    void deleteAllBooks();
}
