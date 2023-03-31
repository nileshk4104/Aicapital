package com.tech.aicapital.activities;


import static com.tech.aicapital.mvps.Constant.USER_ID;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tech.aicapital.Adapter.ViewPagerAdapter;
import com.tech.aicapital.ExpandableHeightGridView;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.ProductDataList;
import com.tech.aicapital.datalist.TransTypeDatalist;
import com.tech.aicapital.datalist.TransTypeResponse;
import com.tech.aicapital.fragment.TransactionHistoryFragment;
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

public class TypewiseTransactionHistoryActivity extends AppCompatActivity {
//    GridProductAdapter gridProductAdapter;
//    @BindView(R.id.gridView2)

    @BindView(R.id.tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    int position;
    String categoryid, user_id,shop_id,shop_name; boolean isAdmin;

    List<ProductDataList> menuItemListDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_base_activity);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        isAdmin=intent.getBooleanExtra("isAdmin",false);

        if(isAdmin)
        {
            user_id=intent.getStringExtra("user_id");

        }
        else{
            user_id= Pref.getValue(TypewiseTransactionHistoryActivity.this,USER_ID,"");
        }

        Utility.pageSetupBackButton(TypewiseTransactionHistoryActivity.this,"All TRANSACTIONS");
        getTransTypes();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();

    }

    public void getTransTypes() {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<TransTypeResponse> call = apiService.getTransTypes();

        call.enqueue(new Callback<TransTypeResponse>() {
            @Override
            public void onResponse(Call<TransTypeResponse> call, Response<TransTypeResponse> response) {
                try {
                    TransTypeResponse hospitalListResponse = response.body();
                    if (hospitalListResponse != null) {

                        viewPagerMobile(hospitalListResponse.getData(), hospitalListResponse.getMessage());
                    } else
                        Toast.makeText(getApplicationContext(), "Api Response Error  ", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                }
            }
            @Override
            public void onFailure(Call<TransTypeResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getApplicationContext(), "No Internet connection or poor network ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Server un available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void viewPagerMobile(final List<TransTypeDatalist> menuListData, String baseUrl) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (menuListData.size() > 0)
        {
            for (int i = 0; i < menuListData.size(); i++)
            {
                adapter.addFrag(new TransactionHistoryFragment(user_id,menuListData.get(i).getTypeId()), menuListData.get(i).getTypeNname());
            }

        }
//        adapter.addFrag(new ShopFragment(shop_id,categoryid,"",true),"Shop Information ");
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setCurrentItem(position, true);

    }
}
