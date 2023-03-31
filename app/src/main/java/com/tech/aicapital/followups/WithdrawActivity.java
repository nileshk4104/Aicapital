package com.tech.aicapital.followups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tech.aicapital.R;
import com.tech.aicapital.mvps.Utility;

import butterknife.ButterKnife;

public class WithdrawActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);

        Utility.pageSetupBackButton(WithdrawActivity.this,"Withdraw Fund");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}