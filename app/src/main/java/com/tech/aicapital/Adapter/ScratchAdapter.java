package com.tech.aicapital.Adapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.tech.aicapital.R;
import com.tech.aicapital.datalist.ScratchDataList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.myinnos.androidscratchcard.ScratchCard;
import pl.droidsonroids.gif.GifImageView;

public class ScratchAdapter extends ArrayAdapter<ScratchDataList> {
    private Context mContext;
    private int layoutResourceId;
    private List<ScratchDataList> mGridData;
    private ScratchAdapter.OnItemClickListener listener;
    String baseurl;

    public ScratchAdapter(Context mContext, int layoutResourceId,String baseurl,
                           List<ScratchDataList> mGridData, ScratchAdapter.OnItemClickListener listener)
    {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
        this.listener=listener;
        this.baseurl=baseurl;
    }
    /**
     * Updates grid data and refresh grid items.
     *
     * @param mGridData
     */
    public void setGridData(ArrayList<ScratchDataList> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ScratchAdapter.ViewHolder holder;
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);
        holder = new ScratchAdapter.ViewHolder();
        final ScratchDataList item = mGridData.get(position);

        holder.cardView = (CardView) row.findViewById(R.id.cardView);
        holder.scratchCard = (ScratchCard) row.findViewById(R.id.scratchCard);
        holder.reward = (GifImageView) row.findViewById(R.id.reward);
        holder.relScratchCard = (RelativeLayout) row.findViewById(R.id.relScratchCard);
        holder.textView = (TextView) row.findViewById(R.id.textView);
        holder.rewardCoin = (ImageView) row.findViewById(R.id.rewardCoin);



        holder.relScratchCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(item.getStatus().equalsIgnoreCase("0"))
                {
                    listener.OnItemClickListener(position,true);
                }
            else{

                }
            }
        });

        if(item.getStatus().equalsIgnoreCase("0"))
        {
            holder.relScratchCard.setBackgroundResource(R.drawable.scratch);
            holder.textView.setVisibility(View.VISIBLE);
            holder.reward.setVisibility(View.GONE);
            holder.textView.setText("Opens On \n"+item.getUpdatedDate());
            holder.textView.setTextColor(Color.parseColor("#AAFFCCCC"));
            holder.rewardCoin.setVisibility(View.GONE);
        }
        else{
            holder.relScratchCard.setBackgroundResource(R.color.white);

            holder.textView.setVisibility(View.VISIBLE);
            holder.textView.setTextColor(Color.parseColor("#000000"));
                if(item.getUnit().equalsIgnoreCase("RS"))
                {
                    holder.reward.setVisibility(View.VISIBLE);
                    holder.rewardCoin.setVisibility(View.GONE);
                    holder.textView.setText("You won"+" \n"+getContext().getResources().getString(R.string.Rs)+" "+item.getScratchWorth());
                }
            else{
                    holder.reward.setVisibility(View.GONE);
                    holder.rewardCoin.setVisibility(View.VISIBLE);
                    ObjectAnimator animation = ObjectAnimator.ofFloat(holder.rewardCoin, "rotationY", 0.0f, 360f);
                    // HERE 360 IS THE ANGLE OF ROTATE, YOU CAN USE 90, 180 IN PLACE OF IT,  ACCORDING TO YOURS REQUIREMENT
                    animation.setDuration(3000);
                    // HERE 500 IS THE DURATION OF THE ANIMATION,
                    // YOU CAN INCREASE OR DECREASE ACCORDING TO YOURS REQUIREMENT
                    animation.setInterpolator(new AccelerateDecelerateInterpolator());
                    animation.setRepeatCount(100);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.start();
                    holder.textView.setText("You won"+" \n"+item.getScratchWorth()+" "+item.getUnit());
                }

        }

        row.setTag(holder);
        return row;
    }
    public interface OnItemClickListener {
        void OnItemClickListener(int position,boolean isScratchable);
    }
    static class ViewHolder {
        ScratchCard scratchCard;
        TextView textView;
        RelativeLayout relScratchCard;
        CardView cardView;
        ImageView rewardCoin;
        GifImageView reward;

    }

}
