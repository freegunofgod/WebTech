package com.example.webtech;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment implements OnItemClicked  {
    private RecyclerView recyclerView;

    List<Song> songList;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listfragment, container, false);

       songList = getArguments() != null ? (List<Song>) getArguments().getSerializable("KEY_DATA"):null;

        recyclerView = view.findViewById(R.id.rv_songs);

        SongsAdapter songsAdapter = new SongsAdapter(songList, this, getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songsAdapter);
        return view;
    }

    @Override
    public void onItemClicked(Song song) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("SONG", song);
        startActivity(intent);
    }
}
