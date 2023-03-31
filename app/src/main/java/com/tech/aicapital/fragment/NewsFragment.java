package com.tech.aicapital.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.tech.aicapital.R;

import butterknife.ButterKnife;

public class NewsFragment extends Fragment {


    public NewsFragment() {
    }

    @SuppressLint("ValidFragment")
    public NewsFragment(String user_id, String paidstatus) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepage, container, false);
        ButterKnife.bind(this, view);

//        sharedPreferences = getActivity().getSharedPreferences("Reg", 0);
//        mobile = sharedPreferences.getString("mobile", "");
//        user_id = sharedPreferences.getString("user_id", "");
        alertDialog();
        return view;

    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.winning_page, null);
        final AlertDialog dialogu = builder.create();
        dialogu.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogu.setView(dialogLayout, 0, 0, 0, 0);
        dialogu.setCanceledOnTouchOutside(true);
        dialogu.setCancelable(true);
        WindowManager.LayoutParams wlmp = dialogu.getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER;


        builder.setView(dialogLayout);
        dialogu.show();
    }
}

