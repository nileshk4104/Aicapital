package com.tech.aicapital.mvps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.tech.aicapital.MyBankDetailsActivity;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.MemberDataList;
import com.tech.aicapital.datalist.MemberDataResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by saurabha on 2/8/16.
 */
public class Utility {
    private static final String TAG = Utility.class.getSimpleName();
    private static ProgressDialog sProgressDialog;
//  private static Context context;
    /**
     * This method will make the navigation bar and status bar transparent only for api 21+
     *
     * @param activity
     */
    public static void makeFullScreen(Activity activity)
    {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // Set the status bar and navigation bar as transparent
//            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        }

        if (Build.VERSION.SDK_INT >= 21)
        {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }



    public static void pageSetupBackButton(Activity activity,String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<b><font color='#000000'>"+title+"</font></b>"));

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFFFF")));

        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.white));// set status background white
        }

    }
    public static void pageSetupBackButtonSub(Activity activity,String title,String subtitle) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<small><b><font color='#000000'>"+title+"</font></b></small>"));
        actionBar.setSubtitle(Html.fromHtml("<small><font color='#000000'>"+subtitle+"</font></small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00FFFFFF")));
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.white));// set status background white
        }

    }
    public static void pageSetupBackButtonSubcolor(Activity activity,String title,boolean isAtive)
    {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
            upArrow.setColorFilter(activity.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
            actionBar.setHomeAsUpIndicator(upArrow);
            actionBar.setTitle(Html.fromHtml("<b><font color='#000000'>"+title+"</font></b>"));
            if(isAtive)
            {
                actionBar.setSubtitle(Html.fromHtml("<b><font color='#FFFFFF'>"+"ACTIVE"+"</font><b/>"));
            }
            else
            {
                actionBar.setSubtitle(Html.fromHtml("<font color='#FF0000'>"+"INACTIVE"+"</font>"));
            }
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF047BD5")));
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorAccent));// set status background white
            }
    }

    public static void alertDia(Context conte, String message)
    {
        new SweetAlertDialog(conte, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(message)
                .setContentText("")
                .setCustomImage(R.mipmap.ic_launcher_round)
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismiss();
                    }
                })
                .show();
    }
    public static void alertDiaNormal(Context conte, String message, String title)
    {
        new SweetAlertDialog(conte, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText(title)
                .setContentText(message)
                .setCustomImage(R.mipmap.ic_launcher_round)
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog)
                    {
                        sDialog.dismiss();
                    }
                })
                .show();
    }
    public static void pageSetupPrimary(Activity activity,String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<b><font color='#000000'>"+title+"</font></b>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF047BD5")));
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorAccent));// set status background white
        }

    }
    public static void pageSetupPrimarySub(Activity activity,String title,String subtitle) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<small><b><font color='#000000'>"+title+"</font></b></small>"));
        actionBar.setSubtitle(Html.fromHtml("<small><b><font color='#000000'>"+subtitle+"</font></b></small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF047BD5")));
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorAccent));// set status background white
        }

    }
    public static void pageSetupPrimarySubGroup(Activity activity,String title,String subtitle) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<small><b><font color='#FFFFFF'>"+title+"</font></b></small>"));
        actionBar.setSubtitle(Html.fromHtml("<small><b><font color='#FFFFFF'>"+subtitle+"</font></b></small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0011FF")));
//        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorBluenila));// set status background white
        }

    }
    public static void pageSetupPrimarySub2(Activity activity,String title,String subtitle) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<small><b><font color='#FFFFFF'>"+title+"</font></b></small>"));
        actionBar.setSubtitle(Html.fromHtml("<small><b><font color='#FFFFFF'>"+subtitle+"</font></b></small>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF047BD5")));
//        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.phonep));// set status background white
        }

    }
    public static void pageSetup(Activity activity,String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<b><font color='#000000'>"+title+"</font></b>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF047BD5")));
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorAccent));// set status background white
        }

    }
    public static void pageSetupVideoList(Activity activity,String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<b><font color='#FFFFFF'>"+title+"</font></b>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF047BD5")));
//        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorAccent));// set status background white
        }

    }
    public static void pageSetupNo(Activity activity,String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();


    }
    public static void pageSetupSrt(Activity activity,String title)
    {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        actionBar.setHomeAsUpIndicator(upArrow);

        actionBar.setTitle(Html.fromHtml("<b><font color='#FFFFFF'>"+title+"</font></b>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#07002b")));
