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
import com.tech.aicapital.followups.adapter.CustomerListAdapter;
import com.tech.aicapital.followups.datalist.CustomerDataList;
import com.tech.aicapital.followups.datalist.CustomerDataResponse;
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

public class CustomerListActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviews);
        ButterKnife.bind(this);
        Utility.pageSetupBackButton(CustomerListActivity.this,"Followups");
        getMyCustomers();
    }
    private void showAlertPinList(final List<CustomerDataList> dataList) {
        try {

            CustomerListAdapter transactionAdapter = new CustomerListAdapter(getApplicationContext(), true, dataList,
                    new CustomerListAdapter.OnItemClickListener() {
                        @Override
                        public void onListItemClick(int position) {
                            Intent intent=new Intent(CustomerListActivity.this, FollowUpHistoryActivity.class);
                            intent.putExtra("customer_id",dataList.get(position).getCustomerId());
                            intent.putExtra("cname",dataList.get(position).getCname());
                            intent.putExtra("customer_mobile",dataList.get(position).getMobileNo());
                            intent.putExtra("work",dataList.get(position).getWork());
                            intent.putExtra("mi",dataList.get(position).getmIncome());
                            intent.putExtra("education",dataList.get(position).getEducation());
                            intent.putExtra("regiDate",dataList.get(position).getCreatedDate());
                            startActivity(intent);


                        }

                        @Override
                        public void onUpdateQty(int position, String qty, double intrestrate, int selvalue, int mainDuration, boolean alert) {

                        }
                    }, new CustomerListAdapter.OnDoubleClickListener() {
                @Override
                public void onDetailsView(int position) {

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);
//            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.setAdapter(transactionAdapter);

        } catch (Exception e) {
        }


    }
    public void getMyCustomers() {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<CustomerDataResponse> call = apiService.getMyCustomers(Pref.getValue(CustomerListActivity.this, USER_ID, "0"));
        call.enqueue(new Callback<CustomerDataResponse>() {
            @Override
            public void onResponse(Call<CustomerDataResponse> call, Response<CustomerDataResponse> response) {
                CustomerDataResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();
                if (hospitalListResponse != null) {
                    if (hospitalListResponse.isStatus()) {
                        showAlertPinList(hospitalListResponse.getData());
                    } else {
                        Toast.makeText(getApplicationContext(), hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else
                    Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CustomerDataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(), "error 2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error 3", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
