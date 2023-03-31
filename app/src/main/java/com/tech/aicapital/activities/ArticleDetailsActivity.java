package com.tech.aicapital.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tech.aicapital.R;
import com.tech.aicapital.mvps.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

public class ArticleDetailsActivity extends AppCompatActivity {

    String title,full,desc,image;

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDescription)
    TextView tvDescription;

//    @BindView(R.id.tvTitle)
//    TextView tvTitle;
//    @BindView(R.id.tvTitle)
//    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        title=intent.getStringExtra("title");
        Utility.pageSetupBackButton(ArticleDetailsActivity.this,title);

        full=intent.getStringExtra("full");
        desc=intent.getStringExtra("desc");
        image=intent.getStringExtra("image");

        tvDescription.setText(desc+full);
//        tvTitle.setText(title);



    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}