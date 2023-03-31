package com.tech.aicapital.bots.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.tech.aicapital.R;
import com.tech.aicapital.bots.datalist.BotDataList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BotAdapter extends
        RecyclerView.Adapter<BotAdapter.MyViewHolder> {
    Context context;
    private List<BotDataList> mDataList;
    private BotAdapter.OnItemClickListener listener;
    private BotAdapter.OnDoubleClickListener listener2;
    int lastPosition = -1;
    String baseurl;
    int mainDuration,mainAmounnt=1;
    boolean isImage;
    public BotAdapter(Context context, String baseurl, List<BotDataList> resourceList,boolean isImage,
                          BotAdapter.OnItemClickListener listener,
                          BotAdapter.OnDoubleClickListener listener2)
    {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.baseurl = baseurl;this.isImage = isImage;
        this.listener2 = listener2;
    }
    @Override
    public BotAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bot_list, parent, false);
        return new BotAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final BotAdapter.MyViewHolder holder, final int position)
    {
        final BotDataList menuItemListData = mDataList.get(position);


        if(position==0){
            holder.tvFeesType.setText("Fees");
        }
        else {
            holder.tvFeesType.setText("Yearly Fees");
        }


        holder.tvFees.setText("$"+menuItemListData.getFees());
        holder.tvBotName.setText(menuItemListData.getBotName());
        if(menuItemListData.getStatus().equalsIgnoreCase("1"))
        {
            holder.tvBotStatus.setText("Active");
            holder.tvBotStatus.setBackgroundColor(Color.parseColor("#008800"));
            holder.tvBotStatus.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else{
            holder.tvBotStatus.setBackgroundColor(Color.parseColor("#FF7700"));
            holder.tvBotStatus.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvBotStatus.setText("Subscribe");
        }

        holder.tvLotPerTrade.setText(menuItemListData.getLotPerTrade());
        holder.tvProfitPer.setText(menuItemListData.getProfit()+"%");
        holder.tvMinDeposit.setText("$"+menuItemListData.getBotMinDeposit());
        if(Double.parseDouble(menuItemListData.getProfit())<0){
            holder.tvProfitPer.setTextColor(Color.parseColor("#FF0000"));
        }
        else holder.tvProfitPer.setTextColor(Color.parseColor("#00aa00"));


    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
    public interface OnItemClickListener {
        void onListItemClick(int position);
        void onUpdateQty(int position, String qty, double intrestrate, int selvalue, int mainDuration, boolean alert);
    }
    public interface OnDoubleClickListener {
        void onDetailsView(int position);
    }
    public static String stripNonDigits(
            final CharSequence input /* inspired by seh's comment */){
        final StringBuilder sb = new StringBuilder(input.length() /* also inspired by seh's comment */);
        for(int i = 0; i < input.length(); i++){
            final char c = input.charAt(i);
            if(c > 47 && c < 58){
                sb.append(c);
            }
        }
        return sb.toString();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        @BindView(R.id.tvLotPerTrade)
        TextView tvLotPerTrade;
        @BindView(R.id.tvBotName)
        TextView tvBotName;
        @BindView(R.id.tvBotStatus)
        TextView tvBotStatus;
        @BindView(R.id.tvFeesType)
        TextView tvFeesType;

        @BindView(R.id.tvMinDeposit)
        TextView tvMinDeposit;
        @BindView(R.id.tvFees)
        TextView tvFees;
        @BindView(R.id.tvProfitPer)
        TextView tvProfitPer;






        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onListItemClick(position);
        }
        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            listener2.onDetailsView(position);
            return false;
        }
    }


}
