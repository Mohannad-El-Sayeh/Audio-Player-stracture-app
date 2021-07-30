package com.msaye7.musicapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*
 * Created by Mohannad El-Sayeh on 29/07/2021
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsViewHolder> {
    private List<Song> songs;
    private Context context;

    public SongsAdapter(Context context, List<Song> songs) {
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongsViewHolder(LayoutInflater.from(context).inflate(R.layout.song_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
        holder.name.setText(songs.get(position).getName());
        holder.artist.setText(songs.get(position).getArtist());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NowPlayingActivity.class)
                        .putExtra("song", songs.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}

class SongsViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView artist;

    public SongsViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        artist = itemView.findViewById(R.id.tv_artist);
    }
}
