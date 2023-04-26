package com.fit2081.week2lab;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends AndroidViewModel {
    private BookRepository bookRepository;
    private LiveData<List<Book>> allBooks;
    public BookViewModel(@NonNull Application application){
        super(application);
        bookRepository = new BookRepository(application);
        allBooks = bookRepository.getAllBooks();
    }
    public LiveData<List<Book>> getAllBooks(){
        return allBooks;
    }
    public void addBook(Book book){
        bookRepository.addBook(book);
    }
    public void deleteAllBooks(){
        bookRepository.deleteAllBooks();
    }
}
