package com.tech.aicapital.fragment;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.MainActivity;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.activities.AllArticlesActivity;
import com.tech.aicapital.activities.WebViewActivity;
import com.tech.aicapital.cart.datalist.PinDataList;
import com.tech.aicapital.cart.datalist.PinListResponse;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.tech.aicapital.datalist.ProductDataList;
import com.tech.aicapital.followups.datalist.UserDashboardData;
import com.tech.aicapital.followups.datalist.UserDashboardDataResponse;
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

import static com.tech.aicapital.mvps.Constant.USER_ID;
import static com.tech.aicapital.mvps.Constant.USER_MOBILE;
import static com.tech.aicapital.mvps.Utility.hideProgressBar;

public class WalletFragment extends Fragment
{

     @BindView(R.id.cardSelfPurchase)
     CardView cardSelfPurchase;
     @BindView(R.id.cardShare)
     CardView cardShare;
     @BindView(R.id.cardAbout)
     CardView cardAbout;
     @BindView(R.id.tvWalletBalance)
     TextView tvWalletBalance;

     @BindView(R.id.tvTodaysProfit)
     TextView tvTodaysProfit;
     @BindView(R.id.tvTotalProfit)
     TextView tvTotalProfit;


     @BindView(R.id.tvTotalGrowth)
     TextView tvTotalGrowth;
     @BindView(R.id.tvMonthlyRatio)
     TextView tvMonthlyRatio;

     @BindView(R.id.tvTotalDeposit)
     TextView tvTotalDeposit;
     @BindView(R.id.tvTotalWithdraw)
     TextView tvTotalWithdraw;



    boolean ISACTIVE=false,isContact=true;
    MemberDataResponse memberDataRespons;
    public WalletFragment() {
    }
    @SuppressLint("ValidFragment")
    public WalletFragment(MemberDataResponse memberDataRespons) {
        this.memberDataRespons=memberDataRespons;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.wallet_frag, container, false);
        ButterKnife.bind(this, view);
        cardAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intennt3=new Intent(getActivity(), AllArticlesActivity.class);
                startActivity(intennt3);
            }
        });
        cardShare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String whatsAppMessage = "*Welcome to AICAPITAL  " +
                ""+"https://play.google.com/store/apps/details?id=com.tech.aicapital&referrer="+Pref.getValue(getActivity(),USER_MOBILE,"")+"" +
                        " मेरा रेफर कोड है  "+Pref.getValue(getActivity(),USER_MOBILE,"")+" \n www.aicapital.in ";
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT,"SRUSHTII..");
                intent.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
                startActivity(Intent.createChooser(intent,"Share via"));
            }
        });


        cardSelfPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        showMemberdetails(memberDataRespons);
        getUserDashboardCounts();

        return view;
    }

    private void showMemberdetails(MemberDataResponse memberDataResponse) {

            if (memberDataResponse != null)
            {
                if(memberDataResponse.isStatus())
                {

                        ISACTIVE=true;
                        tvWalletBalance.setText("$"+memberDataResponse.getData().get(0).getAvailBalance());

                }
                else
                {
                    Utility.showRedMessage(getActivity(),memberDataResponse.getMessage());
                }
            }
    }

    private void showAlertPinList(final List<UserDashboardData> dataList)
    {

        tvMonthlyRatio.setText(dataList.get(0).getTotalMonthly()+"%");
        tvTotalGrowth.setText(dataList.get(0).getTotalAverage()+"%");

        tvTodaysProfit.setText("$"+dataList.get(0).getTodaysProfit());
        tvTotalProfit.setText("$"+dataList.get(0).getTotalProfit());

        tvTotalWithdraw.setText("$"+dataList.get(0).getTotalWithdraw());
        tvTotalDeposit.setText("$"+dataList.get(0).getTotalDeposit());



    }

    public void getUserDashboardCounts()
    {

        Utility.showProgressDialog(getActivity());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<UserDashboardDataResponse> call = apiService.getUserDashboardCounts(Pref.getValue(getActivity(),USER_ID,"0"));
        call.enqueue(new Callback<UserDashboardDataResponse>() {
            @Override
            public void onResponse(Call<UserDashboardDataResponse> call, Response<UserDashboardDataResponse> response) {
                UserDashboardDataResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();
                if (hospitalListResponse != null) {
                    if(hospitalListResponse.isStatus())
                    {
                            showAlertPinList(hospitalListResponse.getData());
                    }
                    else{
                        Toast.makeText(getActivity(),hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else
                    Toast.makeText(getActivity(),"error 1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserDashboardDataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(),"error 2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"error 3", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

}