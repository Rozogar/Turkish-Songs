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
            fillingUP();

            settingUP();


        }

    private void fillingUP() {
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






