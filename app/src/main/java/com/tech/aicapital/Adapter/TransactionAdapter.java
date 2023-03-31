package com.tech.aicapital.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.tech.aicapital.R;
import com.tech.aicapital.datalist.TransactionDatalist;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends
        RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context context;
    private List<TransactionDatalist> mDataList;
    private OnItemClickListener listener;
    private OnDoubleClickListener listener2;
    int lastPosition = -1;
    boolean alert;
    int mainDuration,mainAmounnt=1;
    double intrestrate;
    public TransactionAdapter(Context context, List<TransactionDatalist> resourceList,
                              OnItemClickListener listener,
                              OnDoubleClickListener listener2) {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.listener2 = listener2;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_transactions, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        final TransactionDatalist bean = mDataList.get(position);

        holder.tvDate.setText(String.valueOf(bean.getDateTime()));

            holder.tvTransType.setText(bean.getType());

        if(bean.getCreditAmt().equalsIgnoreCase("0"))
        {
            holder.tvCreditDebit.setTextColor(Color.parseColor("#ff0000"));
            holder.tvCreditDebit.setText("- $"+String.valueOf(bean.getDebitAmt()));
        }
    else{
            holder.tvCreditDebit.setTextColor(Color.parseColor("#00aa00"));
            holder.tvCreditDebit.setText("+ $"+String.valueOf(bean.getCreditAmt()));
        }
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
        @BindView(R.id.tvReferId)
        TextView tvReferId;
        @BindView(R.id.tvDate)
        TextView tvDate;
        @BindView(R.id.tvAvail)
        TextView tvAvail;
        @BindView(R.id.tvCreditDebit)
        TextView tvCreditDebit;
        @BindView(R.id.tvTransType)
        TextView tvTransType;
        @BindView(R.id.tvPreBalance)
        TextView tvPreBalance;

        @BindView(R.id.tvBalanceType)
        TextView tvBalanceType;


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
