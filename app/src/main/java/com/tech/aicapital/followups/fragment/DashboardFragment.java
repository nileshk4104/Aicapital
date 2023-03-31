package com.tech.aicapital.followups.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.aicapital.ExpandableHeightGridView;
import com.tech.aicapital.R;
import com.tech.aicapital.followups.CustomerListActivity;
import com.tech.aicapital.followups.datalist.DashboardData;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment
{

    //    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
    @BindView(R.id.gridView)
    ExpandableHeightGridView gridView;
    public DashboardFragment()
    {

    }
    @SuppressLint("ValidFragment")
    public DashboardFragment(boolean isAdmin, String status)
    {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.admin_smart_dashboard, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
//    public void getDashboardCounts()
//    {
//        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
//        Call<DashboardCountResponse> call = apiService.getDashboardCounts();
//        call.enqueue(new Callback<DashboardCountResponse>() {
//            @Override
//            public void onResponse(Call<DashboardCountResponse> call, Response<DashboardCountResponse> response) {
//                DashboardCountResponse memberDataResponse2 = response.body();
//
//                if (memberDataResponse2 != null)
//                {
//                    if(memberDataResponse2.isStatus())
//                    {
//
//                        showDashboard(memberDataResponse2.getData());
//                    }
//
//                } else
//                    Toast.makeText(getActivity(),"Some error occur", Toast.LENGTH_SHORT).show();
//            }
//            @Override
//            public void onFailure(Call<DashboardCountResponse> call, Throwable t) {
//
//                if (t instanceof IOException) {
//                    //Add your code for displaying no network connection error
//                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

}