//        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.colorBlueDark3));// set status background white
        }

    }
    public static List<MemberDataList> getMemberDetail(Context context, String userId, List<MemberDataList> mam)
    {

        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<MemberDataResponse> call = apiService.member_details(userId);
        call.enqueue(new Callback<MemberDataResponse>() {
            @Override
            public void onResponse(Call<MemberDataResponse> call, Response<MemberDataResponse> response) {
                MemberDataResponse memberDataResponse = response.body();
                if (memberDataResponse != null)
                {
                    if(memberDataResponse.isStatus())
                    {

                        mam.addAll(memberDataResponse.getData());
                    }

                }

            }
            @Override
            public void onFailure(Call<MemberDataResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return mam;

    }
    public static void pageSetupWhite(Activity activity,String title) {
        ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Drawable upArrow = activity.getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(activity.getResources().getColor(R.color.black), PorterDuff.Mode.LIGHTEN);
        actionBar.setHomeAsUpIndicator(upArrow);
        actionBar.setTitle(Html.fromHtml("<font color='#000000'>"+title+"</font>"));
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFFFF")));
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity,R.color.white));// set status background white
        }

    }
    public static boolean isValidEmailAddress(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
    public static void showGreenMessage(Context con,String message) {
        Toast toast = Toast.makeText(con, message,
                Toast.LENGTH_LONG);
        View view = toast.getView();
        view.getBackground().setColorFilter(Color.parseColor("#EEFFEE"), PorterDuff.Mode.SRC_ATOP);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
    }
    public static void showRedMessage(Context con,String message) {
        Toast toast = Toast.makeText(con, message,
                Toast.LENGTH_LONG);
        View view = toast.getView();
        view.getBackground().setColorFilter(Color.parseColor("#FFDDDD"), PorterDuff.Mode.SRC_IN);
        TextView text = view.findViewById(android.R.id.message);
        text.setTextColor(Color.parseColor("#000000"));
        toast.show();
    }
    public static void showProgressDialog(Context context) {
        if (null != sProgressDialog && sProgressDialog.isShowing()) {
            return;
        }
        sProgressDialog = new ProgressDialog(context);
        sProgressDialog.setMessage("loading please wait ... ");
        sProgressDialog.setCancelable(false);

        if (context instanceof Activity) {
            if (!((Activity) context).isFinishing()) {
                sProgressDialog.show();
            }
        } else {
            sProgressDialog.show();
        }
    }
    public static void showProgresDialog(Context context,String msg) {
        if (null != sProgressDialog && sProgressDialog.isShowing()) {
            return;
        }
        sProgressDialog = new ProgressDialog(context);
        sProgressDialog.setMessage(msg);
        sProgressDialog.setCancelable(false);

        if (context instanceof Activity) {
            if (!((Activity) context).isFinishing()) {
                sProgressDialog.show();
            }
        } else {
            sProgressDialog.show();
        }
    }
    public static void hideProgressBar() {
        try {
            if (null != sProgressDialog && sProgressDialog.isShowing()) {

                Context context = sProgressDialog.getContext();

                if (context instanceof Activity) {

                    if (!((Activity) context).isFinishing()) {


                        sProgressDialog.dismiss();
                        sProgressDialog = null;
                    }
                } else {


                    sProgressDialog.dismiss();
                    sProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException e) {
            Log.w(TAG, "Simple ignore the exceprion", e);
        }
    }
    public static boolean isConnectedToInternet(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);

                if (networkInfo != null && networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                //noinspection deprecation
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo networkInfo : info) {
                        if (networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                            Log.d(TAG,
                                    "NETWORKNAME: " + networkInfo.getTypeName());
                            return true;
                        }
                    }
                }
            }
        }
        Log.v(TAG, "not connected to internet");
        return false;
    }



}
