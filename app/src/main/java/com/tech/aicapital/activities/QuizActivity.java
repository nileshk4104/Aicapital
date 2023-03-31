package com.tech.aicapital.activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tech.aicapital.CountDownActivity;
import com.tech.aicapital.MainActivity;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.QuizQuestionsDataList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class QuizActivity extends AppCompatActivity {

    private String EVENT_DATE_TIME = "2019-10-15 23:59:59";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private LinearLayout linear_layout_2;
    private TextView tv_days, tv_hour, tv_minute, tv_second, tv_milisecond_title;
    private Handler handler = new Handler();
    private Runnable runnable;
    private TextView btnCheck;
    MediaPlayer offersound;

    @BindView(R.id.ivLogo)
    ImageView ivLogo;

    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.btnNext2)
    Button btnNext2;

    @BindView(R.id.linearLayoutQue)
    LinearLayout linearLayoutQue;

    @BindView(R.id.tvQuestions)
    TextView tvQuestions;

    @BindView(R.id.tvAnsA)
    TextView tvAnsA;
    @BindView(R.id.tvAnsB)
    TextView tvAnsB;
    @BindView(R.id.tvAnsC)
    TextView tvAnsC;
    @BindView(R.id.tvAnsD)
    TextView tvAnsD;
    List<QuizQuestionsDataList> quizQuestionsDataLists;
//    int myProgress = 0;
//    ProgressBar progressBarView;
//    Button btn_start;
//    TextView tv_time;
//    EditText et_timer;
//    int progress;
//    CountDownTimer countDownTimer;
//    int endTime = 250;

     TextView tv;
     ProgressBar pb;

    private int progressStatus = 0;
    private Handler handler2 = new Handler();

    int i=0;

    boolean CLICKED=false;
    String ANS="O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        ButterKnife.bind(this);
        tv = (TextView) findViewById(R.id.tv);
        pb = (ProgressBar) findViewById(R.id.pb);
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        final Button btn = (Button) findViewById(R.id.btn);
        initUI();
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        /*****************************************************Srushti LogoAnimation *******************************************************************************/
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

        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);
        /*************************************************************************************************************************************************************/
        loadDatalist();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // GET QUESTION
                CLICKED=false;
                refreshdata();
                linearLayoutQue.setVisibility(View.VISIBLE);
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
                linearLayoutQue.startAnimation(animFadein);

                tvQuestions.setText(quizQuestionsDataLists.get(i).getQuestionName());
                tvAnsA.setText(quizQuestionsDataLists.get(i).getAnsA());
                tvAnsB.setText(quizQuestionsDataLists.get(i).getAnsB());
                tvAnsC.setText(quizQuestionsDataLists.get(i).getAnsC());
                tvAnsD.setText(quizQuestionsDataLists.get(i).getAnsD());
                i=i+1;
                if(i==6){
                    i=0;
                }
            }
        });

        btnNext2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // OUT QUESTION
                CLICKED=false;
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
                linearLayoutQue.startAnimation(animFadein);
                linearLayoutQue.setVisibility(View.GONE);
                refreshdata();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set the progress status zero on each button click
                progressStatus = 0;

                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(progressStatus < 70)
                        {
                            // Update the progress status
                            progressStatus +=1;
                            // Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(1000);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                            // Update the progress bar
                            handler2.post(new Runnable()
                            {
                                @Override
                                public void run() {
                                    pb.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    tv.setText(progressStatus+"");

                                    // If task execution completed
                                    if(progressStatus == 70){
                                        // Set a message of completion
                                        tv.setText("Time Up");
                                    }
                                }

                            });

                        }
