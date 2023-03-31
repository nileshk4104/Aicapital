package com.tech.aicapital.followups;

import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.followups.adapter.FollowupListAdapter;
import com.tech.aicapital.followups.datalist.FollowupDataList;
import com.tech.aicapital.followups.datalist.FollowupDataListResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowUpHistoryActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ivImageNodata)
    GifImageView ivImageNodata;
    @BindView(R.id.tvRegisteredDate)
    TextView tvRegisteredDate;
    @BindView(R.id.tvWork)
    TextView tvWork;
    @BindView(R.id.tvEducation)
    TextView tvEducation;
    @BindView(R.id.tvMi)
    TextView tvMi;
    @BindView(R.id.iv_AddNew)
    FloatingActionButton iv_AddNew;
    String cname,customer_id,customer_mobile,work,mi,education,regiDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followup_history_list);
        ButterKnife.bind(this);
        
        Intent intent=getIntent();
        cname= intent.getStringExtra("cname");
        customer_id= intent.getStringExtra("customer_id");
        customer_mobile= intent.getStringExtra("customer_mobile");
        work= intent.getStringExtra("work");
        mi= intent.getStringExtra("mi");
        education= intent.getStringExtra("education");
        regiDate= intent.getStringExtra("regiDate");

        tvMi.setText(mi+" P/M");
        tvRegisteredDate.setText(regiDate);
        tvEducation.setText(education);
        tvWork.setText(work);


        Utility.pageSetupBackButtonSub(FollowUpHistoryActivity.this,cname,customer_mobile);

        iv_AddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(FollowUpHistoryActivity.this,AddNewFollowupActivity.class);
                intent1.putExtra("customerId",customer_id);
                intent1.putExtra("custName",cname);
                startActivity(intent1);
            }
        });
        if(Utility.isConnectedToInternet(this))
        {
            getAllFollowups(customer_id);
        }
    else{
            ivImageNodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return super.onSupportNavigateUp();
    }

    public void getAllFollowups(String customer_id)
    {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<FollowupDataListResponse> call = apiService.getAllFollowups(customer_id);
        call.enqueue(new Callback<FollowupDataListResponse>() {
            @Override
            public void onResponse(Call<FollowupDataListResponse> call, Response<FollowupDataListResponse> response) {
                FollowupDataListResponse hospitalListResponse = response.body();
                if (hospitalListResponse != null)
                {
                    if(hospitalListResponse.getData().size()>0)
                    {
                        if(hospitalListResponse.isStatus()){
                            onSuccessShopList(hospitalListResponse.getData(),hospitalListResponse.getBaseUrl());
                        }
                        else {
                            ivImageNodata.setVisibility(View.VISIBLE);
                        }
                    }

                    else {
                        ivImageNodata.setVisibility(View.VISIBLE);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"UNKNOWN RESPONSE", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<FollowupDataListResponse> call, Throwable t) {
                ivImageNodata.setVisibility(View.VISIBLE);
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onSuccessShopList(final List<FollowupDataList> couponDataLists, String baseurl)
    {
        FollowupListAdapter transactionAdapter = new FollowupListAdapter(getApplicationContext(), true,couponDataLists,
                new FollowupListAdapter.OnItemClickListener() {
                    @Override
                    public void onListItemClick(int position)
                    {

                    }
                    @Override
                    public void onUpdateQty(int position, String qty, double intrestrate,int selvalue, int mainDuration, boolean alert)
                    {

                    }
                }, new FollowupListAdapter.OnDoubleClickListener() {
            @Override
            public void onDetailsView(int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(transactionAdapter);

    }



}
