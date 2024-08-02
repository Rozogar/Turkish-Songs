package com.rozogar.turkishsongs.Activity;

import android.os.Bundle;

import Model.Music;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.rozogar.turkishsongs.Adapter.MyAdapter;
import com.rozogar.turkishsongs.R;
import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;
    private ArrayList<Music> music;
    LinearLayoutManager verticalLayoutManager;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_songs);
            setupView();
            fillingUp();
            showUp();

            settingUP();


        }

    private void showUp() {
        music = new ArrayList<>();
        music.add(new Music(R.drawable.pic, "Song 01",R.raw.song01));
        music.add(new Music(R.drawable.pic, "Song 02",R.raw.song02));
        music.add(new Music(R.drawable.pic, "Song 03",R.raw.song03));
        music.add(new Music(R.drawable.pic, "Song 04",R.raw.song04));
        music.add(new Music(R.drawable.pic, "Song 05",R.raw.song05));
        music.add(new Music(R.drawable.pic, "Song 06",R.raw.song06));
        music.add(new Music(R.drawable.pic, "Song 07",R.raw.song07));
        music.add(new Music(R.drawable.pic, "Song 08",R.raw.songs08));
        music.add(new Music(R.drawable.pic, "Song 09",R.raw.song09));
        music.add(new Music(R.drawable.pic, "Song 10",R.raw.song10));
        music.add(new Music(R.drawable.pic, "Song 11",R.raw.song11));
        music.add(new Music(R.drawable.pic, "Song 12",R.raw.song12));
        music.add(new Music(R.drawable.pic, "Song 13",R.raw.song13));
        music.add(new Music(R.drawable.pic, "Song 14",R.raw.song14));
    }

    private void fillingUp() {
            music = new ArrayList<>();
            music.add(new Music(R.drawable.pic, "Song 01"));
            music.add(new Music(R.drawable.pic, "Song 02"));
            music.add(new Music(R.drawable.pic, "Song 03"));
            music.add(new Music(R.drawable.pic, "Song 04"));
            music.add(new Music(R.drawable.pic, "Song 05"));
            music.add(new Music(R.drawable.pic, "Song 06"));
            music.add(new Music(R.drawable.pic, "Song 07"));
            music.add(new Music(R.drawable.pic, "Song 08"));
            music.add(new Music(R.drawable.pic, "Song 09"));
            music.add(new Music(R.drawable.pic, "Song 10"));
            music.add(new Music(R.drawable.pic, "Song 11"));
            music.add(new Music(R.drawable.pic, "Song 12"));
            music.add(new Music(R.drawable.pic, "Song 13"));
            music.add(new Music(R.drawable.pic, "Song 14"));

    }

    private void settingUP() {
        MyAdapter adapter = new MyAdapter(music,this);
        recyclerView.setAdapter(adapter);
        verticalLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayoutManager);


        refreshLayout.setOnRefreshListener(() -> refreshLayout.setRefreshing(false));
    }

    private void setupView() {
        // connection the Views in xml layout.
        recyclerView = findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refresherLayout);

    }

    }






