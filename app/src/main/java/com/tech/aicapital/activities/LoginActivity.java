package com.tech.aicapital.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.tech.aicapital.CheckPermission;
import com.tech.aicapital.MainActivity;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.MemberDetailResponse;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.aicapital.mvps.Constant.USER_EMAIL;
import static com.tech.aicapital.mvps.Constant.USER_ID;
import static com.tech.aicapital.mvps.Constant.USER_MOBILE;
import static com.tech.aicapital.mvps.Constant.USER_NAME;
import static com.tech.aicapital.mvps.Constant.isLOGIN;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tvSignUp)
    TextView tvSignUp;

    @BindView(R.id.edMobile)
    TextView edMobile;
    @BindView(R.id.edPassword)
    TextView edPassword;
    @BindView(R.id.tvSignIn)
    TextView tvSignIn;



    private static final String TAG = "LoginActivity";
    private static final int RC_SIGN_IN = 1001;
    GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    SignInButton signInButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);
        Utility.makeFullScreen(LoginActivity.this);

        if( Pref.getLoginStatus(LoginActivity.this,isLOGIN,false))
        {
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
        tvSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                signInToGoogle();
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(Utility.isConnectedToInternet(getApplicationContext()))
                {
                    if(edMobile.getText().toString().trim().length()<5){

                        Utility.showRedMessage(LoginActivity.this,"Please enter correct details");
                    }else if(edPassword.getText().toString().trim().length()<4){
                        Utility.showRedMessage(LoginActivity.this,"Please enter correct details");
                    }
                    else{
                        final String member_id=edMobile.getText().toString();
                        String pwd=edPassword.getText().toString();
                        Utility.showProgressDialog(LoginActivity.this);
                        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
                        Call<MemberDetailResponse> call = apiService.getnLogin(member_id,pwd);
                        call.enqueue(new Callback<MemberDetailResponse>() {
                            @Override
                            public void onResponse(Call<MemberDetailResponse> call, Response<MemberDetailResponse> response)
                            {
                                MemberDetailResponse hospitalListResponse = response.body();
                                Utility.hideProgressBar();
                                if (hospitalListResponse != null)
                                {
                                    if(hospitalListResponse.isStatus())
                                    {
                                        Pref.setLoginStatus(LoginActivity.this,isLOGIN,true);
                                        Pref.setValue(LoginActivity.this,USER_ID,hospitalListResponse.getFetchLoginData().get(0).getUserId());
                                        Pref.setValue(LoginActivity.this,USER_MOBILE,hospitalListResponse.getFetchLoginData().get(0).getUserMobile());
                                        Pref.setValue(LoginActivity.this,USER_EMAIL,hospitalListResponse.getFetchLoginData().get(0).getUserEmail());
                                        Pref.setValue(LoginActivity.this,USER_NAME,hospitalListResponse.getFetchLoginData().get(0).getUserName());
                                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Utility.showRedMessage(LoginActivity.this,hospitalListResponse.getMessage());
                                    }
                                } else
                                    Utility.showRedMessage(LoginActivity.this,"UNKNOWN RESPONSE ");
                            }
                            @Override
                            public void onFailure(Call<MemberDetailResponse> call, Throwable t) {
                                Utility.hideProgressBar();
                                if (t instanceof IOException) {
                                    //Add your code for displaying no network connection error
                                    Utility.showRedMessage(LoginActivity.this,t.getMessage());
                                } else {
                                    Utility.showRedMessage(LoginActivity.this,t.getMessage());
                                }
                            }
                        });
                    }
                }
                else{

                }

            }
        });

        configureGoogleClient();
    }
    private void configureGoogleClient()
    {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
    }
    public void usersignup(final String name, String email)
    {
        Utility.showProgressDialog(this);
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<NewResponse> call = apiService.memberRegistration(name, "1","1","10000",email);
        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();
                if (hospitalListResponse != null)
                {
                    if(hospitalListResponse.isStatus())
                    {
                        Pref.setLoginStatus(LoginActivity.this,isLOGIN,true);
                        Pref.setValue(LoginActivity.this,USER_ID,hospitalListResponse.getData());
//                        Pref.setValue(LoginActivity.this,USER_MOBILE,"0");
                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    
                } else
                    Toast.makeText(getApplicationContext(), "Unknown Response ", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException)
                {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            Log.d(TAG, "Currently Signed in: " + currentUser.getEmail());
//            showToastMessage("Currently Logged in: " + currentUser.getEmail());
        }
    }
    public void signInToGoogle()
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
//                showToastMessage("Google Sign in Succeeded");
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
//                showToastMessage("Google Sign in Failed " + e);
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount account)
    {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Log.d(TAG, "signInWithCredential:success: currentUser: " + user.getEmail());
//                            showToastMessage("Firebase Authentication Succeeded ");
                            launchMainActivity(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            showToastMessage("Firebase Authentication failed:" + task.getException());
                        }
                    }
                });
    }
    private void launchMainActivity(FirebaseUser user)
    {
        if (user != null) {
//            Utility.showERRORMessage(this,user.getDisplayName()+user.getEmail());
//            Toast.makeText(getApplicationContext(),user.getDisplayName()+user.getEmail(),Toast.LENGTH_LONG).show();
            usersignup(user.getDisplayName(),user.getEmail());
        }
    }
    @Override
    public boolean onSupportNavigateUp()
    {
        finish();
        return true;
    }
}
