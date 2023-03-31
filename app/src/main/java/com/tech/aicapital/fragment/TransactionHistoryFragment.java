package com.tech.aicapital.fragment;

import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.Adapter.TransactionAdapter;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.TransactionDatalist;
import com.tech.aicapital.datalist.TransactionHistoryResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionHistoryFragment extends Fragment {

    String member_id;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.ivImageNodata)
    GifImageView ivImageNodata;

    String type_id;

    public TransactionHistoryFragment() {
    }

    @SuppressLint("ValidFragment")
    public TransactionHistoryFragment(String member_id, String type_id) {
        this.member_id = member_id;
        this.type_id = type_id;

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

        sharedPreferences = getActivity().getSharedPreferences("Reg", 0);
        member_id = sharedPreferences.getString("user_id", "");


        if(Utility.isConnectedToInternet(getActivity()))
        {
            getMyTransactionHistory(Pref.getValue(getActivity(),USER_ID,"0"));
        }
        else {
            ivImageNodata.setVisibility(View.VISIBLE);
        }

        return view;

    }

    public void getMyTransactionHistory(String user_id)
    {
        Utility.showProgressDialog(getActivity());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<TransactionHistoryResponse> call = apiService.getMyTransactionHistory(user_id,type_id);
        call.enqueue(new Callback<TransactionHistoryResponse>() {
            @Override
            public void onResponse(Call<TransactionHistoryResponse> call, Response<TransactionHistoryResponse> response) {
                TransactionHistoryResponse hospitalListResponse = response.body();
                Utility.hideProgressBar();


                if (hospitalListResponse != null) {
                    if(hospitalListResponse.isStatus()){
                        gridProductList(hospitalListResponse.getData());
                    }
                    else{
                        ivImageNodata.setVisibility(View.VISIBLE);
                    }

                } else
                    Toast.makeText(getActivity(),"error 0", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<TransactionHistoryResponse> call, Throwable t) {
                ivImageNodata.setVisibility(View.VISIBLE);
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private void gridProductList(final List<TransactionDatalist> menuListData)
    {
        TransactionAdapter transactionAdapter=new TransactionAdapter(getActivity(), menuListData,
                new TransactionAdapter.OnItemClickListener() {
                    @Override
                    public void onListItemClick(int position) {
                    }
                    @Override
                    public void onUpdateQty(int position, String qty,
                                            double intrestrate,
                                            int selvalue,int mainDuration, boolean alert)
                    {

                    }
                },new TransactionAdapter.OnDoubleClickListener() {
            @Override
            public void onDetailsView(int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(transactionAdapter);

    }

}