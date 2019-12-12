package com.example.webtech;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="song")
class Song implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="flag")
    private String flag;

    @ColumnInfo(name="url")
    private String url;

    @ColumnInfo(name="name")
    private String name;

    public Song(int id, String title, String flag, String url, String name) {
        this.id = id;
        this.title = title;
        this.flag = flag;
        this.url = url;
        this.name = name;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFlag() {
        return flag;
    }

    public String getUrl() {return url;}

    public String getName() {return name;}

}
