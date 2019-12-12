package com.example.webtech;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Song.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "song_database";

    private static com.example.webtech.AppDatabase instance;

    public static synchronized com.example.webtech.AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract SongDao getSongDao();
}
