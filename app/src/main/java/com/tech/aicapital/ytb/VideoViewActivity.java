package com.tech.aicapital.ytb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;


import com.tech.aicapital.R;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideoViewActivity extends AppCompatActivity {

    Boolean flag;
    VideoView video;
    Button skip;


    String user_id;
    String addressline, zip, city, mobile, mail;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sharedPreferences = getSharedPreferences("Reg", 0);
        user_id = sharedPreferences.getString("user_id","");

        Intent intent=getIntent();
        path=intent.getStringExtra("path");



        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

//        String path="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4";
        String path1="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4";

        Uri uri= Uri.parse(path);

        video=findViewById(R.id.VideoView01);
        video.setVideoURI(uri);
        video.start();
        skip= findViewById(R.id.btnSkip);

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished)
            {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                skip.setText("skip: " + millisUntilFinished / 1000);
                skip.setClickable(false);
            }

            public void onFinish()
            {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                skip.setText("Skip:");
                skip.setClickable(true);
//                rewardToUser();
            }

        }.start();
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//    private void rewardToUser() {
//        final NetworkCaller apiService = ApiClient.getClient().create(NetworkCaller.class);
//        Call<NewResponse> call = apiService.rewardPoints(user_id,"10","REWARD VIDEO");
//        call.enqueue(new Callback<NewResponse>() {
//            @Override
//            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
//                NewResponse hospitalListResponse = response.body();
//                if (hospitalListResponse != null) {
//                    Toast.makeText(getApplicationContext(),
//                            "You earned 10 coins successfully", Toast.LENGTH_SHORT).show();
//                } else
//                    Toast.makeText(getApplicationContext(),
//                            "failure ", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(Call<NewResponse> call, Throwable t) {
//                if (t instanceof IOException) {
//                    //Add your code for displaying no network connection error
//                    Toast.makeText(getApplicationContext(),
//                            "failure ", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),
//                            "failure ", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}
