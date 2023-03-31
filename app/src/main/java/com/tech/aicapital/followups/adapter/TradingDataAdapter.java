package com.tech.aicapital.followups.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tech.aicapital.R;
import com.tech.aicapital.followups.datalist.FollowupDataList;
import com.tech.aicapital.followups.datalist.TradingDataList;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TradingDataAdapter extends
        RecyclerView.Adapter<TradingDataAdapter.MyViewHolder> {
    Context context;
    private List<TradingDataList> mDataList;
    private TradingDataAdapter.OnItemClickListener listener;
    int lastPosition = -1;
    boolean isAdmin;
    int mainDuration,mainAmounnt=1;
    double intrestrate;
    public TradingDataAdapter(Context context, List<TradingDataList> resourceList,
                               TradingDataAdapter.OnItemClickListener listener) {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.isAdmin = isAdmin;

    }
    @Override
    public TradingDataAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_market_value, parent, false);
        return new TradingDataAdapter.MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final TradingDataAdapter.MyViewHolder holder, final int position)
    {

        final TradingDataList bean = mDataList.get(position);
        holder.tvSymbol.setText(bean.getSymbol()+" ("+bean.getName()+")");
        double price=Double.parseDouble(bean.getPriceUsd());
        holder.tvSymbolPrice.setText("$"+String.format("%.5f",price));
        double percent=Double.parseDouble(bean.getChangePercent24Hr());
        if(percent<0)
        {
            holder.tvPercentage.setTextColor(Color.parseColor("#FF0000"));
            holder.tvPercentage.setText(String.format("%.2f",percent)+"%");
        }
        else
        {
            holder.tvPercentage.setTextColor(Color.parseColor("#009900"));
            holder.tvPercentage.setText("+"+String.format("%.2f",percent)+"%");
        }

        double oldprice=0;
        if(bean.getVwap24Hr()==null)
        {
            oldprice=0;
        }
    else{
            oldprice=Double.parseDouble(bean.getVwap24Hr());
        }
        holder.tv24Hrsprice.setText("$"+String.format("%.5f",oldprice));


    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
    public interface OnItemClickListener {
        void onListItemClick(int position);
    }
    public static String stripNonDigits(final CharSequence input /* inspired by seh's comment */){
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
        @BindView(R.id.tvSymbol)
        TextView tvSymbol;
        @BindView(R.id.tvSymbolPrice)
        TextView tvSymbolPrice;

        @BindView(R.id.tv24Hrsprice)
        TextView tv24Hrsprice;
        @BindView(R.id.tvPercentage)
        TextView tvPercentage;



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
