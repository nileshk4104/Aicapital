package com.tech.aicapital.followups;

import static com.tech.aicapital.mvps.Constant.USER_ID;
import static com.tech.aicapital.mvps.Utility.hideProgressBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.Adapter.VillageAdapter;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.datalist.VillageData;
import com.tech.aicapital.datalist.VillageDataResponse;
import com.tech.aicapital.followups.adapter.EducationAdapter;
import com.tech.aicapital.followups.datalist.EducationDataList;
import com.tech.aicapital.followups.datalist.EducationDataResponse;
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

public class AddNewCustomerActivity extends AppCompatActivity {
    @BindView(R.id.edEducation)
    TextView edEducation;
    @BindView(R.id.edWork)
    EditText edWork;

    @BindView(R.id.edMonthlyIncome)
    EditText edMonthlyIncome;
    @BindView(R.id.edReferalName)
    EditText edReferalName;

    @BindView(R.id.eduserName)
    EditText eduserName;
    @BindView(R.id.edUserMobile)
    EditText edUserMobile;

    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    @BindView(R.id.tvLogIn)
    TextView tvLogIn;


    @BindView(R.id.tvDistrict)
    TextView tvDistrict;
    @BindView(R.id.tvTehasil)
    TextView tvTehasil;


    int educationCode=0;
    String pin_no, amount, userId, referId,distCode="1",tehsilCode="1";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_customer);
        ButterKnife.bind(this);
        Utility.pageSetupBackButton(AddNewCustomerActivity.this, "Add New Prospect");
        edEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEducation();
            }
        });
        tvDistrict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDistrict();
            }
        });
        tvTehasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(distCode!=null){
                    getTehasil();
                }
            }
        });
        edUserMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String charl = String.valueOf(cs.toString().trim());
                if (charl.length() == 10) {
//                    getUserDetailbyMobile(edUserMobile.getText().toString());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertCustomer(eduserName.getText().toString(),edUserMobile.getText().toString(),
                        edWork.getText().toString(),edReferalName.getText().toString(),
                        edMonthlyIncome.getText().toString(),String.valueOf(educationCode),
                        Pref.getValue(AddNewCustomerActivity.this,USER_ID,""),
                        distCode,tehsilCode);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void insertCustomer(String username, String mobile, String work, String reference, String monthIncome,
                                String education, String sponserId, String distCode, String tehsilCode) {
//        Utility.showProgressDialog(this);

        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.insertCustomer(username,mobile,work,reference,
                monthIncome,education,sponserId,distCode, tehsilCode);
        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse hospitalListResponse = response.body();


                if (hospitalListResponse != null)
                {
                    Toast.makeText(getApplicationContext(), hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    finish();
                } else
                    Toast.makeText(getApplicationContext(), "error 0", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error 2", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public void getDistrict()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<VillageDataResponse> call = apiService.getDistrict();
        call.enqueue(new Callback<VillageDataResponse>() {
            @Override
            public void onResponse(Call<VillageDataResponse> call, Response<VillageDataResponse> response) {
                VillageDataResponse hospitalListResponse = response.body();
                hideProgressBar();


                if (hospitalListResponse != null) {
                    onSuccessCityList(hospitalListResponse.getData(),1);
                } else
                    Toast.makeText(getApplicationContext(), "error 0", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<VillageDataResponse> call, Throwable t) {
                hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error 2", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void getTehasil()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<VillageDataResponse> call = apiService.getTehasil(distCode);
        call.enqueue(new Callback<VillageDataResponse>() {
            @Override
            public void onResponse(Call<VillageDataResponse> call, Response<VillageDataResponse> response) {
                VillageDataResponse hospitalListResponse = response.body();
                hideProgressBar();
                if (hospitalListResponse != null) {
                    onSuccessCityList(hospitalListResponse.getData(),2);
                } else
                    Toast.makeText(getApplicationContext(), "error 0", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<VillageDataResponse> call, Throwable t) {
                hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error 2", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void onSuccessCityList(final List<VillageData> couponDataLists, int code)
    {
        hideProgressBar();
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = ((Activity) this).getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.homepage, null);
            final AlertDialog dialog = builder.create();
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.setView(dialogLayout, 0, 0, 0, 0);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            WindowManager.LayoutParams wlmp = dialog.getWindow()
                    .getAttributes();
            wlmp.gravity = Gravity.CENTER;
            RecyclerView recycleView = (RecyclerView) dialogLayout.findViewById(R.id.recyclerView);

            VillageAdapter transactionAdapter = new VillageAdapter(getApplicationContext(), couponDataLists,
                    new VillageAdapter.OnItemClickListener() {
                        @Override
                        public void onListItemClick(int position) {
                            if(code==1)
                            {
                                tvDistrict.setText(couponDataLists.get(position).getName());
                                distCode=couponDataLists.get(position).getCode();
//                                getTehasil();
                                dialog.dismiss();
                            }
                            else if(code==2){
                                tvTehasil.setText(couponDataLists.get(position).getName());
                                tehsilCode=couponDataLists.get(position).getCode();
                                dialog.dismiss();
                            }

                        }

                        @Override
                        public void onUpdateQty(int position, String qty,
                                                double intrestrate,
                                                int selvalue, int mainDuration, boolean alert) {

                        }
                    }, new VillageAdapter.OnDoubleClickListener() {
                @Override
                public void onDetailsView(int position) {

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recycleView.setLayoutManager(mLayoutManager);
            recycleView.setAdapter(transactionAdapter);
            builder.setView(dialogLayout);
            dialog.show();
        }
        catch (Exception e){

        }
    }
    public void getEducation()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<EducationDataResponse> call = apiService.getEducationList();
        call.enqueue(new Callback<EducationDataResponse>() {
            @Override
            public void onResponse(Call<EducationDataResponse> call, Response<EducationDataResponse> response) {
                EducationDataResponse hospitalListResponse = response.body();
                hideProgressBar();
                if (hospitalListResponse != null) {
                    onSuccessEducationList(hospitalListResponse.getData(),2);
                } else
                    Toast.makeText(getApplicationContext(), "error 0", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<EducationDataResponse> call, Throwable t) {
                hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(), "error 1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error 2", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    public void onSuccessEducationList(final List<EducationDataList> couponDataLists, int code)
    {
        hideProgressBar();
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = ((Activity) this).getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.homepage, null);
            final AlertDialog dialog = builder.create();
            dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            dialog.setView(dialogLayout, 0, 0, 0, 0);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            WindowManager.LayoutParams wlmp = dialog.getWindow()
                    .getAttributes();
            wlmp.gravity = Gravity.CENTER;
            RecyclerView recycleView = (RecyclerView) dialogLayout.findViewById(R.id.recyclerView);

            EducationAdapter transactionAdapter = new EducationAdapter(getApplicationContext(), couponDataLists,
                    new EducationAdapter.OnItemClickListener() {
                        @Override
                        public void onListItemClick(int position) {

                                edEducation.setText(couponDataLists.get(position).getEducation());
                                educationCode=Integer.parseInt(couponDataLists.get(position).getEduId());
                                dialog.dismiss();


                        }

                        @Override
                        public void onUpdateQty(int position, String qty,
                                                double intrestrate,
                                                int selvalue, int mainDuration, boolean alert) {

                        }
                    }, new EducationAdapter.OnDoubleClickListener() {
                @Override
                public void onDetailsView(int position) {

                }
            });
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            recycleView.setLayoutManager(mLayoutManager);
            recycleView.setAdapter(transactionAdapter);
            builder.setView(dialogLayout);
            dialog.show();
        }
        catch (Exception e){

        }
    }

}