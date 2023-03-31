package com.tech.aicapital.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tech.aicapital.Adapter.ViewPagerAdapter;
import com.tech.aicapital.ExpandableHeightGridView;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.CategoryDataList;
import com.tech.aicapital.datalist.CategoryDataResponse;
import com.tech.aicapital.datalist.ProductDataList;
import com.tech.aicapital.fragment.ArticlesFragments;
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

public class AllArticlesActivity extends AppCompatActivity {
    @BindView(R.id.gridView2)
    ExpandableHeightGridView gridView2;
    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    int position;
    String categoryid, shop_id,shop_name; boolean isAdmin;

    List<ProductDataList> menuItemListDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_base_activity);
        ButterKnife.bind(this);

//        Intent intent=getIntent();
//        categoryid=intent.getStringExtra("category_id");
//        shop_id=intent.getStringExtra("shop_id");
//        isAdmin=intent.getBooleanExtra("isAdmin",false);
//        shop_name=intent.getStringExtra("shop_name");

        Utility.pageSetupBackButton(AllArticlesActivity.this,"Services");
        getArticleTypes();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }

    public void getArticleTypes() {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<CategoryDataResponse> call = apiService.getArticleTypes();

        call.enqueue(new Callback<CategoryDataResponse>() {
            @Override
            public void onResponse(Call<CategoryDataResponse> call, Response<CategoryDataResponse> response) {
                try {
                    CategoryDataResponse hospitalListResponse = response.body();
                    if (hospitalListResponse != null) {

                        viewPagerMobile(hospitalListResponse.getData(), hospitalListResponse.getBaseUrl());
                    } else
                        Toast.makeText(getApplicationContext(), "Api Response Error  ", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<CategoryDataResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(), "No Internet connection or poor network ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Server un available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void viewPagerMobile(final List<CategoryDataList> menuListData, String baseUrl) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (menuListData.size() > 0)
        {
            for (int i = 0; i < menuListData.size(); i++)
            {
                adapter.addFrag(new ArticlesFragments(menuListData.get(i).getCategoryId(),menuListData.get(i).getIsSubcat()), menuListData.get(i).getCategoryName());
            }

        }
//        adapter.addFrag(new ShopFragment(shop_id,categoryid,"",true),"Shop Information ");
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(position, true);

    }
}
