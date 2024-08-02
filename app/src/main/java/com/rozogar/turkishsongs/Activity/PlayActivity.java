package com.rozogar.turkishsongs.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rozogar.turkishsongs.Media.MediaPlayerManager;
import com.rozogar.turkishsongs.R;

import java.io.IOException;
import java.util.ArrayList;

import Model.Music;
import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {
    private ImageView img;
    private TextView txt1;
    private SeekBar seek;
    private Button btnpre;
    private Button btnnext;
    private TextView txtstart;
    private TextView txtendtime;
    private Button btnplay;
    private Intent intent;
    MediaPlayer player;
    private Handler handler = new Handler();
    private ArrayList<Music> musicList;
    private int currentIndex = 0;
    private int playButtonClickCount = 1;
    private int pausedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.model);
        setUpView();
        adjusting();


    }

    private void adjusting() {
        intent = getIntent();
        if (intent != null) {
            //noinspection unchecked
            musicList = (ArrayList<Music>) intent.getSerializableExtra("thisMusic");
            currentIndex = intent.getIntExtra("currentIndex", 0);

            if (musicList != null && !musicList.isEmpty()) {
                player = MediaPlayerManager.getMediaPlayerInstance();
                updateUIWithCurrentSong();
                setUpPlayButton();
                setUpNextButton();
                setUpPreviousButton();
                setUpSeekBar();
                playCurrentSong();
            } else {
                Toast.makeText(this, "Music list is empty or null", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void setUpSeekBar() {
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && player != null) {
                    player.seekTo(progress);
                }    txtstart.setText(formatTime(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void setUpPreviousButton() {
        btnpre.setOnClickListener(v -> {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = musicList.size() - 1; // Loop back to the end
            }
            updateUIWithCurrentSong();
            playCurrentSong();
        });
    }

    private void setUpNextButton() {
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if (currentIndex >= musicList.size()) {
                    currentIndex = 0; // Loop back to the beginning
                }
                updateUIWithCurrentSong();
                playCurrentSong();
            }
        });
    }

    private void setUpPlayButton() {
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    if (player.isPlaying()) {
                        pausedPosition = player.getCurrentPosition();
                        player.pause();
                        btnplay.setText(R.string.play);
                        updateSeekBar(); // Update seek bar and text view position
                    } else {
                        player.seekTo(pausedPosition);
                        player.start();
                        btnplay.setText(R.string.pause);
                        updateSeekBar(); // Update seek bar and text view position
                    }
                }
            }
        });
    }



    private void playCurrentSong() {
        Music currentSong = musicList.get(currentIndex);
        int ahang = currentSong.getSong();
        player.reset();
        try {
            player.setDataSource(PlayActivity.this, Uri.parse("android.resource://" + getPackageName() + "/" + ahang));
            player.prepare();
            player.start();
            btnplay.setText(R.string.pause);
            seek.setMax(player.getDuration());
            txtendtime.setText(formatTime(player.getDuration()));
            txtstart.setText(formatTime(0)); // Update initial position
            updateSeekBar();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // Move to the next song
                    currentIndex++;
                    if (currentIndex >= musicList.size()) {
                        currentIndex = 0; // Loop back to the beginning
                    }
                    updateUIWithCurrentSong();
                    playCurrentSong();
                }
            });
            new Thread(() -> {
                while (player != null && player.isPlaying()) {
                    seek.setProgress(player.getCurrentPosition());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(PlayActivity.this, "Error playing music", Toast.LENGTH_SHORT).show();
        }

    }
    private void updateUIWithCurrentSong() {
        Music currentSong = musicList.get(currentIndex);
        int imageResource = currentSong.getImg();
        String musicName = currentSong.getName();
        img.setImageResource(imageResource);
        txt1.setText(musicName);
        txtstart.setText(formatTime(0));
    }
    private void updateSeekBar() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (player != null && player.isPlaying()) {
                    seek.setProgress(player.getCurrentPosition());
                    txtstart.setText(formatTime(player.getCurrentPosition()));
                    updateSeekBar(); // Continue updating
                }
            }
        }, 1000);
    }


    private String formatTime(int milliseconds) {
        int minutes = (milliseconds / 1000) / 60;
        int seconds = (milliseconds / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }



    private void setUpView() {
        img = findViewById(R.id.img);
        txt1 =findViewById(R.id.txt1);
        seek = findViewById(R.id.seek);
        btnnext = findViewById(R.id.btnnext);
        btnplay = findViewById(R.id.btnplay);
        btnpre = findViewById(R.id.btnpree);
        txtstart = findViewById(R.id.txtstart);
        txtendtime = findViewById(R.id.txtendtime);


    }


}