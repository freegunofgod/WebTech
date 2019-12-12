package com.example.webtech;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsHolder> {
    private final static String TAG = SongsAdapter.class.getSimpleName();
    private OnItemClicked onItemClicked;

    private List<Song> songs;


    SongsAdapter(List<Song> result, OnItemClicked onItemclicked, Context context) {

        this.onItemClicked = onItemclicked;
        songs = result;

        final SongDao songDao = AppDatabase.getInstance(context).getSongDao();

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                songDao.insertAllSongs(songs);
            }
        };
        thread.start();
    }

    @NonNull
    @Override
    public SongsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.song_item, parent, false);
        return new SongsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsHolder holder, int position) {
        Song song = songs.get(position);
        holder.populate(onItemClicked, song);
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}


