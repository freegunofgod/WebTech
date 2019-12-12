package com.example.webtech;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SongDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllSongs(List<Song>songs);

    @Query("SELECT*FROM song")
    public List<Song> getSongs();

    @Query("Select*FROM song WHERE id= :id")
    public Song getSongWithId(String id);
}