package com.fit2081.week2lab;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Book.class}, version = 1)
public abstract class BookDB extends RoomDatabase{
    public static final String BOOK_DB_NAME = "book_database";
    public abstract BookDao customerDao();
    //volatile instance ensures atomic(?) access to the variable
    private static volatile BookDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static BookDB getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (BookDB.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BookDB.class, BOOK_DB_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}