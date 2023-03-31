package com.tech.aicapital.fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import com.tech.aicapital.Adapter.ScratchAdapter;
import com.tech.aicapital.ExpandableHeightGridView;
import com.tech.aicapital.Pref;
import com.tech.aicapital.R;
import com.tech.aicapital.datalist.NewResponse;
import com.tech.aicapital.datalist.ScratchDataList;
import com.tech.aicapital.datalist.ScratchListResponse;
import com.tech.aicapital.mvps.ApiClient;
import com.tech.aicapital.mvps.NetworkCaller;
import com.tech.aicapital.mvps.Utility;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import in.myinnos.androidscratchcard.ScratchCard;
import pl.droidsonroids.gif.GifImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tech.aicapital.mvps.Constant.USER_ID;

public class MyRewardFragment extends Fragment {

    @BindView(R.id.gridView2)
    ExpandableHeightGridView gridView2;
    @BindView(R.id.tvTotalSrt)
    TextView tvTotalSrt;



    public MyRewardFragment() {
    }

    @SuppressLint("ValidFragment")
    public MyRewardFragment(String memberId, String paidstatus) {

    }



//
    @BindView(R.id.tvTotal)
    TextView tvTotal;

    String user_id;
    String mobile;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.scratch_fragmnt, container, false);
        ButterKnife.bind(this, view);

        sharedPreferences = getActivity().getSharedPreferences("Reg", 0);
        mobile = sharedPreferences.getString("mobile", "");
        user_id = sharedPreferences.getString("user_id","");


        if(Utility.isConnectedToInternet(getActivity()))
        {
            getMyScratchcard();
        }

        return view;

    }

    public void getMyScratchcard()
    {
        Utility.showProgressDialog(getActivity());
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);

        Call<ScratchListResponse> call = apiService.getMyScratchcard(Pref.getValue(getActivity(),USER_ID,"0"));

        call.enqueue(new Callback<ScratchListResponse>() {
            @Override
            public void onResponse(Call<ScratchListResponse> call, Response<ScratchListResponse> response) {
                Utility.hideProgressBar();
                ScratchListResponse hospitalListResponse = response.body();
                if (hospitalListResponse != null) {
                    gridCategoryList(hospitalListResponse.getData());
                } else
                    Toast.makeText(getActivity(),"UNKNOWN RESPONSE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ScratchListResponse> call, Throwable t) {
                Utility.hideProgressBar();

                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void updateScratchCard(String scratchid,String amountrewarded,String unit)
    {
        final NetworkCaller apiService = ApiClient.getClient8().create(NetworkCaller.class);

        Call<NewResponse> call = apiService.updateScratchCard(Pref.getValue(getActivity(),USER_ID,"0"),scratchid,amountrewarded,unit);

        call.enqueue(new Callback<NewResponse>() {
            @Override
            public void onResponse(Call<NewResponse> call, Response<NewResponse> response) {
                NewResponse hospitalListResponse = response.body();
                if (hospitalListResponse != null) {
                    Toast.makeText(getActivity(),hospitalListResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NewResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    //Add your code for displaying no network connection error
                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"error", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void gridCategoryList(final List<ScratchDataList> menuListData)
    {
        Double tot=0.0,val=0.0;
        Double tot2=0.0,val2=0.0;
        for(int i=0;i<menuListData.size();i++)
        {
          if(menuListData.get(i).getStatus().equalsIgnoreCase("1"))
          {
              if(menuListData.get(i).getUnit().equalsIgnoreCase("RS"))
              {
                  val=Double.parseDouble(menuListData.get(i).getScratchWorth());
                  tot=tot+val;
              }
          else{
                  val2=Double.parseDouble(menuListData.get(i).getScratchWorth());
                  tot2=tot2+val2;
              }
          }
        }

        tvTotal.setText(String.format("%.2f",tot));
        tvTotalSrt.setText(String.format("%.2f",tot2));
        ScratchAdapter categoryAdapter = new ScratchAdapter(getActivity(), R.layout.row_scratch, "",menuListData,
        new ScratchAdapter.OnItemClickListener()
        {
                    @Override
                    public void OnItemClickListener(int position,boolean isScratchable)
                    {
                        if(isScratchable)
                        {
                            alertDialog(menuListData.get(position).getScratchId(),menuListData.get(position).getScratchWorth(),
                                    menuListData.get(position).getUnit());
                        }
                    else{
                            alertDialog(menuListData.get(position).getScratchId(),menuListData.get(position).getScratchWorth(),
                                    menuListData.get(position).getUnit());
                        }
                    }
        });
        gridView2.setAdapter(categoryAdapter);
        gridView2.setExpanded(true);

    }

    private void alertDialog(final String scratchid, final String amt,final String unit)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.alert_scatchcard,null);
        final AlertDialog dialogu = builder.create();
        dialogu.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialogu.setView(dialogLayout, 0, 0, 0, 0);
        dialogu.setCanceledOnTouchOutside(false);
        dialogu.setCancelable(true);
        WindowManager.LayoutParams wlmp = dialogu.getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER;
        final TextView textView = (TextView) dialogLayout.findViewById(R.id.textView);
        final ScratchCard mScratchCard = (ScratchCard) dialogLayout.findViewById(R.id.scratchCard);
        final GifImageView giFwin = (GifImageView) dialogLayout.findViewById(R.id.giFwin);
        final GifImageView reward = (GifImageView) dialogLayout.findViewById(R.id.reward);
        final ImageView rewardCoin = (ImageView) dialogLayout.findViewById(R.id.rewardCoin);
        final CircleImageView ivCancel = (CircleImageView) dialogLayout.findViewById(R.id.ivCancel);

        if(unit.equalsIgnoreCase("RS"))
        {
            reward.setVisibility(View.VISIBLE);
            rewardCoin.setVisibility(View.GONE);
        }
        else{
            reward.setVisibility(View.GONE);
            rewardCoin.setVisibility(View.VISIBLE);
//            ObjectAnimator animation = ObjectAnimator.ofFloat(rewardCoin, "rotationY", 0.0f, 360f);
//            // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
//            animation.setDuration(3000);
//            // HERE 500 IS THE DURATION OF THE ANIMATION,
//            // YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
//            animation.setInterpolator(new AccelerateDecelerateInterpolator());
//            animation.setRepeatCount(100);
//            animation.setInterpolator(new LinearInterpolator());
//            animation.start();
//            textView.setText("You won"+" \n"+amt+" "+unit);
        }

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogu.dismiss();
            }
        });

        mScratchCard.setOnScratchListener(new ScratchCard.OnScratchListener() {
            @Override
            public void onScratch(ScratchCard scratchCard, float visiblePercent) {
                if (visiblePercent > 0.5)
                {
                    mScratchCard.setVisibility(View.GONE);

                    if(unit.equalsIgnoreCase("RS"))
                    {
                        textView.setText(getResources().getString(R.string.Rs)+" "+amt);
                    }
                    else{
                        textView.setText("sR "+amt);
                    }

                    updateScratchCard(scratchid,amt,unit);
                    dialogu.setCancelable(true);
                    dialogu.setCanceledOnTouchOutside(true);
                    getMyScratchcard();
                    ivCancel.setVisibility(View.GONE);

                }
            }
        });

        builder.setView(dialogLayout);
        dialogu.show();
    }


}
