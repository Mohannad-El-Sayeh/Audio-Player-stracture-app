package com.msaye7.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

public class NowPlayingActivity extends AppCompatActivity {

    TextView name, artist;
    ImageButton controlBtn;
    Boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        setTitle(getString(R.string.now_playing));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        controlBtn = findViewById(R.id.control);
        name = findViewById(R.id.name);
        artist = findViewById(R.id.artist);
        Song song = (Song) getIntent().getSerializableExtra("song");
        name.setText(song.getName());
        artist.setText(song.getArtist());
        controlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    isPlaying = false;
                    controlBtn.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                } else {
                    isPlaying = true;
                    controlBtn.setImageResource(R.drawable.ic_baseline_pause_24);
                }
            }
        });
    }
}