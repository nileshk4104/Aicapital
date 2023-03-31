package com.tech.aicapital.followups.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.tech.aicapital.Adapter.ArticleAdapter;
import com.tech.aicapital.Adapter.CategoryAdapter;
import com.tech.aicapital.ExpandableHeightGridView;
import com.tech.aicapital.R;
import com.tech.aicapital.activities.ArticleDetailsActivity;
import com.tech.aicapital.datalist.ArticleDataList;
import com.tech.aicapital.datalist.ArticleDataResponse;
import com.tech.aicapital.datalist.BannerDataList;
import com.tech.aicapital.datalist.BannerDataResponse;
import com.tech.aicapital.datalist.CategoryDataList;
import com.tech.aicapital.datalist.CategoryDataResponse;
import com.tech.aicapital.followups.TradeDetailsActivity;
import com.tech.aicapital.followups.adapter.CategoryNewAdapter;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainHomePageFragment extends Fragment implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener,CategoryNewAdapter.OnItemClickListener {


    @BindView(R.id.slider)
    SliderLayout sliderLayout;


    @BindView(R.id.gridView)
    ExpandableHeightGridView gridView;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    HashMap<String, String> Hash_file_maps;
    private Context mContext;
    private List<CategoryDataList> categoryDataLists = new ArrayList<>();


    public MainHomePageFragment() {
    }

    @SuppressLint("ValidFragment")
    public MainHomePageFragment(String id) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_homepage_frag, container, false);
        ButterKnife.bind(this, view);
//        mContext = requireActivity();
//        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

//        getBannerList();





//        rvNewlyPublish.set

        return view;

    }

    @Override
    public void onResume() {
        getBannerList();
        getAllTypes();
        getArticles();
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }

    public void getBannerList() {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<BannerDataResponse> call = apiService.getBannerList();
        call.enqueue(new Callback<BannerDataResponse>() {
            @Override
            public void onResponse(Call<BannerDataResponse> call, Response<BannerDataResponse> response) {
                BannerDataResponse bannerDataResponse = response.body();
                if (bannerDataResponse != null) {
                    bannerList(bannerDataResponse.getData(), bannerDataResponse.getBaseUrl());

                } else
                    Toast.makeText(getActivity(), "UNKNOWN RESPONSE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BannerDataResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
//                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void bannerList(List<BannerDataList> menuListData, String baseUrl) {
        Hash_file_maps = new HashMap<>();
        for (int i = 0; i < menuListData.size(); i++) {
            Hash_file_maps.put(menuListData.get(i).getNannerName(), baseUrl + menuListData.get(i).getBannerImage());
        }
        for (String name : Hash_file_maps.keySet()) {
            DefaultSliderView textSliderView = new DefaultSliderView(getContext());
            textSliderView.description(name).image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(5000);

        sliderLayout.addOnPageChangeListener(this);

    }



    public void getAllTypes() {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<CategoryDataResponse> call = apiService.getAllTypes();
        call.enqueue(new Callback<CategoryDataResponse>() {
            @Override
            public void onResponse(Call<CategoryDataResponse> call, Response<CategoryDataResponse> response) {
                CategoryDataResponse categoryDataResponse = response.body();
                try {
                    if (categoryDataResponse != null) {
                        gridTypesList(categoryDataResponse.getData(),categoryDataResponse.getBaseUrl());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CategoryDataResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
//                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private void gridTypesList(final List<CategoryDataList> menuListData, String baseUrl)
    {
//        categoryDataLists=menuListData;

        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(),
                R.layout.row_category_data_list, baseUrl,menuListData,
                new CategoryAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(int position)
                    {



                    }
                });
        gridView.setAdapter(categoryAdapter);
        gridView.setExpanded(true);
        gridView.setNumColumns(2);

    }


    private void showCategories(CategoryDataResponse categoryDataResponse) {

//        categoryDataLists.clear();
//        categoryDataLists.addAll(categoryDataResponse.getData());
//        CategoryNewAdapter categoryAdapter = new CategoryNewAdapter(mContext,
//                categoryDataResponse.getBaseUrl(), categoryDataLists, this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
//                LinearLayoutManager.HORIZONTAL, false);
//        mrecyclerView.setLayoutManager(layoutManager);
//        mrecyclerView.setAdapter(categoryAdapter);


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

//        Toast.makeText(getActivity(),slider.getUrl(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onCategoryClickListener(int position) {
        CategoryDataList categoryDataList = categoryDataLists.get(position);
        Intent intent = new Intent(getActivity(), TradeDetailsActivity.class);
//        intent.putExtra("category_id", categoryDataList.categoryId);
        startActivity(intent);
    }


    public void getArticles()
    {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<ArticleDataResponse> call = apiService.getArticles("5");
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


                }
                else{

                    Toast.makeText(getActivity(),"UNKNOWN RESPONSE", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ArticleDataResponse> call, Throwable t) {

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
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(transactionAdapter);

    }


}