//                        questionOut();
                    }
                }).start(); // Start the operation
            }
        });

        tvAnsA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CLICKED)
                {

                }
                else{
                    tvAnsA.setBackgroundResource(R.drawable.border_layout_selected);
                    ANS="A";
                }
                CLICKED=true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRefresh();
                    }
                }, 1000);


            }
        });
        tvAnsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CLICKED){

                }
                else{
                    tvAnsB.setBackgroundResource(R.drawable.border_layout_selected);
                    ANS="B";
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRefresh();
                    }
                }, 1000);
            }
        });
        tvAnsC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CLICKED){

                }
                else{
                    tvAnsC.setBackgroundResource(R.drawable.border_layout_selected);
                    ANS="C";
                }
                CLICKED=true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRefresh();
                    }
                }, 1000);
            }
        });
        tvAnsD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(CLICKED){

                }
                else{
                    tvAnsD.setBackgroundResource(R.drawable.border_layout_selected);
                    ANS="D";
                }
                CLICKED=true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setRefresh();
                    }
                }, 1000);
            }
        });

    }

    private void setRefresh()
    {
        // OUT QUESTION
        CLICKED=false;
        Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
        linearLayoutQue.startAnimation(animFadein);
        linearLayoutQue.setVisibility(View.GONE);
        refreshdata();

    }

    private void questionOut()
    {
        // OUT QUESTION
        CLICKED=false;
        Animation animFadeout= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
        linearLayoutQue.startAnimation(animFadeout);
        linearLayoutQue.setVisibility(View.GONE);

    }
    private void loadDatalist() {
        QuizQuestionsDataList quizQuestionsDataList=new QuizQuestionsDataList("1",
                "This is system generated que 1","A) Wagh ","B) Bakari ","C) Wheat ","D) Maize ","A");
        QuizQuestionsDataList quizQuestionsDataList2=new QuizQuestionsDataList("1",
                "This is system generated que 2","A) Wagh ","B) Wagh ","C) Wheat ","D) Maize ","B");
        QuizQuestionsDataList quizQuestionsDataList3=new QuizQuestionsDataList("1",
                "This is system generated que 3","A) Wagh ","B) Wheat ","C) Wheat ","D) Maize ","C");
        QuizQuestionsDataList quizQuestionsDataList4=new QuizQuestionsDataList("1",
                "This is system generated que 4","A) Wheat ","B) Bakari ","C) Wheat ","D) Maize ","D");
        QuizQuestionsDataList quizQuestionsDataList5=new QuizQuestionsDataList("1",
                "This is system generated que 5","A) Maize ","B) Bakari ","C) Wheat ","D) Maize ","A");
        QuizQuestionsDataList quizQuestionsDataList6=new QuizQuestionsDataList("1",
                "This is system generated que 6","A) Surat ","B) Bakari ","C) Wheat ","D) Maize ","B");
        QuizQuestionsDataList quizQuestionsDataList7=new QuizQuestionsDataList("1",
                "This is system generated que 7","A) Maka ","B) Bakari ","C) Wheat ","D) Maize ","A");

        quizQuestionsDataLists=new ArrayList<>();
        quizQuestionsDataLists.add(quizQuestionsDataList);
        quizQuestionsDataLists.add(quizQuestionsDataList2);
        quizQuestionsDataLists.add(quizQuestionsDataList3);
        quizQuestionsDataLists.add(quizQuestionsDataList4);
        quizQuestionsDataLists.add(quizQuestionsDataList5);
        quizQuestionsDataLists.add(quizQuestionsDataList6);
        quizQuestionsDataLists.add(quizQuestionsDataList7);


    }
    private void flashInQuestion(List<QuizQuestionsDataList> quizQuestionsDataLists)
    {

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                    CLICKED=false;
                    tvAnsA.setBackgroundResource(R.drawable.border_layout);
                    tvAnsB.setBackgroundResource(R.drawable.border_layout);
                    tvAnsC.setBackgroundResource(R.drawable.border_layout);
                    tvAnsD.setBackgroundResource(R.drawable.border_layout);
                    linearLayoutQue.setVisibility(View.VISIBLE);
                    Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
                    linearLayoutQue.startAnimation(animFadein);
                    tvQuestions.setText(quizQuestionsDataLists.get(i).getQuestionName());
                    tvAnsA.setText(quizQuestionsDataLists.get(i).getAnsA());
                    tvAnsB.setText(quizQuestionsDataLists.get(i).getAnsB());
                    tvAnsC.setText(quizQuestionsDataLists.get(i).getAnsC());
                    tvAnsD.setText(quizQuestionsDataLists.get(i).getAnsD());
                    i=i+1;
                    if(i==6)
                    {
                        i=0;
                    }
                    restartTimer();
            }
        }, 3000);


    }
    private void checkRightAns(String selected,boolean isTrue,List<QuizQuestionsDataList> quizQuestionsDataLists)
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                // Flash out
                CLICKED=false;
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
                linearLayoutQue.startAnimation(animFadein);
                linearLayoutQue.setVisibility(View.GONE);
                refreshdata();
                timerReset();
            }
        }, 2000);

        switch(ANS) {
            case "A":
                if(isTrue){
                    tvAnsA.setBackgroundResource(R.drawable.border_layout_true);
                }
                else{
                    tvAnsA.setBackgroundResource(R.drawable.border_layout_false);
                }

                break;
            case "B":
                if(isTrue){
                    tvAnsB.setBackgroundResource(R.drawable.border_layout_true);
                }
                else{
                    tvAnsB.setBackgroundResource(R.drawable.border_layout_false);
                }
                break;
            case "C":
                if(isTrue){
                    tvAnsC.setBackgroundResource(R.drawable.border_layout_true);
                }
                else{
                    tvAnsC.setBackgroundResource(R.drawable.border_layout_false);
                }
                break ;
            case "D":
                if(isTrue){
                    tvAnsD.setBackgroundResource(R.drawable.border_layout_true);
                }
                else{
                    tvAnsD.setBackgroundResource(R.drawable.border_layout_false);
                }
        }

        if(selected.equalsIgnoreCase("A")&&isTrue)
        {
            tvAnsA.setBackgroundResource(R.drawable.border_layout_true);
        }
        else if(selected.equalsIgnoreCase("B")&&isTrue) {
            tvAnsB.setBackgroundResource(R.drawable.border_layout_true);
        }else if(selected.equalsIgnoreCase("C")&&isTrue) {
            tvAnsC.setBackgroundResource(R.drawable.border_layout_true);
        }else if(selected.equalsIgnoreCase("D")&&isTrue) {
            tvAnsD.setBackgroundResource(R.drawable.border_layout_true);
        }





    }
    private void timerReset() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                flashInQuestion(quizQuestionsDataLists);
            }
        }, 2000);

    }
    private void restartTimer() {
        // Set the progress status zero on each button click
        progressStatus = 0;

        // Start the lengthy operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 10){
                    // Update the progress status
                    progressStatus +=1;


                    // Try to sleep the thread for 20 milliseconds
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler2.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStatus);
                            // Show the progress on TextView
                            tv.setText(progressStatus+"");
                            // If task execution completed
                            if(progressStatus == 10){
                                // Set a message of completion
                                tv.setText("Time Up");
                                boolean sel=false;
                                if(quizQuestionsDataLists.get(i).getCorrectAns().equalsIgnoreCase(ANS)){
                                    sel=true;
                                }
                                else{
                                    sel=false;
                                }
                                checkRightAns(quizQuestionsDataLists.get(i).getCorrectAns(),sel,quizQuestionsDataLists);
                            }
                        }
                    });
                }
            }
        }).start(); // Start the operation
    }
    private void refreshdata() {
               new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvAnsA.setBackgroundResource(R.drawable.border_layout);
                tvAnsB.setBackgroundResource(R.drawable.border_layout);
                tvAnsC.setBackgroundResource(R.drawable.border_layout);
                tvAnsD.setBackgroundResource(R.drawable.border_layout);

                // GET QUESTION
                CLICKED=false;
//                refreshdata();
                linearLayoutQue.setVisibility(View.VISIBLE);
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
                linearLayoutQue.startAnimation(animFadein);

                tvQuestions.setText(quizQuestionsDataLists.get(i).getQuestionName());
                tvAnsA.setText(quizQuestionsDataLists.get(i).getAnsA());
                tvAnsB.setText(quizQuestionsDataLists.get(i).getAnsB());
                tvAnsC.setText(quizQuestionsDataLists.get(i).getAnsC());
                tvAnsD.setText(quizQuestionsDataLists.get(i).getAnsD());
                i=i+1;
                if(i==6){
                    i=0;
                }
            }
        }, 3000);

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
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable);
        offersound.stop();
    }


}
