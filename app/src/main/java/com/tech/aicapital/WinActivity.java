package com.tech.aicapital;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

public class WinActivity extends AppCompatActivity {

    @BindView(R.id.ivLogo)
    ImageView ivLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_up);
        ButterKnife.bind(this);

        alertDialog();

        ObjectAnimator animation = ObjectAnimator.ofFloat(ivLogo, "rotationY", 0.0f, 360f);
        // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
        animation.setDuration(2000); // HERE 500 IS THE DURATION OF THE ANIMATION,
        // YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setRepeatCount(20);
        animation.setInterpolator(new LinearInterpolator());

        animation.start();

    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.winning_page,null);
        final AlertDialog dialogu = builder.create();
        dialogu.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogu.setView(dialogLayout, 0, 0, 0, 0);
        dialogu.setCanceledOnTouchOutside(true);
        dialogu.setCancelable(true);
        WindowManager.LayoutParams wlmp = dialogu.getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER;


        builder.setView(dialogLayout);
        dialogu.show();
    }


}
