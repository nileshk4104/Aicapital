package com.tech.aicapital.ytb;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.tech.aicapital.R;
import com.tech.aicapital.mvps.Constant;
import com.google.android.youtube.player.YouTubePlayerView;


public class YoutubePlayActivity extends YouTubeBaseActivity {
    private static final String TAG = YoutubePlayActivity.class.getSimpleName();
    private String videoID;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube_player_view);

        //get the video id
        videoID = getIntent().getStringExtra("video_id");
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initializeYoutubePlayer();
    }

    /**
     * initialize the youtube player
     */

//    private void initializeYoutubePlayer()
//    {
//        youTubePlayerView.initialize(Constant.YOUTUBE_DEVELOPER_KEY,
//                new YouTubePlayer.OnInitializedListener() {
//
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
//                                                boolean wasRestored) {
//
//                //if initialization success then load the video id to youtube player
//                if (!wasRestored) {
//                    //set the player style here: like CHROMELESS, MINIMAL, DEFAULT
//                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//
//                    youTubePlayer.loadVideo(videoID);
//
//
//
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
//                //print or show error if initialization failed
//                Log.e(TAG, "Youtube Player View initialization failed");
//            }
//        });
//    }

    private void initializeYoutubePlayer()
    {
        youTubePlayerView.initialize(Constant.YOUTUBE_DEVELOPER_KEY,
                new YouTubePlayer.OnInitializedListener() {

                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer,
                                                        boolean wasRestored) {

                        //if initialization success then load the video id to youtube player
                        if (!wasRestored) {
                            //set the player style here: like CHROMELESS, MINIMAL, DEFAULT
                            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);

                            youTubePlayer.loadVideo(videoID);



                        }
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                        //print or show error if initialization failed
                        Log.e(TAG, "Youtube Player View initialization failed");
                    }
                });
    }
}