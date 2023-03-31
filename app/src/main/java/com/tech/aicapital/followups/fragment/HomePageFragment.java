package com.tech.aicapital.followups.fragment;

import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tech.aicapital.Adapter.ViewPagerAdapter;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.CategoryDataList;
import com.tech.aicapital.datalist.MemberDataResponse;
import com.tech.aicapital.followups.FollowUpHistoryActivity;
import com.tech.aicapital.followups.adapter.CustomerListAdapter;
import com.tech.aicapital.followups.datalist.CustomerDataList;
import com.tech.aicapital.followups.datalist.CustomerDataResponse;
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

public class HomePageFragment extends Fragment {

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    int position;
    String userId;
    MemberDataResponse memberDataRespons;

    public HomePageFragment() {
    }

    @SuppressLint("ValidFragment")
    public HomePageFragment(String userId) {
        this.userId = userId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage_layout, container, false);
        ButterKnife.bind(this, view);
        viewPagerMobile();

        return view;

    }

    private void viewPagerMobile() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());

                adapter.addFrag(new CustomerListFragment(""),"Active");
                adapter.addFrag(new CustomerListFragment(""),"In Active");
                adapter.addFrag(new CustomerListFragment(""),"Negative ");
//                adapter.addFrag(new CustomerListFragment(""),"Waiting ");

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(position, true);

    }



}