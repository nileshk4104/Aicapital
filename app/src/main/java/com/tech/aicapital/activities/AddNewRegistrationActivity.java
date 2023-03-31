package com.tech.aicapital.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tech.aicapital.R;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;


import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNewRegistrationActivity extends AppCompatActivity {
    private static final String TAG = "DATA";

    @BindView(R.id.TImobile)
    TextInputLayout TImobile;
    @BindView(R.id.edUserMobile)
    EditText edUserMobile;
    @BindView(R.id.eduserName)
    EditText eduserName;
    @BindView(R.id.edReferalName)
    EditText edReferalName;
    @BindView(R.id.edReferCode)
    EditText edReferCode;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    String user_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_up);
        ButterKnife.bind(this);

        Utility.pageSetupPrimary(AddNewRegistrationActivity.this,"ADD NEW FRIEND");
        tvSignUp.setVisibility(View.GONE);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}
