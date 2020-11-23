package com.example.shaaditest.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface PersonDao {

    @Query("SELECT * FROM persond")
    List<Persond> getAll();

    @Insert
    void insert(Persond persond);

    @Delete
    void delete(Persond persond);

    @Update
    void update(Persond persond);
}
