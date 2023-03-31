package com.tech.aicapital;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;

import com.tech.aicapital.activities.LoginActivity;
import com.tech.aicapital.datalist.CheckResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SplashScreen extends Activity {

boolean logIn,isOnline=false;
String useerId;
int currentVersion;

@BindView(R.id.ivLogo)
CircleImageView ivLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        changeStatusBarColor();
        PackageManager packageManager = this.getPackageManager();
        PackageInfo packageInfo = null;
        try
        {
            packageInfo =  packageManager.getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        currentVersion = packageInfo.versionCode;

//        ObjectAnimator animation = ObjectAnimator.ofFloat(ivLogo, "rotationY", 0.0f, 360f);
//        // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
//        animation.setDuration(4000); // HERE 500 IS THE DURATION OF THE ANIMATION,
//        // YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
//        animation.setInterpolator(new AccelerateDecelerateInterpolator());
//        animation.setRepeatCount(1);
//        animation.setInterpolator(new LinearInterpolator());
//        animation.start();
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if(Utility.isConnectedToInternet(getApplicationContext()))
                {
                    getVersionNo();
                }
            else{
                    Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
                }
            }
        }, 2000);

//        if(Utility.isConnectedToInternet(getApplicationContext()))
//        {
//            getVersionNo();
//        }
//        else{
//
//        }

    }
    public void showAlert(String title)
    {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater =getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.withdrawal_alert, null);
            final AlertDialog dialog = builder.create();
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.setView(dialogLayout, 0, 0, 0, 0);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            WindowManager.LayoutParams wlmp = dialog.getWindow().getAttributes();
            wlmp.gravity = Gravity.CENTER;

            TextView tvTitle = (TextView) dialogLayout.findViewById(R.id.tvTitle);
            final EditText edAmount = (EditText) dialogLayout.findViewById(R.id.edAmount);
            tvTitle.setText(title);
            Button btnYes = (Button) dialogLayout.findViewById(R.id.btnYes);
            RadioGroup radioGroup = (RadioGroup) dialogLayout.findViewById(R.id.radioGroup);
            Button btnCancel = (Button) dialogLayout.findViewById(R.id.btnCancel);

            radioGroup.setVisibility(View.GONE);
            edAmount.setVisibility(View.GONE);

            btnYes.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.tech.aicapital"));
                    startActivity(intent);
                    SplashScreen.this.finish();
                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    finish();
                }
            });
            builder.setView(dialogLayout);
            dialog.show();
        }
        catch (Exception e){

        }
    }
    private void changeStatusBarColor()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    private void alertDia(String alermsg)
    {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)

                .setTitleText("UPDATE")
                .setContentText(alermsg)
                .setCustomImage(R.mipmap.ic_launcher)
                .setConfirmText("UPDATE")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.tech.aicapital"));
                        startActivity(intent);
                        SplashScreen.this.finish();
                    }
                })

           .setCancelButton("CANCEL", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                String websiteurl= "www.srushtii.com";
                finish();
            }
        })
                .show();
    }
    public void getVersionNo()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<CheckResponse> call = apiService.getVersionNo();
        call.enqueue(new Callback<CheckResponse>() {
            @Override
            public void onResponse(Call<CheckResponse> call, Response<CheckResponse> response) {
                CheckResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();

                if (hospitalListResponse != null) {
                    onsuccessVersionn(hospitalListResponse.getVersionNo(),hospitalListResponse.getVersionname());
                } else
                    onsuccessVersionn("0",hospitalListResponse.getVersionname());
            }

            @Override
            public void onFailure(Call<CheckResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Utility.hideProgressBar();
                    Toast.makeText(getApplicationContext(),"no network ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    //Add your code for displaying no network connection error
//                    responseReceived.onFailure("Please check Network connection ");
                } else {
                    Utility.hideProgressBar();
                    Toast.makeText(getApplicationContext(),"some error  ", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }
    public void onsuccessVersionn(String versionno, String alertmsg)
    {

        if(versionno.equalsIgnoreCase(String.valueOf(currentVersion)))
        {
            if(logIn)
            {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                intent.putExtra("useerId",useerId);
                startActivity(intent);
                finish();
            }
        }
        else
        {
            alertDia(alertmsg);
        }

    }
}
