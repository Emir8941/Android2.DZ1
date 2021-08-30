package com.example.lesson21.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lesson21.model.NoteModel;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM NoteModel")
   LiveData<List<NoteModel>> getAll();

    @Insert
    void insertNote(NoteModel note);

    @Delete
    void delete(NoteModel note);
    
}
