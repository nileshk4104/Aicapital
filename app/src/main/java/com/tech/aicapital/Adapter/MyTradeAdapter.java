package com.tech.aicapital.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.R;
import com.tech.aicapital.datalist.ArticleDataList;
import com.tech.aicapital.datalist.MyTradeDataList;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyTradeAdapter extends
        RecyclerView.Adapter<MyTradeAdapter.MyViewHolder> {
    Context context;
    private List<MyTradeDataList> mDataList;
    private MyTradeAdapter.OnItemClickListener listener;
    int lastPosition = -1;
    String baseurl;
    int mainDuration,mainAmounnt=1;
    boolean isImage;
    public MyTradeAdapter(Context context, String baseurl, List<MyTradeDataList> resourceList,
                          MyTradeAdapter.OnItemClickListener listener)
    {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.baseurl = baseurl;this.isImage = isImage;
    }
    @Override
    public MyTradeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_trade_list, parent, false);
        return new MyTradeAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyTradeAdapter.MyViewHolder holder, final int position)
    {
        final MyTradeDataList menuItemListData = mDataList.get(position);


        if(menuItemListData.getTypeId().equalsIgnoreCase("0")){
            holder.tvLotSize.setText("sell "+menuItemListData.getLotSize());
            holder.tvLotSize.setTextColor(Color.parseColor("#ff0000"));
        }else{
            holder.tvLotSize.setText("buy "+menuItemListData.getLotSize());
            holder.tvLotSize.setTextColor(Color.parseColor("#0088ff"));
        }




        holder.tvEntity.setText(menuItemListData.getEntityName());
        holder.tvDateTime.setText(menuItemListData.getEndTime());

        holder.tvOpenTrade.setText(menuItemListData.getOpenPos()+" -> ");
        holder.tvCloseTrade.setText(menuItemListData.getClosePos());

        if(Double.parseDouble(menuItemListData.getPlgain())<0){
            holder.tvTradeGain.setTextColor(Color.parseColor("#FF0000"));
        }
        else
        {
            holder.tvTradeGain.setTextColor(Color.parseColor("#0088FF"));

        }
        holder.tvTradeGain.setText(menuItemListData.getPlgain());
    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
    public interface OnItemClickListener {
        void onListItemClick(int position);
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
            implements View.OnClickListener {

        @BindView(R.id.tvEntity)
        TextView tvEntity;
        @BindView(R.id.tvLotSize)
        TextView tvLotSize;
        @BindView(R.id.tvDateTime)
        TextView tvDateTime;

        @BindView(R.id.tvOpenTrade)
        TextView tvOpenTrade;
        @BindView(R.id.tvCloseTrade)
        TextView tvCloseTrade;
        @BindView(R.id.tvTradeGain)
        TextView tvTradeGain;




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

    }


}
