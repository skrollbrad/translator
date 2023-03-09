package com.example.translator.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.translator.entity.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    void insert(Word word);


    @Query("SELECT * FROM Word")
    List<Word> getWords();
}
