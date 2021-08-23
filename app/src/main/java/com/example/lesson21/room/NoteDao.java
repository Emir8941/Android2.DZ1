package com.example.lesson21.room;

import android.provider.ContactsContract;
import android.view.LayoutInflater;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lesson21.Model.NoteModel;

import java.util.ArrayList;
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
