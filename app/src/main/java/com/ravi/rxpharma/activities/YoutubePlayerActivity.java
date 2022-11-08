package com.ravi.rxpharma.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController;
import com.ravi.rxpharma.R;
import com.squareup.picasso.Picasso;

public class YoutubePlayerActivity extends AppCompatActivity {
    private float time = 0;
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);

        String id = "RTEr_HpBf3o";
        String image = "https://img.youtube.com/vi/RTEr_HpBf3o/hqdefault.jpg";
        youTubePlayerView = findViewById(R.id.youtubePlayer);
        ImageView imageView = findViewById(R.id.ivThumbnail);
        Picasso
                .get()
                .load(image)
                .resize(1000, 1000)
                .noFade()
                .into(imageView);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.setEnableAutomaticInitialization(false);

        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {

            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                DefaultPlayerUiController defaultPlayerUiController = new DefaultPlayerUiController(youTubePlayerView,youTubePlayer);
                defaultPlayerUiController.showSeekBar(true)
                        .showVideoTitle(false)
                        .setFullScreenButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                                } else {
                                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                                }
                            }
                        })
                        .setCustomAction1(AppCompatResources.getDrawable(YoutubePlayerActivity.this,R.drawable.ic_round_replay_65), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                youTubePlayer.seekTo(time-10);
                            }
                        })
                        .setCustomAction2(AppCompatResources.getDrawable(YoutubePlayerActivity.this,R.drawable.ic_round_forward_65), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                youTubePlayer.seekTo(time+10);
                            }
                        })
                        .showYouTubeButton(false)
                        .showPlayPauseButton(true);
                youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.getRootView());
                youTubePlayer.loadVideo(id,0);
                youTubePlayer.pause();

            }

            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {
                super.onCurrentSecond(youTubePlayer, second);
                time = second;
            }
        };

// disable iframe ui
        IFramePlayerOptions options = new IFramePlayerOptions.Builder()
                .controls(0)
                .ivLoadPolicy(3)
                .ccLoadPolicy(1)
                .build();
        youTubePlayerView.initialize(listener, options);

    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen();
        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            youTubePlayerView.exitFullScreen();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}