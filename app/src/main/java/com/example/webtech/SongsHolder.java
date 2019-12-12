package com.example.webtech;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class SongsHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView flag;

    public SongsHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.holdertitle);
        flag = itemView.findViewById(R.id.image);
    }

    public void populate(final OnItemClicked onItemClicked,final Song song) {

    title.setText(song.getTitle());

    Picasso.get()
            .load(song.getFlag())
            .into(flag);

    itemView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            onItemClicked.onItemClicked(song);
        }
    });
    }
}
