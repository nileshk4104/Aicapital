package com.tech.aicapital.fragment;

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

import com.tech.aicapital.Adapter.ArticleAdapter;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.activities.ArticleDetailsActivity;
import com.tech.aicapital.datalist.ArticleDataList;
import com.tech.aicapital.datalist.ArticleDataResponse;
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

import static com.tech.aicapital.mvps.Constant.USER_ID;

public class ArticlesFragments extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ivImageNodata)
    GifImageView ivImageNodata;


    String typeId;

    public ArticlesFragments() {
    }

    @SuppressLint("ValidFragment")
    public ArticlesFragments(String typeId, String typeName) {
        this.typeId=typeId;

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

//        ((BusinessDashboardActivity)getActivity()).pageTitle("MY CATALOGUE");


        if(Utility.isConnectedToInternet(getActivity())){
            getArticles(typeId);
        }
        else {
            ivImageNodata.setVisibility(View.VISIBLE);
        }

        return view;

    }

    public void getArticles(String userId)
    {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<ArticleDataResponse> call = apiService.getArticles(userId);
        call.enqueue(new Callback<ArticleDataResponse>() {
            @Override
            public void onResponse(Call<ArticleDataResponse> call, Response<ArticleDataResponse> response) {
                ArticleDataResponse hospitalListResponse = response.body();
                if (hospitalListResponse != null)
                {
                    if(hospitalListResponse.getData().size()>0)
                    {
                        onSuccessShopList(hospitalListResponse.getData(),hospitalListResponse.getBaseUrl());
                    }

                    else {
                        ivImageNodata.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    ivImageNodata.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(),"UNKNOWN RESPONSE", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ArticleDataResponse> call, Throwable t) {
                ivImageNodata.setVisibility(View.VISIBLE);
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onSuccessShopList(final List<ArticleDataList> couponDataLists, String baseurl)
    {



        ArticleAdapter transactionAdapter = new ArticleAdapter(getActivity(), baseurl,couponDataLists,true,
                new ArticleAdapter.OnItemClickListener() {
                    @Override
                    public void onListItemClick(int position)
                    {
                        Intent intent=new Intent(getActivity(), ArticleDetailsActivity.class);
                        intent.putExtra("title",couponDataLists.get(position).getTitle());
                        intent.putExtra("image",couponDataLists.get(position).getImage());
                        intent.putExtra("desc",couponDataLists.get(position).getDescription());
                        intent.putExtra("full",couponDataLists.get(position).getFulldesc());
                        startActivity(intent);
                    }
                    @Override
                    public void onUpdateQty(int position, String qty, double intrestrate,int selvalue, int mainDuration, boolean alert)
                    {

                    }
                }, new ArticleAdapter.OnDoubleClickListener() {
            @Override
            public void onDetailsView(int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(transactionAdapter);

    }


}