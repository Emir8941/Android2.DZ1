package com.example.lesson21.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lesson21.model.NoteModel;

@Database(entities = {NoteModel.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao getDao();

}
