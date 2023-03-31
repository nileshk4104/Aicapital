package com.tech.aicapital.activities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.goodiebag.pinview.Pinview;
import com.tech.aicapital.MainActivity;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.SmsBroadcastReceiver;
import com.tech.aicapital.SmsListener;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;
import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.aicapital.mvps.Constant.USER_ID;
import static com.tech.aicapital.mvps.Constant.USER_MOBILE;
import static com.tech.aicapital.mvps.Constant.isLOGIN;

//import androidx.localbroadcastmanager.content.LocalBroadcastManager;

/**
 * Created by Admin on 9/26/2018.
 */

public class OtpActivity extends AppCompatActivity
{

    @BindView(R.id.button_next_one)
    Button button_next_one;

    @BindView(R.id.button_resendotp)
    Button button_resendotp;

    @BindView(R.id.ednewpin)
    EditText ednewpin;

    @BindView(R.id.edConfirmpwd)
    EditText edConfirmpwd;

    @BindView(R.id.ednewPwd)
    EditText ednewPwd;

    @BindView(R.id.pin_hidden_edittext)
    EditText pin_hidden_edittext;
    @BindView(R.id.pin_layout_hidden)
    LinearLayout pin_layout_hidden;
    @BindView(R.id.pin_layout)
    LinearLayout pin_layout;


    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    String name,mobile,mail,refer_id,password,otp,pincode;
    private static final int PERMISSION_REQUEST_ID = 100;
    private static final String TAG = MainActivity.class.getSimpleName() ;
    SmsBroadcastReceiver mSmsBroadcastReceiver;
    private final String  BROADCAST_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    private IntentFilter intentFilter;
    Pinview pin;
    boolean action=false,forgotpwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_activity);
        ButterKnife.bind(this);

        pin = new Pinview(this);

        pin = (Pinview) findViewById(R.id.pinview);

        pin.setPinViewEventListener(new Pinview.PinViewEventListener(){
            @Override
            public void onDataEntered(Pinview pinview, boolean fromUser)
            {
                // Make api calls here or what not
                pin_hidden_edittext.setText(pinview.getValue());
                Toast.makeText(OtpActivity.this, pinview.getValue(), Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent=getIntent();
        mobile=intent.getStringExtra("mobile");
        name=intent.getStringExtra("name");
        mail=intent.getStringExtra("mail");
        refer_id=intent.getStringExtra("refer_id");
         otp=intent.getStringExtra("otp");
        pincode=intent.getStringExtra("pin_code");
        password=intent.getStringExtra("password");
        pin_hidden_edittext.setText(String.valueOf(otp));


        Utility.pageSetup(this,"OTP Verification ");
        ednewpin.setVisibility(View.GONE);
        mSmsBroadcastReceiver = new SmsBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        requestRuntimePermissions(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS);

        if (forgotpwd)
        {
          edConfirmpwd.setVisibility(View.VISIBLE);
          ednewPwd.setVisibility(View.VISIBLE);
        }
        else
        {
            edConfirmpwd.setVisibility(View.GONE);
            ednewPwd.setVisibility(View.GONE);
        }
        button_next_one.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (forgotpwd){
                    if(ednewPwd.getText().toString().trim().length()<4){
                        Toast.makeText(getApplicationContext(),"Minimum 4 character password requird  ", Toast.LENGTH_SHORT).show();
                    }else if(!edConfirmpwd.getText().toString().equalsIgnoreCase(ednewPwd.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Please Confirm Password Properly ! ", Toast.LENGTH_SHORT).show();
                    }else{
                    }
                }
                else{
                    if(pin.getValue().toString().trim().length()>3)
                    {
                        verifyOtp(mobile,pin.getValue());
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Pleasr Enter Proper Otp", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        button_resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (forgotpwd){
                    send_otp(mobile);
                }
                else{
                    send_otp(mobile);
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
//    public void sendforgot_otp(String mobile_no,String otp)
//    {
////        Toast.makeText(getApplicationContext(),"Calling resend  ", Toast.LENGTH_SHORT).show();
//        Utility.showProgressDialog(this);
//        final NetworkCaller apiService = ApiClient.getClient().create(NetworkCaller.class);
//        Call<NewResponse> call = apiService.verifyOtp(mobile_no,otp);
//        Log.e("Resend otp: ",mobile_no);
//        call.enqueue(new Callback<NewResponse>()
//        {
//            @Override
//            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
//                NewResponse parlimentDataResponse = response.body();
//                Utility.hideProgressBar();
//
//                if (parlimentDataResponse != null) {
//                    Toast.makeText(getApplicationContext(),parlimentDataResponse.getMessage(), Toast.LENGTH_SHORT).show();
//
//                } else
//                    Toast.makeText(getApplicationContext(),"Error  ", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(Call<NewResponse> call, Throwable t) {
//                Utility.hideProgressBar();
//                if (t instanceof IOException)
//                {
//                    //Add your code for displaying no network connection error
//                    Toast.makeText(getApplicationContext(),"Network Issue", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"Server Issue", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
    public void send_otp(String mobile_no)
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.sendOtp(mobile_no);
        Log.e("Resend otp: ",mobile_no);
        call.enqueue(new Callback<NewResponse>()
        {
                    @Override
                    public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                        NewResponse parlimentDataResponse = response.body();
                        Utility.hideProgressBar();

                        if (parlimentDataResponse != null) {

                            Toast.makeText(getApplicationContext(),parlimentDataResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        } else
                            Toast.makeText(getApplicationContext(),"Error  ", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<NewResponse> call, Throwable t) {
                        Utility.hideProgressBar();
                        if (t instanceof IOException)
                        {
                            Toast.makeText(getApplicationContext(),"Network Issue", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Server Issue", Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }

    public void verifyOtp(String mobile_no, String otp)
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.verifyOtp(mobile_no,otp);
        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse parlimentDataResponse = response.body();
                Utility.hideProgressBar();
                if (parlimentDataResponse != null)
                {
                    Log.e("verify_otp",parlimentDataResponse.getMessage()+" Message "+parlimentDataResponse.getMessage());
                    if (parlimentDataResponse.isStatus()){
                        // SUCCESS
                        makeRegistration();
                    }
                } else
                    Toast.makeText(getApplicationContext(),"Error  ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"Network Issue", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Server Issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void changeStatusBarColor()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#22aa33"));
        }
    }
    public void makeRegistration()
    {
        Utility.showProgressDialog(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateandTime = sdf.format(new Date());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.makeRegistration(name, mobile,password,refer_id,mail,"0");
        call.enqueue(new Callback<NewResponse>()
        {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();

                if (hospitalListResponse != null)
                {
                    Toast.makeText(getApplicationContext(),hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    if(hospitalListResponse.isStatus())
                    {
                        Pref.setLoginStatus(OtpActivity.this,isLOGIN,true);
                        Pref.setValue(OtpActivity.this,USER_ID,hospitalListResponse.getData());
//                        Pref.setValue(OtpActivity.this,USER_MOBILE,hospitalListResponse.getFetchLoginData().get(0).getUserMobile());

                        Intent intent=new Intent(OtpActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.enter,R.anim.exit);
                        finish();
                    }
                    else{

                    }


                } else
                    Toast.makeText(getApplicationContext(),"Unknown Response", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Utility.hideProgressBar();

                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void hideProgressBar() {

        Utility.hideProgressBar();
    }
    private void requestRuntimePermissions(String... permissions) {
        for (String perm : permissions) {

            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{perm}, PERMISSION_REQUEST_ID);

            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==PERMISSION_REQUEST_ID){

            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted
                mSmsBroadcastReceiver.bindListener(new SmsListener() {
                    @Override
                    public void messageReceived(String sender, String messageText) {
                        Log.e("Text",messageText+sender);
//                        pin.setValue(messageText);
                        Toast.makeText(OtpActivity.this,"Message: "+messageText,Toast.LENGTH_LONG).show();
                    }
                });

            } else {
                Log.e(TAG, "Permission not granted");



            }
        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(mSmsBroadcastReceiver, intentFilter);
//        Log.e("MainActivity","Registered receiver");
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mSmsBroadcastReceiver);
//        Log.e("MainActivity","Unregistered receiver");

    }




}
