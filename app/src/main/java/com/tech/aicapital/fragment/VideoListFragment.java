package com.tech.aicapital.fragment;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.Adapter.VideoListAdapter;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.VideoDataList;
import com.tech.aicapital.datalist.VideoDataListResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;
import com.tech.aicapital.ytb.YoutubePlayActivity;
import com.tech.aicapital.ytb.YoutubeRecyclerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.aicapital.mvps.Constant.USER_ID;

public class VideoListFragment extends Fragment{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ivImageNodata)
    GifImageView ivImageNodata;

    boolean ISAPP=false;
        public VideoListFragment() {
        }

        @SuppressLint("ValidFragment")
        public VideoListFragment(boolean ISAPP)
        {

            this.ISAPP=ISAPP;
        }
        @Override
        public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.listviews, container, false);
            ButterKnife.bind(this, view);

            if(Utility.isConnectedToInternet(getActivity()))
            {
                getVideoList();
            }
            else {
                ivImageNodata.setVisibility(View.VISIBLE);
            }

            return view;


}


    public void getVideoList()
    {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);
        Call<VideoDataListResponse> call;
        if(ISAPP){
            call = apiService.getYouTubeList("1");
        }
        else{
        call = apiService.getYouTubeList("0");
        }


        call.enqueue(new Callback<VideoDataListResponse>() {
            @Override
            public void onResponse(Call<VideoDataListResponse> call, Response<VideoDataListResponse> response) {
                VideoDataListResponse newResponse = response.body();
                if (newResponse != null)
                {
                    if (newResponse.isStatus()){
                        onSuccessVideoList(newResponse.getData(),newResponse.getBaseUrl());
                        ivImageNodata.setVisibility(View.GONE);
                    }else{
                        ivImageNodata.setVisibility(View.VISIBLE);
                    }

                }
                else
                    Toast.makeText(getActivity(),"Unknown Response",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<VideoDataListResponse> call, Throwable t) {
                ivImageNodata.setVisibility(View.VISIBLE);
                if (t instanceof IOException) {

                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void onSuccessVideoList(final List<VideoDataList> data, String baseurl)
    {
//        VideoListAdapter parlimentListAdapter=new VideoListAdapter(getActivity(), baseurl,data,
//                new VideoListAdapter.OnItemClickListener()
//                {
//                    @Override
//                    public void onListItemClick(int position)
//                    {
////                        video_id=data.get(position).getVideoId();
//                        Intent intent=new Intent(getActivity(), YoutubePlayActivity.class);
//                        intent.putExtra("video_id",data.get(position).getYoutubeId());
//                        intent.putExtra("path",data.get(position).getVideoImage());
//                        startActivity(intent);
//                    }
//                    @Override
//                    public void onUpdateQty(int position, boolean isSubscribe)
//                    {
////                        video_id=data.get(position).getVideoId();
//                        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + data.get(position).getYoutubeId()));
//                        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + data.get(position).getYoutubeId()));
//                        try
//                        {
//                            startActivity(appIntent);
//                        } catch (ActivityNotFoundException ex)
//                        {
//                            startActivity(webIntent);
//                        }
//
//                    }
//                },new VideoListAdapter.OnDoubleClickListener() {
//            @Override
//            public void onDetailsView(int position) {
//            }
//        });
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        mrecyclerView.setLayoutManager(mLayoutManager);
//        mrecyclerView.setAdapter(parlimentListAdapter);


        if(ISAPP){
            VideoListAdapter parlimentListAdapter=new VideoListAdapter(getActivity(), baseurl,data,
                    new VideoListAdapter.OnItemClickListener() {
                        @Override
                        public void onListItemClick(int position)
                        {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(data.get(position).getLinkurl()));
                            startActivity(intent);
                        }
                        @Override
                        public void onUpdateQty(int position, boolean isSubscribe)
                        {

                        }
                    },new VideoListAdapter.OnDoubleClickListener() {
                @Override
                public void onDetailsView(int position) {
                }
            });

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(parlimentListAdapter);
        }
        else{
            YoutubeRecyclerAdapter mRecyclerAdapter = new YoutubeRecyclerAdapter(data);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mRecyclerAdapter);
        }




    }

}
