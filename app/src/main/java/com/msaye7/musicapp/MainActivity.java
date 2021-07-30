package com.msaye7.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Song> songs;
    private SongsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getString(R.string.music_list));
        songs = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rv_main);

        adapter = new SongsAdapter(this, songs);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            getSongs();
        }
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getSongs();
            }
        }
    }

    private void getSongs() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artistIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            do {
                String name = cursor.getString(nameIndex);
                String artist = cursor.getString(artistIndex);
                songs.add(new Song(name, artist));
            } while (cursor.moveToNext());
            adapter.notifyDataSetChanged();
        }
    }
}