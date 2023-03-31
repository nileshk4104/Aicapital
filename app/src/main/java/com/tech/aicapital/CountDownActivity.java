package com.tech.aicapital;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;

import com.tech.aicapital.mvps.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountDownActivity extends AppCompatActivity {

    private String EVENT_DATE_TIME = "2021-01-03 00:00:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private LinearLayout linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second,tv_milisecond_title;
    private Handler handler = new Handler();
    private Runnable runnable;
    private TextView btnCheck;
    MediaPlayer offersound;

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvJoinNow)
    TextView tvJoinNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.count_down_activity);
        ButterKnife.bind(this);

        initUI();
        countDownStart();

        tvJoinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.showGreenMessage(CountDownActivity.this,"COMMING SOON ");
            }
        });

        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        offersound = MediaPlayer.create(this, R.raw.offer);
        offersound.start();

        ObjectAnimator animation = ObjectAnimator.ofFloat(ivLogo, "rotationY", 0.0f, 360f);
        // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
        animation.setDuration(6000); // HERE 500 IS THE DURATION OF THE ANIMATION,
        // YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setRepeatCount(100);
        animation.setInterpolator(new LinearInterpolator());
        animation.start();


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(CountDownActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 4, 0);


    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void initUI()
    {
        linear_layout_2 = findViewById(R.id.linear_layout_2);
        tv_days = findViewById(R.id.tv_days);
        tv_hour = findViewById(R.id.tv_hour);
        tv_minute = findViewById(R.id.tv_minute);
        btnCheck = findViewById(R.id.btnCheck);
        tv_second = findViewById(R.id.tv_second);
        tv_milisecond_title = findViewById(R.id.tv_milisecond_title);

    }

    private void countDownStart() {
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 10);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;
                        long milSeconds = diff / 100 % 100;
                        //
                        tv_days.setText(String.format("%02d", Days));
                        tv_hour.setText(String.format("%02d", Hours));
                        tv_minute.setText(String.format("%02d", Minutes));
                        tv_second.setText(String.format("%02d", Seconds));
//                        tv_milisecond_title.setText(String.format("%02d", milSeconds));
                    } else {

//                        Intent intent=new Intent(CountDownActivity.this,MainActivity.class);
//                        startActivity(intent);

//                        linear_layout_1.setVisibility(View.VISIBLE);
                        linear_layout_2.setVisibility(View.GONE);
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
        offersound.stop();
    }
}