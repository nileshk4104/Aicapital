package com.tech.aicapital.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.tech.aicapital.Adapter.CategoryAdapter;
import com.tech.aicapital.Adapter.TreeDataAdapter;
import com.tech.aicapital.ExpandableHeightGridView;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.BannerDataList;
import com.tech.aicapital.datalist.BannerDataResponse;
import com.tech.aicapital.datalist.CategoryDataList;
import com.tech.aicapital.datalist.TreeDataList;
import com.tech.aicapital.datalist.TreeDataResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    HashMap<String, String> Hash_file_maps;

    @BindView(R.id.slider)
    SliderLayout sliderLayout;
    @BindView(R.id.tvAddShop)
    TextView tvAddShop;

    @BindView(R.id.gridView2)
    ExpandableHeightGridView gridView2;

    List<TreeDataList> dataLists;

    VideoView video;
    String video_url = "https://www.videvo.net/videvo_files/converted/2015_08/preview/Ringtailed_Lemur.mp430017.webm";
    ProgressDialog pd;
    public HomeFragment() {
    }

    @SuppressLint("ValidFragment")
    public HomeFragment(String user_id, String paidstatus) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);
        ButterKnife.bind(this, view);
        getBannerList();



        return view;
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
        gridView2.setAdapter(categoryAdapter);
        gridView2.setExpanded(true);

    }



    public void getBannerList()
    {
        Utility.showProgressDialog(getActivity());
        final NetworkCaller apiService = ApiClient.getClient7().create(NetworkCaller.class);
        Call<BannerDataResponse> call = apiService.getBannerList();
        call.enqueue(new Callback<BannerDataResponse>() {
            @Override
            public void onResponse(Call<BannerDataResponse> call, Response<BannerDataResponse> response) {
                BannerDataResponse bannerDataResponse = response.body();
                Utility.hideProgressBar();

                List<BannerDataList> aa=bannerDataResponse.getData();

                if (bannerDataResponse != null) {
                    bannerList(bannerDataResponse.getData(),bannerDataResponse.getBaseUrl());
                } else
                    Toast.makeText(getActivity(),"Some error occur",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BannerDataResponse> call, Throwable t) {
                Utility.hideProgressBar();
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(),"Some error occur 3",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Some error occur 2",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void bannerList(List<BannerDataList> menuListData,String baseurl)
    {
        Hash_file_maps = new HashMap<>();

        for(int i=0;i<menuListData.size();i++)
        {
            Hash_file_maps.put(menuListData.get(i).getNannerName(),
                    baseurl+menuListData.get(i).getBannerImage());

        }
        for(String name : Hash_file_maps.keySet())
        {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.description(name).image(Hash_file_maps.get(name)).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);
            sliderLayout.addSlider(textSliderView);
//            sliderLayout2.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(5000);
        sliderLayout.addOnPageChangeListener(this);

    }


//    public void gridCategoryList()
//    {
//        Utility.showProgressDialog(getActivity());
//        final NetworkCaller apiService = ApiClient.getClient().create(NetworkCaller.class);
//
//        Call<TreeDataResponse> call = apiService.getCategorylist();
//
//        call.enqueue(new Callback<TreeDataResponse>() {
//            @Override
//            public void onResponse(Call<TreeDataResponse> call, Response<TreeDataResponse> response) {
//                TreeDataResponse hospitalListResponse = response.body();
//                Utility.hideProgressBar();
//                try{
//                    if (hospitalListResponse != null) {
////                        gridCategoryList(hospitalListResponse.getData(),hospitalListResponse.getBaseUrl());
//                    } else
//                        Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
//
//                }catch (Exception e){
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TreeDataResponse> call, Throwable t) {
//                Utility.hideProgressBar();
//                if (t instanceof IOException) {
//                    //Add your code for displaying no network connection error
//                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//
//    }

    private void gridCategoryList(final List<TreeDataList> menuListData,String baseUrl)
    {
        TreeDataAdapter categoryAdapter = new TreeDataAdapter(getActivity(),
                R.layout.row_category_data_list, baseUrl,menuListData,
                new TreeDataAdapter.OnItemClickListener() {
                    @Override
                    public void OnItemClickListener(int position)
                    {


                    }
                });
        gridView2.setAdapter(categoryAdapter);
        gridView2.setExpanded(true);

    }



    @Override
    public void onSliderClick(BaseSliderView slider) {

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
}