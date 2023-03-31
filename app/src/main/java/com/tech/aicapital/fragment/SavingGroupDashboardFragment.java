package com.tech.aicapital.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tech.aicapital.R;

import butterknife.ButterKnife;

public class SavingGroupDashboardFragment extends Fragment {
    public SavingGroupDashboardFragment() {
    }
    @SuppressLint("ValidFragment")
    public SavingGroupDashboardFragment(String user_id, String paidstatus) {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.homepage, container, false);
        ButterKnife.bind(this, view);
        return view;

    }
}