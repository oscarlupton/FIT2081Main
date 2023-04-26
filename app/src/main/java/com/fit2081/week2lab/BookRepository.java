package com.fit2081.week2lab;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;
    BookRepository(Application application){
        BookDB db = BookDB.getDatabase(application);
        bookDao = db.customerDao();
        allBooks = bookDao.getAllBooks();
    }
    LiveData<List<Book>> getAllBooks(){
        return allBooks;
    }
    void addBook(Book book){
        BookDB.databaseWriteExecutor.execute(()->bookDao.addBook(book));
    }
    void deleteAllBooks(){
        BookDB.databaseWriteExecutor.execute(()->{
            bookDao.deleteAllBooks();
        });
    }
}
