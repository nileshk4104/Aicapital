package com.tech.aicapital.fragment;

import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.Adapter.EarningAdapter;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.bots.adapter.BotAdapter;
import com.tech.aicapital.bots.datalist.BotDataList;
import com.tech.aicapital.bots.datalist.BotDataResponse;
import com.tech.aicapital.datalist.EarningDataList;
import com.tech.aicapital.followups.adapter.TradingDataAdapter;
import com.tech.aicapital.followups.datalist.TradingDataList;
import com.tech.aicapital.followups.datalist.TradingResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptoDashboardFragment extends Fragment {

    @BindView(R.id.card)
    CardView cardView;
    @BindView(R.id.tvAmount)
    TextView tvAmount;

    Handler handler;
    Runnable runnable;
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    public CryptoDashboardFragment() {
    }

    @SuppressLint("ValidFragment")
    public CryptoDashboardFragment(String user_id, String paidstatus) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.crypto_dashboard, container, false);
        ButterKnife.bind(this, view);

        tvAmount.setText("$"+String.format("%.2f", 8.65));
        getMyPassbookList();
//        getTradingList();
        return view;

    }

    private void shineAnimation()
    {
        // attach the animation layout Using AnimationUtils.loadAnimation
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.left_right);
        cardView.startAnimation(anim);
        // override three function There will error
        // line below the object
        // click on it and override three functions
    }
    public void getMyPassbookList() {
        Utility.showProgressDialog(getActivity());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<BotDataResponse> call = apiService.getBotList("1");
        call.enqueue(new Callback<BotDataResponse>() {
            @Override
            public void onResponse(Call<BotDataResponse> call, Response<BotDataResponse> response) {
                BotDataResponse response1 = response.body();
                Utility.hideProgressBar();
                if (response1 != null) {
                    showList(response1.getData());
                } else
                    Toast.makeText(getActivity(), response1.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BotDataResponse> call, Throwable t) {
                if (t instanceof IOException) {

                    //Add your code for displaying no network connection error
//                    responseReceived.onFailure("Please check Network connection ");
                } else {
                    Toast.makeText(getActivity(), "some error  ", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }
    public void getTradingList() {
        Utility.showProgressDialog(getActivity());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<TradingResponse> call = apiService.getTradingList("https://api.coincap.io/v2/assets");
        call.enqueue(new Callback<TradingResponse>() {
            @Override
            public void onResponse(Call<TradingResponse> call, Response<TradingResponse> response) {
                TradingResponse response1 = response.body();
                Utility.hideProgressBar();
                if (response1 != null)
                {
                    showTradingData(response1.getData());
                } else
                    Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TradingResponse> call, Throwable t) {
                if (t instanceof IOException) {

                    //Add your code for displaying no network connection error
//                    responseReceived.onFailure("Please check Network connection ");
                } else {
                    Toast.makeText(getActivity(), "some error  ", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }
    private void showList(List<BotDataList> data) {
        BotAdapter transactionAdapter = new BotAdapter(getActivity(),"", data,true,
                new BotAdapter.OnItemClickListener() {
                    @Override
                    public void onListItemClick(int position) {

                    }

                    @Override
                    public void onUpdateQty(int position, String qty, double intrestrate, int selvalue, int mainDuration, boolean alert) {

                    }
                }, new BotAdapter.OnDoubleClickListener() {
            @Override
            public void onDetailsView(int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(transactionAdapter);
    }
    private void showTradingData(List<TradingDataList> data) {

        TradingDataAdapter transactionAdapter = new TradingDataAdapter(getActivity(), data,
                new TradingDataAdapter.OnItemClickListener()
                {
                    @Override
                    public void onListItemClick(int position) {

                    }
                });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(transactionAdapter);
    }

}