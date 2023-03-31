package com.tech.aicapital.followups;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tech.aicapital.R;
import com.tech.aicapital.mvps.Utility;

import butterknife.ButterKnife;

public class TransactionHistoryActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        ButterKnife.bind(this);
        Utility.pageSetupBackButton(TransactionHistoryActivity.this,"All Transactions");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}