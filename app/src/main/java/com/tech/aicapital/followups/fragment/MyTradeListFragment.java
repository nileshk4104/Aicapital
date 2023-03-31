package com.tech.aicapital.followups.fragment;

import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.Adapter.MyTradeAdapter;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.tech.aicapital.datalist.MyTradeDataList;
import com.tech.aicapital.datalist.MyTradeResponse;
import com.tech.aicapital.followups.FollowUpHistoryActivity;
import com.tech.aicapital.followups.TradeDetailsActivity;
import com.tech.aicapital.followups.adapter.CustomerListAdapter;
import com.tech.aicapital.followups.datalist.CustomerDataList;
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

public class MyTradeListFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rlv)
    RelativeLayout rlv;
    @BindView(R.id.tvTitle2)
    TextView tvTitle2;
    @BindView(R.id.tvTitle4)
    TextView tvTitle4;


    boolean ISACTIVE = false, isContact = true;
    String userId;
    MemberDataResponse memberDataRespons;

    public MyTradeListFragment() {
    }

    @SuppressLint("ValidFragment")
    public MyTradeListFragment(String userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listviews, container, false);
        ButterKnife.bind(this, view);
        getMyCustomers();
        rlv.setVisibility(View.VISIBLE);


        return view;

    }

    private void showAlertPinList(final List<MyTradeDataList> dataList,String todaysProfit) {
        try {
            double tot=0;
            for (int i=0;i<dataList.size();i++)
            {
                tot=tot+Double.parseDouble(dataList.get(i).getPlgain());
            }

            // TOTAL
            Double todays=Double.parseDouble(todaysProfit);
            tvTitle2.setText(String.format("%.2f",tot));
            // TODAYS
            tvTitle4.setText(String.format("%.2f",todays));

            MyTradeAdapter transactionAdapter = new MyTradeAdapter(getActivity(), "", dataList,
            new MyTradeAdapter.OnItemClickListener()
            {
                        @Override
                        public void onListItemClick(int position) {
                            Intent intent=new Intent(getActivity(), TradeDetailsActivity.class);
                            intent.putExtra("NAME",dataList.get(position).getEntityName());
                            intent.putExtra("PROFIT",dataList.get(position).getPlgain());
                            intent.putExtra("OPEN_P",dataList.get(position).getOpenPos());
                            intent.putExtra("CLOSE_P",dataList.get(position).getClosePos());
                            intent.putExtra("START_T",dataList.get(position).getStartTime());
                            intent.putExtra("END_T",dataList.get(position).getEndTime());
                            intent.putExtra("TYPE",dataList.get(position).getTypeId());
                            intent.putExtra("LOT",dataList.get(position).getLotSize());
                            startActivity(intent);


                        }});
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(transactionAdapter);

        } catch (Exception e) {
        }


    }
    public void getMyCustomers() {

        Utility.showProgressDialog(getActivity());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<MyTradeResponse> call = apiService.getMyTrades(Pref.getValue(getActivity(), USER_ID, "0"));
        call.enqueue(new Callback<MyTradeResponse>() {
            @Override
            public void onResponse(Call<MyTradeResponse> call, Response<MyTradeResponse> response) {
                MyTradeResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();
                if (hospitalListResponse != null) {
                    if (hospitalListResponse.isStatus()) {
                        showAlertPinList(hospitalListResponse.getData(),hospitalListResponse.getBaseUrl());
                    } else {
                        Toast.makeText(getActivity(), hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                } else
                    Toast.makeText(getActivity(), "error 1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MyTradeResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(), "error 2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "error 3", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}