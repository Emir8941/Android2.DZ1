package com.example.lesson21.utils;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.lesson21.room.NoteDatabase;

public class App extends Application{
    private static Context context;
    public static NoteDatabase database = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initDatabase();
    }

    private static NoteDatabase initDatabase() {
        if (database ==null){
            database = Room.databaseBuilder(context,
                    NoteDatabase.class,"database-note")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
    return database;
    }
    public static NoteDatabase getDatabase(){
        return database;
    }
}
