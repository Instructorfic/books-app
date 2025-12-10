package com.fic.cursoandroid2025g4.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Book.class},version = 1,exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    private static BookDatabase INSTANCE;
    public abstract BookDao bookDao();

    public static synchronized BookDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BookDatabase.class,
                    "book_database"
            ).allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

}
