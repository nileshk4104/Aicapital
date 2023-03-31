package com.tech.aicapital.followups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tech.aicapital.R;
import com.tech.aicapital.mvps.Utility;

import butterknife.ButterKnife;

public class AddFundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fund);
        ButterKnife.bind(this);

        Utility.pageSetupBackButton(AddFundActivity.this,"Add Fund");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}