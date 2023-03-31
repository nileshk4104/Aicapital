package com.tech.aicapital.followups.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.tech.aicapital.R;
import com.tech.aicapital.cart.datalist.PinDataList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyPaymentListAdapter extends
        RecyclerView.Adapter<MyPaymentListAdapter.MyViewHolder> {
    Context context;
    private List<PinDataList> mDataList;
    private OnItemClickListener listener;
    private OnDoubleClickListener listener2;
    int lastPosition = -1;
    boolean isAdmin;
    int mainDuration,mainAmounnt=1;
    String baseurl;
    public MyPaymentListAdapter(Context context, String baseurl, List<PinDataList> resourceList,
                                OnItemClickListener listener,
                                OnDoubleClickListener listener2) {
        this.context = context;
        this.mDataList = resourceList;
        this.listener = listener;
        this.baseurl = baseurl;
        this.listener2 = listener2;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_payment_deposit, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final PinDataList bean = mDataList.get(position);

        holder.tvPlanAmount.setText(context.getResources().getString(R.string.Rs)+bean.getAmount()+" ("+bean.getDateTime()+")");
        holder.tvUserName.setText(bean.getUserName());
        holder.tvPlanTitle.setText(bean.getPlanName());
        Picasso.with(context).load(bean.getScreenshot()).placeholder(R.drawable.defa).into(holder.ivProductImage);
//        GlideApp.with(context).load(bean.getScreenshot()).placeholder(R.drawable.defa).into(holder.ivProductImage);
        if(bean.getStatus().equalsIgnoreCase("2"))
        {
            holder.tvStatus.setText("Verified");
            holder.tvStatus.setTextColor(Color.parseColor("#0088ff"));
        }
        else if(bean.getStatus().equalsIgnoreCase("1"))
        {
            holder.tvStatus.setText("Pending");
            holder.tvStatus.setTextColor(Color.parseColor("#FF8800"));
        }
        else if(bean.getStatus().equalsIgnoreCase("3"))
        {
            holder.tvStatus.setText("Complete");
            holder.tvStatus.setTextColor(Color.parseColor("#008800"));
        }
        else if(bean.getStatus().equalsIgnoreCase("4"))
        {
            holder.tvStatus.setText("Rejected");
            holder.tvStatus.setTextColor(Color.parseColor("#FF0000"));
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
        @BindView(R.id.tvPlanTitle)
        TextView tvPlanTitle;
        @BindView(R.id.tvUserName)
        TextView tvUserName;
        @BindView(R.id.tvPlanAmount)
        TextView tvPlanAmount;
        @BindView(R.id.tvStatus)
        TextView tvStatus;
        @BindView(R.id.ivProductImage)
        ImageView ivProductImage;


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
