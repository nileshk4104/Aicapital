package com.tech.aicapital.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tech.aicapital.CheckPermission;
import com.tech.aicapital.MainActivity;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.SplashScreen;
import com.tech.aicapital.datalist.CheckResponse;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.datalist.SimpleResponse;
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
import static com.tech.aicapital.mvps.Constant.isLOGIN;

public class RegistrationActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.eduserName)
    EditText eduserName;
    @BindView(R.id.edUserMobile)
    EditText edUserMobile;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.edReferCode)
    EditText edReferCode;
    @BindView(R.id.edReferalName)
    EditText edReferalName;

    @BindView(R.id.tvLogIn)
    TextView tvlogin;

    @BindView(R.id.tvSignUp)
    TextView tvSignUp;
    @BindView(R.id.tvBackButton)
    TextView tvBackButton;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private static final String TAG = "DATA";
    boolean isReferCode=false;
    String refer_id="10000";

    String token;
    private static final int RC_SIGN_IN = 1;
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_sign_up);
        ButterKnife.bind(this);
        Utility.makeFullScreen(RegistrationActivity.this);

        tvBackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                     startActivity(intent);
                    finish();
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                sendOtp();
                makeRegistration();
            }
        });


        edReferCode.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                String charl = String.valueOf(cs.toString().trim());
                if(charl.length()==10) {

                    getUserDetailbyMobile(edReferCode.getText().toString());
                }


            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
        edReferCode.setText(Pref.getValue(this, "REF", "0000000000"));
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            String val = data.getStringExtra("val");
            if(val!=null){
                if(resultCode == RESULT_OK) {
                }
            }else{
//                try {
                Uri contactData = data.getData();
                Cursor phone = getContentResolver().query(contactData, null, null, null, null);
                if(phone.moveToFirst()){
                    String id =phone.getString(phone.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                    String hasPhone =phone.getString(phone.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (hasPhone.equalsIgnoreCase("1"))
                    {
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,
                                null, null);
                        phones.moveToFirst();
                        String cNumber = phones.getString(phones.getColumnIndex("data1"));
                        cNumber=cNumber.trim();
                        cNumber = cNumber.replace(" ", "");
                        String last3="";

                        if (cNumber == null || cNumber.length() == 10) {
                            last3 = cNumber;
                        } else {
                            last3 = cNumber.substring(cNumber.length() - 10);
                        }
                        if(isReferCode){
                            edReferCode.setText(last3);
                        }else edUserMobile.setText(last3);

//                            Toast.makeText(getApplicationContext(),last3,Toast.LENGTH_SHORT).show();
                    }
                    String name = phone.getString(phone.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    if(isReferCode){

                    }else  eduserName.setText(name);
                }

            }
        }catch (Exception e){

        }
    }
    public void makeRegistration()
    {
        Utility.showProgressDialog(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentDateandTime = sdf.format(new Date());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.makeRegistration(eduserName.getText().toString(), edUserMobile.getText().toString(),
                edPassword.getText().toString(),refer_id,edEmail.getText().toString(),"0");
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
                        Pref.setLoginStatus(RegistrationActivity.this,isLOGIN,true);
                        Pref.setValue(RegistrationActivity.this,USER_ID,hospitalListResponse.getData());
//                        Pref.setValue(OtpActivity.this,USER_MOBILE,hospitalListResponse.getFetchLoginData().get(0).getUserMobile());

                        Intent intent=new Intent(RegistrationActivity.this, MainActivity.class);
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

    public void sendOtp()
    {
        Utility.showProgressDialog(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // String currentDateandTime = sdf.format(new Date());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.sendOtp(edUserMobile.getText().toString());
        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();
                if (hospitalListResponse != null)
                {
                    Intent intent=new Intent(RegistrationActivity.this,OtpActivity.class);
                    intent.putExtra("name",eduserName.getText().toString());
                    intent.putExtra("mobile",edUserMobile.getText().toString());
                    intent.putExtra("mail",edEmail.getText().toString());
                    intent.putExtra("pin_code","411001");
                    intent.putExtra("refer_id",refer_id);
                    intent.putExtra("password",edPassword.getText().toString());
                    startActivity(intent);
                    finish();
                }

            }
            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
//                    Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getApplicationContext(),"", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getUserDetailbyMobile(String usermobile)
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<MemberDataResponse> call = apiService.getUserDetailbyMobile(usermobile);
        call.enqueue(new Callback<MemberDataResponse>() {
            @Override
            public void onResponse(Call<MemberDataResponse> call, Response<MemberDataResponse> response)
            {
                MemberDataResponse bannerDataResponse = response.body();
                Utility.hideProgressBar();
                if (bannerDataResponse != null) {
                    if(bannerDataResponse.isStatus())
                    {
                        if(bannerDataResponse.getData().size()>0)
                        {
                            refer_id=bannerDataResponse.getData().get(0).getUserId();
                            edReferalName.setText(bannerDataResponse.getData().get(0).getUserName());
                        }

                    }
                    else{
                        Utility.showRedMessage(RegistrationActivity.this,"Entered refer mobile number is not registerd with us , try with another ");
                    }



                }
            }

            @Override
            public void onFailure(Call<MemberDataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error

                } else {

                }
            }
        });

    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Toast.makeText(getApplicationContext(), String.valueOf(connectionResult.toString()), Toast.LENGTH_SHORT);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
