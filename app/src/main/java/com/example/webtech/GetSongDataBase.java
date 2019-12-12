package com.example.webtech;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class GetSongDataBase extends AsyncTask<String, Void, String> {

    private final Context context;
    private List<Song> songList;
    private OnRequestCompleted onRequestCompleted;

    public GetSongDataBase(Context context, OnRequestCompleted onRequestCompleted) {
        this.context = context;
        this.onRequestCompleted = onRequestCompleted;
    }

    @Override
    protected String doInBackground(String... strings) {
        SongDao songDao = AppDatabase.getInstance(context).getSongDao();
        songList = songDao.getSongs();
        return null;
    }

    @Override
    protected  void onPostExecute(String s){
        super.onPostExecute(s);
        onRequestCompleted.onGetSongsCompleted(songList);
    }
}
