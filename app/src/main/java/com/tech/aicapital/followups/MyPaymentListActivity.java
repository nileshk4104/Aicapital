package com.tech.aicapital.followups;


import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.cart.datalist.PinDataList;
import com.tech.aicapital.cart.datalist.PinListResponse;
import com.tech.aicapital.followups.adapter.MyPaymentListAdapter;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPaymentListActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviews);
        Utility.pageSetupBackButton(MyPaymentListActivity.this,"My Deposits");
        ButterKnife.bind(this);

        getMyPinList();

    }

    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return super.onSupportNavigateUp();
    }
    private void showAlertPinList(final List<PinDataList> dataList, String baseUrl)
    {
        showPinList(dataList,baseUrl);
    }
    private void showPinList(List<PinDataList> dataList,String baseUrl)
    {
        MyPaymentListAdapter transactionAdapter=new MyPaymentListAdapter(getApplicationContext(),baseUrl, dataList,
                new MyPaymentListAdapter.OnItemClickListener() {
                    @Override
                    public void onListItemClick(int position)
                    {
//                        Intent intent=new Intent(MyPaymentListActivity.this,PayDetailActivity.class);
//                        intent.putExtra("isAdmin",false);
//                        intent.putExtra("amount",dataList.get(position).getAmount());
//                        intent.putExtra("pin_no",dataList.get(position).getPinNo());
//                        intent.putExtra("plan_name",dataList.get(position).getPlanName());
//                        intent.putExtra("plan_id",dataList.get(position).getPlanId());
//                        intent.putExtra("date",dataList.get(position).getDateTime());
//                        intent.putExtra("status",dataList.get(position).getStatus());
//                        intent.putExtra("image",dataList.get(position).getScreenshot());
//                        intent.putExtra("user_id",dataList.get(position).getAssignedTo());
//                        startActivity(intent);
                    }
                    @Override
                    public void onUpdateQty(int position, String qty,double intrestrate, int selvalue,int mainDuration, boolean alert)
                    {
                    }
                },new MyPaymentListAdapter.OnDoubleClickListener() {
            @Override
            public void onDetailsView(int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(transactionAdapter);
    }
    public void getMyPinList()
    {

        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<PinListResponse> call = apiService.getMyPinList(Pref.getValue(MyPaymentListActivity.this,USER_ID,""));
        call.enqueue(new Callback<PinListResponse>() {
            @Override
            public void onResponse(Call<PinListResponse> call, Response<PinListResponse> response) {
                PinListResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();
                if (hospitalListResponse != null) {
                    if(hospitalListResponse.getStatus())
                    {
                        showAlertPinList(hospitalListResponse.getData(),hospitalListResponse.getMessage());
                    }
                    else{
                        Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else
                    Toast.makeText(getApplicationContext(),"error 1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PinListResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"error 2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"error 3", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    protected void onResume() {
        getMyPinList();
        super.onResume();

    }
}
