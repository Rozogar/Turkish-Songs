package com.rozogar.turkishsongs.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import Model.Music;
import androidx.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer player;
    private ArrayList<Music> music;
    private int currentIndex = 0;
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            music = intent.getParcelableArrayListExtra("music");
            currentIndex = intent.getIntExtra("currentIndex", 0);

            if (music != null && !music.isEmpty()) {
                if (player == null) {
                    player = new MediaPlayer();
                    playCurrentSong();
                }
            } else {
                Toast.makeText(this, "Music list is empty or null", Toast.LENGTH_SHORT).show();
            }
        }
        return START_STICKY;
    }

    public void playCurrentSong() {
        if (music == null || music.isEmpty()) {
            return;
        }

        Music currentSong = music.get(currentIndex);
        int songResource = currentSong.getSong();
        player.reset();
        try {
            player.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + songResource));
            player.prepare();
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    currentIndex++;
                    if (currentIndex >= music.size()) {
                        currentIndex = 0;
                    }
                    playCurrentSong();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error playing music", Toast.LENGTH_SHORT).show();
        }
    }

    public void play() {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    public void pause() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    public boolean isPlaying() {
        return player != null && player.isPlaying();
    }

    public int getCurrentPosition() {
        return player != null ? player.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return player != null ? player.getDuration() : 0;
    }

    public void seekTo(int position) {
        if (player != null) {
            player.seekTo(position);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
