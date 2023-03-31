package com.tech.aicapital.followups;

import static com.tech.aicapital.mvps.Constant.USER_ID;
import static com.tech.aicapital.mvps.Utility.hideProgressBar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewFollowupActivity extends AppCompatActivity {
    @BindView(R.id.tvFollowups)
    TextView tvFollowups;

    @BindView(R.id.edRemark)
    EditText edRemark;

    @BindView(R.id.edReminder)
    TextView edReminder;

    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    @BindView(R.id.tvLogIn)
    TextView tvLogIn;


    String customerId, custName, userId, referId,distCode="1",tehsilCode="1";
    String date_time = "";
    int mYear;
    int mMonth;
    int mDay;

    int mHour;
    int mMinute;
    int educationCode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_followup_activity);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        customerId=intent.getStringExtra("customerId");
        custName=intent.getStringExtra("custName");
        Utility.pageSetupBackButton(AddNewFollowupActivity.this, custName);

        tvFollowups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getReasons();
            }
        });

            tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                insertFollowup(tvFollowups.getText().toString(),edRemark.getText().toString(),
                        edReminder.getText().toString());
            }
        });

            edReminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    datePicker();
                }
            });
    }

    private void datePicker(){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //*************Call Time Picker Here ********************
                        tiemPicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private void tiemPicker(){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;

                        edReminder.setText(date_time+" "+hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }
    public void getReasons()
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<EducationDataResponse> call = apiService.getReasons();
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

                            tvFollowups.setText(couponDataLists.get(position).getEducation());
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

    private void insertFollowup(String followup, String remark, String reminder) {
//        Utility.showProgressDialog(this);

        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.insertFollowup(customerId,followup,remark,"3","1",reminder);
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

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}