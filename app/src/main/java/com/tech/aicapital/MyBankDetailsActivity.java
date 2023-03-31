package com.tech.aicapital;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.tech.aicapital.datalist.IfscResponse;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;


import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.aicapital.mvps.Constant.USER_ID;
import static com.tech.aicapital.mvps.Constant.USER_MOBILE;

public class MyBankDetailsActivity extends AppCompatActivity
{
    @BindView(R.id.edBankIfsc)
    EditText edBankIfsc;
    @BindView(R.id.edBankName)
    EditText edBankName;
    @BindView(R.id.edAccountNumber)
    EditText edBankAccountNo;
    @BindView(R.id.edConfirmAccountNo)
    EditText edConfirmAccountNo;
    @BindView(R.id.edAmount)
    EditText edAmount;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.BankAddress)
    TextView BankAddress;
    @BindView(R.id.tvBalance)
    TextView tvBalance;
    @BindView(R.id.tilBankName)
    TextInputLayout tilBankName;
    @BindView(R.id.tiLAmount)
    TextInputLayout tilAmount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bankdetailinfo);
        ButterKnife.bind(this);
        Utility.pageSetupBackButton(MyBankDetailsActivity.this,"Bank Details");
        edBankIfsc.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {

                String ifsccode=cs.toString();
                if(ifsccode.trim().length()==11)
                {
                    tilBankName.setVisibility(View.VISIBLE);
                    getIfscode(edBankIfsc.getText().toString());
                }
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
            {
//                Toast.makeText(getApplicationContext(),"before text change", Toast.LENGTH_LONG).show();
            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(),"after text change", Toast.LENGTH_LONG).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnSubmit.getText().toString().equalsIgnoreCase("WITHDRAW")){
                    withdrawMoney();
                }
                else{
                    sendBankDetails();
                }

            }
        });

        getMemberDetail();


    }
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
    public void sendBankDetails()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.sendBankDetails(Pref.getValue(MyBankDetailsActivity.this,USER_ID,""),"0",
                edBankName.getText().toString(),edBankIfsc.getText().toString(),
                "'"+edBankAccountNo.getText().toString());
        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse parlimentDataResponse = response.body();
                Utility.hideProgressBar();

                if (parlimentDataResponse != null) {
                    alertDia(parlimentDataResponse.getMessage());
                } else
                    Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"network problem ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void getMemberDetail()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<MemberDataResponse> call = apiService.member_details(Pref.getValue(MyBankDetailsActivity.this,USER_ID,""));
        call.enqueue(new Callback<MemberDataResponse>() {
            @Override
            public void onResponse(Call<MemberDataResponse> call, Response<MemberDataResponse> response)
            {
                MemberDataResponse bannerDataResponse = response.body();
                Utility.hideProgressBar();
                if (bannerDataResponse != null) {
                    if(bannerDataResponse.isStatus())
                    {
                        tvBalance.setText("$"+bannerDataResponse.getData().get(0).getAvailBalance());
                        if(bannerDataResponse.getData().get(0).getBankstatus().equalsIgnoreCase("2"))
                        {
                            edBankIfsc.setText(bannerDataResponse.getData().get(0).getBankIfsc());
                            edBankAccountNo.setText(bannerDataResponse.getData().get(0).getBankAccNo());
                            tvBalance.setText("$"+bannerDataResponse.getData().get(0).getAvailBalance());
                            edBankIfsc.setEnabled(false);
                            edBankAccountNo.setEnabled(false);
                            tilAmount.setVisibility(View.VISIBLE);
                            btnSubmit.setText("WITHDRAW");
                        }
                        else{
                            tilAmount.setVisibility(View.GONE);
                            btnSubmit.setText("UPDATE");
                        }

                    }



                } else
                    Toast.makeText(getApplicationContext(),"Some error occur", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MemberDataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
//                    Toast.makeText(getActivity(),"Some error occur 3", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getActivity(),"Some error occur 2", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void withdrawMoney()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.withdrawMoney(Pref.getValue(MyBankDetailsActivity.this,USER_ID,""),edAmount.getText().toString());
        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse parlimentDataResponse = response.body();
                Utility.hideProgressBar();

                if (parlimentDataResponse != null) {
                    alertDia(parlimentDataResponse.getMessage());
                } else
                    Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"network problem ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void getIfscode(String ifsccode)
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient4().create(NetworkCaller.class);
        Call<IfscResponse> call = apiService.getIfscode(ifsccode);
        call.enqueue(new Callback<IfscResponse>() {
            @Override
            public void onResponse(Call<IfscResponse> call, Response<IfscResponse> response) {
                IfscResponse parlimentDataResponse = response.body();
                Utility.hideProgressBar();

                if (parlimentDataResponse != null) {
                    edBankName.setText(parlimentDataResponse.getBANK());
                    BankAddress.setText(parlimentDataResponse.getBRANCH()+" "+parlimentDataResponse.getADDRESS());
//                    Toast.makeText(getApplicationContext(),parlimentDataResponse.getADDRESS(),Toast.LENGTH_SHORT).show();

//                    alertDia(parlimentDataResponse.getMessage());
                } else
                    Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<IfscResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"network problem ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void alertDia(String message)
    {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(message)
                .setContentText("Bank Verification ")
                .setCustomImage(R.mipmap.ic_launcher_round)
                .setConfirmText("Yes")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        MyBankDetailsActivity.this.finish();
                    }
                }).setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {

                finish();
            }
        })
                .show();
    }

}
