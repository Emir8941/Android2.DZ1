package com.example.lesson21.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.lesson21.Model.NoteModel;

@Database(entities = {NoteModel.class}, version =1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao getDao();

}
